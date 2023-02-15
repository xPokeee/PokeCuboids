package xpoke.space.cuboids.events.entityListeners;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

public class EntityPlaceEvent implements Listener {

    @EventHandler
    public void onEntityPlace(org.bukkit.event.entity.EntityPlaceEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getEntity().getLocation())) return;
        if(e.getPlayer() == null){
            e.setCancelled(true);
            return;
        }
        Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getEntity().getLocation());
        assert cuboid != null;
        if(cuboid.getMembers().contains(e.getPlayer().getName())) return;
        if(e.getEntity().getType().equals(EntityType.BOAT) ||
        e.getEntity().getType().name().equalsIgnoreCase("CHEST_BOAT") ||
        e.getEntity().getType().equals(EntityType.MINECART) ||
        e.getEntity().getType().equals(EntityType.MINECART_HOPPER) ||
        e.getEntity().getType().equals(EntityType.MINECART_CHEST) ||
        e.getEntity().getType().equals(EntityType.MINECART_FURNACE) ||
        e.getEntity().getType().equals(EntityType.MINECART_TNT)){
            if(!cuboid.isVehiclesUse()){
               e.setCancelled(true);
            }
        }
    }

}
