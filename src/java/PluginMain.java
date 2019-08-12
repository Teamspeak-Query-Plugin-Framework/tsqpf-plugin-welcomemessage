import com.github.theholywaffle.teamspeak3.api.event.*;
import net.vortexdata.tsqpf.modules.*;

public class PluginMain extends PluginInterface {

    @Override
    public String getName() {
        return "WelcomeMessage";
    }

    @Override
    public void onEnable() {

        getConfig().setDefault("messageWelcome", "Welcome to TS3!");
        getConfig().saveAll();

        getLogger().printInfo("WelcomeMessage loaded.");
        getLogger().printDebug("Welcome message: " + getConfig().readValue("messageWelcome"));
    }

    @Override
    public void onDisable() {
        getLogger().printInfo("WelcomeMessage unloaded.");
    }

    @Override
    public void onClientJoin(ClientJoinEvent clientJoinEvent) {
        getAPI().sendPrivateMessage(clientJoinEvent.getClientId(), getConfig().readValue("messageWelcome"));
    }
}