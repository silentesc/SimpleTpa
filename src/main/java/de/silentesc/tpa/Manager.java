package de.silentesc.tpa;

import de.silentesc.tpa.commands.TpaCommand;
import de.silentesc.tpa.commands.TpacceptCommand;
import de.silentesc.tpa.utils.ConfigUtils;
import de.silentesc.tpa.utils.JavaUtils;
import de.silentesc.tpa.utils.LocationUtils;
import de.silentesc.tpa.utils.ShortMessages;
import org.bukkit.Bukkit;

import java.io.File;
import java.util.Objects;

public class Manager {
    /*
     * Manager for instances, register commands and load configs
     * Used for example to init everything at the plugin start like prefix, utils and so on
     * You can get all utils from this class
     */

    // Global variables
    private String prefix;
    // Util instances
    private ShortMessages shortMessages;
    private ConfigUtils configUtils;
    private JavaUtils javaUtils;
    private LocationUtils locationUtils;

    public Manager() {
        loadConfig();
        initialize();
        register();
    }

    // Create config.yaml if it doesn't exist
    private void loadConfig() {
        String configPath = "config.yaml";
        File file = new File(Main.getInstance().getDataFolder().getPath() + "/" + configPath);
        if (!file.exists()) Main.getInstance().saveResource(configPath, false);
    }

    // Init all classes like utils etc.
    private void initialize() {
        prefix = "§7[§eTPA§7] ";
        shortMessages = new ShortMessages();
        javaUtils = new JavaUtils();
        locationUtils = new LocationUtils();
        configUtils = new ConfigUtils(); // Init FileConfig: config.yaml
    }

    // Register Commands, TabCompleter and Listeners
    private void register() {
        // Commands
        Objects.requireNonNull(Bukkit.getPluginCommand("tpa")).setExecutor(new TpaCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("tpaccept")).setExecutor(new TpacceptCommand());
    }

    // Getter
    public String getPrefix() {
        return prefix;
    }
    public ShortMessages getShortMessages() {
        return shortMessages;
    }
    public ConfigUtils getConfigUtils() {
        return configUtils;
    }
    public JavaUtils getJavaUtils() {
        return javaUtils;
    }
    public LocationUtils getLocationUtils() {
        return locationUtils;
    }
}
