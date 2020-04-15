package de.rexlnico.signsystem.configuration;

import de.rexlnico.signsystem.main.Main;
import de.rexlnico.signsystem.server.Server;
import de.rexlnico.signsystem.server.ServerType;
import de.rexlnico.signsystem.sign.Sign;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SignLayoutManager {

    private File fileStatic;
    private YamlConfiguration cfgStatic;

    private File fileDynamic;
    private YamlConfiguration cfgDynamic;

    private ArrayList<String> serverOnlineStatic;
    private int currentStaticOnlineCount = 1;
    private ArrayList<String> serverFullStatic;
    private int currentStaticFullCount = 1;
    private ArrayList<String> serverOfflineStatic;
    private int currentStaticOfflineCount = 1;

    private ArrayList<String> serverOnlineDynamic;
    private int currentDynamicOnlineCount = 1;
    private ArrayList<String> serverFullDynamic;
    private int currentDynamicFullCount = 1;
    private ArrayList<String> serverOfflineDynamic;
    private int currentDynamicOfflineCount = 1;


    public SignLayoutManager() throws IOException {
        fileStatic = new File("plugins/SignSystem/config/SignLayoutStatic.yml");
        cfgStatic = YamlConfiguration.loadConfiguration(fileStatic);
        fileDynamic = new File("plugins/SignSystem/config/SignLayoutDynamic.yml");
        cfgDynamic = YamlConfiguration.loadConfiguration(fileDynamic);
        serverOnlineStatic = new ArrayList<>();
        serverFullStatic = new ArrayList<>();
        serverOfflineStatic = new ArrayList<>();
        update();
    }

    public void update() throws IOException {
        if (!fileStatic.exists()) {
            ArrayList<String> layout1 = new ArrayList<>();
            ArrayList<String> layout2 = new ArrayList<>();
            ArrayList<String> layout3 = new ArrayList<>();
            layout1.add("&0[&a%serverDisplay%&0]");
            layout1.add("&0➟ &aOnline");
            layout1.add("&0%playerOnline%/%playerMax%");
            layout1.add("&0%motd1%");
            cfgStatic.set("OnlineLayout", layout1);
            cfgStatic.save(fileStatic);
            layout2.add("&0[&a%serverDisplay%&0]");
            layout2.add("&0➟ &6Online");
            layout2.add("&0%playerOnline%/%playerMax%");
            layout2.add("&0%motd1%");
            cfgStatic.set("FullLayout", layout2);
            cfgStatic.save(fileStatic);
            layout3.add("&0[&a%serverDisplay%&0]");
            layout3.add("&0➟ &4Offline");
            layout3.add("&8● &7● ● ●");
            layout3.add("");
            layout3.add("&0[&a%serverDisplay%&0]");
            layout3.add("&0➟ &4Offline");
            layout3.add("&8● ● &7● ●");
            layout3.add("");
            layout3.add("&0[&a%serverDisplay%&0]");
            layout3.add("&0➟ &4Offline");
            layout3.add("&8● ● ● &7●");
            layout3.add("");
            layout3.add("&0[&a%serverDisplay%&0]");
            layout3.add("&0➟ &4Offline");
            layout3.add("&8● ● ● ●");
            layout3.add("");
            layout3.add("&0[&a%serverDisplay%&0]");
            layout3.add("&0➟ &4Offline");
            layout3.add("&7● &8● ● ●");
            layout3.add("");
            layout3.add("&0[&a%serverDisplay%&0]");
            layout3.add("&0➟ &4Offline");
            layout3.add("&7● ● &8● ●");
            layout3.add("");
            layout3.add("&0[&a%serverDisplay%&0]");
            layout3.add("&0➟ &4Offline");
            layout3.add("&7● ● ● &8●");
            layout3.add("");
            layout3.add("&0[&a%serverDisplay%&0]");
            layout3.add("&0➟ &4Offline");
            layout3.add("&7● ● ● ●");
            layout3.add("");
            cfgStatic.set("OfflineLayout", layout3);
            cfgStatic.save(fileStatic);
        }
        if (!fileDynamic.exists()) {
            ArrayList<String> layout1 = new ArrayList<>();
            ArrayList<String> layout2 = new ArrayList<>();
            ArrayList<String> layout3 = new ArrayList<>();
            layout1.add("&0[&a%group%&0]");
            layout1.add("&8- %serverDisplay% -");
            layout1.add("&0➟ &aOnline");
            layout1.add("&0%playerOnline%/%playerMax%");
            cfgDynamic.set("OnlineLayout", layout1);
            layout2.add("&0[&a%group%&0]");
            layout2.add("&8- %serverDisplay% -");
            layout2.add("&0➟ &6Online");
            layout2.add("&0%playerOnline%/%playerMax%");
            cfgDynamic.set("FullLayout", layout2);
            layout3.add("&0[&a%group%&0]");
            layout3.add("&0➟ &4Offline");
            layout3.add("&8● &7● ● ●");
            layout3.add("");
            layout3.add("&0[&a%group%&0]");
            layout3.add("&0➟ &4Offline");
            layout3.add("&8● ● &7● ●");
            layout3.add("");
            layout3.add("&0[&a%group%&0]");
            layout3.add("&0➟ &4Offline");
            layout3.add("&8● ● ● &7●");
            layout3.add("");
            layout3.add("&0[&a%group%&0]");
            layout3.add("&0➟ &4Offline");
            layout3.add("&8● ● ● ●");
            layout3.add("");
            layout3.add("&0[&a%group%&0]");
            layout3.add("&0➟ &4Offline");
            layout3.add("&7● &8● ● ●");
            layout3.add("");
            layout3.add("&0[&a%group%&0]");
            layout3.add("&0➟ &4Offline");
            layout3.add("&7● ● &8● ●");
            layout3.add("");
            layout3.add("&0[&a%group%&0]");
            layout3.add("&0➟ &4Offline");
            layout3.add("&7● ● ● &8●");
            layout3.add("");
            layout3.add("&0[&a%group%&0]");
            layout3.add("&0➟ &4Offline");
            layout3.add("&7● ● ● ●");
            layout3.add("");
            cfgDynamic.set("OfflineLayout", layout3);
            cfgDynamic.save(fileDynamic);
        }
        serverFullDynamic = (ArrayList<String>) cfgDynamic.getStringList("FullLayout");
        serverFullStatic = (ArrayList<String>) cfgStatic.getStringList("FullLayout");
        serverOfflineDynamic = (ArrayList<String>) cfgDynamic.getStringList("OfflineLayout");
        serverOfflineStatic = (ArrayList<String>) cfgStatic.getStringList("OfflineLayout");
        serverOnlineDynamic = (ArrayList<String>) cfgDynamic.getStringList("OnlineLayout");
        serverOnlineStatic = (ArrayList<String>) cfgStatic.getStringList("OnlineLayout");
    }

    public String[] getSignLayout(Sign sign) {
        boolean online = sign.getServer().isOnline();
        boolean full = sign.getServer().isFull();
        ServerType serverType = sign.getServer().getServerType();
        int position = sign.getSignLayout().getCurrent();
        if (!online) {
            if (serverType == ServerType.DYNAMIC) {
                int max = serverOfflineDynamic.size() / 4;
                if (max < position) position = 1;
                String[] list = {replace(serverOfflineDynamic.get((position * 4) - 4), sign),
                        replace(serverOfflineDynamic.get((position * 4) - 3), sign),
                        replace(serverOfflineDynamic.get((position * 4) - 2), sign),
                        replace(serverOfflineDynamic.get((position * 4) - 1), sign)};
                sign.getSignLayout().setNextCurrent(max);
                return list;
            } else if (serverType == ServerType.STATIC) {
                int max = serverOfflineStatic.size() / 4;
                if (max < position) position = 1;
                String[] list = {replace(serverOfflineStatic.get((position * 4) - 4), sign),
                        replace(serverOfflineStatic.get((position * 4) - 3), sign),
                        replace(serverOfflineStatic.get((position * 4) - 2), sign),
                        replace(serverOfflineStatic.get((position * 4) - 1), sign)};
                sign.getSignLayout().setNextCurrent(max);
                return list;
            }
        } else if (full) {
            if (serverType == ServerType.DYNAMIC) {
                int max = serverFullDynamic.size() / 4;
                if (max < position) position = 1;
                String[] list = {replace(serverFullDynamic.get((position * 4) - 4), sign),
                        replace(serverFullDynamic.get((position * 4) - 3), sign),
                        replace(serverFullDynamic.get((position * 4) - 2), sign),
                        replace(serverFullDynamic.get((position * 4) - 1), sign)};
                return list;
            } else if (serverType == ServerType.STATIC) {
                int max = serverFullStatic.size() / 4;
                if (max < position) position = 1;
                String[] list = {replace(serverFullStatic.get((position * 4) - 4), sign),
                        replace(serverFullStatic.get((position * 4) - 3), sign),
                        replace(serverFullStatic.get((position * 4) - 2), sign),
                        replace(serverFullStatic.get((position * 4) - 1), sign)};
                sign.getSignLayout().setNextCurrent(max);
                return list;
            }
        } else {
            if (serverType == ServerType.DYNAMIC) {
                int max = serverOnlineDynamic.size() / 4;
                if (max < position) position = 1;
                String[] list = {replace(serverOnlineDynamic.get((position * 4) - 4), sign),
                        replace(serverOnlineDynamic.get((position * 4) - 3), sign),
                        replace(serverOnlineDynamic.get((position * 4) - 2), sign),
                        replace(serverOnlineDynamic.get((position * 4) - 1), sign)};
                sign.getSignLayout().setNextCurrent(max);
                return list;
            } else if (serverType == ServerType.STATIC) {
                int max = serverOnlineStatic.size() / 4;
                if (max < position) position = 1;
                String[] list = {replace(serverOnlineStatic.get((position * 4) - 4), sign),
                        replace(serverOnlineStatic.get((position * 4) - 3), sign),
                        replace(serverOnlineStatic.get((position * 4) - 2), sign),
                        replace(serverOnlineStatic.get((position * 4) - 1), sign)};
                sign.getSignLayout().setNextCurrent(max);
                return list;
            }
        }
        return null;
    }

    public String replace(String text, Sign sign) {
        return ChatColor.translateAlternateColorCodes('&', text
                .replace("%serverName%", sign.getServer().getServerName())
                .replace("%serverDisplay%", sign.getServer().getDisplayName())
                .replace("%ip%", sign.getServer().getIp())
                .replace("%port%", sign.getServer().getPort() + "")
                .replace("%playerOnline%", sign.getServer().getOnlinePlayer() + "")
                .replace("%playerMax%", sign.getServer().getMaxPlayer() + "")
                .replace("%motd1%", sign.getServer().getMotd1() == null ? "null" : sign.getServer().getMotd1())
                .replace("%motd2%", sign.getServer().getMotd2() == null ? "null" : sign.getServer().getMotd2())
                .replace("%group%", sign.getSignType() == ServerType.DYNAMIC ? Main.getInstance().getServerManager().getGroup(sign).getGroup() : "%group%"));
    }

}
