package xpoke.space.cuboids.events.entityListeners;

import org.bukkit.entity.Player;
import org.bukkit.event.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

public class EntityInteractEvent implements Listener {

    @EventHandler
    public void onEntityInteract(org.bukkit.event.entity.EntityInteractEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getEntity().getLocation())) return;
        if(!(e.getEntity() instanceof Player)){
            Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getEntity().getLocation());
            assert cuboid != null;
            if(!cuboid.isInteraction()) {
                e.setCancelled(true);
            }
        }
    }

}
