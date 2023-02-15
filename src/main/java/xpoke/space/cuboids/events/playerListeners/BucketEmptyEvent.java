package xpoke.space.cuboids.events.playerListeners;

import org.bukkit.event.*;
import org.bukkit.event.player.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

public class BucketEmptyEvent implements Listener {

    @EventHandler
    public void onBucketEmpty(PlayerBucketEmptyEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getBlock().getLocation())) return;
        Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getBlock().getLocation());
        assert cuboid != null;
        if(cuboid.getMembers().contains(e.getPlayer().getName())) return;
        if(!cuboid.isBlockPlace()){
           e.setCancelled(true);
        }
    }

}
