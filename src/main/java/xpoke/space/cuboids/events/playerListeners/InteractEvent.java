package xpoke.space.cuboids.events.playerListeners;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.inventory.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

import java.util.*;
public class InteractEvent implements Listener {
    @EventHandler
    public void onInteract(org.bukkit.event.player.PlayerInteractEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getPlayer().getLocation())) return;
        Player p = e.getPlayer();
        Cuboid cuboid = CuboidManager.getCuboidFromLocation(p.getLocation());
        assert cuboid != null;
        if(cuboid.getMembers().contains(p.getName())) return;
        if(e.getAction() == Action.LEFT_CLICK_AIR){
            return;
        }
        if(e.getAction() == Action.LEFT_CLICK_BLOCK){
            if(Objects.requireNonNull(e.getClickedBlock()).getType().equals(Material.NOTE_BLOCK)){
                if(!cuboid.isMechanismsUse()){
                    e.setCancelled(true);
                    return;
                }
            }
        }
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getClickedBlock() == null) return;
            List<String> storageBlocks = DataManager.getStorageBlocks();
            if(!CuboidManager.doesLocationContainsCuboid(Objects.requireNonNull(e.getClickedBlock()).getLocation(), cuboid)) return;
            if(e.getClickedBlock().getType().name().endsWith("_BED")) {
                if(!cuboid.isBedsUse()) {
                    e.setCancelled(true);
                    return;
                }
            }
            if(e.getClickedBlock().getType().name().endsWith("_BUTTON")){
                if(!cuboid.isMechanismsUse()){
                    e.setCancelled(true);
                    return;
                }
            }
            if(e.getClickedBlock().getType().name().equalsIgnoreCase("LEAVER")){
                if(!cuboid.isMechanismsUse()){
                    e.setCancelled(true);
                    return;
                }
            }
            if(e.getClickedBlock().getType().name().endsWith("_DOOR")){
                if(!cuboid.isInteraction()){
                    e.setCancelled(true);
                    return;
                }
            }
            if(e.getClickedBlock().getType().name().endsWith("_TRAPDOOR")){
                if(!cuboid.isInteraction()){
                    e.setCancelled(true);
                    return;
                }
            }
            if(e.getClickedBlock().getType().name().endsWith("_GATE")){
                if(!cuboid.isInteraction()){
                    e.setCancelled(true);
                    return;
                }
            }
            if(e.getClickedBlock().getType().name().endsWith("_BOAT")){
                if(!cuboid.isVehiclesUse()){
                    e.setCancelled(true);
                    return;
                }
            }
            if(e.getClickedBlock().getType().equals(Material.MINECART)){
                if (!cuboid.isVehiclesUse()) {
                    e.setCancelled(true);
                    return;
                }
            }
            if (p.getInventory().getItemInMainHand().isSimilar(new ItemStack(Material.BONE_MEAL)) || p.getInventory().getItemInOffHand().isSimilar(new ItemStack(Material.BONE_MEAL))){
                if (!cuboid.isBlockPlace()){
                    e.setCancelled(true);
                }
            }
            for(String block : storageBlocks){
                if(e.getClickedBlock().getType().name().equalsIgnoreCase(block)){
                    if(!cuboid.isStorageUse()) {
                        e.setCancelled(true);
                        return;
                    }
                }
            }
            if(!cuboid.isInteraction()){
                if (e.getClickedBlock().getType() == Material.NOTE_BLOCK) {
                    e.setCancelled(true);
                }
            }

        }
        if(e.getAction() == Action.RIGHT_CLICK_AIR){
            if(!cuboid.isInteraction()){
                e.setCancelled(true);
                return;
            }
        }
        if(e.getAction() == Action.PHYSICAL){
            if(Objects.requireNonNull(e.getClickedBlock()).getType().name().endsWith("_PLATE")){
                if(!cuboid.isMechanismsUse()){
                    e.setCancelled(true);
                    return;
                }
            }
        }
        if(e.getMaterial() == Material.CHORUS_FRUIT || e.getMaterial() == Material.ENDER_PEARL){
            if(!cuboid.isPearlsUse()){
                e.setCancelled(true);
            }
        }

    }



}
