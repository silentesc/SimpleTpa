package de.silentesc.tpa.commands;

import de.silentesc.tpa.Main;
import de.silentesc.tpa.Manager;
import de.silentesc.tpa.classes.Tpa;
import de.silentesc.tpa.classes.TpaMode;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TpaCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        // Manager
        Manager manager = Main.getInstance().getManager();

        // Return if sender is not a player
        if (!(commandSender instanceof Player)) {
            manager.getShortMessages().notAPlayer(commandSender);
            return true;
        }

        Player player = (Player) commandSender;
        String[] usages = {"/tpa [player]"};

        // Return if args length is not 1
        if (strings.length != 1) {
            manager.getShortMessages().wrongUsage(player, usages);
            return true;
        }

        // Get target and return if not exists
        Player target = Bukkit.getPlayerExact(strings[0]);
        if (target == null) {
            manager.getShortMessages().playerDoesNotExist(player);
            return true;
        }

        // Create tpa
        new Tpa(player, target, TpaMode.TPA);

        // Send messages
        manager.getShortMessages().sendSuccessMessage(player, String.format("A tpa request has been sent to§e %s", target.getDisplayName()));
        manager.getShortMessages().sendSuccessMessage(target, String.format("You have received a tpa request from§e %s", player.getDisplayName()));

        // Send clickable messages
        ComponentBuilder tpacceptMessage = new ComponentBuilder("Accept")
                .color(ChatColor.GREEN)
                .bold(true)
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, String.format("/tpaccept %s", player.getDisplayName())));
        ComponentBuilder tpdenyMessage = new ComponentBuilder("Deny")
                .color(ChatColor.RED)
                .bold(true)
                .event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, String.format("/tpdeny %s", player.getDisplayName())));
        target.spigot().sendMessage(tpacceptMessage.create());
        target.spigot().sendMessage(tpdenyMessage.create());

        return true;
    }
}
