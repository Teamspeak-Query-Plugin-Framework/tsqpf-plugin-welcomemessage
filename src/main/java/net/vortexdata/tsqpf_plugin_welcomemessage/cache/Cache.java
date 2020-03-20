package net.vortexdata.tsqpf_plugin_welcomemessage.cache;

import com.github.theholywaffle.teamspeak3.*;
import net.vortexdata.tsqpf.plugins.*;
import net.vortexdata.tsqpf_plugin_welcomemessage.cache.values.*;

import java.util.*;

public class Cache {

    private ArrayList<CachedValue> cachedValues;
    private TS3Api api;
    private PluginConfig config;
    private PluginLogger logger;

    public Cache(TS3Api api, PluginConfig config, PluginLogger logger) {
        this.config = config;
        this.api = api;
        cachedValues = new ArrayList<>();
        this.logger = logger;

        // Add default values
        cachedValues.add(new CVBannedClientCount(api));
        cachedValues.add(new CVChannelCount(api));
        cachedValues.add(new CVChannelGroupCount(api));
        cachedValues.add(new CVLastIssueBanDate(api));
        cachedValues.add(new CVOnlineStaffCount(api, config));
        CVOnlineUsers ou = new CVOnlineUsers(api);
        cachedValues.add(ou);
        cachedValues.add(new CVOnlineOtherUsers(api, ou));
        cachedValues.add(new CVServerGroupCount(api));
        cachedValues.add(new CVUniqueUsers(api));

    }

    public String getValue(String key) {
        for (CachedValue c : cachedValues) {
            if (c.getKey().equalsIgnoreCase(key))
                return c.getValue();
        }
        return null;
    }

    public void updateAllValues() {
        logger.printDebug("Updating all cached values...");
        for (CachedValue c : cachedValues)
            c.update();
        logger.printDebug("All cached values updated.");
    }

}
