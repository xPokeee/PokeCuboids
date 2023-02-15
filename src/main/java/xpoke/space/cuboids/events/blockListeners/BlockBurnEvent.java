package xpoke.space.cuboids.events.blockListeners;

import org.bukkit.event.*;
import xpoke.space.cuboids.managers.*;

public class BlockBurnEvent implements Listener {

    @EventHandler
    public void onBurn(org.bukkit.event.block.BlockBurnEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getBlock().getLocation())) return;
        e.setCancelled(true);
    }

}
