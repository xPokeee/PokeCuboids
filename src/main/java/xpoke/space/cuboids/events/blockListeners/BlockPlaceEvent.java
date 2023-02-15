package xpoke.space.cuboids.events.blockListeners;

import net.md_5.bungee.api.*;
import net.md_5.bungee.api.chat.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import xpoke.space.cuboids.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;
import xpoke.space.cuboids.utils.*;

public class BlockPlaceEvent implements Listener {

    private final Main instance = Main.getInstance();

    @EventHandler
    public void onBlockPlace(org.bukkit.event.block.BlockPlaceEvent e){
        Player p = e.getPlayer();
        Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getBlockPlaced().getLocation());
        assert cuboid != null;
        if(CuboidManager.doesLocationContainsAnyCuboid(e.getBlockPlaced().getLocation())) {
            if(ItemUtil.isBlockCuboidBlock(e.getBlock()) && !p.isSneaking()){
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(MessageManager.CANT_PLACE_CUBOID_ON_OTHER_CUBOID));
                e.setCancelled(true);
            }
            if(e.getPlayer().hasPermission("pokecuboids.admin")){
                e.setCancelled(false);
                return;
            }
            if(!cuboid.getMembers().contains(p.getName())){
                if(!cuboid.isBlockPlace()){
                    e.setCancelled(true);
                }
            }
            return;
        }
        if(e.getBlock().getType().name().endsWith("_BED")){
            int xplus = e.getBlockPlaced().getX()+1;
            int zplus = e.getBlockPlaced().getZ()+1;
            int xminus = e.getBlockPlaced().getX()-1;
            int zminus = e.getBlockPlaced().getZ()-1;
            Location locxplus = new Location(e.getBlockPlaced().getWorld(), xplus, e.getBlockPlaced().getY(), e.getBlockPlaced().getZ());
            Location locxminus = new Location(e.getBlockPlaced().getWorld(), xminus, e.getBlockPlaced().getY(), e.getBlockPlaced().getZ());
            Location loczplus = new Location(e.getBlockPlaced().getWorld(), e.getBlockPlaced().getX(), e.getBlockPlaced().getY(), zplus);
            Location loczminus = new Location(e.getBlockPlaced().getWorld(), e.getBlockPlaced().getX(), e.getBlockPlaced().getY(), zminus);
            if(CuboidManager.doesLocationContainsAnyCuboid(locxplus)){
                e.setCancelled(true);
            }
            if(CuboidManager.doesLocationContainsAnyCuboid(locxminus)) {
                e.setCancelled(true);
            }
            if(CuboidManager.doesLocationContainsAnyCuboid(loczplus)){
                e.setCancelled(true);
            }
            if(CuboidManager.doesLocationContainsAnyCuboid(loczminus)){
                e.setCancelled(true);
            }
            return;
        }

        if(ItemUtil.isBlockCuboidBlock(e.getBlock())){
            if(e.getPlayer().isSneaking()){
                return;
            }
            if(!DataManager.getAllowedWorlds().contains(e.getBlockPlaced().getWorld().getName())){
                p.sendMessage(MessageManager.CANT_PLACE_CUBOID_IN_WORLD);
                e.setCancelled(true);
                return;
            }
            if(PlayerManager.playerHasAnyCuboid(p)){
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(MessageManager.PLAYER_HAS_CUBOID));
                e.setCancelled(true);
                return;
            }
            if(CuboidManager.doesAreaContainsAnyCuboid(e.getBlock().getLocation())){
                p.sendMessage(MessageManager.TOO_CLOSE_TO_OTHER_CUBOID);
                e.setCancelled(true);
                return;
            }
            CuboidManager.createCuboid(p, e.getBlockPlaced().getLocation());
            p.sendMessage(MessageManager.CUBOID_CREATE_SUCCESS);
            p.sendTitle(MessageManager.CUBOID_CREATE_TITLE, MessageManager.CUBOID_CREATE_SUBTITLE, 10, 40, 10);
        }
    }

}
