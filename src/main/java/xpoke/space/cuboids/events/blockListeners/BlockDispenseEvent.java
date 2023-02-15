package xpoke.space.cuboids.events.blockListeners;

import org.bukkit.*;
import org.bukkit.event.*;
import xpoke.space.cuboids.managers.*;

public class BlockDispenseEvent implements Listener {

    @EventHandler
    public void onDispense(org.bukkit.event.block.BlockDispenseEvent e){
        if(CuboidManager.doesLocationContainsAnyCuboid(e.getBlock().getLocation())) return;
        int xplus = e.getBlock().getLocation().getBlockX()+1;
        int zplus = e.getBlock().getLocation().getBlockZ()+1;
        int xminus = e.getBlock().getLocation().getBlockX()-1;
        int zminus = e.getBlock().getLocation().getBlockZ()-1;
        Location locxplus = new Location(e.getBlock().getWorld(), xplus, e.getBlock().getLocation().getY(), e.getBlock().getLocation().getZ());
        Location locxminus = new Location(e.getBlock().getWorld(), xminus, e.getBlock().getLocation().getY(), e.getBlock().getLocation().getZ());
        Location loczplus = new Location(e.getBlock().getWorld(), e.getBlock().getLocation().getX(), e.getBlock().getLocation().getY(), zplus);
        Location loczminus = new Location(e.getBlock().getWorld(), e.getBlock().getLocation().getX(), e.getBlock().getLocation().getY(), zminus);
        if(CuboidManager.doesLocationContainsAnyCuboid(locxplus)){
            e.setCancelled(true);
        }
        if(CuboidManager.doesLocationContainsAnyCuboid(locxminus)) {
            e.setCancelled(true);
        }
        if(CuboidManager.doesLocationContainsAnyCuboid(loczplus)){
            e.setCancelled(true);
        }
        if(CuboidManager.doesLocationContainsAnyCuboid(loczminus)){
            e.setCancelled(true);
        }
    }

}
