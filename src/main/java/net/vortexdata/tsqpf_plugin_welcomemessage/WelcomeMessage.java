package net.vortexdata.tsqpf_plugin_welcomemessage;

import com.github.theholywaffle.teamspeak3.api.event.*;
import net.vortexdata.tsqpf.plugins.*;
import net.vortexdata.tsqpf_plugin_welcomemessage.cache.*;
import org.apache.logging.log4j.core.util.*;

import java.util.concurrent.*;

public class WelcomeMessage extends TeamspeakPlugin {

    private MessageProcessor messageProcessor;
    private Cache cache;
    private boolean isFirstJoin = true;
    private ThreadPoolExecutor tpe;
    private ClientRegister clientRegister;

    @Override
    public void onEnable() {
        tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        clientRegister = new ClientRegister(getLogger());
        getConfig().setDefault("messageWelcome", "Welcome %clientNickname% to this Teamspeak server! There are currently %onlineOtherUsers% other users online.");
        getConfig().setDefault("messageWelcomePoke", "");
        getConfig().setDefault("messageFirstJoin", "");
        getConfig().setDefault("messageFirstJoinPoke", "Welcome to this server!");
        getConfig().setDefault("staffGroups", "1");
        getConfig().saveAll();
        cache = new Cache(getAPI(), getConfig(), getLogger());
        messageProcessor = new MessageProcessor(getAPI(), getConfig(), cache);
        clientRegister.init();
    }

    @Override
    public void onDisable() {
        messageProcessor = null;
    }

    @Override
    public void onClientJoin(ClientJoinEvent clientJoinEvent) {
        if (getAPI().getClientInfo(clientJoinEvent.getClientId()).isRegularClient())  {
            if (isFirstJoin) {
                cache.updateAllValues();
                isFirstJoin = false;
            }
            try {
                getLogger().printDebug("Trying to send welcome message to " + clientJoinEvent.getUniqueClientIdentifier() + ".");

                String pokeMsg = getConfig().readValue("messageWelcomePoke");
                if (!pokeMsg.isEmpty()) {
                    getAPI().pokeClient(clientJoinEvent.getClientId(), messageProcessor.process(pokeMsg, clientJoinEvent));
                }

                getAPI().sendPrivateMessage(clientJoinEvent.getClientId(), messageProcessor.process(getConfig().readValue("messageWelcome"), clientJoinEvent));
                // Check if client is joining the server for the first time
                if (clientRegister.isReady() && !clientRegister.isClientRegistered(clientJoinEvent.getUniqueClientIdentifier())) {
                    String firstMsgPoke = getConfig().readValue("messageFirstJoinPoke");
                    String firstMsg = getConfig().readValue("messageFirstJoin");
                    if (!firstMsgPoke.isEmpty()) {
                        getAPI().pokeClient(clientJoinEvent.getClientId(), messageProcessor.process(firstMsgPoke, clientJoinEvent));
                    }
                    if (!firstMsg.isEmpty()) {
                        getAPI().sendPrivateMessage(clientJoinEvent.getClientId(), messageProcessor.process(firstMsg, clientJoinEvent));
                    }
                    clientRegister.addClientIfNotExisting(clientJoinEvent.getUniqueClientIdentifier());
                } else {
                     if (!clientRegister.isReady()) {
                         getLogger().printWarn("Can't check if client is new as client register has crashed.");
                     }
                }
            } catch (Exception e) {
                getLogger().printDebug("Failed to send private message to " + clientJoinEvent.getUniqueClientIdentifier() + ". This user probably disconnected before the private message could be sent. Debug: " + e.getMessage());
            }
            tpe.submit(new UpdateTask(cache));
        } else {
            getLogger().printDebug("Client " + clientJoinEvent.getUniqueClientIdentifier() + " is not a regular client.");
        }
    }
    @Override
    public void onClientLeave(ClientLeaveEvent clientLeaveEvent) {
        tpe.submit(new UpdateTask(cache));
    }
}