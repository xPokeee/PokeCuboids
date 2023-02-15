package xpoke.space.cuboids.events.playerListeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

public class ItemDropEvent implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getPlayer().getLocation())) return;
        Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getPlayer().getLocation());
        assert cuboid != null;
        if(cuboid.getMembers().contains(e.getPlayer().getName())) return;
        if(!cuboid.isItemDrop()){
            e.setCancelled(true);
        }
    }

}
