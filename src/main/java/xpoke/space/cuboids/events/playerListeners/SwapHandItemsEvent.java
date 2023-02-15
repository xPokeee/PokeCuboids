package xpoke.space.cuboids.events.playerListeners;

import org.bukkit.event.*;
import org.bukkit.event.player.*;
import xpoke.space.cuboids.managers.*;

public class SwapHandItemsEvent implements Listener {

    @EventHandler
    public void onItemMove(PlayerSwapHandItemsEvent e){
        String invName = e.getPlayer().getOpenInventory().getTitle();
        if(invName.equalsIgnoreCase(MessageManager.CUBOID_INFO_GUI_NAME) ||
                invName.equalsIgnoreCase(MessageManager.CUBOID_MAIN_GUI_NAME) ||
                invName.equalsIgnoreCase(MessageManager.CUBOID_SETTINGS_GUI_NAME) ||
                invName.equalsIgnoreCase(MessageManager.CUBOID_UPGRADE_GUI_NAME)){
            e.setCancelled(true);
        }
    }

}
