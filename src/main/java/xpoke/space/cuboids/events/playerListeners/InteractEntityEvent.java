package xpoke.space.cuboids.events.playerListeners;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

public class InteractEntityEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e){
        if(!CuboidManager.doesAreaContainsAnyCuboid(e.getRightClicked().getLocation())) return;
        Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getRightClicked().getLocation());
        assert cuboid != null;
        if(cuboid.getMembers().contains(e.getPlayer().getName())) return;
        if(e.getRightClicked().getType().equals(EntityType.MINECART_CHEST) ||
                e.getRightClicked().getType().equals(EntityType.MINECART_HOPPER)){
            if(cuboid.isStorageUse()) return;
            e.setCancelled(true);
            return;
        }
        if(e.getRightClicked().getType().equals(EntityType.MINECART)){
            if(cuboid.isVehiclesUse()) return;
            e.setCancelled(true);
            return;
        }

        if(!cuboid.isInteraction()){
            e.setCancelled(true);
        }
    }

}
