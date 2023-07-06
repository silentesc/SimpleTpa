package de.silentesc.simpletpa.commands;

import de.silentesc.simpletpa.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadConfigCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        // Check permission
        if (!commandSender.hasPermission("simpletpa.reload")) {
            Main.getInstance().getManager().getShortMessages().notPermitted(commandSender);
            return true;
        }

        // Reload Config
        Main.getInstance().getManager().getConfigUtils().reloadConfig();

        // Send message
        Main.getInstance().getManager().getShortMessages().sendMessageToSender(commandSender, "Config has been reloaded.");

        return true;
    }
}
