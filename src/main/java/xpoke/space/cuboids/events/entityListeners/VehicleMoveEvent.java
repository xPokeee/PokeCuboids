package xpoke.space.cuboids.events.entityListeners;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

public class VehicleMoveEvent implements Listener {

    @EventHandler
    public void onVehicleMove(org.bukkit.event.vehicle.VehicleMoveEvent e){
        Location vehicleLocation = e.getVehicle().getLocation();
        if(!CuboidManager.doesLocationContainsAnyCuboid(vehicleLocation)) return;
        Cuboid cuboid = CuboidManager.getCuboidFromLocation(vehicleLocation);
        for(Entity entity : e.getVehicle().getPassengers()){
            if(entity instanceof Player player){
                assert cuboid != null;
                if(cuboid.getMembers().contains(player.getName())) return;
                if(!cuboid.isVehiclesUse()) {
                    e.getVehicle().setVelocity(e.getVehicle().getVelocity().multiply(0));
                    e.getVehicle().teleport(e.getFrom());
                    player.leaveVehicle();
                }
            }
        }
    }

}
