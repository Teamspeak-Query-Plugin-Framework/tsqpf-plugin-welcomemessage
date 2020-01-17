package net.vortexdata.tsqpf_plugin_welcomemessage;

import com.github.theholywaffle.teamspeak3.*;
import com.github.theholywaffle.teamspeak3.api.event.*;
import com.github.theholywaffle.teamspeak3.api.wrapper.*;
import net.vortexdata.tsqpf.plugins.*;

import java.time.*;
import java.time.format.*;
import java.util.*;

public class MessageProcessor {

    private TS3Api api;
    private PluginConfig config;

    public MessageProcessor(TS3Api api, PluginConfig config) {
        this.api = api;
        this.config = config;
    }

    public String process(String message, ClientJoinEvent cje) {
        String exportValue = "";

        String[] parts = message.split("%");
        for (String part : parts) {
            // Replace placeholders
            if (part.equals("onlineUsers")) {
                exportValue += api.getServerInfo().getClientsOnline() - 1;
            } else if (part.equals("onlineOtherUsers")) {
                exportValue += api.getServerInfo().getClientsOnline() - 2;
            } else if (part.equals("totalUniqueUsers")) {
                exportValue += api.getDatabaseClients().size();
            } else if (part.equals("servergroupCount")) {
                exportValue += api.getServerGroups().size();
            } else if (part.equals("channelCount")) {
                exportValue += api.getChannels().size();
            } else if (part.equals("channelgroupCount")) {
                exportValue += api.getChannelGroups().size();
            } else if (part.equals("onlineStaffCount")) {

                ArrayList<Integer> staffGroups = new ArrayList<>();
                Arrays.stream(config.readValue("staffGroups").split(",")).forEach(x -> {
                    staffGroups.add(Integer.parseInt(x));
                });

                ArrayList<ServerGroupClient> staffMembers = new ArrayList<>();

                for (int c : staffGroups) {
                    staffMembers.addAll(api.getServerGroupClients(c));
                }

                ArrayList<Integer> onlineStaffIds = new ArrayList<>();

                for (ServerGroupClient c : staffMembers) {
                    try {
                        onlineStaffIds.add(api.getClientByUId(c.getUniqueIdentifier()).getId());
                    } catch (Exception e) {
                        // Skip, as this staff member is not online
                    }
                }


                exportValue += onlineStaffIds.size();
            } else if (part.equals("bannedPlayersCount")) {
                exportValue += api.getBans().size();
            } else if (part.equals("lastIssuedBanDate")) {

                ArrayList<Ban> bans = new ArrayList<>();
                if (bans.size() > 0) {
                    Ban mostRecentBan = bans.get(0);

                    for (Ban c : bans) {

                        if (c.getCreatedDate().before(mostRecentBan.getCreatedDate())) {
                            mostRecentBan = c;
                        }

                    }
                    LocalDate date = Instant.ofEpochMilli(mostRecentBan.getCreatedDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
                    exportValue += formatter.format(date);

                    bans = null;
                } else {
                    exportValue += 0;
                }


            } else if (part.equals("clientNickname")) {
                exportValue += cje.getClientNickname();
            }

            else {
                exportValue += part;
            }
        }

        return exportValue;
    }

}
