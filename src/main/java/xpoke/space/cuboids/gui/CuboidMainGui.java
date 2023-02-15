package xpoke.space.cuboids.gui;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.utils.*;

public class CuboidMainGui {

    public CuboidMainGui(Player p){
        Inventory inv = Bukkit.createInventory(null, 36, MessageManager.CUBOID_MAIN_GUI_NAME);
        ItemStack teleportItem = ItemUtil.createItem(Material.ENDER_PEARL, ColorUtil.hexColor("&7Teleportacja na działkę"));
        ItemStack headBlock = ItemUtil.createSkull(p, ColorUtil.hexColor("&7Zarządzaj ustawieniami"));
        ItemStack upgradeItem = ItemUtil.createItem(Material.ENCHANTED_GOLDEN_APPLE, ColorUtil.hexColor("&7Ulepszenia działki"));
        ItemStack infoItem = ItemUtil.createItem(Material.PAPER, ColorUtil.hexColor("&7Informacje o działce"));
        ItemStack deleteItem = ItemUtil.createItem(Material.BARRIER, ColorUtil.hexColor("&cUsuń działkę!"));
        ItemStack orange_glass = ItemUtil.createItem(Material.ORANGE_STAINED_GLASS_PANE, MessageManager.GUI_ORANGE_GLASS_NAME);
        ItemStack yellow_glass = ItemUtil.createItem(Material.YELLOW_STAINED_GLASS_PANE, MessageManager.GUI_YELLOW_GLASS_NAME);
        ItemStack closeItem = ItemUtil.createItem(Material.RED_CONCRETE, MessageManager.GUI_CLOSE_ITEM_NAME);

        inv.setItem(0, orange_glass);
        inv.setItem(8, orange_glass);
        inv.setItem(10, orange_glass);
        inv.setItem(16, orange_glass);
        inv.setItem(19, orange_glass);
        inv.setItem(25, orange_glass);
        inv.setItem(27, orange_glass);
        inv.setItem(35, closeItem);
        inv.setItem(13, headBlock);
        inv.setItem(11, teleportItem);
        inv.setItem(15, upgradeItem);
        inv.setItem(21, deleteItem);
        inv.setItem(23, infoItem);
        for (int index = 0; index<=35 ;index++)
        {
            if(inv.getItem(index) == null){
                inv.setItem(index, yellow_glass);
            }
        }
        p.openInventory(inv);
    }

}
