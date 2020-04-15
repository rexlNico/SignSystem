package de.rexlnico.signsystem.main;

import de.rexlnico.signsystem.command.AddServer;
import de.rexlnico.signsystem.command.AddSign;
import de.rexlnico.signsystem.command.StaticSign;
import de.rexlnico.signsystem.configuration.MessageManager;
import de.rexlnico.signsystem.configuration.PermissionManager;
import de.rexlnico.signsystem.configuration.SignLayoutManager;
import de.rexlnico.signsystem.listeners.BlockBreake;
import de.rexlnico.signsystem.server.ServerManager;
import de.rexlnico.signsystem.sign.SignManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class Main extends JavaPlugin {

    private static Main instance;
    private PluginManager pm;
    private ServerManager serverManager;
    private static MessageManager messageManager;
    private static SignLayoutManager signLayoutManager;
    private static PermissionManager permissionManager;
    private SignManager signManager;

    @Override
    public void onEnable() {
        instance = this;
        pm = Bukkit.getPluginManager();

        try {
            messageManager = new MessageManager();
            signLayoutManager = new SignLayoutManager();
            permissionManager = new PermissionManager();
        } catch (IOException e) {
        }
        signManager = new SignManager();
        serverManager = new ServerManager();

        pm.registerEvents(new BlockBreake(), this);

        getCommand("createSign").setExecutor(new StaticSign());
        getCommand("addServer").setExecutor(new AddServer());
        getCommand("addSign").setExecutor(new AddSign());

        Bukkit.getConsoleSender().sendMessage("§aSignSystem loaded");

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            signManager.updateAll();
            serverManager.updateAll();
        }, 0, 20);

    }

    @Override
    public void onDisable() {

    }

    public static PermissionManager getPermissionManager() {
        return permissionManager;
    }

    public static SignLayoutManager getSignLayoutManager() {
        return signLayoutManager;
    }

    public SignManager getSignManager() {
        return signManager;
    }

    public static MessageManager getMessageManager() {
        return messageManager;
    }

    public static Main getInstance() {
        return instance;
    }

    public ServerManager getServerManager() {
        return serverManager;
    }
}
