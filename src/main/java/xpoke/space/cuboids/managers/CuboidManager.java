package xpoke.space.cuboids.managers;

import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import xpoke.space.cuboids.Main;
import xpoke.space.cuboids.objects.Cuboid;
import xpoke.space.cuboids.utils.DateUtil;

import java.util.*;

public class CuboidManager {

    private static final Main instance = Main.getInstance();
    private static final FileConfiguration cuboidsFile = instance.getFileManager().getCuboidsFile();

    private static final List<Cuboid> cuboidsList = new ArrayList<>();

    public static List<Cuboid> getCuboids(){
        return cuboidsList;
    }

    public static void loadCuboids(){
        ConfigurationSection cuboids = instance.getFileManager().getCuboidsFile().getConfigurationSection("cuboids");
        assert cuboids != null;
        for(String s : cuboids.getKeys(false)){
            String cuboidWorld = instance.getFileManager().getCuboidsFile().getString("cuboids." + s + ".world");
            String cuboidOwner = instance.getFileManager().getCuboidsFile().getString("cuboids." + s + ".owner");
            String cuboidCreateDate = instance.getFileManager().getCuboidsFile().getString("cuboids." + s + ".createDate");
            int cuboidLevel = instance.getFileManager().getCuboidsFile().getInt("cuboids." + s + ".cuboidLevel");
            double cuboid_x = instance.getFileManager().getCuboidsFile().getDouble("cuboids." + s + ".cuboid_x");
            double cuboid_y = instance.getFileManager().getCuboidsFile().getDouble("cuboids." + s + ".cuboid_y");
            double cuboid_z = instance.getFileManager().getCuboidsFile().getDouble("cuboids." + s + ".cuboid_z");
            double cuboid_max_x = instance.getFileManager().getCuboidsFile().getDouble("cuboids." + s + ".max_x");
            double cuboid_min_x = instance.getFileManager().getCuboidsFile().getDouble("cuboids." + s + ".min_x");
            double cuboid_max_z = instance.getFileManager().getCuboidsFile().getDouble("cuboids." + s + ".max_z");
            double cuboid_min_z = instance.getFileManager().getCuboidsFile().getDouble("cuboids." + s + ".min_z");
            boolean cuboidEnter  = instance.getFileManager().getCuboidsFile().getBoolean("cuboids." + s + ".options.cuboidEnter");
            boolean cuboidPvp = instance.getFileManager().getCuboidsFile().getBoolean("cuboids." + s + ".options.pvp");
            boolean cuboidInteraction = instance.getFileManager().getCuboidsFile().getBoolean("cuboids." + s + ".options.interaction");
            boolean cuboidBlockPlace = instance.getFileManager().getCuboidsFile().getBoolean("cuboids." + s + ".options.blockPlace");
            boolean cuboidBlockBreak = instance.getFileManager().getCuboidsFile().getBoolean("cuboids." + s + ".options.blockBreak");
            boolean cuboidVehicleUse = instance.getFileManager().getCuboidsFile().getBoolean("cuboids." + s + ".options.vehiclesUse");
            boolean cuboidMechanismsUse = instance.getFileManager().getCuboidsFile().getBoolean("cuboids." + s + ".options.useMechanisms");
            boolean cuboidMobsDamage = instance.getFileManager().getCuboidsFile().getBoolean("cuboids." + s + ".options.mobsDamage");
            boolean cuboidPearlsUse = instance.getFileManager().getCuboidsFile().getBoolean("cuboids." + s + ".options.pearlsUse");
            boolean cuboidStorageUse = instance.getFileManager().getCuboidsFile().getBoolean("cuboids." + s + ".options.storageUse");
            boolean cuboidItemDrop = instance.getFileManager().getCuboidsFile().getBoolean("cuboids." + s + ".options.itemDrop");
            boolean cuboidItemPickUp = instance.getFileManager().getCuboidsFile().getBoolean("cuboids." + s + ".options.itemPickUp");
            boolean cuboidPortalsUse = instance.getFileManager().getCuboidsFile().getBoolean("cuboids." + s + ".options.portalsUse");
            boolean cuboidBedsUse = instance.getFileManager().getCuboidsFile().getBoolean("cuboids." + s + ".options.bedsUse");
            List<String> cuboidMembers = instance.getFileManager().getCuboidsFile().getStringList("cuboids." + s + ".members");
            cuboidsList.add(new Cuboid(s, cuboidWorld, cuboidOwner, cuboidCreateDate, cuboidLevel, cuboid_x, cuboid_y, cuboid_z,
                    cuboid_max_x, cuboid_min_x, cuboid_max_z, cuboid_min_z, cuboidEnter, cuboidPvp, cuboidInteraction, cuboidBlockPlace,
                    cuboidBlockBreak, cuboidVehicleUse, cuboidMechanismsUse, cuboidMobsDamage, cuboidPearlsUse, cuboidStorageUse, cuboidItemDrop,
                    cuboidItemPickUp, cuboidPortalsUse, cuboidBedsUse, cuboidMembers));
        }
    }

