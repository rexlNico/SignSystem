package de.rexlnico.signsystem.command;

import de.rexlnico.signsystem.main.Main;
import de.rexlnico.signsystem.server.Server;
import de.rexlnico.signsystem.server.ServerType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddServer implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(Main.getPermissionManager().getAddDynamicServer())) {
                if (args.length == 5) {
                    String group = args[0];
                    String serverName = args[1];
                    String displayName = args[2];
                    String ip = args[3];
                    int port = 0;
                    try {
                        port = Integer.parseInt(args[4]);
                    } catch (Exception e) {
                        player.sendMessage(Main.getMessageManager().ErrorMessage("port must be an integer"));
                        return false;
                    }
                    if (Main.getInstance().getServerManager().getServerGroups().get(group) != null && Main.getInstance().getServerManager().getServerGroups().get(group).containsServer(serverName, port)) {
                        player.sendMessage(Main.getMessageManager().AlreadyExisting(serverName, ip, port, group));
                        return false;
                    }
                    Server server = new Server(ServerType.DYNAMIC, serverName, displayName, ip, port, group);
                    Main.getInstance().getServerManager().addServer(server);
                } else {
                    player.sendMessage(Main.getMessageManager().ErrorMessage("/addServer <ServerGroup> <ServerName> <DisplayName> <IP> <Port>"));
                }
            }
        }
        return false;
    }
}
