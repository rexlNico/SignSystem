package de.rexlnico.signsystem.server;

import de.rexlnico.signsystem.main.Main;
import org.bukkit.Bukkit;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;

public class Server {

    private ServerType serverType;
    private String group;
    private String serverName;
    private String displayName;
    private String ip;
    private int port;

    private boolean online;
    private int maxPlayer;
    private int onlinePlayer;
    private String motd1;
    private String motd2;

    public Server(ServerType serverType, String serverName, String displayName, String ip, int port, String group) {
        this.serverType = serverType;
        this.group = group;
        this.serverName = serverName;
        this.displayName = displayName;
        this.ip = ip;
        this.port = port;
    }

    public Server(ServerType serverType, String serverName, String displayName, String ip, int port) {
        this.serverType = serverType;
        this.serverName = serverName;
        this.displayName = displayName;
        this.ip = ip;
        this.port = port;
        this.group = "";
    }

    public void update() {
        try {
            Socket socket = new Socket();
            socket.setSoTimeout(2500);
            socket.connect(new InetSocketAddress(ip, port));
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-16BE"));
            dataOutputStream.write(new byte[]{-2, 1});
            int packetID = inputStream.read();
            if (packetID == -1) {
                Bukkit.getConsoleSender().sendMessage(Main.getMessageManager().ErrorMessage("End of Stream"));
                dataOutputStream.close();
                inputStream.close();
                inputStreamReader.close();
                socket.close();
            }
            if (packetID != 255) {
                Bukkit.getConsoleSender().sendMessage(Main.getMessageManager().ErrorMessage("Invalid packetID [" + packetID + "]"));
                dataOutputStream.close();
                inputStream.close();
                inputStreamReader.close();
                socket.close();
            }
            final int packetLenght = inputStreamReader.read();
            if (packetLenght == -1) {
                Bukkit.getConsoleSender().sendMessage(Main.getMessageManager().ErrorMessage("End of Stream"));
                dataOutputStream.close();
                inputStream.close();
                inputStreamReader.close();
                socket.close();
            }
            if (packetLenght == 0) {
                Bukkit.getConsoleSender().sendMessage(Main.getMessageManager().ErrorMessage("Invalid packetID [" + packetID + "]"));
                dataOutputStream.close();
                inputStream.close();
                inputStreamReader.close();
                socket.close();
            }
            final char[] chars = new char[packetLenght];
            if (inputStreamReader.read(chars, 0, packetLenght) != packetLenght) {
                Bukkit.getConsoleSender().sendMessage(Main.getMessageManager().ErrorMessage("End of Stream"));
                dataOutputStream.close();
                inputStream.close();
                inputStreamReader.close();
                socket.close();
            }
            final String str = new String(chars);
            final String[] info = str.split("\u0000");
            try {
                this.motd1 = info[3];
                this.motd2 = info[3];
                this.online = motd1 != null;
                this.onlinePlayer = Integer.parseInt(info[4]);
                this.maxPlayer = Integer.parseInt(info[5]);
                dataOutputStream.close();
                inputStream.close();
                inputStreamReader.close();
                socket.close();
            } catch (Exception ex) {
                this.online = false;
                this.maxPlayer = 0;
                this.onlinePlayer = 0;
                this.motd1 = null;
                this.motd2 = null;
            }
            dataOutputStream.close();
            inputStream.close();
            inputStreamReader.close();
            socket.close();
        } catch (Exception e) {
            this.online = false;
            this.maxPlayer = 0;
            this.onlinePlayer = 0;
            this.motd1 = null;
            this.motd2 = null;
        }
    }

    public int getMaxPlayer() {
        return maxPlayer;
    }

    public boolean isFull() {
        return getMaxPlayer() <= getOnlinePlayer();
    }

    public int getOnlinePlayer() {
        return onlinePlayer;
    }

    public int getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }

    public ServerType getServerType() {
        return serverType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getGroup() {
        return group;
    }

    public boolean isOnline() {
        return online;
    }

    public String getMotd1() {
        return motd1;
    }

    public String getMotd2() {
        return motd2;
    }

    public String getServerName() {
        return serverName;
    }

    @Override
    public String toString() {
        return ip + ":" + port;
    }
}
