package net.vortexdata.tsqpf_plugin_welcomemessage;

import net.vortexdata.tsqpf.plugins.PluginLogger;
import org.json.simple.JSONArray;

import java.io.*;
import java.util.ArrayList;

/**
 * @author  Sandro Kierner
 * @since   1.1.0
 */
public class ClientRegister {

    private ArrayList<Client> clients;
    private static final String SAVE_FILE_LOCATION = "plugins//WelcomeMessage//clientregister.data";
    private PluginLogger logger;
    private boolean isReady;

    public ClientRegister(PluginLogger logger) {
        this.logger = logger;
        clients = new ArrayList<>();
        isReady = false;
    }

    /**
     * Inits the client register by loading save file
     * @return Status, true if register is ready to be used.
     */
    public boolean init() {
        load();
        return isReady;
    }

    /**
     * Clears in-memory client array and replaces it with
     * client objects from save file.
     */
    public void load() {
        logger.printDebug("Loading clients from save file...");
        clients.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader(SAVE_FILE_LOCATION));
            while (br.ready()) {
                String line = br.readLine();
                // Should check for validity of identifier
                clients.add(new Client(line));
            }
            logger.printDebug("Clients from save file loaded and added.");
            isReady = true;
        } catch (FileNotFoundException e) {
            logger.printWarn("Client register save file could not be found, trying to create new one...");
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(SAVE_FILE_LOCATION));
                bw.flush();
                bw.close();
                isReady = true;
                logger.printInfo("Save file created.");
            } catch (IOException ioException) {
                logger.printError("Failed to create save file, disabling client register.");
                isReady = false;
            }
        } catch (IOException e) {
            logger.printError("Failed to read from save file, appending error message: " + e.getMessage());
        }
    }

    /**
     * Overrides client save file with clients
     */
    public void save() {
        logger.printDebug("Trying to save in-memory client register to save file...");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(SAVE_FILE_LOCATION, false));
            for (Client c : clients)
                bw.write(c.getClientUID() + "\n");
            bw.flush();
            bw.close();
            logger.printDebug("In-memory client register saved to file.");
        } catch (IOException e) {
            logger.printError("Failed to write to save file, disabling client register.");
            isReady = false;
        }
    }

    /**
     * Checks if a client is already in the client register.
     * @param clientUID
     * @return true if client is in register.
     */
    public boolean isClientRegistered(String clientUID) {
        return (getClientByUID(clientUID) != null);
    }

    /**
     * Returns the client with specified client UID. Returns null if they don't exists.
     * @param clientUID
     * @return Specified client.
     */
    public Client getClientByUID(String clientUID) {
        for (Client c : clients) {
            if (c.getClientUID().equals(clientUID))
                return c;
        }
        return null;
    }

    public boolean isReady() {
        return isReady;
    }

    public void addClientIfNotExisting(String clientUID) {
        if (!isClientRegistered(clientUID)) {
            logger.printDebug("Adding " + clientUID + " to register.");
            clients.add(new Client(clientUID));
            save();
        } else {
            logger.printDebug("Not adding " + clientUID + " as they are already registed.");
        }
    }

}
