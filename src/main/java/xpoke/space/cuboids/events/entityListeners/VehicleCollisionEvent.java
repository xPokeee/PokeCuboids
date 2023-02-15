package xpoke.space.cuboids.events.entityListeners;

import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

public class VehicleCollisionEvent implements Listener {

    @EventHandler
    public void onCollision(VehicleEntityCollisionEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getEntity().getLocation())) return;
        if(e.getEntity() instanceof Player player) {
            Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getVehicle().getLocation());
            assert cuboid != null;
            if(cuboid.isVehiclesUse()) return;
            if (cuboid.getMembers().contains(player.getName())){
                return;
            }
            e.setCancelled(true);
            return;
        }
        e.setCancelled(true);
    }

}
