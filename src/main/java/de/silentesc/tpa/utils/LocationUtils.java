package de.silentesc.tpa.utils;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class LocationUtils {
    /*
     * A class for location stuff
     * For example teleporting players and playing a sound after
     */

    // Teleport player to location and play sound for all player in a specific radius
    public void teleportPlayer(Player player, Location location) {
        player.teleport(location);
        player.playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
    }
}
