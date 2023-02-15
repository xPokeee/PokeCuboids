package xpoke.space.cuboids.commands.implementers;

import net.md_5.bungee.api.chat.*;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xpoke.space.cuboids.Main;
import xpoke.space.cuboids.gui.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.objects.*;
import xpoke.space.cuboids.tasks.CuboidInviteTask;
import xpoke.space.cuboids.utils.*;

import java.util.*;

public class CuboidCommand implements CommandExecutor {

    private final Main instance = Main.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender sender,@NotNull Command cmd,@NotNull String arg, String[] args) {
        Player p = (Player) sender;
        if(cmd.getName().equalsIgnoreCase("dzialka")){
            if(args.length == 0){
                if(!PlayerManager.playerHasAnyCuboid(p)){
                    p.sendMessage(MessageManager.NO_CUBOID);
                    return true;
                }
                new CuboidMainGui(p);
                return true;
            }
            Cuboid cuboid = CuboidManager.getPlayerCuboid(p.getName());
            assert cuboid != null;
            if(args.length == 1){
                switch (args[0].toLowerCase()) {
                    case "info" -> {
                        if (!PlayerManager.playerHasAnyCuboid(p)) {
                            p.sendMessage(MessageManager.NO_CUBOID);
                            return false;
                        }
                        new CuboidInfoGui(p, cuboid);
                    }
                    case "ulepszanie" -> {
                        if (!PlayerManager.playerHasAnyCuboid(p)) {
                            p.sendMessage(MessageManager.NO_CUBOID);
                            return false;
                        }
                        int cuboidLevel = cuboid.getCuboidLevel();
                        if (cuboidLevel < 5 && cuboidLevel >= 1) {
                            new CuboidUpgradeGui(p, cuboid);
                        } else {
                            new CuboidMaxLevelGui(p, cuboid);
                        }
                    }
                    case "panel" -> {
                        if (!PlayerManager.playerHasAnyCuboid(p)) {
                            p.sendMessage(MessageManager.NO_CUBOID);
                            return false;
                        }
                        new CuboidMainGui(p);
                    }
                    case "dodaj" -> p.sendMessage(MessageManager.SUBCOMMAND_USAGE_ADD);
                    case "akceptuj" -> p.sendMessage(MessageManager.SUBCOMMAND_USAGE_ACCEPT);
                    case "wyrzuc" -> p.sendMessage(MessageManager.SUBCOMMAND_USAGE_KICK);
                    case "odrzuc" -> p.sendMessage(MessageManager.SUBCOMMAND_USAGE_DENY);
                    case "opusc" -> p.sendMessage(MessageManager.SUBCOMMAND_USAGE_LEAVE);
                    case "reload" -> {
                        if(!p.hasPermission("cuboids.admin")){
                            p.sendMessage(MessageManager.NO_PERMISSION);
                            return false;
                        }
                        p.sendMessage(MessageManager.CONFIG_RELOAD);
                        instance.getFileManager().reloadFiles();
                    }
                    case "tp" -> {
                        if(!PlayerManager.playerHasAnyCuboid(p)){
                            p.sendMessage(MessageManager.NO_CUBOID);
                            return false;
                        }
                        if(!instance.getEcon().has(p, 5)){
                            p.closeInventory();
                            p.sendMessage(MessageManager.NO_MONEY_FOR_TP
                                    .replace("%money%", String.valueOf(DataManager.getTeleportCost())));
                            return false;
                        }
                        double cuboidLoc_x = cuboid.getCuboid_x() + 0.5;
                        double cuboidLoc_y = cuboid.getCuboid_y() + 1;
                        double cuboidLoc_z = cuboid.getCuboid_z() + 0.5;
                        p.closeInventory();
                        double targetBalance = 5;
                        double currentPlayersBalance = instance.getEcon().getBalance(p);
                        double difference = currentPlayersBalance - targetBalance;

                        if(difference > 0){
                            instance.getEcon().depositPlayer(p, difference);
                        } else {
                            instance.getEcon().withdrawPlayer(p, -difference);
                        }
                        p.teleport(new Location(cuboid.getCuboidLocation(cuboid).getWorld(), cuboidLoc_x, cuboidLoc_y, cuboidLoc_z, 0, 0));
                        p.sendMessage(MessageManager.TP_SUCCESS);
                    }
                    case "daj" -> {
                        if(!p.hasPermission("cuboids.admin")){
                            p.sendMessage(MessageManager.NO_PERMISSION);
                            return false;
                        }
                        p.sendMessage(MessageManager.SUBCOMMAND_USAGE_GIVE);
                    }
                }
            }
            if(args.length == 2){
                switch (args[0].toLowerCase()){
                    case "dodaj" -> {
                        Player invitedPlayer = Bukkit.getPlayer(args[1]);
                        if(invitedPlayer == null){
                            p.sendMessage(MessageManager.OFFLINE_PLAYER);
                            return false;
                        }
                        if(args[1].equals(p.getName())){
                            p.sendMessage(MessageManager.SELF_INVITE_MESSAGE);
                            return false;
                        }
                        if(!PlayerManager.playerHasAnyCuboid(p)){
                            p.sendMessage(MessageManager.NO_CUBOID);
                            return false;
                        }
                        if(cuboid.getMembers().contains(invitedPlayer.getName())) {
                            p.sendMessage(MessageManager.PLAYER_ALREADY_ADDED);
                            return false;
                        }
                        if(PlayerManager.isInvitedBy(invitedPlayer.getName(), p.getName())){
                            p.sendMessage(MessageManager.PLAYER_ALREADY_INVITED);
                            return false;
                        }
                        PlayerManager.sendInviteToPlayer(args[1], p.getName());
                        CuboidInviteTask cuboidInviteTask = new CuboidInviteTask(p, invitedPlayer);
                        p.sendMessage(MessageManager.PLAYER_INVITE_SUCCESS.replace("%player%", invitedPlayer.getName()));
                        for(String s : MessageManager.INVITE_RECEIVED_MESSAGES){
                            invitedPlayer.sendMessage(ColorUtil.hexColor(s.replace("%player%", p.getName())));
                        }
                        TextComponent msg = new TextComponent("");
                        TextComponent acceptTc = new TextComponent(ColorUtil.hexColor("&a&lAKCEPTUJ"));
                        TextComponent denyTc = new TextComponent(ColorUtil.hexColor("&c&lODRZUĆ"));
                        acceptTc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ColorUtil.hexColor("&6Kliknij, aby &azaakceptować &6zaproszenie!")).create()));
                        acceptTc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dzialka akceptuj " + p.getName()));
                        msg.addExtra(acceptTc);
                        denyTc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ColorUtil.hexColor("&6Kliknij, aby &codrzucić &6zaproszenie!")).create()));
                        denyTc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dzialka odrzuc " + p.getName()));
                        msg.addExtra(" ");
                        msg.addExtra(denyTc);
                        invitedPlayer.spigot().sendMessage(msg);
                        cuboidInviteTask.runTaskTimer(instance, 0, 20);
                        return true;
                    }
                    case "akceptuj" -> {
                        Player senderPlayer = Bukkit.getPlayer(args[1]);
                        if(Objects.equals(args[1], p.getName())){
                            p.sendMessage(MessageManager.SELF_ACCEPT_MESSAGE);
                            return false;
                        }
                        if(PlayerManager.isInvitedBy(p.getName(), args[1])){
                            PlayerManager.deleteInviteFromTo(args[1], p.getName());
                            Objects.requireNonNull(CuboidManager.getPlayerCuboid(args[1])).getMembers().add(p.getName());
                            if(senderPlayer != null) {
                                senderPlayer.sendMessage(MessageManager.PLAYER_JOINED_CUBOID.replace("%player%", p.getName()));
                            }
                            p.sendMessage(MessageManager.CUBOID_JOIN_SUCCESS.replace("%player%", args[1]));
                        } else {
                            p.sendMessage(MessageManager.NO_INVITE_FROM.replace("%player%", args[1]));
                        }
                        return true;
                    }
                    case "wyrzuc" -> {
                        if(Objects.equals(args[1], p.getName())){
                            p.sendMessage(MessageManager.SELF_CUBOID_REMOVE);
                            return false;
                        }
                        if(!cuboid.getMembers().contains(args[1])){
                            p.sendMessage(ColorUtil.hexColor(MessageManager.PLAYER_NOT_MEMBER_OF_CUBOID));
                            return false;
                        }
                        Player removePlayer = Bukkit.getPlayer(args[1]);
                        assert removePlayer != null;
                        cuboid.getMembers().remove(removePlayer.getName());
                        p.sendMessage(MessageManager.PLAYER_KICK_SUCCESS.replace("%player%", removePlayer.getName()));
                        return true;
                    }
                    case "odrzuc" -> {
                        Player senderPlayer = Bukkit.getPlayer(args[1]);
                        if(Objects.equals(args[1], p.getName())){
                            p.sendMessage(MessageManager.SELF_INVITE_DENY);
                            return false;
                        }
                        if(!PlayerManager.isInvitedBy(p.getName(), args[1])){
                            p.sendMessage(MessageManager.NO_INVITE_FROM.replace("%player%", args[1]));
                            return false;
                        }
                        PlayerManager.deleteInviteFromTo(args[1], p.getName());
                        p.sendMessage(MessageManager.INVITE_DENY_SUCCESS.replace("%player%", args[1]));
                        if(senderPlayer != null){
                            senderPlayer.sendMessage(MessageManager.INVITE_DENY_MESSAGE.replace("%player%", p.getName()));
                        }
                        return true;
                    }
                    case "opusc" -> {
                        if(Objects.equals(args[1], p.getName())){
                            p.sendMessage(MessageManager.CANT_LEAVE_OWN_CUBOID);
                            return false;
                        }
                        if(!Objects.requireNonNull(CuboidManager.getPlayerCuboid(args[1])).getMembers().contains(p.getName())){
                            p.sendMessage(MessageManager.NOT_MEMBER_OF_CUBOID.replace("%player", args[1]));
                            return false;
                        }
                        Objects.requireNonNull(CuboidManager.getPlayerCuboid(args[1])).getMembers().remove(p.getName());
                        p.sendMessage(MessageManager.CUBOID_LEAVE_SUCCESS.replace("%player%", args[1]));
                        for(String player : Objects.requireNonNull(CuboidManager.getPlayerCuboid(args[1])).getMembers()){
                            if(Bukkit.getPlayer(player) != null){
                                Objects.requireNonNull(Bukkit.getPlayer(player)).sendMessage(MessageManager.PLAYER_CUBOID_LEAVE_BROADCAST.replace("%player%", p.getName()).replace("%owner%", args[1]));
                            }
                        }
                        return true;
                    }
                    case "daj" -> {
                        if(!p.hasPermission("cuboids.admin")){
                            p.sendMessage(MessageManager.NO_PERMISSION);
                            return false;
                        }
                        Player givePlayer = Bukkit.getPlayer(args[1]);
                        if(givePlayer == null){
                            p.sendMessage(MessageManager.OFFLINE_PLAYER);
                            return false;
                        }
                        givePlayer.getInventory().addItem(ItemUtil.getCuboidBlock());
                        p.sendMessage(MessageManager.CUBOID_GIVE_SUCCESS.replace("%player%", givePlayer.getName()));
                        givePlayer.sendMessage(MessageManager.CUBOID_RECIEVE.replace("%player%", p.getName()));
                    }
                    case "usun" -> {
                        if(!p.hasPermission("cuboids.admin")){
                            p.sendMessage(MessageManager.NO_PERMISSION);
                            return false;
                        }
                        String removePlayer = args[1];
                        CuboidManager.deleteCuboid(CuboidManager.getPlayerCuboid(removePlayer));
                        p.sendMessage(MessageManager.CUBOID_REMOVE_SUCCESS);
                    }
                }
            }
            return false;
        }
        return false;
    }
}
