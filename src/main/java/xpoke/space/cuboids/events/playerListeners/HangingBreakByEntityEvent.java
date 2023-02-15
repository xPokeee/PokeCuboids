package xpoke.space.cuboids.events.playerListeners;

import org.bukkit.event.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

public class HangingBreakByEntityEvent implements Listener {

    @EventHandler
    public void onHangingBreak(org.bukkit.event.hanging.HangingBreakByEntityEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getEntity().getLocation())) return;
        Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getEntity().getLocation());
        assert cuboid != null;
        if(e.getRemover() == null) {
            e.setCancelled(true);
            return;
        }
        if(cuboid.getMembers().contains(e.getRemover().getName())) return;
        if(!cuboid.isBlockBreak()){
            e.setCancelled(true);
        }
    }

}
