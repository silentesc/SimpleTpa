package de.silentesc.tpa.listeners;

import de.silentesc.tpa.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        cancelGodPlayers(event);
    }

    private void cancelGodPlayers(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player))
            return;
        Player player = (Player) event.getEntity();
        // Cancel event if player invincible
        if (Main.getInstance().getManager().getLists().getInvinciblePlayers().contains(player.getUniqueId())) {
            event.setCancelled(true);
        }
    }
}
