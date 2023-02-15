package xpoke.space.cuboids.events.worldEvents;

import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.world.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.*;

import java.util.*;

public class TreeCreateEvent implements Listener {

    @EventHandler
    public void onTree(StructureGrowEvent e){
        int xplus = e.getLocation().getBlockX()+10;
        int zplus = e.getLocation().getBlockZ()+10;
        int xminus = e.getLocation().getBlockX()-10;
        int zminus = e.getLocation().getBlockZ()-10;
        Location locxplus = new Location(e.getWorld(), xplus, e.getLocation().getY(), e.getLocation().getZ());
        Location locxminus = new Location(e.getWorld(), xminus, e.getLocation().getY(), e.getLocation().getZ());
        Location loczplus = new Location(e.getWorld(), e.getLocation().getX(), e.getLocation().getY(), zplus);
        Location loczminus = new Location(e.getWorld(), e.getLocation().getX(), e.getLocation().getY(), zminus);
        if(!CuboidManager.doesLocationContainsAnyCuboid(locxplus) &&
                !CuboidManager.doesLocationContainsAnyCuboid(locxminus) &&
                !CuboidManager.doesLocationContainsAnyCuboid(loczplus) &&
                !CuboidManager.doesLocationContainsAnyCuboid(loczminus)){
            return;
        }
        if(CuboidManager.doesLocationContainsAnyCuboid(locxplus)){
            Cuboid cuboid = CuboidManager.getCuboidFromLocation(locxplus);
            if(!e.isFromBonemeal()){
                e.setCancelled(true);
                return;
            } else {
                assert cuboid != null;
                if (cuboid.getMembers().contains(Objects.requireNonNull(e.getPlayer()).getName()))
                    return;
                e.setCancelled(true);
            }
        }
        if(CuboidManager.doesLocationContainsAnyCuboid(locxminus)) {
            if (!e.isFromBonemeal()) {
                e.setCancelled(true);
                return;
            } else {
                Cuboid cuboid = CuboidManager.getCuboidFromLocation(locxminus);
                assert cuboid != null;
                if (cuboid.getMembers().contains(Objects.requireNonNull(e.getPlayer()).getName()))
                    return;
                e.setCancelled(true);
            }
        }
        if(CuboidManager.doesLocationContainsAnyCuboid(loczplus)){
            if(!e.isFromBonemeal()){
                e.setCancelled(true);
                return;
            } else {
                Cuboid cuboid = CuboidManager.getCuboidFromLocation(loczplus);
                assert cuboid != null;
                if (cuboid.getMembers().contains(Objects.requireNonNull(e.getPlayer()).getName()))
                    return;
                e.setCancelled(true);
            }
        }
        if(CuboidManager.doesLocationContainsAnyCuboid(loczminus)){
            if(!e.isFromBonemeal()){
                e.setCancelled(true);
            } else {
                Cuboid cuboid = CuboidManager.getCuboidFromLocation(loczminus);
                assert cuboid != null;
                if (cuboid.getMembers().contains(Objects.requireNonNull(e.getPlayer()).getName()))
                    return;
                e.setCancelled(true);
            }
        }
    }

}
