package de.silentesc.simpletpa.utils;

import de.silentesc.simpletpa.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileConfig extends YamlConfiguration {
    /*
     * A class for saving files
     * Used to create and save files easily
     */

    private final String path;

    public FileConfig(String fileName) {
        this.path = Main.getInstance().getDataFolder().getPath() + "/" + fileName;
        File file = new File(this.path);
        if (!file.exists())
            this.saveConfig();
        try {
            load(this.path);
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            save(this.path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
