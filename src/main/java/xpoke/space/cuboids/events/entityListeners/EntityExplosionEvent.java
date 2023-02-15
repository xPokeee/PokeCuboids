package xpoke.space.cuboids.events.entityListeners;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import xpoke.space.cuboids.managers.*;

public class EntityExplosionEvent implements Listener {

    @EventHandler
    public void onExplosion(ExplosionPrimeEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getEntity().getLocation())) return;
        e.setCancelled(true);
        e.setFire(false);
        e.setRadius(0);
    }

    @EventHandler
    public void onMinecartExplosion(EntityExplodeEvent e){
        double locx1 = e.getLocation().getBlockX()+10;
        double locy1 = e.getLocation().getBlockY();
        double locz1 = e.getLocation().getBlockZ()+10;
        Location loc1 = new Location(e.getLocation().getWorld(), locx1, locy1, locz1);
        double locx2 = e.getLocation().getBlockX()-10;
        double locy2 = e.getLocation().getBlockY();
        double locz2 = e.getLocation().getBlockZ()-10;
        Location loc2 = new Location(e.getLocation().getWorld(), locx2, locy2, locz2);
        double locx3 = e.getLocation().getBlockX()+10;
        double locy3 = e.getLocation().getBlockY();
        double locz3 = e.getLocation().getBlockZ()-10;
        Location loc3 = new Location(e.getLocation().getWorld(), locx3, locy3, locz3);
        double locx4 = e.getLocation().getBlockX()-10;
        double locy4 = e.getLocation().getBlockY();
        double locz4 = e.getLocation().getBlockZ()+10;
        Location loc4 = new Location(e.getLocation().getWorld(), locx4, locy4, locz4);

        if(CuboidManager.doesLocationContainsAnyCuboid(loc1) || CuboidManager.doesAreaContainsAnyCuboid(loc2) || CuboidManager.doesAreaContainsAnyCuboid(loc3) || CuboidManager.doesAreaContainsAnyCuboid(loc4) || CuboidManager.doesAreaContainsAnyCuboid(e.getLocation())) {
            e.setCancelled(true);
            e.setYield(0);
        }
    }

}
