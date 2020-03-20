package net.vortexdata.tsqpf_plugin_welcomemessage;

import com.github.theholywaffle.teamspeak3.*;
import com.github.theholywaffle.teamspeak3.api.event.*;
import com.github.theholywaffle.teamspeak3.api.wrapper.*;
import net.vortexdata.tsqpf.plugins.*;
import net.vortexdata.tsqpf_plugin_welcomemessage.cache.*;

import java.time.*;
import java.time.format.*;
import java.util.*;

public class MessageProcessor {

    private TS3Api api;
    private PluginConfig config;
    private Cache cache;

    public MessageProcessor(TS3Api api, PluginConfig config, Cache cache) {
        this.api = api;
        this.config = config;
        this.cache = cache;
    }

    public String process(String message, ClientJoinEvent cje) {
        StringBuilder sb = new StringBuilder();

        String[] parts = message.split("%");
        for (String part : parts) {

            if (part.equalsIgnoreCase("clientNickname")) {
                sb.append(cje.getClientNickname());
            } else {
                String value = cache.getValue(part);
                if (value != null)
                    sb.append(value);
                else
                    sb.append(part);
            }

        }

        return sb.toString();
    }

}
