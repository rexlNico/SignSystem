package de.rexlnico.signsystem.sign;

import de.rexlnico.signsystem.main.Main;
import de.rexlnico.signsystem.server.Server;
import de.rexlnico.signsystem.server.ServerType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;

public class Sign {

    private ServerType signType;
    private Server server;
    private Location location;
    private Location back;
    private SignLayout signLayout;

    public Sign(ServerType signType, Location location) {
        this.signType = signType;
        this.location = location;
        org.bukkit.material.Sign sign = (org.bukkit.material.Sign) location.getBlock().getState().getData();
        Block block = location.getBlock().getRelative(sign.getAttachedFace());
        this.back = block.getLocation();
        this.server = null;
        signLayout = new SignLayout();
        update();
    }

    public Sign(ServerType signType, Location location, Server server) {
        this.signType = signType;
        this.location = location;
        org.bukkit.material.Sign sign = (org.bukkit.material.Sign) location.getBlock().getState().getData();
        Block block = location.getBlock().getRelative(sign.getAttachedFace());
        this.back = block.getLocation();
        this.server = server;
        signLayout = new SignLayout();
        update();
    }


    public void update() {
        if (server != null) {
            signLayout.setLayout(Main.getSignLayoutManager().getSignLayout(this));
            if (signLayout.getLayout() != null) {
                try {
                    org.bukkit.block.Sign sign = (org.bukkit.block.Sign) location.getBlock().getState();
                    sign.setLine(0, signLayout.getLayout()[0]);
                    sign.setLine(1, signLayout.getLayout()[1]);
                    sign.setLine(2, signLayout.getLayout()[2]);
                    sign.setLine(3, signLayout.getLayout()[3]);
                    sign.update();
                } catch (Exception e) {
                    if (signType == ServerType.STATIC) {
                        Main.getInstance().getServerManager().removeServer(getServer());
                        Main.getInstance().getSignManager().removeSign(this);
                        return;
                    }
                }
            }
        }
        if (server != null) server.update();
        if (server == null || !server.isOnline()) {
            String material = Main.getMessageManager().BlockOffline();
            if (material.contains(":")) {
                String[] list = material.split(":");
                back.getBlock().setType(Material.valueOf(list[0]));
                back.getBlock().setData((byte) Integer.parseInt(list[1]));
            } else {
                back.getBlock().setType(Material.valueOf(material));
            }
        } else if (server.isFull()) {
            String material = Main.getMessageManager().BlockFull();
            if (material.contains(":")) {
                String[] list = material.split(":");
                back.getBlock().setType(Material.valueOf(list[0]));
                back.getBlock().setData((byte) Integer.parseInt(list[1]));
            } else {
                back.getBlock().setType(Material.valueOf(material));
            }
        } else {
            String material = Main.getMessageManager().BlockOnline();
            if (material.contains(":")) {
                String[] list = material.split(":");
                back.getBlock().setType(Material.valueOf(list[0]));
                back.getBlock().setData((byte) Integer.parseInt(list[1]));
            } else {
                back.getBlock().setType(Material.valueOf(material));
            }
        }
    }

    public SignLayout getSignLayout() {
        return signLayout;
    }

    public ServerType getSignType() {
        return signType;
    }

    public Server getServer() {
        return server;
    }

    public Location getLocation() {
        return location;
    }

    public Location getBack() {
        return back;
    }

    public void setServer(Server server) {
        this.server = server;
    }

}
