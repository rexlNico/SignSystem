package de.rexlnico.signsystem.configuration;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PermissionManager {

    private File file;
    private YamlConfiguration cfg;

    private String createStaticSign = "signsystem.create.static";
    private String removeStaticSign = "signsystem.remove.static";
    private String addDynamicSign = "signsystem.add.sign";
    private String removeDynamicSign = "signsystem.remove.sign";
    private String addDynamicServer = "signsystem.add.server";
    private String removeDynamicServer = "signsystem.remove.server";

    public PermissionManager() throws IOException {
        file = new File("plugins/SignSystem/config/Permissions.yml");
        cfg = YamlConfiguration.loadConfiguration(file);
        update();
    }

    public void update() throws IOException {
        if (!file.exists()) {
            cfg.set("CreateStaticSign", createStaticSign);
            cfg.set("RemoveStaticSign", removeStaticSign);
            cfg.set("AddDynamicSign", addDynamicSign);
            cfg.set("RemoveDynamicSign", removeDynamicSign);
            cfg.set("AddDynamicServer", addDynamicServer);
            cfg.set("RemoveDynamicServer", removeDynamicServer);
            cfg.save(file);
        }
        createStaticSign = cfg.getString("CreateStaticSign");
        removeStaticSign = cfg.getString("RemoveStaticSign");
        addDynamicSign = cfg.getString("AddDynamicSign");
        removeDynamicSign = cfg.getString("RemoveDynamicSign");
        addDynamicServer = cfg.getString("AddDynamicServer");
        removeDynamicServer = cfg.getString("RemoveDynamicServer");
    }

    public String getRemoveStaticSign() {
        return removeStaticSign;
    }

    public String getRemoveDynamicSign() {
        return removeDynamicSign;
    }

    public String getRemoveDynamicServer() {
        return removeDynamicServer;
    }

    public String getCreateStaticSign() {
        return createStaticSign;
    }

    public String getAddDynamicSign() {
        return addDynamicSign;
    }

    public String getAddDynamicServer() {
        return addDynamicServer;
    }
}
