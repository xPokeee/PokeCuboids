package xpoke.space.cuboids.events.playerListeners;

import org.bukkit.event.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

import java.util.*;

public class HangingPlaceEvent implements Listener {

    @EventHandler
    public void onHangingPlace(org.bukkit.event.hanging.HangingPlaceEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getBlock().getLocation())) return;
        Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getBlock().getLocation());
        assert cuboid != null;
        if(cuboid.getMembers().contains(Objects.requireNonNull(e.getPlayer()).getName())) return;
        if(!cuboid.isBlockPlace()){
           e.setCancelled(true);
        }
    }

}
