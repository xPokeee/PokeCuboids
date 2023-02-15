package xpoke.space.cuboids.events.customEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import xpoke.space.cuboids.objects.Cuboid;

public class PlayerCuboidEnterEvent extends Event {
    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
    private static final HandlerList handlerList = new HandlerList();

    private final Player player;
    private final Cuboid cuboid;

    public PlayerCuboidEnterEvent(Player player, Cuboid cuboid) {
        this.player = player;
        this.cuboid = cuboid;
    }

    public Player getPlayer() {
        return player;
    }

    public Cuboid getCuboid() {
        return cuboid;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}