package de.silentesc.simpletpa.utils;

import de.silentesc.simpletpa.Main;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShortMessages {
    public void notAPlayer(CommandSender sender) {
        sender.sendMessage(Main.getInstance().getManager().getPrefix() + "Only players can execute this command.");
    }
    public void sendSuccessMessage(Player player, String message) {
        player.sendMessage(Main.getInstance().getManager().getPrefix() + message);
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
    }
    public void sendFailMessage(Player player, String message) {
        player.sendMessage(Main.getInstance().getManager().getPrefix() + "§c" + message);
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
