package net.vortexdata.tsqpf_plugin_welcomemessage;

import com.github.theholywaffle.teamspeak3.api.event.*;
import net.vortexdata.tsqpf.plugins.*;

public class WelcomeMessage extends TeamspeakPlugin {

    private MessageProcessor messageProcessor;

    @Override
    public void onEnable() {
        getConfig().setDefault("messageWelcome", "Welcome %clientNickname% to this Teamspeak server! There are currently %onlineOtherUsers% other users online. ");
        getConfig().setDefault("staffGroups", "1");
        getConfig().saveAll();

        messageProcessor = new MessageProcessor(getAPI(), getConfig());
    }

    @Override
    public void onDisable() {
        messageProcessor = null;
    }

    @Override
    public void onClientJoin(ClientJoinEvent clientJoinEvent) {
        try {
            getAPI().sendPrivateMessage(clientJoinEvent.getClientId(), messageProcessor.process(getConfig().readValue("messageWelcome"), clientJoinEvent));
        } catch (Exception e) {
            getLogger().printDebug("Failed to send private message to " + clientJoinEvent.getInvokerName() + ". This user probably disconnected before the private message could be sent.");
        }

    }
}