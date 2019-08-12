import com.github.theholywaffle.teamspeak3.api.event.*;
import net.vortexdata.tsqpf.modules.*;

public class PluginMain extends PluginInterface {

    private ConfigManager configManager;
    private PlaceholderEvaluator placeholderEvaluator;

    @Override
    public String getName() {
        return "WelcomeMessage";
    }

    @Override
    public void onEnable() {
        configManager = new ConfigManager(getConfig());
        placeholderEvaluator = new PlaceholderEvaluator(getAPI());
        configManager.load();
        getLogger().printInfo("WelcomeMessage loaded.");
    }

    @Override
    public void onDisable() {
        getLogger().printInfo("WelcomeMessage unloaded.");
    }

    @Override
    public void onClientJoin(ClientJoinEvent clientJoinEvent) {
        getAPI().sendPrivateMessage(clientJoinEvent.getClientId(), placeholderEvaluator.process(configManager.getValue("messageWelcome")));
    }
}