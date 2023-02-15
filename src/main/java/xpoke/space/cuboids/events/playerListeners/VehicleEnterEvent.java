package xpoke.space.cuboids.events.playerListeners;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.*;

public class VehicleEnterEvent implements Listener {

    @EventHandler
    public void onVehicleEnter(org.bukkit.event.vehicle.VehicleEnterEvent e){
        Location vehicleLocation = e.getVehicle().getLocation();
        if(!CuboidManager.doesLocationContainsAnyCuboid(vehicleLocation)) return;
        Cuboid cuboid = CuboidManager.getCuboidFromLocation(vehicleLocation);
        for(Entity entity : e.getVehicle().getPassengers()){
            if(entity instanceof Player player){
                assert cuboid != null;
                if(CuboidManager.isPlayerAddedToCuboid(cuboid, player)) return;
                if(!cuboid.isVehiclesUse()) {
                    e.setCancelled(true);
                    player.leaveVehicle();
                }
            }
        }
    }

}
