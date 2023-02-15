package xpoke.space.cuboids.gui;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import xpoke.space.cuboids.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.*;
import xpoke.space.cuboids.utils.*;

import java.util.*;

public class CuboidSettingsGui {

    private final Main instance = Main.getInstance();

    public CuboidSettingsGui(Player p, Cuboid cuboid) {
        String invName = MessageManager.CUBOID_SETTINGS_GUI_NAME;
        Inventory inv = Bukkit.createInventory(null, 54, invName);
        List<String> pvpItemLore = new ArrayList<>();
        pvpItemLore.add(" ");
        pvpItemLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona, każdy będzie"));
        pvpItemLore.add(ColorUtil.hexColor("&7mógł bić innych graczy na Twojej działce!"));
        pvpItemLore.add(ColorUtil.hexColor("&7Gdy opcja jest wyłączona, nikt nie będzie"));
        pvpItemLore.add(ColorUtil.hexColor("&7mógł się na niej bić!"));
        pvpItemLore.add(" ");
        pvpItemLore.add(" ");
        if (cuboid.isPvp()) {
            pvpItemLore.set(6, ColorUtil.hexColor("&7Status: &aWŁĄCZONE"));
        } else {
            pvpItemLore.set(6, ColorUtil.hexColor("&7Status: &CWYŁĄCZONE"));
        }
        ItemStack pvpItem = ItemUtil.createItem(Material.NETHERITE_SWORD, ColorUtil.hexColor("&aPVP na działce"), pvpItemLore);

        List<String> walkItemLore = new ArrayList<>();
        walkItemLore.add(" ");
        walkItemLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla wszystkich"));
        walkItemLore.add(ColorUtil.hexColor("&7graczy, każdy będzie mógł wejść na"));
        walkItemLore.add(ColorUtil.hexColor("&7Twoją działkę!"));
        walkItemLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla członków"));
        walkItemLore.add(ColorUtil.hexColor("&7działki, tylko osoby dodane do działki"));
        walkItemLore.add(ColorUtil.hexColor("&7będą mogły na nią wejść!"));
        walkItemLore.add(" ");
        walkItemLore.add(" ");
        if (cuboid.isCuboidEnter()) {
            walkItemLore.set(8, ColorUtil.hexColor("&7Status: &6WSZYSCY"));
        } else {
            walkItemLore.set(8, ColorUtil.hexColor("&7Status: &6CZŁONKOWIE"));
        }
        ItemStack walkItem = ItemUtil.createItem(Material.LEATHER_BOOTS, ColorUtil.hexColor("&aWchodzenie na działkę"), walkItemLore);

        List<String> interactItemLore = new ArrayList<>();
        interactItemLore.add(" ");
        interactItemLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla wszystkich"));
        interactItemLore.add(ColorUtil.hexColor("&7graczy, każdy będzie mógł prowadzić"));
        interactItemLore.add(ColorUtil.hexColor("&7interakcje z blokami na Twojej działce!"));
        interactItemLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla członków"));
        interactItemLore.add(ColorUtil.hexColor("&7działki, tylko osoby dodane do działki"));
        interactItemLore.add(ColorUtil.hexColor("&7będą mogły prowadzić interakcje z blokami!"));
        interactItemLore.add(" ");
        interactItemLore.add(" ");
        interactItemLore.add(" ");
        if (cuboid.isInteraction()) {
            interactItemLore.set(8, ColorUtil.hexColor("&7Status: &6WSZYSCY"));
        } else {
            interactItemLore.set(8, ColorUtil.hexColor("&7Status: &6CZŁONKOWIE"));
        }
        ItemStack interactItem = ItemUtil.createItem(Material.OAK_FENCE_GATE, ColorUtil.hexColor("&aInterakcja na działce"), interactItemLore);

        List<String> blockPlaceLore = new ArrayList<>();
        blockPlaceLore.add(" ");
        blockPlaceLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla wszystkich"));
        blockPlaceLore.add(ColorUtil.hexColor("&7graczy, każdy będzie mógł stawiać bloki"));
        blockPlaceLore.add(ColorUtil.hexColor("&7na Twojej działce!"));
        blockPlaceLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla członków"));
        blockPlaceLore.add(ColorUtil.hexColor("&7działki, tylko osoby dodane do działki"));
        blockPlaceLore.add(ColorUtil.hexColor("&7będą mogły na niej stawiać bloki!"));
        blockPlaceLore.add(" ");
        blockPlaceLore.add(" ");
        blockPlaceLore.add(" ");
        if (cuboid.isBlockPlace()) {
            blockPlaceLore.set(8, ColorUtil.hexColor("&7Status: &6WSZYSCY"));
        } else {
            blockPlaceLore.set(8, ColorUtil.hexColor("&7Status: &6CZŁONKOWIE"));
        }
        ItemStack blockPlaceItem = ItemUtil.createItem(Material.GRASS_BLOCK, ColorUtil.hexColor("&aStawianie bloków na działce"), blockPlaceLore);

        List<String> blockBreakLore = new ArrayList<>();
        blockBreakLore.add(" ");
        blockBreakLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla wszystkich"));
        blockBreakLore.add(ColorUtil.hexColor("&7graczy, każdy będzie mógł niszczyć bloki"));
        blockBreakLore.add(ColorUtil.hexColor("&7na Twojej działce!"));
        blockBreakLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla członków"));
        blockBreakLore.add(ColorUtil.hexColor("&7działki, tylko osoby dodane do działki"));
        blockBreakLore.add(ColorUtil.hexColor("&7będą mogły na niej niszczyć bloki!"));
        blockBreakLore.add(" ");
        blockBreakLore.add(" ");
        if (cuboid.isBlockBreak()) {
            blockBreakLore.set(8, ColorUtil.hexColor("&7Status: &6WSZYSCY"));
        } else {
            blockBreakLore.set(8, ColorUtil.hexColor("&7Status: &6CZŁONKOWIE"));
        }
        ItemStack blockBreakItem = ItemUtil.createItem(Material.IRON_PICKAXE, ColorUtil.hexColor("&aNiszczenie bloków na działce"), blockBreakLore);

        List<String> useVehiclesLore = new ArrayList<>();
        useVehiclesLore.add(" ");
        useVehiclesLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla wszystkich"));
        useVehiclesLore.add(ColorUtil.hexColor("&7graczy, każdy będzie mógł korzystać z"));
        useVehiclesLore.add(ColorUtil.hexColor("&7pojazdów na Twojej działce!"));
        useVehiclesLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla członków"));
        useVehiclesLore.add(ColorUtil.hexColor("&7działki, tylko osoby dodane do działki"));
        useVehiclesLore.add(ColorUtil.hexColor("&7będą mogły korzystać z pojazdów na niej!"));
        useVehiclesLore.add(" ");
        useVehiclesLore.add(" ");
        useVehiclesLore.add(" ");
        if (cuboid.isVehiclesUse()) {
            useVehiclesLore.set(8, ColorUtil.hexColor("&7Status: &6WSZYSCY"));
        } else {
            useVehiclesLore.set(8, ColorUtil.hexColor("&7Status: &6CZŁONKOWIE"));
        }
        ItemStack useVehiclesItem = ItemUtil.createItem(Material.OAK_BOAT, ColorUtil.hexColor("&aUżywanie pojazdów"), useVehiclesLore);

        List<String> useMechanismsLore = new ArrayList<>();
        useMechanismsLore.add(" ");
        useMechanismsLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla wszystkich"));
        useMechanismsLore.add(ColorUtil.hexColor("&7graczy, każdy będzie mógł korzystać z"));
        useMechanismsLore.add(ColorUtil.hexColor("&7mechanizmów na Twojej działce!"));
        useMechanismsLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla członków"));
        useMechanismsLore.add(ColorUtil.hexColor("&7działki, tylko osoby dodane do działki"));
        useMechanismsLore.add(ColorUtil.hexColor("&7będą mogły na niej korzystać z mechanizmów!"));
        useMechanismsLore.add(" ");
        useMechanismsLore.add(" ");
        useMechanismsLore.add(" ");
        if (cuboid.isMechanismsUse()) {
            useMechanismsLore.set(8, ColorUtil.hexColor("&7Status: &6WSZYSCY"));
        } else {
            useMechanismsLore.set(8, ColorUtil.hexColor("&7Status: &6CZŁONKOWIE"));
        }
        ItemStack mechanismsItem = ItemUtil.createItem(Material.REDSTONE_TORCH, ColorUtil.hexColor("&aUżywanie mechanizmów"), useMechanismsLore);

        List<String> mobsHurtLore = new ArrayList<>();
        mobsHurtLore.add(" ");
        mobsHurtLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla wszystkich"));
        mobsHurtLore.add(ColorUtil.hexColor("&7graczy, każdy będzie mógł bić zwierzęta"));
        mobsHurtLore.add(ColorUtil.hexColor("&7na Twojej działce!"));
        mobsHurtLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla członków"));
        mobsHurtLore.add(ColorUtil.hexColor("&7działki, tylko osoby dodane do działki"));
        mobsHurtLore.add(ColorUtil.hexColor("&7będą mogły bić zwierzęta na niej!"));
        mobsHurtLore.add(" ");
        mobsHurtLore.add(" ");
        if (cuboid.isMobsDamage()) {
            mobsHurtLore.set(8, ColorUtil.hexColor("&7Status: &6WSZYSCY"));
        } else {
            mobsHurtLore.set(8, ColorUtil.hexColor("&7Status: &6CZŁONKOWIE"));
        }
        ItemStack mobsHurtItem = ItemUtil.createItem(Material.IRON_SWORD, ColorUtil.hexColor("&aBicie mobów"), mobsHurtLore);

        List<String> pearlsUseLore = new ArrayList<>();
        pearlsUseLore.add(" ");
        pearlsUseLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla wszystkich"));
        pearlsUseLore.add(ColorUtil.hexColor("&7graczy, każdy będzie mógł używać perły"));
        pearlsUseLore.add(ColorUtil.hexColor("&7na Twojej działce!"));
        pearlsUseLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla członków"));
        pearlsUseLore.add(ColorUtil.hexColor("&7działki, tylko osoby dodane do działki"));
        pearlsUseLore.add(ColorUtil.hexColor("&7będą mogły używać perły na niej!"));
        pearlsUseLore.add(" ");
        pearlsUseLore.add(" ");
        if (cuboid.isPearlsUse()) {
            pearlsUseLore.set(8, ColorUtil.hexColor("&7Status: &6WSZYSCY"));
        } else {
            pearlsUseLore.set(8, ColorUtil.hexColor("&7Status: &6CZŁONKOWIE"));
        }
        ItemStack pearlsUseItem = ItemUtil.createItem(Material.ENDER_PEARL, ColorUtil.hexColor("&aUżywanie pereł"), pearlsUseLore);

        List<String> chestsUseLore = new ArrayList<>();
        chestsUseLore.add(" ");
        chestsUseLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla wszystkich"));
        chestsUseLore.add(ColorUtil.hexColor("&7graczy, każdy będzie mógł korzystać ze"));
        chestsUseLore.add(ColorUtil.hexColor("&7skrzynek na Twojej działce!"));
        chestsUseLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla członków"));
        chestsUseLore.add(ColorUtil.hexColor("&7działki, tylko osoby dodane do działki"));
        chestsUseLore.add(ColorUtil.hexColor("&7będą mogły otwierać skrzynki na niej!"));
        chestsUseLore.add(" ");
        chestsUseLore.add(" ");
        chestsUseLore.add(" ");
        if (cuboid.isStorageUse()) {
            chestsUseLore.set(8, ColorUtil.hexColor("&7Status: &6WSZYSCY"));
        } else {
            chestsUseLore.set(8, ColorUtil.hexColor("&7Status: &6CZŁONKOWIE"));
        }
        ItemStack chestsUseItem = ItemUtil.createItem(Material.CHEST, ColorUtil.hexColor("&aKorzystanie ze skrzynek"), chestsUseLore);

        List<String> itemDropLore = new ArrayList<>();
        itemDropLore.add(" ");
        itemDropLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla wszystkich"));
        itemDropLore.add(ColorUtil.hexColor("&7graczy, każdy będzie mógł wyrzucać itemy"));
        itemDropLore.add(ColorUtil.hexColor("&7na Twojej działce!"));
        itemDropLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla członków"));
        itemDropLore.add(ColorUtil.hexColor("&7działki, tylko osoby dodane do działki"));
        itemDropLore.add(ColorUtil.hexColor("&7będą mogły wyrzucać itemy na niej!"));
        itemDropLore.add(" ");
        itemDropLore.add(" ");
        itemDropLore.add(" ");
        if (cuboid.isItemDrop()) {
            itemDropLore.set(8, ColorUtil.hexColor("&7Status: &6WSZYSCY"));
        } else {
            itemDropLore.set(8, ColorUtil.hexColor("&7Status: &6CZŁONKOWIE"));
        }
        ItemStack itemDropItem = ItemUtil.createItem(Material.DROPPER, ColorUtil.hexColor("&aWyrzucanie itemów"), itemDropLore);

        List<String> itemPickUpLore = new ArrayList<>();
        itemPickUpLore.add(" ");
        itemPickUpLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla wszystkich"));
        itemPickUpLore.add(ColorUtil.hexColor("&7graczy, każdy będzie mógł podnosić itemy"));
        itemPickUpLore.add(ColorUtil.hexColor("&7na Twojej działce!"));
        itemPickUpLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla członków"));
        itemPickUpLore.add(ColorUtil.hexColor("&7działki, tylko osoby dodane do działki"));
        itemPickUpLore.add(ColorUtil.hexColor("&7będą mogły podnosić itemy na niej!"));
        itemPickUpLore.add(" ");
        itemPickUpLore.add(" ");
        itemPickUpLore.add(" ");
        if (cuboid.isItemPickUp()) {
            itemPickUpLore.set(8, ColorUtil.hexColor("&7Status: &6WSZYSCY"));
        } else {
            itemPickUpLore.set(8, ColorUtil.hexColor("&7Status: &6CZŁONKOWIE"));
        }
        ItemStack itemPickUpItem = ItemUtil.createItem(Material.HOPPER, ColorUtil.hexColor("&aPodnoszenie itemów"), itemPickUpLore);

        List<String> portalUseLore = new ArrayList<>();
        portalUseLore.add(" ");
        portalUseLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla wszystkich"));
        portalUseLore.add(ColorUtil.hexColor("&7graczy, każdy będzie mógł używać portali"));
        portalUseLore.add(ColorUtil.hexColor("&7na Twojej działce!"));
        portalUseLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla członków"));
        portalUseLore.add(ColorUtil.hexColor("&7działki, tylko osoby dodane do działki"));
        portalUseLore.add(ColorUtil.hexColor("&7będą mogły używać portali na niej!"));
        portalUseLore.add(" ");
        portalUseLore.add(" ");
        portalUseLore.add(" ");
        if (cuboid.isPortalsUse()) {
            portalUseLore.set(8, ColorUtil.hexColor("&7Status: &6WSZYSCY"));
        } else {
            portalUseLore.set(8, ColorUtil.hexColor("&7Status: &6CZŁONKOWIE"));
        }
        ItemStack portalUseItem = ItemUtil.createItem(Material.OBSIDIAN, ColorUtil.hexColor("&aUżywanie portali"), portalUseLore);

        List<String> bedLore = new ArrayList<>();
        bedLore.add(" ");
        bedLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla wszystkich"));
        bedLore.add(ColorUtil.hexColor("&7graczy, każdy będzie mógł korzystać z łóżek"));
        bedLore.add(ColorUtil.hexColor("&7na Twojej działce!"));
        bedLore.add(ColorUtil.hexColor("&7Gdy opcja jest włączona dla członków"));
        bedLore.add(ColorUtil.hexColor("&7działki, tylko osoby dodane do działki"));
        bedLore.add(ColorUtil.hexColor("&7będą mogły korzystać z łóżek na niej!"));
        bedLore.add(" ");
        bedLore.add(" ");
        bedLore.add(" ");
        if (cuboid.isBedsUse()) {
            bedLore.set(8, ColorUtil.hexColor("&7Status: &6WSZYSCY"));
        } else {
            bedLore.set(8, ColorUtil.hexColor("&7Status: &6CZŁONKOWIE"));
        }
        ItemStack bedItem = ItemUtil.createItem(Material.RED_BED, ColorUtil.hexColor("&aKorzystanie z łóżek"), bedLore);


        ItemStack orange_glass = ItemUtil.createItem(Material.ORANGE_STAINED_GLASS_PANE, MessageManager.GUI_ORANGE_GLASS_NAME);
        ItemStack yellow_glass = ItemUtil.createItem(Material.YELLOW_STAINED_GLASS_PANE, MessageManager.GUI_YELLOW_GLASS_NAME);
        ItemStack closeItem = ItemUtil.createItem(Material.RED_CONCRETE, MessageManager.GUI_CLOSE_ITEM_NAME);
        ItemStack returnItem = ItemUtil.createItem(Material.RED_CONCRETE, MessageManager.GUI_BACK_ITEM_NAME);

        inv.setItem(19, pvpItem);
        inv.setItem(20, walkItem);
        inv.setItem(21, interactItem);
        inv.setItem(22, blockPlaceItem);
        inv.setItem(23, blockBreakItem);
        inv.setItem(24, useVehiclesItem);
        inv.setItem(25, mechanismsItem);
        inv.setItem(28, mobsHurtItem);
        inv.setItem(29, pearlsUseItem);
        inv.setItem(30, chestsUseItem);
        inv.setItem(31, itemDropItem);
        inv.setItem(32, itemPickUpItem);
        inv.setItem(33, portalUseItem);
        inv.setItem(34, bedItem);


        inv.setItem(0, orange_glass);
        inv.setItem(3, orange_glass);
        inv.setItem(4, orange_glass);
        inv.setItem(5, orange_glass);
        inv.setItem(8, orange_glass);
        inv.setItem(11, orange_glass);
        inv.setItem(12, orange_glass);
        inv.setItem(14, orange_glass);
        inv.setItem(15, orange_glass);
        inv.setItem(38, orange_glass);
        inv.setItem(39, orange_glass);
        inv.setItem(41, orange_glass);
        inv.setItem(42, orange_glass);
        inv.setItem(45, returnItem);
        inv.setItem(48, orange_glass);
        inv.setItem(49, orange_glass);
        inv.setItem(50, orange_glass);
        inv.setItem(53, closeItem);

        for (int index = 0; index<=53 ;index++)
        {
            if(inv.getItem(index) == null){
                inv.setItem(index, yellow_glass);
            }
        }
        p.openInventory(inv);


    }

}
