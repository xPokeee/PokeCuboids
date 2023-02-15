package xpoke.space.cuboids.events.playerListeners;

import org.bukkit.event.*;
import org.bukkit.event.player.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

public class ItemPickupEvent implements Listener {

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getPlayer().getLocation())) return;
        Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getPlayer().getLocation());
        assert cuboid != null;
        if(cuboid.getMembers().contains(e.getPlayer().getName())) return;
        if(!cuboid.isItemPickUp()){
            e.setCancelled(true);
        }
    }

}
