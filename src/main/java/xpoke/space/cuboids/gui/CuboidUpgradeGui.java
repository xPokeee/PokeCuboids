package xpoke.space.cuboids.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.*;
import xpoke.space.cuboids.utils.ColorUtil;
import xpoke.space.cuboids.utils.ItemUtil;

import java.util.ArrayList;
import java.util.List;
public class CuboidUpgradeGui {

    public CuboidUpgradeGui(Player p, Cuboid cuboid){
        int cuboidLevel = cuboid.getCuboidLevel();
        int cuboidSize = DataManager.getCuboidSize(cuboid.getCuboidLevel());
        int nextCuboidLevel = cuboidLevel+1;
        int nextCuboidLevelCost = DataManager.getLevelCost(nextCuboidLevel);
        int cuboidNextLevelSize = DataManager.getCuboidSize(nextCuboidLevel);
        Inventory inv = Bukkit.createInventory(null, 27, MessageManager.CUBOID_UPGRADE_GUI_NAME);

        List<String> levelLore = new ArrayList<>();
        levelLore.add(" ");
        levelLore.add(ColorUtil.hexColor("&f  &8&m-----------------&r  &f"));
        levelLore.add(" ");
        levelLore.add(ColorUtil.hexColor(String.format("    &7Poziom działki: &b%s", CuboidManager.getCuboidLevel(cuboid))));
        levelLore.add(ColorUtil.hexColor(String.format("          &a%s&8x&a%s", cuboidSize, cuboidSize)));
        levelLore.add(" ");
        levelLore.add(ColorUtil.hexColor("&f  &8&m-----------------&r  &f"));
        levelLore.add(" ");
        ItemStack levelItem = ItemUtil.createItem(Material.ELYTRA, ColorUtil.hexColor("&7     Poziom działki  "), levelLore);


        List<String> buyLore = new ArrayList<>();
        buyLore.add(" ");
        buyLore.add(ColorUtil.hexColor("&f  &8&m--------------------&r  &f"));
        buyLore.add(" ");
        buyLore.add(ColorUtil.hexColor("    &aKliknij, aby ulepszyć!"));
        buyLore.add(ColorUtil.hexColor("        &7Koszt: " + nextCuboidLevelCost));
        buyLore.add(" ");
        buyLore.add(ColorUtil.hexColor("&f  &8&m--------------------&r  &f"));
        buyLore.add(" ");
        ItemStack buyItem = ItemUtil.createItem(Material.GREEN_CONCRETE, ColorUtil.hexColor("&7      Zakup ulepszenie    "), buyLore);

        List<String> nextLevelLore = new ArrayList<>();
        nextLevelLore.add(" ");
        nextLevelLore.add(ColorUtil.hexColor("&f  &8&m----------------------&r  &f"));
        nextLevelLore.add(" ");
        nextLevelLore.add(ColorUtil.hexColor(String.format("      &7Następny poziom: &b%s", nextCuboidLevel)));
        nextLevelLore.add(ColorUtil.hexColor(String.format("            &a%s&8x&a%s", cuboidNextLevelSize, cuboidNextLevelSize)));
        nextLevelLore.add(" ");
        nextLevelLore.add(ColorUtil.hexColor("&f  &8&m----------------------&r  &f"));
        nextLevelLore.add(" ");
        ItemStack nextLevelItem = ItemUtil.createItem(Material.PAPER, ColorUtil.hexColor("&7   Opis następnego poziomu  "), nextLevelLore);


        ItemStack orange_glass = ItemUtil.createItem(Material.ORANGE_STAINED_GLASS_PANE, MessageManager.GUI_ORANGE_GLASS_NAME);
        ItemStack yellow_glass = ItemUtil.createItem(Material.YELLOW_STAINED_GLASS_PANE, MessageManager.GUI_YELLOW_GLASS_NAME);
        ItemStack closeItem = ItemUtil.createItem(Material.RED_CONCRETE, MessageManager.GUI_CLOSE_ITEM_NAME);
        ItemStack returnItem = ItemUtil.createItem(Material.RED_CONCRETE, MessageManager.GUI_BACK_ITEM_NAME);


        inv.setItem(0, orange_glass);
        inv.setItem(3, orange_glass);
        inv.setItem(5, orange_glass);
        inv.setItem(8, orange_glass);
        inv.setItem(10, orange_glass);
        inv.setItem(16, orange_glass);
        inv.setItem(18, returnItem);
        inv.setItem(21, orange_glass);
        inv.setItem(23, orange_glass);
        inv.setItem(26, closeItem);

        for (int index = 0; index<=26 ;index++)
        {
            if(inv.getItem(index) == null){
                inv.setItem(index, yellow_glass);
            }
        }


        inv.setItem(11, levelItem);
        inv.setItem(13, buyItem);
        inv.setItem(15, nextLevelItem);


        p.openInventory(inv);
    }

}
