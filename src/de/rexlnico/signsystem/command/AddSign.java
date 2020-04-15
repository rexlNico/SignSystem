package de.rexlnico.signsystem.command;

import de.rexlnico.signsystem.main.Main;
import de.rexlnico.signsystem.server.Server;
import de.rexlnico.signsystem.server.ServerType;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class AddSign implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Main.getPermissionManager().getAddDynamicServer())) {
                if (args.length == 1) {
                    String group = args[0];
                    Location location = player.getTargetBlock((HashSet<Byte>) null, 10).getLocation();
                    if (location.getBlock().getState() instanceof org.bukkit.block.Sign) {
                        if (Main.getInstance().getSignManager().getSign(location) == null) {
                            Main.getInstance().getSignManager().addSign(location, ServerType.DYNAMIC, null, group);
                            player.sendMessage(Main.getMessageManager().AddSignDynamic(group));
                            return false;
                        }
                        player.sendMessage(Main.getMessageManager().ErrorMessage("Sign already registered!"));
                        return false;
                    }
                    player.sendMessage(Main.getMessageManager().ErrorMessage("No sign"));
                } else {
                    player.sendMessage(Main.getMessageManager().ErrorMessage("/addServer <ServerGroup>"));
                }
            }
        }
        return false;
    }
}
