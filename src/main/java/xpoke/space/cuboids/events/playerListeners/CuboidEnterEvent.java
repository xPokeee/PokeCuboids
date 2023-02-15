package xpoke.space.cuboids.events.playerListeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import xpoke.space.cuboids.events.customEvents.PlayerCuboidEnterEvent;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

public class CuboidEnterEvent implements Listener {

    @EventHandler
    public void onCuboidEnter(PlayerCuboidEnterEvent e){
        Player p = e.getPlayer();
        Cuboid cuboid = e.getCuboid();
        p.sendTitle(
                MessageManager.CUBOID_ENTER_TITLE.replace("%player%", cuboid.getCuboidOwner()),
                MessageManager.CUBOID_ENTER_SUBTITLE.replace("%player%", cuboid.getCuboidOwner()),
                10,
                40,
                10
        );
    }

}
