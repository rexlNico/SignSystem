package de.rexlnico.signsystem.command;

import de.rexlnico.signsystem.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Reload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Main.getPermissionManager().getReloadConfigs())) {
                try {
                    Main.getMessageManager().update();
                } catch (IOException e) {
                    Bukkit.getConsoleSender().sendMessage(Main.getMessageManager().ErrorMessage("§6"+e.getMessage()));
                    player.sendMessage(Main.getMessageManager().ErrorMessage("See the full error in the console!"));
                }
                try {
                    Main.getPermissionManager().update();
                } catch (IOException e) {
                    Bukkit.getConsoleSender().sendMessage(Main.getMessageManager().ErrorMessage("§6"+e.getMessage()));
                    player.sendMessage(Main.getMessageManager().ErrorMessage("See the full error in the console!"));
                }
                try {
                    Main.getSignLayoutManager().update();
                } catch (IOException e) {
                    Bukkit.getConsoleSender().sendMessage(Main.getMessageManager().ErrorMessage("§6"+e.getMessage()));
                    player.sendMessage(Main.getMessageManager().ErrorMessage("See the full error in the console!"));
                }
                player.sendMessage(Main.getMessageManager().ReloadConfigs());
            }
        }
        return false;
    }
}
