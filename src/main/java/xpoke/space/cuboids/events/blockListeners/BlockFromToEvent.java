package xpoke.space.cuboids.events.blockListeners;

import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.event.*;
import xpoke.space.cuboids.managers.*;

public class BlockFromToEvent implements Listener {

    @EventHandler
    public void onFromTo(org.bukkit.event.block.BlockFromToEvent e){
        Block blockTo = e.getToBlock();
        Block blockFrom = e.getBlock();
        Location location = blockTo.getLocation();
        if(CuboidManager.doesLocationContainsAnyCuboid(blockFrom.getLocation())) return;
        if(CuboidManager.doesLocationContainsAnyCuboid(location)){
            e.setCancelled(true);
        }
    }

}
