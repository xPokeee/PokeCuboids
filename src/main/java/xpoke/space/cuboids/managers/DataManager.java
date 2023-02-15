package xpoke.space.cuboids.managers;

import xpoke.space.cuboids.Main;

import java.util.*;

public class DataManager {

    private final static List<String> storageBlocks = new ArrayList<>();
    private final static Map<Integer, Integer> cuboidSizes = new HashMap<>();
    private final static List<String> allowedWorlds = new ArrayList<>();
    private final static Map<Integer, Integer> upgradeCosts = new HashMap<>();

    private static int teleportCost = 0;

    private final static Main instance = Main.getInstance();

    public static void loadData(){
        allowedWorlds.addAll(instance.getFileManager().getConfigurationFile().getStringList("cuboids.allowedWorlds"));
        for(String s : Objects.requireNonNull(instance.getFileManager().getConfigurationFile().getConfigurationSection("cuboids.levels")).getKeys(false)){
            int level = Integer.parseInt(s.replace("level_", ""));
            int size = instance.getFileManager().getConfigurationFile().getInt("cuboids.levels.level_" + level + ".size");
            int cost = instance.getFileManager().getConfigurationFile().getInt("cuboids.levels.level_" + level + ".upgradeCost");

            cuboidSizes.put(level, size);
            upgradeCosts.put(level, cost);
        }
        storageBlocks.addAll(instance.getFileManager().getConfigurationFile().getStringList("cuboids.storageBlocks"));
        teleportCost = instance.getFileManager().getConfigurationFile().getInt("cuboids.teleportCost");

    }

    public static void clearData(){
        storageBlocks.clear();
        cuboidSizes.clear();
        allowedWorlds.clear();
        upgradeCosts.clear();
    }

    public static List<String> getStorageBlocks(){
        return storageBlocks;
    }

    public static List<String> getAllowedWorlds(){
        return allowedWorlds;
    }

    public static int getCuboidSize(int level){
        return cuboidSizes.get(level);
    }

    public static int getLevelCost(int level){
        return upgradeCosts.get(level);
    }

    public static int getTeleportCost(){
        return teleportCost;
    }

}
