package xpoke.space.cuboids.gui;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.*;
import xpoke.space.cuboids.utils.*;

import java.util.*;

public class CuboidInfoGui {

    public CuboidInfoGui(Player p, Cuboid cuboid){
        Location cuboidLoc = cuboid.getCuboidLocation(cuboid);
        int cuboidSize = DataManager.getCuboidSize(cuboid.getCuboidLevel());
        Inventory inv = Bukkit.createInventory(null, 27, MessageManager.CUBOID_INFO_GUI_NAME);
        List<String> infoLore = new ArrayList<>();
        infoLore.add(" ");
        infoLore.add(ColorUtil.hexColor("  &8&m-------------------&r  &f"));
        infoLore.add(" ");
        infoLore.add(ColorUtil.hexColor("    &7Lokalizacja działki:   &f"));
        infoLore.add(ColorUtil.hexColor(String.format("           &8(&e%s&8)  &f",
                Objects.requireNonNull(cuboid.getCuboidWorld()))));
        infoLore.add(ColorUtil.hexColor(String.format("          &7X: &6%s  &f", cuboidLoc.getBlockX()+0.5)));
        infoLore.add(ColorUtil.hexColor(String.format("          &7Y: &6%s  &f", cuboidLoc.getBlockY())));
        infoLore.add(ColorUtil.hexColor(String.format("          &7Z: &6%s  &f", cuboidLoc.getBlockZ()+0.5)));
        infoLore.add(" ");
        infoLore.add(ColorUtil.hexColor("  &8&m-------------------&r  &f"));
        infoLore.add(" ");
        ItemStack locItem = ItemUtil.createItem(Material.COMPASS, ColorUtil.hexColor("&7        Lokalizacja"), infoLore);



        List<String> levelLore = new ArrayList<>();
        levelLore.add(" ");
        levelLore.add(ColorUtil.hexColor("  &8&m------------&r  &f"));
        levelLore.add(" ");
        levelLore.add(ColorUtil.hexColor(String.format("     &7Poziom: &b%s", CuboidManager.getCuboidLevel(cuboid))));
        levelLore.add(ColorUtil.hexColor(String.format("      &a%s&8x&a%s", cuboidSize, cuboidSize)));
        levelLore.add(" ");
        levelLore.add(ColorUtil.hexColor("  &8&m------------&r  &f"));
        levelLore.add(" ");
        ItemStack levelItem = ItemUtil.createItem(Material.ELYTRA, ColorUtil.hexColor("&7  Poziom kuboidy  "), levelLore);


        List<String> ownerItemLore = new ArrayList<>();
        ownerItemLore.add(" ");
        ownerItemLore.add(ColorUtil.hexColor("&r  &8&m---------------&r  &r"));
        ownerItemLore.add(" ");
        ownerItemLore.add(ColorUtil.hexColor(String.format("    &7Nazwa: &6%s", cuboid.getCuboidOwner())));
        ownerItemLore.add(" ");
        ownerItemLore.add(ColorUtil.hexColor("&r  &8&m---------------&r  &r"));
        ownerItemLore.add(" ");
        ItemStack ownerItem = ItemUtil.createSkull(p, ColorUtil.hexColor("&7       Właściciel     "), ownerItemLore);


        List<String> membersLore = new ArrayList<>();
        membersLore.add(" ");
        membersLore.add(ColorUtil.hexColor("&r  &8&m--------------------&r  &r"));
        membersLore.add(" ");
        membersLore.add(ColorUtil.hexColor(String.format("     &7Liczba członków: &b%s", cuboid.getMembers().size())));
        membersLore.add(" ");
        membersLore.add(ColorUtil.hexColor("&r  &8&m--------------------&r  &r"));
        membersLore.add(" ");
        ItemStack membersItem = ItemUtil.createItem(Material.PAPER, ColorUtil.hexColor("&7      Członkowie działki "), membersLore);


        List<String> createDateLore = new ArrayList<>();
        createDateLore.add(" ");
        createDateLore.add(ColorUtil.hexColor("&r  &8&m---------------------------------&r  &r"));
        createDateLore.add(" ");
        createDateLore.add(ColorUtil.hexColor(String.format("    &7Data utworzenia: &a%s", cuboid.getCuboidCreateDate())));
        createDateLore.add(" ");
        createDateLore.add(ColorUtil.hexColor("&r  &8&m---------------------------------&r  &r"));
        createDateLore.add(" ");
        ItemStack createDateItem = ItemUtil.createItem(Material.CLOCK, ColorUtil.hexColor("&7           Data utworzenia działki "), createDateLore);

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


        inv.setItem(11, locItem);
        inv.setItem(12, levelItem);
        inv.setItem(13, ownerItem);
        inv.setItem(14, membersItem);
        inv.setItem(15, createDateItem);


        p.openInventory(inv);
    }

}
