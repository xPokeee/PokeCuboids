package xpoke.space.cuboids.tasks;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import xpoke.space.cuboids.managers.*;

public class CuboidInviteTask extends BukkitRunnable {

    private final Player sender;

    private final Player receiver;

    public CuboidInviteTask(Player sender, Player receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    private int timer = 60;
    public void run() {
        if(!PlayerManager.isInvitedBy(receiver.getName(), sender.getName())){
            cancel();
            return;
        }
        if (timer == 0) {
            PlayerManager.deleteInviteFromTo(sender.getName(), receiver.getName());
            receiver.sendMessage(MessageManager.INVITE_FROM_EXPIRED.replace("%player%", sender.getName()));
            sender.sendMessage(MessageManager.INVITE_TO_EXPIRED.replace("%player%", receiver.getName()));
            cancel();
            return;
        }
        timer--;
    }

}