    public static void saveCuboids(){
        for(String cub : Objects.requireNonNull(instance.getFileManager().getCuboidsFile().getConfigurationSection("cuboids")).getKeys(false)){
            if(!cuboidsList.contains(getPlayerCuboid(instance.getFileManager().getCuboidsFile().getString("cuboids." + cub + ".owner")))){
                instance.getFileManager().getCuboidsFile().set("cuboids." + cub, null);
                instance.getFileManager().saveCuboidsFile();
                cuboidsList.remove(getPlayerCuboid(instance.getFileManager().getCuboidsFile().getString("cuboids." + cub + ".owner")));
            }
        }
        for(Cuboid cuboid : cuboidsList){
            String path = "cuboids." + cuboid.getCuboidUUID();
            String optionsPath = path + ".options.";

            cuboidsFile.set(path + ".world", cuboid.getCuboidWorld());
            cuboidsFile.set(path + ".owner", cuboid.getCuboidOwner());
            cuboidsFile.set(path + ".createDate", cuboid.getCuboidCreateDate());
            cuboidsFile.set(path + ".cuboidLevel", cuboid.getCuboidLevel());
            cuboidsFile.set(path + ".cuboid_x", cuboid.getCuboid_x());
            cuboidsFile.set(path + ".cuboid_y", cuboid.getCuboid_y());
            cuboidsFile.set(path + ".cuboid_z", cuboid.getCuboid_z());
            cuboidsFile.set(path + ".max_x", cuboid.getCuboid_max_x());
            cuboidsFile.set(path + ".min_x", cuboid.getCuboid_min_x());
            cuboidsFile.set(path + ".max_z", cuboid.getCuboid_max_z());
            cuboidsFile.set(path + ".min_z", cuboid.getCuboid_min_z());
            cuboidsFile.set(optionsPath + "cuboidEnter", cuboid.isCuboidEnter());
            cuboidsFile.set(optionsPath + "pvp", cuboid.isPvp());
            cuboidsFile.set(optionsPath + "interaction", cuboid.isInteraction());
            cuboidsFile.set(optionsPath + "blockPlace", cuboid.isBlockPlace());
            cuboidsFile.set(optionsPath + "blockBreak", cuboid.isBlockBreak());
            cuboidsFile.set(optionsPath + "vehiclesUse", cuboid.isVehiclesUse());
            cuboidsFile.set(optionsPath + "useMechanisms", cuboid.isMechanismsUse());
            cuboidsFile.set(optionsPath + "mobsDamage", cuboid.isMobsDamage());
            cuboidsFile.set(optionsPath + "pearlsUse", cuboid.isPearlsUse());
            cuboidsFile.set(optionsPath + "storageUse", cuboid.isStorageUse());
            cuboidsFile.set(optionsPath + "itemDrop", cuboid.isItemDrop());
            cuboidsFile.set(optionsPath + "itemPickUp", cuboid.isItemPickUp());
            cuboidsFile.set(optionsPath + "portalsUse", cuboid.isPortalsUse());
            cuboidsFile.set(optionsPath + "bedsUse", cuboid.isBedsUse());
            cuboidsFile.set(path + ".members", cuboid.getMembers());
            instance.getFileManager().saveCuboidsFile();
        }
        cuboidsList.clear();
    }


    public static void createCuboid(Player player, Location location){
        int max_x = location.getBlockX() + DataManager.getCuboidSize(1);
        int min_x = location.getBlockX() - DataManager.getCuboidSize(1);
        int max_z = location.getBlockZ() + DataManager.getCuboidSize(1);
        int min_z = location.getBlockZ() - DataManager.getCuboidSize(1);
        List<String> playersList = new ArrayList<>();
        playersList.add(player.getName());
        Cuboid cuboid = new Cuboid(
                player.getUniqueId().toString(),
                Objects.requireNonNull(location.getWorld()).getName(),
                player.getName(),
                DateUtil.getDate(),
                1,
                location.getX(),
                location.getY(),
                location.getZ(),
                max_x,
                min_x,
                max_z,
                min_z,
                true,
                true,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                true,
                true,
                true,
                playersList
        );
        PlayerManager.createPlayer(player, cuboid);
        cuboidsList.add(cuboid);
    }

    public static void deleteCuboid(Cuboid cuboid){
        cuboidsList.remove(cuboid);
        PlayerManager.deletePlayer(cuboid.getCuboidOwner());
    }


