package de.rexlnico.signsystem.server;

import de.rexlnico.signsystem.main.Main;
import de.rexlnico.signsystem.sign.Sign;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class ServerManager {

    private HashMap<String, Server> servers;
    private HashMap<String, ServerGroup> serverGroups;

    public ServerManager() {
        servers = new HashMap<>();
        serverGroups = new HashMap<>();
    }

    public void addServerGroup(ServerGroup serverGroup) {
        if (!serverGroups.containsKey(serverGroup.getGroup())) {
            serverGroups.put(serverGroup.getGroup(), serverGroup);
        }
    }

    public ServerGroup getGroup(String group) {
        if (serverGroups.containsKey(group)) {
            return serverGroups.get(group);
        }
        return null;
    }

    public ServerGroup getGroup(Sign sign) {
        for (ServerGroup serverGroup : serverGroups.values()) {
            if (serverGroup.getSigns().contains(sign)) {
                return serverGroup;
            }
        }
        return null;
    }

    public void addServer(Server server) {
        if (server.getServerType() == ServerType.STATIC) {
            if (!servers.containsKey(server.getServerName())) {
                servers.put(server.getServerName(), server);
            }
        } else if (server.getServerType() == ServerType.DYNAMIC) {
            ServerGroup serverGroup;
            if (!serverGroups.containsKey(server.getGroup())) {
                serverGroup = new ServerGroup(server.getGroup());
                serverGroup.addServer(server);
                serverGroups.put(server.getGroup(), serverGroup);
            } else {
                serverGroup = serverGroups.get(server.getGroup());
                serverGroup.addServer(server);
            }
        }
    }

    public HashMap<String, ServerGroup> getServerGroups() {
        return serverGroups;
    }

    public void removeServer(Server server) {
        if (server.getServerType() == ServerType.STATIC) {
            if (servers.containsKey(server.getServerName())) {
                servers.remove(server.getServerName());
            }
        } else if (server.getServerType() == ServerType.DYNAMIC) {
            ServerGroup serverGroup = serverGroups.get(server.getGroup());
            serverGroup.removeServer(server);
        }
    }

    public void updateAll() {
        for (ServerGroup serverGroup : serverGroups.values()) {
            serverGroup.update();
        }
    }

}
