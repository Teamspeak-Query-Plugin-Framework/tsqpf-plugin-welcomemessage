package net.vortexdata.tsqpf_plugin_welcomemessage.cache.values;

import com.github.theholywaffle.teamspeak3.*;
import com.github.theholywaffle.teamspeak3.api.wrapper.*;
import net.vortexdata.tsqpf.plugins.*;
import net.vortexdata.tsqpf_plugin_welcomemessage.cache.*;

import java.util.*;

public class CVOnlineStaffCount extends CachedValue {

    private PluginConfig config;

    public CVOnlineStaffCount(TS3Api api, PluginConfig config) {
        super("onlineStaffCount", "0", api);
    }

    @Override
    public void update() {

        try {

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
            value = Integer.toString(onlineStaffIds.size());
        } catch (Exception e) {

        }
    }

}
