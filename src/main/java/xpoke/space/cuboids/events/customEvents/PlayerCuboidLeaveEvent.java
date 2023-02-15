package xpoke.space.cuboids.events.customEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import xpoke.space.cuboids.managers.CuboidManager;
import xpoke.space.cuboids.objects.Cuboid;

public class PlayerCuboidLeaveEvent extends Event {

    public PlayerCuboidLeaveEvent(Player player, Cuboid cuboid) {
        this.player = player;
        this.cuboid = cuboid;
    }

    private static final HandlerList handlerList = new HandlerList();

    private final Player player;

    private final Cuboid cuboid;

    public Player getPlayer() {
        return this.player;
    }

    public Cuboid getCuboid() {
        return this.cuboid;
    }

    @NotNull
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

}
