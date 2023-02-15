package xpoke.space.cuboids.events.blockListeners;

import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.event.*;
import xpoke.space.cuboids.managers.*;


public class BlockPistonExtendEvent implements Listener {

    @EventHandler
    public void onBlockPistonExtend(org.bukkit.event.block.BlockPistonExtendEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getBlock().getLocation())){
            if(e.getDirection().equals(BlockFace.NORTH)){
                int block_z = e.getBlock().getZ()-12;
                if(CuboidManager.doesLocationContainsAnyCuboid(new Location(e.getBlock().getWorld(), e.getBlock().getX(), e.getBlock().getY(), block_z))){
                    e.setCancelled(true);
                    return;
                }
            }
            if(e.getDirection().equals(BlockFace.SOUTH)){
                int block_z = e.getBlock().getZ()+12;
                if(CuboidManager.doesLocationContainsAnyCuboid(new Location(e.getBlock().getWorld(), e.getBlock().getX(), e.getBlock().getY(), block_z))){
                    e.setCancelled(true);
                    return;
                }
            }
            if(e.getDirection().equals(BlockFace.WEST)){
                int block_x = e.getBlock().getX()-12;
                if(CuboidManager.doesLocationContainsAnyCuboid(new Location(e.getBlock().getWorld(), block_x, e.getBlock().getY(), e.getBlock().getZ()))){
                    e.setCancelled(true);
                    return;
                }
            }
            if(e.getDirection().equals(BlockFace.EAST)){
                int block_x = e.getBlock().getX()+12;
                if(CuboidManager.doesLocationContainsAnyCuboid(new Location(e.getBlock().getWorld(), block_x, e.getBlock().getY(), e.getBlock().getZ()))){
                    e.setCancelled(true);
                }
            }
        }
    }

}
