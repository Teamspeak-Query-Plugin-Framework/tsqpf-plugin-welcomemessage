import com.github.theholywaffle.teamspeak3.*;

public class MessageProcessor {

    private TS3Api api;

    public MessageProcessor(TS3Api api) {
        this.api = api;
    }

    public String process(String message) {
        String exportValue = "";

        String[] parts = message.split("%");
        for (String part : parts) {
            // Replace placeholders
            if (part.equals("onlineUsers")) {
                exportValue += api.getServerInfo().getClientsOnline() - 1;
            }

            else {
                exportValue += part;
            }
        }

        return exportValue;
    }

}
