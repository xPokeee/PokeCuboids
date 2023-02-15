package xpoke.space.cuboids.objects;

public class CuboidPlayer {

    String playerName;
    Cuboid cuboid;
    String playerUUID;

    public CuboidPlayer(String playerName, Cuboid cuboid, String playerUUID){
        this.playerName = playerName;
        this.cuboid = cuboid;
        this.playerUUID = playerUUID;
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Cuboid getCuboid() {
        return cuboid;
    }

    public void setCuboid(Cuboid cuboid) {
        this.cuboid = cuboid;
    }

    public String getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(String playerUUID) {
        this.playerUUID = playerUUID;
    }
}
