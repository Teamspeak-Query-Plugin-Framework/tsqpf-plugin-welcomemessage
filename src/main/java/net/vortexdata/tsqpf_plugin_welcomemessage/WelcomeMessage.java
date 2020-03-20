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

    @Override
    public void onEnable() {
        tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        getConfig().setDefault("messageWelcome", "Welcome %clientNickname% to this Teamspeak server! There are currently %onlineOtherUsers% other users online.");
        getConfig().setDefault("staffGroups", "1");
        getConfig().saveAll();

        cache = new Cache(getAPI(), getConfig(), getLogger());

        messageProcessor = new MessageProcessor(getAPI(), getConfig(), cache);
    }

    @Override
    public void onDisable() {
        messageProcessor = null;
    }

    @Override
    public void onClientJoin(ClientJoinEvent clientJoinEvent) {
        if (isFirstJoin) {
            cache.updateAllValues();
            isFirstJoin = false;
        }
        try {
            getAPI().sendPrivateMessage(clientJoinEvent.getClientId(), messageProcessor.process(getConfig().readValue("messageWelcome"), clientJoinEvent));
        } catch (Exception e) {
            getLogger().printDebug("Failed to send private message to " + clientJoinEvent.getInvokerName() + ". This user probably disconnected before the private message could be sent. Debug: " + e.getMessage());
        }
        tpe.submit(new UpdateTask(cache));
    }

    @Override
    public void onClientLeave(ClientLeaveEvent clientLeaveEvent) {
        tpe.submit(new UpdateTask(cache));
    }
}