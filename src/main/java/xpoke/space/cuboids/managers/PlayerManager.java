package xpoke.space.cuboids.managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import xpoke.space.cuboids.Main;
import xpoke.space.cuboids.objects.*;

import java.util.*;

public class PlayerManager {

    private static final Map<String, String> playersInvites = new HashMap<>();
    private static final Main instance = Main.getInstance();
    private static final FileConfiguration playerDataFile = instance.getFileManager().getPlayerDataFile();


    public static boolean playerHasAnyCuboid(Player player){
        for (Cuboid cuboid : CuboidManager.getCuboids()){
            if (cuboid.getCuboidOwner().equalsIgnoreCase(player.getName())) {
                return true;
            }
        }
        return false;
    }


    public static void sendInviteToPlayer(String sender, String reciever){
        playersInvites.put(sender, reciever);
    }

    public static void deleteInviteFromTo(String from, String to){
        playersInvites.remove(to, from);
    }

    public static boolean isInvitedBy(String who, String by){
        if(playersInvites.containsKey(who)){
            return playersInvites.get(who).equals(by);
        }
        return false;
    }

    public static void deletePlayer(String playerName){
        playerDataFile.set("players." + playerName, null);
        instance.getFileManager().savePlayerDataFile();
    }

    public static void createPlayer(Player player, Cuboid cuboid) {
        playerDataFile.set("players." + player.getName() + ".uuid", player.getUniqueId().toString());
        playerDataFile.set("players." + player.getName() + ".cuboidOwner", cuboid.getCuboidUUID());
        playerDataFile.set("players." + player.getName() + ".cuboidLevel", 1);
        instance.getFileManager().savePlayerDataFile();
    }
}
