import com.github.theholywaffle.teamspeak3.api.event.*;
import net.vortexdata.tsqpf.modules.*;
import sun.plugin2.message.*;

public class PluginMain extends PluginInterface {

    Config config;
    MessageProcessor messageProcessor;

    @Override
    public String getName() {
        return "WelcomeMessage";
    }

    @Override
    public void onEnable() {
        messageProcessor = new MessageProcessor(getAPI());
        config = new Config(getConfig());
        getLogger().printInfo("WelcomeMessage loaded.");
    }

    @Override
    public void onDisable() {
        getLogger().printInfo("WelcomeMessage unloaded.");
    }

    @Override
    public void onClientJoin(ClientJoinEvent clientJoinEvent) {
        String clientNickname = clientJoinEvent.getClientNickname();

        try {
            getAPI().sendPrivateMessage(clientJoinEvent.getClientId(), messageProcessor.process(getConfig().readValue("messageWelcome")));
        } catch (Exception e) {
            getLogger().printDebug("Failed to send private message to " + clientNickname + ". This user probably disconnected before the private message could be sent.");
        }

    }
}