package de.rexlnico.signsystem.server;

import de.rexlnico.signsystem.sign.Sign;

import java.util.ArrayList;

public class ServerGroup {

    private String group;
    private ArrayList<Server> servers;
    private ArrayList<Sign> signs;
    private ArrayList<Sign> noServer;
    private ArrayList<Server> noSign;
    private ArrayList<Server> fullServer;

    public ServerGroup(String group) {
        this.group = group;
        servers = new ArrayList<>();
        signs = new ArrayList<>();
        noServer = new ArrayList<>();
        noSign = new ArrayList<>();
        fullServer = new ArrayList<>();
    }

    public void addServer(Server server) {
        servers.add(server);
        noSign.add(server);
    }

    public boolean containsServer(String serverName, int port) {
        for (Server server : servers) {
            if (server.getServerName().equals(serverName) || server.getPort() == port) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Sign> getSigns() {
        return signs;
    }

    public ArrayList<Sign> getNoServer() {
        return noServer;
    }

    public ArrayList<Server> getServers() {
        return servers;
    }

    public ArrayList<Server> getNoSign() {
        return noSign;
    }

    public ArrayList<Server> getFullServer() {
        return fullServer;
    }

    public String getGroup() {
        return group;
    }

    public void removeServer(Server server) {
        if (servers.contains(server) && !noSign.contains(server)) {
            for (Sign sign : signs) {
                if (sign.getServer().equals(server)) {
                    noServer.add(sign);
                    sign.setServer(null);
                }
            }
        }
        if (servers.contains(server)) servers.remove(server);
        if (noSign.contains(server)) noSign.remove(server);
    }

    public void removeSign(Sign sign) {
        if (signs.contains(sign) && !noServer.contains(sign)) {
            if (sign.getServer() != null) {
                noSign.add(sign.getServer());
            }
        }
        if (signs.contains(sign)) signs.remove(sign);
        if (noServer.contains(sign)) noServer.remove(sign);
    }

    public void addSign(Sign sign) {
        signs.add(sign);
        noServer.add(sign);
    }

    public void update() {
        if (noSign.size() > 0 && noServer.size() > 0) {
            for (Sign sign : noServer) {
                Server server = noSign.get(0);
                sign.setServer(server);
                noServer.remove(sign);
                noSign.remove(server);
            }
        }
    }

}
