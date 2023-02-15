package xpoke.space.cuboids.events.entityListeners;

import org.bukkit.event.*;
import org.bukkit.event.vehicle.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

public class VehicleKillEvent implements Listener {

    @EventHandler
    public void onVehicleDestroy(VehicleDamageEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getVehicle().getLocation())) return;
        if(e.getAttacker() == null) return;
        Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getVehicle().getLocation());
        assert cuboid != null;
        if(cuboid.getMembers().contains(e.getAttacker().getName())) return;
        if(!cuboid.isInteraction()){
            e.setCancelled(true);
        }
    }

}
