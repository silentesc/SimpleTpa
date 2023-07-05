package de.silentesc.tpa.classes;

import de.silentesc.tpa.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Tpa {
    private static final List<Tpa> Tpas = new ArrayList<>();

    private final Player teleportingPlayer;
    private final Player targetPlayer;

    // Called when a tpa has been started
    public Tpa(Player teleportingPlayer, Player targetPlayer) {
        this.teleportingPlayer = teleportingPlayer;
        this.targetPlayer = targetPlayer;

        // Add tpa to list
        getTpas().add(this);

        // Expire tpa after time is up
        Bukkit.getScheduler().runTaskLater(
                Main.getInstance(), this::tpaExpired,
                Main.getInstance().getManager().getConfigUtils().getKeepAliveSeconds() * 20L
        );
    }

    // Check if there is a tpa between 2 players
    // Returns null if not exists
    @Nullable
    public static Tpa getTpa(final Player teleportingPlayer, final Player targetPlayer) {
        // Loop through tpas
        for (Tpa tpa : getTpas()) {
            if (tpa.teleportingPlayer.getUniqueId() == teleportingPlayer.getUniqueId() &&
                    tpa.targetPlayer.getUniqueId() == targetPlayer.getUniqueId()) {
                return tpa;
            }
        }
        return null;
    }

    // Called to teleport, teleporting ends a tpa
    public void performTeleport() {
        // This "deletes" the tpa
        getTpas().remove(this);

        // Assign variables and check TpaMode
        final int preTeleportSeconds = Main.getInstance().getManager().getConfigUtils().getPreTeleportSeconds();
        final int invincibilitySeconds = Main.getInstance().getManager().getConfigUtils().getInvincibilitySeconds();

        // Check if players are still online
        if (!teleportingPlayer.isOnline() || !targetPlayer.isOnline()) {
            getTpas().remove(this);
            return;
        }

        // Wait until the preTeleportSeconds timer finished
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            // Check if players are still online
            if (!teleportingPlayer.isOnline() || !targetPlayer.isOnline())
                return;

            // Teleport player via LocationUtils to also play sound for all near players
            Main.getInstance().getManager().getLocationUtils().teleportPlayer(teleportingPlayer, targetPlayer.getLocation());

            // Make player temporarily invincible
            Main.getInstance().getManager().getLists().getInvinciblePlayers().add(teleportingPlayer.getUniqueId());
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                // Remove player from list
                Main.getInstance().getManager().getLists().getInvinciblePlayers().remove(teleportingPlayer.getUniqueId());
            }, invincibilitySeconds * 20L);
        }, preTeleportSeconds * 20L);

        // Send messages
        Main.getInstance().getManager().getShortMessages().sendSuccessMessage(teleportingPlayer,
                String.format("You will be teleported to§e %s§7 in§a %s§7 seconds", targetPlayer.getDisplayName(), preTeleportSeconds));
        Main.getInstance().getManager().getShortMessages().sendSuccessMessage(targetPlayer,
                String.format("§e%s§7 will be teleported to you in§a %s§7 seconds", teleportingPlayer.getDisplayName(), preTeleportSeconds));
    }

    // Called when the tpa expires
    private void tpaExpired() {
        // Check if tpa still exists after the keep-alive time
        if (!getTpas().contains(this)) return;

        // "Delete" tpa
        getTpas().remove(this);

        // Check if players are still online
        if (!teleportingPlayer.isOnline() || !targetPlayer.isOnline()) {
            getTpas().remove(this);
            return;
        }

        // Send messages
        Main.getInstance().getManager().getShortMessages().sendFailMessage(teleportingPlayer,
                String.format("Your request to§e %s§c has been expired", targetPlayer.getDisplayName()));
        Main.getInstance().getManager().getShortMessages().sendFailMessage(targetPlayer,
                String.format("§e%s§c's request has been expired", teleportingPlayer.getDisplayName()));
    }

    // Getter
    public static List<Tpa> getTpas() {
        return Tpas;
    }
    public Player getTeleportingPlayer() {
        return teleportingPlayer;
    }
    public Player getTargetPlayer() {
        return targetPlayer;
    }
}
