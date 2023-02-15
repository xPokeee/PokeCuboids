package xpoke.space.cuboids.events.playerListeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import xpoke.space.cuboids.events.customEvents.PlayerCuboidEnterEvent;
import xpoke.space.cuboids.events.customEvents.PlayerCuboidLeaveEvent;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

public class MoveEvent implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        if(e.getTo().distance(e.getFrom()) == 0) return;
        Cuboid cuboidFrom = CuboidManager.getCuboidFromLocation(e.getFrom());
        Cuboid cuboidTo = CuboidManager.getCuboidFromLocation(e.getTo());
        if(cuboidFrom != null && cuboidTo == null){
            Bukkit.getPluginManager().callEvent(new PlayerCuboidLeaveEvent(e.getPlayer(), cuboidFrom));
        }
        if(cuboidFrom == null && cuboidTo != null){
            if(!cuboidTo.isCuboidEnter()){
                if(e.getPlayer().hasPermission("pokecuboids.admin")){
                    Bukkit.getPluginManager().callEvent(new PlayerCuboidEnterEvent(e.getPlayer(), cuboidTo));
                    return;
                }
                if(!cuboidTo.getMembers().contains(e.getPlayer().getName())) {
                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(MessageManager.CANT_WALK_INTO_CUBOID.replace("%player%", cuboidTo.getCuboidOwner())));
                    e.setCancelled(true);
                    return;
                }
            }
            Bukkit.getPluginManager().callEvent(new PlayerCuboidEnterEvent(e.getPlayer(), cuboidTo));
        }
    }

}
