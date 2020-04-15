package de.rexlnico.signsystem.listeners;

import de.rexlnico.signsystem.main.Main;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void on(BlockBreakEvent e) {
        if (e.getBlock().getState() instanceof Sign) {
            de.rexlnico.signsystem.sign.Sign sign = Main.getInstance().getSignManager().getSign(e.getBlock().getLocation());
            if (sign != null) {
                if (!e.getPlayer().hasPermission(Main.getPermissionManager().getRemoveDynamicSign())) {
                    e.setCancelled(true);
                    return;
                }
                e.getPlayer().sendMessage(Main.getMessageManager().RemoveSign(sign.getServer().getServerName(), sign.getServer().getIp(), sign.getServer().getPort()));
            }
        } else {
            de.rexlnico.signsystem.sign.Sign sign = Main.getInstance().getSignManager().getSignFormBlockBehind(e.getBlock().getLocation());
            if (sign != null) {
                if (!e.getPlayer().hasPermission(Main.getPermissionManager().getRemoveDynamicSign())) {
                    e.setCancelled(true);
                    return;
                }
                e.getPlayer().sendMessage(Main.getMessageManager().RemoveSign(sign.getServer().getServerName(), sign.getServer().getIp(), sign.getServer().getPort()));
            }
        }
    }

}
