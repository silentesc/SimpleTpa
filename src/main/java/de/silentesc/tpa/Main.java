package de.silentesc.tpa;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;
    private Manager instanceManager;

    public Main() {
        instance = this;
    }

    @Override
    public void onEnable() {
        // Constructor calls loadConfig(), initialize() and register()
        instanceManager = new Manager();
    }

    // Getter
    public static Main getInstance() {
        return instance;
    }
    public Manager getInstanceManager() {
        return instanceManager;
    }
}
