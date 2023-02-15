package xpoke.space.cuboids.events.playerListeners;

import net.md_5.bungee.api.*;
import net.md_5.bungee.api.chat.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

public class TeleportEvent implements Listener {

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getFrom()) && !CuboidManager.doesLocationContainsAnyCuboid(e.getTo())) return;
        if(
                e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL ||
                        e.getCause() == PlayerTeleportEvent.TeleportCause.CHORUS_FRUIT
        ){
            Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getTo());
            assert cuboid != null;
            if(cuboid.getMembers().contains(e.getPlayer().getName())) return;
            if(!cuboid.isCuboidEnter()){
                e.setCancelled(true);
                if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
                    e.getPlayer().getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
                }
                return;
            }
            if(!cuboid.isPearlsUse()){
                e.setCancelled(true);
                if(e.getPlayer().getGameMode() != GameMode.CREATIVE){
                    e.getPlayer().getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
                }
                return;
            }
        }
        if(e.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL || e.getCause() == PlayerTeleportEvent.TeleportCause.END_PORTAL){
            if(e.getTo() == null) return;
            if(!CuboidManager.doesAreaContainsAnyCuboid(e.getFrom()) && !CuboidManager.doesAreaContainsAnyCuboid(e.getTo())) return;
            Cuboid cuboidFrom = CuboidManager.getCuboidFromLocation(e.getFrom());
            Cuboid cuboidTo = CuboidManager.getCuboidFromLocation(e.getTo());
            if(cuboidFrom != null && cuboidTo == null){
                if(!cuboidFrom.isPearlsUse()){
                    if(!cuboidFrom.getMembers().contains(e.getPlayer().getName())){
                        e.setCancelled(true);
                    }
                }
            } else if(cuboidFrom == null && cuboidTo != null){
                if (!cuboidTo.isPortalsUse()) {
                    if (!cuboidTo.getMembers().contains(e.getPlayer().getName())) {
                        e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(MessageManager.CANT_WALK_INTO_CUBOID.replace("%player%", CuboidManager.getCuboidOwner(cuboidTo))));
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

}
