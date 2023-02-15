package xpoke.space.cuboids.events.playerListeners;

import org.bukkit.event.*;
import xpoke.space.cuboids.managers.*;

public class InventoryDragEvent implements Listener {

    @EventHandler
    public void onInventoryDrag(org.bukkit.event.inventory.InventoryDragEvent e){
        String invName = e.getView().getTitle();
        if(invName.equalsIgnoreCase(MessageManager.CUBOID_INFO_GUI_NAME) ||
        invName.equalsIgnoreCase(MessageManager.CUBOID_MAIN_GUI_NAME) ||
                invName.equalsIgnoreCase(MessageManager.CUBOID_SETTINGS_GUI_NAME) ||
                invName.equalsIgnoreCase(MessageManager.CUBOID_UPGRADE_GUI_NAME)){
            e.setCancelled(true);
        }
    }

}
