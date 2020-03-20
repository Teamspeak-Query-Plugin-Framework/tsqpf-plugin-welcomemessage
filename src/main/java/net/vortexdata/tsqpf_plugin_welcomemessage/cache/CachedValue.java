package net.vortexdata.tsqpf_plugin_welcomemessage.cache;

import com.github.theholywaffle.teamspeak3.*;

public abstract class CachedValue {

    protected String key;
    protected String value;
    protected TS3Api api;

    public CachedValue(String key, String defaultValue, TS3Api api) {
        this.key = key;
        this.value = defaultValue;
        this.api = api;
    }

    public abstract void update();

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
