package de.rexlnico.signsystem.command;

import de.rexlnico.signsystem.main.Main;
import de.rexlnico.signsystem.server.Server;
import de.rexlnico.signsystem.server.ServerType;
import de.rexlnico.signsystem.sign.Sign;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;

public class StaticSign implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Main.getPermissionManager().getCreateStaticSign())) {
                if (args.length == 4) {
                    String serverName = args[0];
                    String displayName = args[1];
                    String ip = args[2];
                    int port = 0;
                    try {
                        port = Integer.parseInt(args[3]);
                    } catch (Exception e) {
                        player.sendMessage(Main.getMessageManager().ErrorMessage("port must be an integer"));
                        return false;
                    }
                    Location location = player.getTargetBlock((HashSet<Byte>) null, 10).getLocation();
                    if (location.getBlock().getState() instanceof org.bukkit.block.Sign) {
                        if (Main.getInstance().getSignManager().getSign(location) == null) {
                            Server server = new Server(ServerType.STATIC, serverName, displayName, ip, port);
                            Sign sign = Main.getInstance().getSignManager().addSign(location, ServerType.STATIC, server, null);
                            sign.setServer(server);
                            Main.getInstance().getServerManager().addServer(server);
                            player.sendMessage(Main.getMessageManager().AddSignStatic(serverName, ip, port));
                        } else {
                            player.sendMessage(Main.getMessageManager().ErrorMessage("Sign already exist!"));
                        }
                    }
                } else {
                    player.sendMessage(Main.getMessageManager().ErrorMessage("/createSign <ServerName> <DisplayName> <IP> <Port>"));
                }
            }
        }
        return true;
    }
}
