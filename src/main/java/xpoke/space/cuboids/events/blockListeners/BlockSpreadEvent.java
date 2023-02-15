package xpoke.space.cuboids.events.blockListeners;

import org.bukkit.event.*;
import xpoke.space.cuboids.managers.*;

public class BlockSpreadEvent implements Listener {

    @EventHandler
    public void onFireSpread(org.bukkit.event.block.BlockSpreadEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getBlock().getLocation())) return;
        e.setCancelled(true);
    }

}
