package xpoke.space.cuboids.events.playerListeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import xpoke.space.cuboids.events.customEvents.PlayerCuboidLeaveEvent;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;
import xpoke.space.cuboids.utils.ColorUtil;

public class CuboidLeaveEvent implements Listener {

    @EventHandler
    public void onCuboidLeave(PlayerCuboidLeaveEvent e){
        Player p = e.getPlayer();
        Cuboid cuboid = e.getCuboid();
        p.sendTitle(
                MessageManager.CUBOID_LEAVE_TITLE.replace("%player%", cuboid.getCuboidOwner()),
                MessageManager.CUBOID_LEAVE_SUBTITLE.replace("%player%", cuboid.getCuboidOwner()),
                10,
                40,
                10
        );
    }

}
