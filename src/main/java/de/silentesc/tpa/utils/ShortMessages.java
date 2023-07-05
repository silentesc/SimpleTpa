package de.silentesc.tpa.utils;

import de.silentesc.tpa.Main;
import de.silentesc.tpa.Manager;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShortMessages {
    Manager manager = Main.getInstance().getManager();
    public void notAPlayer(CommandSender sender) {
        sender.sendMessage(manager.getPrefix() + "Only players can execute this command.");
    }
    public void sendSuccessMessage(Player player, String message) {
        player.sendMessage(manager.getPrefix() + message);
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
    }
    public void sendFailMessage(Player player, String message) {
        player.sendMessage(manager.getPrefix() + "§c" + message);
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
    }
    public void wrongUsage(Player player, String[] usages) {
        sendFailMessage(player, "§cWrong usage\n§fUsage:");
        player.sendMessage(usages);
    }
    public void playerDoesNotExist(Player player) {
        sendFailMessage(player, "This player does not exist or is not currently online.");
    }
}
