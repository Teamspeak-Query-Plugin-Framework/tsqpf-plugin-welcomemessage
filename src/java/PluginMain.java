import com.github.theholywaffle.teamspeak3.api.event.*;
import net.vortexdata.tsqpf.modules.*;
import sun.plugin2.message.*;

public class PluginMain extends PluginInterface {

    Config config = new Config(getConfig());
    MessageProcessor messageProcessor = new MessageProcessor(getAPI());

    @Override
    public String getName() {
        return "WelcomeMessage";
    }

    @Override
    public void onEnable() {
        getLogger().printInfo("WelcomeMessage loaded.");
    }

    @Override
    public void onDisable() {
        getLogger().printInfo("WelcomeMessage unloaded.");
    }

    @Override
    public void onClientJoin(ClientJoinEvent clientJoinEvent) {
        getAPI().sendPrivateMessage(clientJoinEvent.getClientId(), messageProcessor.process(getConfig().readValue("messageWelcome")));
    }
}