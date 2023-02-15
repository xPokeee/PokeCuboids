package xpoke.space.cuboids.objects;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import xpoke.space.cuboids.Main;

import java.util.*;

public class Cuboid {

    String cuboidUUID;
    String cuboidWorld;
    String cuboidOwner;
    String cuboidCreateDate;
    int cuboidLevel;
    double cuboid_x;
    double cuboid_y;
    double cuboid_z;
    double cuboid_max_x;
    double cuboid_min_x;
    double cuboid_max_z;
    double cuboid_min_z;
    boolean cuboidEnter;
    boolean pvp;
    boolean interaction;
    boolean blockPlace;
    boolean blockBreak;
    boolean vehiclesUse;
    boolean mechanismsUse;
    boolean mobsDamage;
    boolean pearlsUse;
    boolean storageUse;
    boolean itemDrop;
    boolean itemPickUp;
    boolean portalsUse;
    boolean bedsUse;
    List<String> members;
    private final static Main instance = Main.getInstance();

    private static final FileConfiguration playerDataFile = instance.getFileManager().getPlayerDataFile();



    public Cuboid(String uuid, String world, String owner, String createDate, int level,
                  double x, double y, double z, double max_x, double min_x, double max_z,
                  double min_z, boolean enterOption, boolean pvpOption, boolean interactionOption,
                  boolean blocksPlaceOption, boolean blocksBreakOption, boolean vehiclesUseOption,
                  boolean mechanismsUseOption, boolean mobsDamageOption, boolean pearlsUseOption,
                  boolean storageUseOption, boolean itemDropOption, boolean itemPickUpOption,
                  boolean portalsUseOption, boolean bedsUseOption, List<String> cuboidMembers){
        this.cuboidUUID = uuid;
        this.cuboidWorld = world;
        this.cuboidOwner = owner;
        this.cuboidCreateDate = createDate;
        this.cuboidLevel = level;
        this.cuboid_x = x;
        this.cuboid_y = y;
        this.cuboid_z = z;
        this.cuboid_max_x = max_x;
        this.cuboid_min_x = min_x;
        this.cuboid_max_z = max_z;
        this.cuboid_min_z = min_z;
        this.cuboidEnter = enterOption;
        this.pvp = pvpOption;
        this.interaction = interactionOption;
        this.blockPlace = blocksPlaceOption;
        this.blockBreak = blocksBreakOption;
        this.vehiclesUse = vehiclesUseOption;
        this.mechanismsUse = mechanismsUseOption;
        this.mobsDamage = mobsDamageOption;
        this.pearlsUse = pearlsUseOption;
        this.storageUse = storageUseOption;
        this.itemDrop = itemDropOption;
        this.itemPickUp = itemPickUpOption;
        this.portalsUse = portalsUseOption;
        this.bedsUse = bedsUseOption;
        this.members = cuboidMembers;
    }


    public String getCuboidUUID() {
        return cuboidUUID;
    }

    public String getCuboidWorld() {
        return cuboidWorld;
    }

    public String getCuboidOwner() {
        return cuboidOwner;
    }

    public String getCuboidCreateDate() {
        return cuboidCreateDate;
    }

    public int getCuboidLevel() {
        return cuboidLevel;
    }

    public double getCuboid_x() {
        return cuboid_x;
    }

    public double getCuboid_y() {
        return cuboid_y;
    }

    public double getCuboid_z() {
        return cuboid_z;
    }

    public double getCuboid_max_x() {
        return cuboid_max_x;
    }

    public double getCuboid_min_x() {
        return cuboid_min_x;
    }

    public void setCuboidLevel(int cuboidLevel) {
        this.cuboidLevel = cuboidLevel;
        playerDataFile.set("players." + this.cuboidOwner + ".cuboidLevel", cuboidLevel);
    }

    public void setCuboid_max_x(double cuboid_max_x) {
        this.cuboid_max_x = cuboid_max_x;
    }

    public void setCuboid_min_x(double cuboid_min_x) {
        this.cuboid_min_x = cuboid_min_x;
    }

    public void setCuboid_max_z(double cuboid_max_z) {
        this.cuboid_max_z = cuboid_max_z;
    }

    public void setCuboid_min_z(double cuboid_min_z) {
        this.cuboid_min_z = cuboid_min_z;
    }

    public void setCuboidEnter(boolean cuboidEnter) {
        this.cuboidEnter = cuboidEnter;
    }

    public void setPvp(boolean pvp) {
        this.pvp = pvp;
    }

    public void setInteraction(boolean interaction) {
        this.interaction = interaction;
    }

    public void setBlockPlace(boolean blockPlace) {
        this.blockPlace = blockPlace;
    }

    public void setBlockBreak(boolean blockBreak) {
        this.blockBreak = blockBreak;
    }

    public void setVehiclesUse(boolean vehiclesUse) {
        this.vehiclesUse = vehiclesUse;
    }

    public void setMechanismsUse(boolean mechanismsUse) {
        this.mechanismsUse = mechanismsUse;
    }

    public void setMobsDamage(boolean mobsDamage) {
        this.mobsDamage = mobsDamage;
    }

    public void setPearlsUse(boolean pearlsUse) {
        this.pearlsUse = pearlsUse;
    }

    public void setStorageUse(boolean storageUse) {
        this.storageUse = storageUse;
    }

    public void setItemDrop(boolean itemDrop) {
        this.itemDrop = itemDrop;
    }

    public void setItemPickUp(boolean itemPickUp) {
        this.itemPickUp = itemPickUp;
    }

    public void setPortalsUse(boolean portalsUse) {
        this.portalsUse = portalsUse;
    }

    public void setBedsUse(boolean bedsUse) {
        this.bedsUse = bedsUse;
    }

    public double getCuboid_max_z() {
        return cuboid_max_z;
    }

    public double getCuboid_min_z() {
        return cuboid_min_z;
    }

    public boolean isCuboidEnter() {
        return cuboidEnter;
    }

    public boolean isPvp() {
        return pvp;
    }

    public boolean isInteraction() {
        return interaction;
    }

    public boolean isBlockPlace() {
        return blockPlace;
    }

    public boolean isBlockBreak() {
        return blockBreak;
    }

    public boolean isVehiclesUse() {
        return vehiclesUse;
    }

    public boolean isMechanismsUse() {
        return mechanismsUse;
    }

    public boolean isMobsDamage() {
        return mobsDamage;
    }

    public boolean isPearlsUse() {
        return pearlsUse;
    }

    public boolean isStorageUse() {
        return storageUse;
    }

    public boolean isItemDrop() {
        return itemDrop;
    }

    public boolean isItemPickUp() {
        return itemPickUp;
    }

    public boolean isPortalsUse() {
        return portalsUse;
    }

    public boolean isBedsUse() {
        return bedsUse;
    }

    public List<String> getMembers() {
        return members;
    }

    public Location getCuboidLocation(Cuboid cuboid){
        return new Location(Bukkit.getWorld(cuboid.getCuboidWorld()), cuboid.getCuboid_x(), cuboid.getCuboid_y(), cuboid.getCuboid_z());
    }
}
