package de.silentesc.tpa.utils;

import de.silentesc.tpa.Main;

public class ConfigUtils {
    /*
     * A class to get the values in the config.yaml
     */

    private FileConfig config;
    private final JavaUtils javaUtils;

    public ConfigUtils() {
        config = new FileConfig("config.yaml");
        javaUtils = Main.getInstance().getInstanceManager().getJavaUtils();
    }

    public void reloadConfig() {
        config = new FileConfig("config.yaml");
    }

    // Get pre-teleport-seconds from config.yaml
    public int getPreTeleportSeconds() {
        final int minValue = 0;
        final int maxValue = 10;
        final int defaultValue = 3;

        Object preTeleportSecondsObj = config.get("pre-teleport-seconds");
        if (preTeleportSecondsObj == null) return defaultValue;
        return javaUtils.getConfigIntFromStr(preTeleportSecondsObj.toString(), minValue, maxValue, defaultValue);
    }

    // Get invincibility-seconds from config.yaml
    public int getInvincibilitySeconds() {
        final int minValue = 0;
        final int maxValue = 10;
        final int defaultValue = 5;

        Object invincibilitySecondsObj = config.get("invincibility-seconds");
        if (invincibilitySecondsObj == null) return defaultValue;
        return javaUtils.getConfigIntFromStr(invincibilitySecondsObj.toString(), minValue, maxValue, defaultValue);
    }

    // Get keep-alive-seconds from config.yaml
    public int getKeepAliveSeconds() {
        final int minValue = 10;
        final int maxValue = 300;
        final int defaultValue = 60;

        Object keepAliveSecondsObj = config.get("keep-alive-seconds");
        if (keepAliveSecondsObj == null) return defaultValue;
        return javaUtils.getConfigIntFromStr(keepAliveSecondsObj.toString(), minValue, maxValue, defaultValue);
    }
}
