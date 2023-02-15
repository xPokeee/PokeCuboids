package xpoke.space.cuboids.events.entityListeners;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

public class EntityDamageEvent implements Listener {

    @EventHandler
    public void onEntityDamage(org.bukkit.event.entity.EntityDamageByEntityEvent e){
        if(e.getEntity() instanceof Monster) return;
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getEntity().getLocation())) return;
        Entity damager = e.getDamager();
        Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getEntity().getLocation());
        assert cuboid != null;
        if(damager instanceof Player){
            if(e.getEntity() instanceof Player){
                if(!cuboid.isPvp()){
                    e.setCancelled(true);
                }
            } else {
                if(cuboid.getMembers().contains(damager.getName())) return;
                if (!cuboid.isMobsDamage()) {
                    e.setCancelled(true);
                }
            }
        }
    }

}
