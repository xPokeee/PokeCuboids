package xpoke.space.cuboids.utils;

import org.bukkit.*;
import org.bukkit.block.*;
import org.bukkit.enchantments.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import xpoke.space.cuboids.managers.*;

import java.util.*;

public class ItemUtil {

    public static ItemStack createItem(Material material, String name, List<String> itemLore){
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(name);
        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack createItem(Material material, String name, List<String> itemLore, boolean hideEnchants){
        ItemStack item = new ItemStack(material);
        item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(name);
        itemMeta.setLore(itemLore);
        if(hideEnchants) itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack createItem(Material material, String name){
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(name);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack createSkull(Player owningPlayer, String name, List<String> itemLore){
        ItemStack headBlock = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headBlockSkullMeta = (SkullMeta)headBlock.getItemMeta();
        assert headBlockSkullMeta != null;
        headBlockSkullMeta.setOwningPlayer(owningPlayer);
        headBlockSkullMeta.setDisplayName(name);
        headBlockSkullMeta.setLore(itemLore);
        headBlock.setItemMeta(headBlockSkullMeta);
        return headBlock;
    }

    public static ItemStack createSkull(Player owningPlayer, String name){
        ItemStack headBlock = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headBlockSkullMeta = (SkullMeta)headBlock.getItemMeta();
        assert headBlockSkullMeta != null;
        headBlockSkullMeta.setOwningPlayer(owningPlayer);
        headBlockSkullMeta.setDisplayName(name);
        headBlock.setItemMeta(headBlockSkullMeta);
        return headBlock;
    }


    public static ItemStack getCuboidBlock(){
        return createItem(Material.NOTE_BLOCK, MessageManager.CUBOID_NAME, MessageManager.CUBOID_LORE, true);
    }

    public static boolean isBlockCuboidBlock(Block block){
        return block.getType().equals(getCuboidBlock().getType());
    }


}
