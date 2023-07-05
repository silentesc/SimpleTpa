package de.silentesc.tpa.utils;

import de.silentesc.tpa.Main;
import de.silentesc.tpa.Manager;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShortMessages {
    Manager instanceManager = Main.getInstance().getInstanceManager();
    public void notAPlayer(CommandSender sender) {
        sender.sendMessage(instanceManager.getPrefix() + "Only players can execute this command.");
    }
    public void notPermitted(Player player) {
        this.sendFailMessage(player, "You are not permitted to do this.");
    }
    public void notPermitted(CommandSender sender) {
        this.sendMessageToSender(sender, "You are not permitted to do this.");
    }
    public void sendSuccessMessage(Player player, String message) {
        player.sendMessage(instanceManager.getPrefix() + message);
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
    }
    public void sendFailMessage(Player player, String message) {
        player.sendMessage(instanceManager.getPrefix() + "§c" + message);
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
    }
    public void sendMessageWithoutSound(Player player, String message) {
        player.sendMessage(instanceManager.getPrefix() + message);
    }
    public void sendRawMessage(Player player, String message) {
        player.sendMessage(message);
    }
    public void wrongUsage(Player player, String[] usages) {
        this.sendFailMessage(player, "§cWrong usage\n§fUsage:");
        player.sendMessage(usages);
    }
    public void sendTrapWarnMessage(Player player) {
        this.sendFailMessage(player, "Anti Trap System Warning:");
        String warnMessage = "§cLocation is not safe. Trying to teleport in a safe radius...";
        this.sendRawMessage(player, warnMessage);
    }
    public void sendSafetpFailMessage(Player player, String type, String name) {
        this.sendFailMessage(player, "Anti Trap System Warning:");
        String warnMessage = "§fThere is no safe location nearby.\n" + "If you understand the risk and still want to teleport use:\n" + "/forcetp " + type + " " + name;
        this.sendRawMessage(player, warnMessage);
    }
    public void sendMessageToSender(CommandSender sender, String message) {
        sender.sendMessage(instanceManager.getPrefix() + message);
    }

    public void warpDoesNotExist(Player player) {
        this.sendFailMessage(player, "This warp does not exist.");
    }
    public void homeDoesNotExist(Player player) {
        this.sendFailMessage(player, "This home does not exist.");
    }

    public void warpAlreadyExists(Player player) {
        this.sendFailMessage(player, "This warp already exists.");
    }
    public void homeAlreadyExists(Player player) {
        this.sendFailMessage(player, "This home already exists.");
    }
}
