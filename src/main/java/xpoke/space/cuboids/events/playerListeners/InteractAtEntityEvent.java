package xpoke.space.cuboids.events.playerListeners;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

public class InteractAtEntityEvent implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getRightClicked().getLocation())) return;
        Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getRightClicked().getLocation());
        assert cuboid != null;
        if(!cuboid.getMembers().contains(e.getPlayer().getName())) {
            if(e.getRightClicked().getType().equals(EntityType.MINECART)){
                if(cuboid.isVehiclesUse()) return;
                e.setCancelled(true);
                return;
            }
            if (!cuboid.isInteraction()) {
                e.setCancelled(true);
            }
        }

    }

}
