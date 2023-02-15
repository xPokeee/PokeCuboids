package xpoke.space.cuboids.events.playerListeners;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import xpoke.space.cuboids.Main;
import xpoke.space.cuboids.gui.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.Cuboid;
import xpoke.space.cuboids.utils.*;

import java.util.*;

public class InventoryClickEvent implements Listener {

    private final Main instance = Main.getInstance();

    @EventHandler
    public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent e){
        if(e.getCurrentItem() == null) return;
        Player p = (Player) e.getWhoClicked();
        Cuboid cuboid = CuboidManager.getPlayerCuboid(p.getName());
        if(cuboid == null){
            return;
        }
        int nextCuboidLevel = cuboid.getCuboidLevel()+1;
        int nextCuboidLevelCost = DataManager.getLevelCost(nextCuboidLevel);
        if(e.getView().getTitle().equalsIgnoreCase(MessageManager.CUBOID_INFO_GUI_NAME)){
            if(Objects.equals(e.getClickedInventory(), p.getInventory())){
                e.setCancelled(true);
                return;
            }
            String itemName = Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getDisplayName();
            if(itemName.equalsIgnoreCase(MessageManager.GUI_CLOSE_ITEM_NAME)){
                e.setCancelled(true);
                p.closeInventory();
                return;
            }
            if(itemName.equalsIgnoreCase(MessageManager.GUI_BACK_ITEM_NAME)){
                e.setCancelled(true);
                new CuboidMainGui(p);
                return;
            }
            e.setCancelled(true);
        }
        if(e.getView().getTitle().equalsIgnoreCase(MessageManager.CUBOID_UPGRADE_GUI_NAME)){
            if(Objects.equals(e.getClickedInventory(), p.getInventory())){
                e.setCancelled(true);
                return;
            }
            String itemName = Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getDisplayName();
            if(itemName.equalsIgnoreCase(MessageManager.GUI_CLOSE_ITEM_NAME)){
                e.setCancelled(true);
                p.closeInventory();
                return;
            }
            if(itemName.equalsIgnoreCase(MessageManager.GUI_BACK_ITEM_NAME)){
                e.setCancelled(true);
                p.closeInventory();
                new CuboidMainGui(p);
                return;
            }
            if(e.getCurrentItem().getType() == Material.GREEN_CONCRETE){
                e.setCancelled(true);
                p.closeInventory();
                if(!instance.getEcon().has(p, nextCuboidLevelCost)){
                    p.sendMessage(ColorUtil.hexColor("&cNie stać Cię na ulepszenie działki!"));
                    return;
                }
                double currentPlayersBalance = instance.getEcon().getBalance(p);
                double difference = currentPlayersBalance - (double) nextCuboidLevelCost;

                if(difference > 0){
                    instance.getEcon().depositPlayer(p, difference);
                } else {
                    instance.getEcon().withdrawPlayer(p, -difference);
                }
                p.sendMessage(ColorUtil.hexColor("&aSukces! Ulepszono działkę na poziom &6" + nextCuboidLevel));
                CuboidManager.setCuboidLevel(nextCuboidLevel, cuboid, cuboid.getCuboidLocation(cuboid));
                return;
            }
            e.setCancelled(true);
        }
        if(e.getView().getTitle().equalsIgnoreCase(MessageManager.CUBOID_MAIN_GUI_NAME)){
            if(Objects.equals(e.getClickedInventory(), p.getInventory())){
                e.setCancelled(true);
                return;
            }
            String itemName = Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getDisplayName();
            if(itemName.equalsIgnoreCase(MessageManager.GUI_CLOSE_ITEM_NAME)){
                e.setCancelled(true);
                p.closeInventory();
                return;
            }
            if(Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.YELLOW_STAINED_GLASS_PANE) || e.getCurrentItem().getType().equals(Material.ORANGE_STAINED_GLASS_PANE)) {
                e.setCancelled(true);
                return;
            }

            switch (e.getCurrentItem().getType()) {
                case ENDER_PEARL -> {
                    if(!instance.getEcon().has(p, DataManager.getTeleportCost())){
                        e.setCancelled(true);
                        p.closeInventory();
                        p.sendMessage(MessageManager.NO_MONEY_FOR_TP
                                .replace("%money%", String.valueOf(DataManager.getTeleportCost())));
                        return;
                    }
                    e.setCancelled(true);
                    p.closeInventory();
                    double targetBalance = 5;
                    double currentPlayersBalance = instance.getEcon().getBalance(p);
                    double difference = currentPlayersBalance - targetBalance;

                    if(difference > 0){
                        instance.getEcon().depositPlayer(p, difference);
                    } else {
                        instance.getEcon().withdrawPlayer(p, -difference);
                    }
                    Location loc = new Location(Bukkit.getWorld(cuboid.getCuboidWorld()), cuboid.getCuboid_x()+0.5, cuboid.getCuboid_y()+1, cuboid.getCuboid_z()+0.5);
                    p.teleport(loc);
                    p.sendMessage(MessageManager.TP_SUCCESS);
                }
                case PLAYER_HEAD -> {
                    e.setCancelled(true);
                    new CuboidSettingsGui(p, cuboid);
                }
                case PAPER -> {
                    e.setCancelled(true);
                    new CuboidInfoGui(p, cuboid);
                }
                case BARRIER -> {
                    e.setCancelled(true);
                    p.closeInventory();
                    p.sendTitle(MessageManager.CUBOID_REMOVE_TITLE, MessageManager.CUBOID_REMOVE_SUBTITLE, 10, 40, 10);
                    Objects.requireNonNull(Bukkit.getWorld(cuboid.getCuboidWorld())).getBlockAt(cuboid.getCuboidLocation(cuboid)).setType(Material.AIR);
                    Objects.requireNonNull(cuboid.getCuboidLocation(cuboid).getWorld()).dropItemNaturally(cuboid.getCuboidLocation(cuboid), ItemUtil.getCuboidBlock());
                    CuboidManager.deleteCuboid(cuboid);
                }
                case ENCHANTED_GOLDEN_APPLE -> {
                    e.setCancelled(true);
                    p.closeInventory();
                    if(cuboid.getCuboidLevel() < 5 && cuboid.getCuboidLevel() >= 1){
                        new CuboidUpgradeGui(p, cuboid);
                    } else {
                        new CuboidMaxLevelGui(p, cuboid);
                    }
                }
            }

        }
        if(e.getView().getTitle().equalsIgnoreCase(MessageManager.CUBOID_SETTINGS_GUI_NAME)){
            if(Objects.equals(e.getClickedInventory(), p.getInventory())){
                e.setCancelled(true);
                return;
            }
            String itemName = "";
            if(e.getCurrentItem().getItemMeta() != null) {
                e.getCurrentItem().getItemMeta().getDisplayName();
                itemName = e.getCurrentItem().getItemMeta().getDisplayName();
            }
            if(itemName.equalsIgnoreCase(MessageManager.GUI_CLOSE_ITEM_NAME)){
                e.setCancelled(true);
                p.closeInventory();
                return;
            }
            if(itemName.equalsIgnoreCase(MessageManager.GUI_BACK_ITEM_NAME)){
                e.setCancelled(true);
                new CuboidMainGui(p);
                return;
            }
            if(Objects.requireNonNull(e.getCurrentItem()).getType().equals(Material.YELLOW_STAINED_GLASS_PANE) || e.getCurrentItem().getType().equals(Material.ORANGE_STAINED_GLASS_PANE)) {
                e.setCancelled(true);
                return;
            }

            switch (e.getCurrentItem().getType()) {
                case LEATHER_BOOTS ->
                        cuboid.setCuboidEnter(!cuboid.isCuboidEnter());
                case NETHERITE_SWORD ->
                        cuboid.setPvp(!cuboid.isPvp());
                case OAK_FENCE_GATE ->
                        cuboid.setInteraction(!cuboid.isInteraction());
                case GRASS_BLOCK ->
                        cuboid.setBlockPlace(!cuboid.isBlockPlace());
                case IRON_PICKAXE ->
                        cuboid.setBlockBreak(!cuboid.isBlockBreak());
                case OAK_BOAT ->
                        cuboid.setVehiclesUse(!cuboid.isVehiclesUse());
                case REDSTONE_TORCH ->
                        cuboid.setMechanismsUse(!cuboid.isMechanismsUse());
                case IRON_SWORD ->
                        cuboid.setMobsDamage(!cuboid.isMobsDamage());
                case ENDER_PEARL ->
                        cuboid.setPearlsUse(!cuboid.isPearlsUse());
                case CHEST ->
                        cuboid.setStorageUse(!cuboid.isStorageUse());
                case DROPPER ->
                        cuboid.setItemDrop(!cuboid.isItemDrop());
                case HOPPER ->
                        cuboid.setItemPickUp(!cuboid.isItemPickUp());
                case OBSIDIAN ->
                        cuboid.setPortalsUse(!cuboid.isPortalsUse());
                case RED_BED ->
                        cuboid.setBedsUse(!cuboid.isBedsUse());
            }
            e.setCancelled(true);
            new CuboidSettingsGui(p, cuboid);
        }
    }

}
