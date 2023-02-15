package xpoke.space.cuboids.managers;

import org.bukkit.configuration.file.*;
import xpoke.space.cuboids.*;
import xpoke.space.cuboids.utils.*;

import java.util.*;

public class MessageManager {
    private static final Main instance = Main.getInstance();
    private static final FileConfiguration messagesFile = instance.getFileManager().getMessagesFile();
    private static String getString(String path){
        return ColorUtil.hexColor(messagesFile.getString(path)).replace("%prefix%", PREFIX);
    }
    private static List<String> getStringList(String path){
        List<String> list = new ArrayList<>();
        messagesFile.getStringList(path).forEach(s -> list.add(ColorUtil.hexColor(s)));
        return list;
    }

    public static String PREFIX = ColorUtil.hexColor(messagesFile.getString("prefix"));
    public static String CUBOID_NAME = getString("cuboid_block_name");
    public static List<String> CUBOID_LORE = getStringList("cuboid_block_lore");
    public static String NO_CUBOID = getString("player_has_no_cuboid");
    public static String NO_PERMISSION = getString("no_permission");
    public static String CUBOID_GIVE_SUCCESS = getString("cuboid_give_success");
    public static String CUBOID_RECIEVE = getString("cuboid_recieve");
    public static String SUBCOMMAND_USAGE_ADD = getString("add_subCommand_usage");
    public static String SUBCOMMAND_USAGE_ACCEPT = getString("accept_subCommand_usage");
    public static String SUBCOMMAND_USAGE_KICK = getString("kick_subCommand_usage");
    public static String SUBCOMMAND_USAGE_DENY = getString("deny_subCommand_usage");
    public static String SUBCOMMAND_USAGE_GIVE = getString("give_subCommand_usage");
    public static String SUBCOMMAND_USAGE_LEAVE = getString("leave_subCommand_usage");
    public static String NO_MONEY_FOR_TP = getString("teleport_noMoney");
    public static String TP_SUCCESS = getString("teleport_success");
    public static String OFFLINE_PLAYER = getString("offline_player");
    public static String SELF_INVITE_MESSAGE = getString("self_invite");
    public static String SELF_ACCEPT_MESSAGE = getString("self_accept");
    public static String PLAYER_ALREADY_ADDED = getString("player_already_added");
    public static String PLAYER_ALREADY_INVITED = getString("player_already_invited");
    public static String PLAYER_INVITE_SUCCESS = getString("player_invite_success");
    public static List<String> INVITE_RECEIVED_MESSAGES = messagesFile.getStringList("invite_received_messages");
    public static String PLAYER_JOINED_CUBOID = getString("player_join_cuboid");
    public static String CUBOID_JOIN_SUCCESS = getString("cuboid_join_success");
    public static String NO_INVITE_FROM = getString("no_invite_from");
    public static String SELF_CUBOID_REMOVE = getString("self_cuboid_remove");
    public static String PLAYER_KICK_SUCCESS = getString("player_kick_success");
    public static String SELF_INVITE_DENY = getString("self_invite_deny");
    public static String INVITE_DENY_SUCCESS = getString("invite_deny_success");
    public static String INVITE_DENY_MESSAGE = getString("invite_deny_message");
    public static String CANT_LEAVE_OWN_CUBOID = getString("cant_leave_own_cuboid");
    public static String NOT_MEMBER_OF_CUBOID = getString("not_member_of_cuboid");
    public static String CUBOID_LEAVE_SUCCESS = getString("cuboid_leave_success");
    public static String PLAYER_CUBOID_LEAVE_BROADCAST = getString("player_cuboid_leave_broadcast");
    public static String CANT_BREAK_OTHER_PLAYER_CUBOID = getString("cant_break_other_player_cuboid");
    public static String CANT_PLACE_CUBOID_ON_OTHER_CUBOID = getString("cant_place_cuboid_on_other_cuboid");
    public static String CUBOID_REMOVE_SUCCESS = getString("cuboid_remove_success");
    public static String CANT_PLACE_CUBOID_IN_WORLD = getString("cant_place_cuboid_in_world");
    public static String PLAYER_HAS_CUBOID = getString("player_has_cuboid");
    public static String TOO_CLOSE_TO_OTHER_CUBOID = getString("too_close_to_other_cuboid");
    public static String CUBOID_CREATE_SUCCESS = getString("cuboid_create_success");
    public static String CUBOID_ENTER_TITLE = getString("cuboid_enter_title");
    public static String CUBOID_ENTER_SUBTITLE = getString("cuboid_enter_subtitle");
    public static String CUBOID_LEAVE_TITLE = getString("cuboid_leave_title");
    public static String CUBOID_LEAVE_SUBTITLE = getString("cuboid_leave_subtitle");
    public static String CUBOID_REMOVE_TITLE = getString("cuboid_remove_title");
    public static String CUBOID_REMOVE_SUBTITLE = getString("cuboid_remove_subtitle");
    public static String CUBOID_CREATE_TITLE = getString("cuboid_create_title");
    public static String CUBOID_CREATE_SUBTITLE = getString("cuboid_create_subtitle");
    public static String CUBOID_INFO_GUI_NAME = getString("cuboid_info_gui_name");
    public static final String PLAYER_NOT_MEMBER_OF_CUBOID = getString("cuboid_not_member");
    public static String CUBOID_UPGRADE_GUI_NAME = getString("cuboid_upgrade_gui_name");
    public static String CUBOID_SETTINGS_GUI_NAME = getString("cuboid_settings_gui_name");
    public static String CUBOID_MAIN_GUI_NAME = getString("cuboid_main_gui_name");
    public static String CANT_WALK_INTO_CUBOID = getString("cant_walk_into_cuboid");
    public static String INVITE_TO_EXPIRED = getString("invite_to_expired");
    public static String INVITE_FROM_EXPIRED = getString("invite_from_expired");
    public static String CONFIG_RELOAD = getString("config_reload");


    public static String GUI_ORANGE_GLASS_NAME = getString("gui_orange_glass_name");
    public static String GUI_YELLOW_GLASS_NAME = getString("gui_yellow_glass_name");
    public static String GUI_BACK_ITEM_NAME = getString("gui_back_item_name");
    public static String GUI_CLOSE_ITEM_NAME = getString("gui_close_item_name");



}
