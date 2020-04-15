package de.rexlnico.signsystem.configuration;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessageManager {

    private File file;
    private YamlConfiguration cfg;

    private String prefix = "§8[§eSignSystem§8]";
    private String errorMessage = "%prefix% &4Something went wrong : %error%";
    private String addSignDynamic = "%prefix% &aYou added a new sign to the group %group%.";
    private String addSignStatic = "%prefix% &aYou added a new sign with the Server %serverName%. ip: %ip%:%port%";
    private String removeSign = "%prefix% &cThe sign was removed successfully.";
    private String alreadyExisting = "%prefix% &4The Server %serverName% with the ip: %ip%:%port% already exist!";
    private String serverNotExist = "%prefix% &cThe server %serverName% does not exist!";
    private String addServer = "%prefix% &aYou successfully added the server %serverName% with the ip: %ip%:%port% to the group %group%.";
    private String removeServer = "%prefix% &cThe server %serverName% with the ip: %ip%:%port% was successfully removed.";
    private String reloadConfigs = "%prefix% &cReloaded all configs.";

    private boolean setBlock = true;

    private String blockOffline = "STAINED_CLAY:14";
    private String blockOnline = "STAINED_CLAY:5";
    private String blockFull = "STAINED_CLAY:4";

    public MessageManager() throws IOException {
        file = new File("plugins/SignSystem/config/Messages.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        update();
    }

    public void update() throws IOException {
        file = new File("plugins/SignSystem/config/Messages.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()) {
            cfg.set("Prefix", prefix);
            cfg.set("ErrorMessage", errorMessage);
            cfg.set("AddSignDynamic", addSignDynamic);
            cfg.set("AddSignStatic", addSignStatic);
            cfg.set("RemoveSign", removeSign);
            cfg.set("ServerExisting", alreadyExisting);
            cfg.set("ServerNotExisting", serverNotExist);
            cfg.set("AddServer", addServer);
            cfg.set("RemoveServer", removeServer);
            cfg.set("BlockBackground", setBlock);
            cfg.set("BlockOffline", blockOffline);
            cfg.set("BlockOnline", blockOnline);
            cfg.set("BlockFull", blockFull);
            cfg.set("ReloadConfig", reloadConfigs);
            cfg.save(file);
        }
        prefix = cfg.getString("Prefix");
        errorMessage = cfg.getString("ErrorMessage");
        addSignDynamic = cfg.getString("AddSignDynamic");
        addSignStatic = cfg.getString("AddSignStatic");
        removeSign = cfg.getString("RemoveSign");
        alreadyExisting = cfg.getString("ServerExisting");
        serverNotExist = cfg.getString("ServerNotExisting");
        addServer = cfg.getString("AddServer");
        removeServer = cfg.getString("removeServer");
        setBlock = cfg.getBoolean("BlockBackground");
        blockOffline = getMaterial(cfg.getString("BlockOffline"), blockOffline);
        blockOnline = getMaterial(cfg.getString("BlockOnline"), blockOnline);
        blockFull = getMaterial(cfg.getString("BlockFull"), blockFull);
        reloadConfigs = cfg.getString("ReloadConfig");
    }

    private boolean isInteger(String subID) {
        try {
            Integer.parseInt(subID);
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    public String ReloadConfigs() {
        return ChatColor.translateAlternateColorCodes('&', reloadConfigs.replace("%prefix%", getPrefix()));
    }

    private String getMaterial(String cfgMaterial, String defaultS) {
        if (cfgMaterial.contains(":")) {
            String[] list = cfgMaterial.split(":");
            if (Material.valueOf(list[0]) != null) {
                if (isInteger(list[1])) {
                    return cfgMaterial;
                }
            }
        } else {
            if (Material.valueOf(cfgMaterial) != null) {
                return cfgMaterial;
            }
        }
        return defaultS;
    }

    public String getPrefix() {
        return prefix;
    }

    public String ErrorMessage(String error) {
        return ChatColor.translateAlternateColorCodes('&', errorMessage.replace("%prefix%", getPrefix()).replace("%error%", error));
    }

    public boolean isSetBlock() {
        return setBlock;
    }

    public String AddServer(String serverName, String ip, int port, String group) {
        return ChatColor.translateAlternateColorCodes('&', addServer.replace("%prefix%", getPrefix()).replace("%ip%", ip).replace("%port%", port + "").replace("%group%", group).replace("%serverName%", serverName));
    }

    public String AddSignDynamic(String group) {
        return ChatColor.translateAlternateColorCodes('&', addSignDynamic.replace("%prefix%", getPrefix()).replace("%group%", group));
    }

    public String AddSignStatic(String serverName, String ip, int port) {
        return ChatColor.translateAlternateColorCodes('&', addSignStatic.replace("%prefix%", getPrefix()).replace("%ip%", ip).replace("%port%", port + "").replace("%serverName%", serverName));
    }

    public String AlreadyExisting(String serverName, String ip, int port, String group) {
        return ChatColor.translateAlternateColorCodes('&', alreadyExisting.replace("%prefix%", getPrefix()).replace("%ip%", ip).replace("%port%", port + "").replace("%group%", group).replace("%serverName%", serverName));
    }

    public String BlockFull() {
        return blockFull;
    }

    public String BlockOffline() {
        return blockOffline;
    }

    public String BlockOnline() {
        return blockOnline;
    }

    public String RemoveServer(String serverName, String ip, int port, String group) {
        return ChatColor.translateAlternateColorCodes('&', removeServer.replace("%prefix%", getPrefix()).replace("%ip%", ip).replace("%port%", port + "").replace("%group%", group).replace("%serverName%", serverName));
    }

    public String RemoveSign(String serverName, String ip, int port) {
        return ChatColor.translateAlternateColorCodes('&', removeSign.replace("%prefix%", getPrefix()).replace("%ip%", ip).replace("%port%", port + "").replace("%serverName%", serverName));
    }

    public String ServerNotExist(String serverName) {
        return ChatColor.translateAlternateColorCodes('&', serverNotExist.replace("%prefix%", getPrefix()).replace("%serverName%", serverName));
    }
}
