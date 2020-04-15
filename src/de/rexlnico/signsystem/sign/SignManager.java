package de.rexlnico.signsystem.sign;

import de.rexlnico.signsystem.main.Main;
import de.rexlnico.signsystem.server.Server;
import de.rexlnico.signsystem.server.ServerGroup;
import de.rexlnico.signsystem.server.ServerType;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class SignManager {

    private ArrayList<Sign> signs;

    public SignManager() {
        signs = new ArrayList<>();
    }

    public Sign addSign(Location location, ServerType signType, Server server, String group) {
        if (signType == ServerType.STATIC) {
            Sign sign = new Sign(signType, location);
            signs.add(sign);
            sign.setServer(server);
            return sign;
        } else if (signType == ServerType.DYNAMIC) {
            Sign sign = new Sign(signType, location);
            signs.add(sign);
            ServerGroup serverGroup = Main.getInstance().getServerManager().getGroup(group);
            if (serverGroup == null) {
                serverGroup = new ServerGroup(group);
                Main.getInstance().getServerManager().addServerGroup(serverGroup);
            }
            serverGroup.addSign(sign);
            return sign;
        }
        return null;
    }

    public void removeSign(Sign sign) {
        if (signs.contains(sign)) {
            signs.remove(sign);
        }
    }

    public Sign getSignFormBlockBehind(Location location) {
        for (Sign sign : signs) {
            if (sign.getBack().getBlock().getLocation().equals(location.getBlock().getLocation())) {
                return sign;
            }
        }
        return null;
    }

    public Sign getSign(Location location) {
        for (Sign sign : signs) {
            if (sign.getLocation().getBlock().getLocation().equals(location.getBlock().getLocation())) {
                return sign;
            }
        }
        return null;
    }

    public void updateAll() {
        try {
            if (!signs.isEmpty()) {
                for (Sign sign : signs) {
                    sign.update();
                }
            }
        } catch (Exception e) {

        }
    }

}