    public static boolean doesLocationContainsCuboid(Location location, Cuboid cuboid){
        int playerx = location.getBlockX();
        int playerz = location.getBlockZ();
        double cuboidCornerX1 = cuboid.getCuboid_max_x();
        double cuboidCornerX2 = cuboid.getCuboid_min_x();
        double cuboidCornerZ1 = cuboid.getCuboid_max_z();
        double cuboidCornerZ2 = cuboid.getCuboid_min_z();

        if(cuboidCornerX1 > cuboidCornerX2){
            if(cuboidCornerZ1 > cuboidCornerZ2){
                if(playerx < cuboidCornerX1 && playerx > cuboidCornerX2){
                    return playerz < cuboidCornerZ1 && playerz > cuboidCornerZ2;
                }
            } else {
                if(playerx < cuboidCornerX1 && playerx > cuboidCornerX2){
                    return playerz < cuboidCornerZ2 && playerz > cuboidCornerZ1;
                }
            }
        }
        if(cuboidCornerX2 > cuboidCornerX1){
            if(cuboidCornerZ1 > cuboidCornerZ2){
                if(playerx < cuboidCornerX2 && playerx > cuboidCornerX1){
                    return playerz < cuboidCornerZ1 && playerz > cuboidCornerZ2;
                }
            } else {
                if(playerx < cuboidCornerX2 && playerx > cuboidCornerX1){
                    return playerz > cuboidCornerZ1 && playerz < cuboidCornerZ2;
                }
            }
        }
        return false;
    }


    public static boolean doesAreaContainsAnyCuboid(Location location){
        int playerx = location.getBlockX();
        int playerz = location.getBlockZ();
        for (Cuboid cuboid : cuboidsList) {
            int maxSize = DataManager.getCuboidSize(5);
            double cuboid_x = cuboid.getCuboid_x();
            double cuboid_z = cuboid.getCuboid_z();
            double cuboid_max_x =  cuboid_x + 2*maxSize;
            double cuboid_min_x = cuboid_x - 2*maxSize;
            double cuboid_max_z =  cuboid_z + 2*maxSize;
            double cuboid_min_z =  cuboid_z - 2*maxSize;

            if(playerx > cuboid_x){
                if(playerz > cuboid_z){
                    return cuboid_max_x >= playerx && cuboid_max_z >= playerz;
                } else {
                    return cuboid_max_x >= playerx && cuboid_min_z <= playerz;
                }
            } else {
                if(playerz > cuboid_z){
                    return cuboid_min_x <= playerx && cuboid_max_z >= playerz;
                } else {
                    return cuboid_min_x <= playerx && cuboid_min_z <= playerz;
                }
            }

        }
        return false;
    }

    public static String getCuboidOwner(Cuboid cuboid){
        return cuboid.getCuboidOwner();
    }


    public static int getCuboidLevel(Cuboid cuboid){
        return cuboid.getCuboidLevel();
    }


    public static boolean isPlayerAddedToCuboid(Cuboid cuboid, Player player){
        return cuboid.getMembers().contains(player.getName());
    }

    public static Cuboid getCuboidFromLocation(Location location){
        for(Cuboid cuboid : CuboidManager.getCuboids()){
            if(CuboidManager.doesLocationContainsCuboid(location, cuboid)){
                return cuboid;
            }
        }
        return null;
    }

    public static Cuboid getPlayerCuboid(String playerName){
        for(Cuboid cuboid : cuboidsList){
            if(cuboid.getCuboidOwner().equalsIgnoreCase(playerName)){
                return cuboid;
            }
        }
        return null;
    }


    public static boolean doesLocationContainsAnyCuboid(Location location) {
        for(Cuboid cuboid : cuboidsList){
            if(CuboidManager.doesLocationContainsCuboid(location, cuboid)){
                return true;
            }
        }
        return false;
    }

    public static void setCuboidArea(int level, Cuboid cuboid, Location location){
        int cuboidSize = DataManager.getCuboidSize(level);
        int max_x = location.getBlockX() + cuboidSize;
        int min_x = location.getBlockX() - cuboidSize;
        int max_z = location.getBlockZ() + cuboidSize;
        int min_z = location.getBlockZ() - cuboidSize;
        cuboid.setCuboid_max_x(max_x);
        cuboid.setCuboid_min_x(min_x);
        cuboid.setCuboid_max_z(max_z);
        cuboid.setCuboid_min_z(min_z);
    }

    public static void setCuboidLevel(int level, Cuboid cuboid, Location cuboidLocation) {
        cuboid.setCuboidLevel(level);
        setCuboidArea(level, cuboid, cuboidLocation);
    }
}
