package xpoke.space.cuboids.events.playerListeners;

import org.bukkit.event.*;
import org.bukkit.event.player.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;

public class JoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if(!CuboidManager.doesLocationContainsAnyCuboid(e.getPlayer().getLocation())) return;
        Cuboid cuboid = CuboidManager.getCuboidFromLocation(e.getPlayer().getLocation());
        assert cuboid != null;
        e.getPlayer().sendTitle(
                MessageManager.CUBOID_ENTER_TITLE.replace("%player%", cuboid.getCuboidOwner()),
                MessageManager.CUBOID_ENTER_SUBTITLE.replace("%player%", cuboid.getCuboidOwner()),
                10,
                40,
                10
        );
    }

}
