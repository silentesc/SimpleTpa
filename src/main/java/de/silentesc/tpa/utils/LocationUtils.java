package de.silentesc.tpa.utils;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class LocationUtils {
    /*
     * A class for location stuff
     * For example teleporting players and playing a sound after
     */

    // Teleport player to location and play sound for all player in a specific radius
    public void teleportPlayer(Player player, Location location) {
        // Teleport player
        player.teleport(location);
        // Play sound for all players around
        List<Entity> nearByPlayers = player.getNearbyEntities(20d, 20d, 20d).stream().filter(e -> (e instanceof Player)).collect(Collectors.toList());
        for (Entity entity : nearByPlayers) {
            Player p = (Player) entity;
            p.playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f);
        }
    }
}
