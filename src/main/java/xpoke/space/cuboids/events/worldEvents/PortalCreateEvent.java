package xpoke.space.cuboids.events.worldEvents;

import org.bukkit.block.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.*;

import java.util.*;

public class PortalCreateEvent implements Listener {

    @EventHandler
    public void onPortalCreate(org.bukkit.event.world.PortalCreateEvent e){
        for(BlockState block : e.getBlocks()){
            if(!CuboidManager.doesLocationContainsAnyCuboid(block.getLocation())) return;
        }
        for(String w : DataManager.getAllowedWorlds()){
            if(!Objects.requireNonNull(e.getEntity()).getWorld().getName().equalsIgnoreCase(w)){
                Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getEntity().getLocation());
                if(!(e.getEntity() instanceof Player p)) return;
                assert cuboid != null;
                if(!cuboid.getMembers().contains(p.getName())){
                    e.setCancelled(true);
                }
            }
        }
    }

}
