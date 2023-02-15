package xpoke.space.cuboids.events.playerListeners;

import org.bukkit.event.*;
import org.bukkit.event.player.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

public class ThrowEvent implements Listener {

    @EventHandler
    public void onThrow(PlayerEggThrowEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getPlayer().getLocation())) return;
        if(CuboidManager.doesLocationContainsAnyCuboid(e.getEgg().getLocation())){
            Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getEgg().getLocation());
            assert cuboid != null;
            if(cuboid.getMembers().contains(e.getPlayer().getName())) return;
            if(!cuboid.isInteraction()){
                e.setHatching(false);
            }
        }
    }

}
