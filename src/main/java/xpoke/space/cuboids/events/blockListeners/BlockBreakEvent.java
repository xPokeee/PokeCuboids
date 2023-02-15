package xpoke.space.cuboids.events.blockListeners;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;
import xpoke.space.cuboids.utils.*;

public class BlockBreakEvent implements Listener {

    @EventHandler
    public void onBlockBreak(org.bukkit.event.block.BlockBreakEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getBlock().getLocation())) return;
        Player p = e.getPlayer();
        Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getBlock().getLocation());
        assert cuboid != null;
        if(!cuboid.getMembers().contains(p.getName())) {
            if (!cuboid.isBlockBreak()) {
                e.setCancelled(!e.getPlayer().hasPermission("pokecuboids.admin"));
                return;
            }
        }
        if(e.getBlock().getType().equals(Material.NOTE_BLOCK)){
            if(cuboid.getCuboidLocation(cuboid).equals(e.getBlock().getLocation())){
                if(!cuboid.getCuboidOwner().equalsIgnoreCase(p.getName())){
                    p.sendMessage(MessageManager.CANT_BREAK_OTHER_PLAYER_CUBOID);
                    e.setCancelled(true);
                    return;
                }
                CuboidManager.deleteCuboid(cuboid);
                p.sendMessage(MessageManager.CUBOID_REMOVE_SUCCESS);
                p.sendTitle(MessageManager.CUBOID_REMOVE_TITLE, MessageManager.CUBOID_REMOVE_SUBTITLE, 10, 40, 10);
                e.setDropItems(false);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), ItemUtil.getCuboidBlock());
            }
        }
    }

}
