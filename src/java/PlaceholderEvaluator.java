import com.github.theholywaffle.teamspeak3.*;

public class PlaceholderEvaluator {

    private TS3Api api;

    public PlaceholderEvaluator(TS3Api api) {
        this.api = api;
    }

    public String process(String message) {
        String exportValue = "";

        String[] splittedMessage = message.split("%");

        for (int i = 0; i < splittedMessage.length; i++) {

            if (splittedMessage[i].equals("onlineClients")) {
                exportValue += api.getServerInfo().getClientsOnline() - 1;
            } else {
                exportValue += splittedMessage[i];
            }

        }

        return exportValue;
    }

}
