package de.silentesc.simpletpa.listeners;

import de.silentesc.simpletpa.Main;
import de.silentesc.simpletpa.classes.Tpa;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        removeTpas(player);
        removeInvincible(player);
    }

    private void removeTpas(Player player) {
        // Remove tpas from list if player is involved
        Tpa.getTpas().removeIf(
                tpa -> tpa.getTeleportingPlayer().getUniqueId() == player.getUniqueId() ||
                tpa.getTargetPlayer().getUniqueId() == player.getUniqueId()
        );
    }

    private void removeInvincible(Player player) {
        Main.getInstance().getManager().getLists().getInvinciblePlayers().remove(player.getUniqueId());
    }
}
