package xpoke.space.cuboids.configuration;

import org.bukkit.*;
import org.bukkit.configuration.file.*;
import xpoke.space.cuboids.*;

import java.io.*;
import java.util.*;

public class FileManager {

    private static File configurationFile;
    private static FileConfiguration customConfigurationFile;
    private static File cuboidsFile;
    private static FileConfiguration cuboidsCustomFile;
    private static File messagesFile;
    private FileConfiguration customMessagesFile;
    private static File playerDataFile;
    private static FileConfiguration customPlayerDataFile;
    private final Main instance = Main.getInstance();

    public FileManager() {
        configurationFile = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin(instance.getDescription().getName())).getDataFolder(), "config.yml");
        cuboidsFile = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin(instance.getDescription().getName())).getDataFolder(), "cuboids.yml");
        messagesFile = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin(instance.getDescription().getName())).getDataFolder(), "messages.yml");
        playerDataFile = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin(instance.getDescription().getName())).getDataFolder(), "playerdata.yml");
        if(!configurationFile.exists()) {
            try {
                if(configurationFile.createNewFile()){
                    instance.getLog().info(String.format("[%s] - Stworzono plik config.yml", instance.getDescription().getName()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        customConfigurationFile = YamlConfiguration.loadConfiguration(configurationFile);
        if(configurationFile.length() == 0){
            generateConfigForConfigFile();
        }
        if(!cuboidsFile.exists()) {
            try {
                if(cuboidsFile.createNewFile()){
                    instance.getLog().info(String.format("[%s] - Stworzono plik cuboids.yml", instance.getDescription().getName()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        cuboidsCustomFile = YamlConfiguration.loadConfiguration(cuboidsFile);
        if(cuboidsFile.length() == 0){
            generateConfigForCuboidsFile();
        }
        if(!messagesFile.exists()) {
            try {
                if(messagesFile.createNewFile()){
                    instance.getLog().info(String.format("[%s] - Stworzono plik messages.yml", instance.getDescription().getName()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        customMessagesFile = YamlConfiguration.loadConfiguration(messagesFile);
        if(messagesFile.length() == 0){
            generateConfigForMessagesFile();
        }
        if(!playerDataFile.exists()) {
            try {
                if(playerDataFile.createNewFile()){
                    instance.getLog().info(String.format("[%s] - Stworzono plik playerdata.yml", instance.getDescription().getName()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        customPlayerDataFile = YamlConfiguration.loadConfiguration(playerDataFile);
        if(playerDataFile.length() == 0){
            generateConfigForPlayerDataFile();
        }
    }



    //Reload all configuration files
    public void reloadFiles(){
        customConfigurationFile = YamlConfiguration.loadConfiguration(configurationFile);
        cuboidsCustomFile = YamlConfiguration.loadConfiguration(cuboidsFile);
        customMessagesFile = YamlConfiguration.loadConfiguration(messagesFile);
        customPlayerDataFile = YamlConfiguration.loadConfiguration(playerDataFile);
    }



    //Get file methods
    public FileConfiguration getCuboidsFile(){
        return cuboidsCustomFile;
    }
    public FileConfiguration getConfigurationFile(){
        return customConfigurationFile;
    }
    public FileConfiguration getMessagesFile(){
        return customMessagesFile;
    }
    public FileConfiguration getPlayerDataFile(){
        return customPlayerDataFile;
    }






    //Saving files
    public void saveConfigurationFile(){
        try{
            customConfigurationFile.save(configurationFile);
        } catch (IOException e){
            instance.getLog().info(String.format("[%s] - B????d podczas zapisywania pliku config.yml!", instance.getDescription().getName()));
        }
    }

    public void saveCuboidsFile(){
        try{
            cuboidsCustomFile.save(cuboidsFile);
        } catch (IOException e){
            instance.getLog().info(String.format("[%s] - B????d podczas zapisywania pliku cuboids.yml!", instance.getDescription().getName()));
        }
    }

    public void saveMessagesFile(){
        try{
            customMessagesFile.save(messagesFile);
        }catch (IOException e){
            instance.getLog().info(String.format("[%s] - B????d podczas zapisywania pliku messages.yml!", instance.getDescription().getName()));
        }
    }

    public void savePlayerDataFile(){
        try{
            customPlayerDataFile.save(playerDataFile);
        } catch (IOException e){
            instance.getLog().info(String.format("[%s] - B????d podczas zapisywania pliku playerdata.yml!", instance.getDescription().getName()));
        }
    }






    //Generating configuration data
    private void generateConfigForConfigFile(){
        var cfg = this.getConfigurationFile();
        cfg.createSection("cuboids.allowedWorlds");
        cfg.createSection("cuboids.teleportCost");
        cfg.createSection("cuboids.levels.level_1.size");
        cfg.createSection("cuboids.levels.level_2.size");
        cfg.createSection("cuboids.levels.level_2.upgradeCost");
        cfg.createSection("cuboids.levels.level_3.size");
        cfg.createSection("cuboids.levels.level_3.upgradeCost");
        cfg.createSection("cuboids.levels.level_4.size");
        cfg.createSection("cuboids.levels.level_4.upgradeCost");
        cfg.createSection("cuboids.levels.level_5.size");
        cfg.createSection("cuboids.levels.level_5.upgradeCost");
        List<String> allowedWorlds = cfg.getStringList("cuboids.allowedWorlds");
        allowedWorlds.add("world");
        cfg.set("cuboids.allowedWorlds", allowedWorlds);
        cfg.set("cuboids.teleportCost", 5);
        List<String> beforeSave = cfg.getStringList( "cuboids.storageBlocks");
        beforeSave.add("CHEST");
        beforeSave.add("FURNACE");
        beforeSave.add("HOPPER");
        beforeSave.add("HOPPER_MINECART");
        beforeSave.add("CHEST_MINECART");
        beforeSave.add("BLAST_FURNACE");
        beforeSave.add("SMOKER");
        beforeSave.add("TRAPPED_CHEST");
        beforeSave.add("SHULKER_BOX");
        beforeSave.add("DISPENSER");
        beforeSave.add("DROPPER");
        beforeSave.add("BARREL");
        beforeSave.add("BREWING_STAND");
        beforeSave.add("COMPOSTER");
        beforeSave.add("BEEHIVE");
        beforeSave.add("FLOWER_POT");
        beforeSave.add("BEE_NEST");
        cfg.set("cuboids.storageBlocks", beforeSave);
        cfg.set("cuboids.levels.level_1.size", 41);
        cfg.set("cuboids.levels.level_2.size", 51);
        cfg.set("cuboids.levels.level_2.upgradeCost", 50);
        cfg.set("cuboids.levels.level_3.size", 61);
        cfg.set("cuboids.levels.level_3.upgradeCost", 100);
        cfg.set("cuboids.levels.level_4.size", 71);
        cfg.set("cuboids.levels.level_4.upgradeCost", 150);
        cfg.set("cuboids.levels.level_5.size", 81);
        cfg.set("cuboids.levels.level_5.upgradeCost", 200);

        cfg.options().copyDefaults(true);
        this.saveConfigurationFile();

    }

    private void generateConfigForCuboidsFile(){
        var cfg = this.getCuboidsFile();
        cfg.createSection("cuboids");
        cfg.options().copyDefaults(true);
        this.saveCuboidsFile();

    }

    private void generateConfigForMessagesFile(){
        var cfg = this.getMessagesFile();
        cfg.addDefault("prefix", "&6Poke&fCuboids");
        cfg.addDefault("cuboid_block_name", "&6Blok dzia??ki");
        List<String> cuboidLore = cfg.getStringList("cuboid_block_lore");
        cuboidLore.add("&f");
        cuboidLore.add("&6   Postaw ten blok, aby stworzy?? dzia??k??!");
        cuboidLore.add("&f");
        cfg.set("cuboid_block_lore", cuboidLore);
        cfg.addDefault("no_permission", "&cNie masz permisji!");
        cfg.addDefault("cuboid_give_success", "&aPomy??lnie dano blok dzia??ki do gracza %player%!");
        cfg.addDefault("cuboid_recieve", "&aOtrzymano blok dzia??ki od gracza %player%!");
        cfg.addDefault("player_has_no_cuboid", "&cNie masz jeszcze dzia??ki!");
        cfg.addDefault("add_subCommand_usage", "&cU??ycie: /dzialka dodaj nick");
        cfg.addDefault("give_subCommand_usage", "&cU??ycie: /dzialka daj nick");
        cfg.addDefault("accept_subCommand_usage", "&cU??ycie: /dzialka akceptuj nick");
        cfg.addDefault("kick_subCommand_usage", "&cU??ycie: /dzialka wyrzuc nick");
        cfg.addDefault("deny_subCommand_usage", "&cU??ycie: /dzialka odrzuc nick");
        cfg.addDefault("leave_subCommand_usage", "&cU??ycie: /dzialka opusc nick");
        cfg.addDefault("teleport_noMoney", "&cBrakuje Ci %money%$ aby si?? przeteleportowa??!");
        cfg.addDefault("teleport_success", "&aPrzeteleportowano na dzia??k??!");
        cfg.addDefault("offline_player", "&cTen gracz jest offline!");
        cfg.addDefault("self_invite", "&cNie mo??esz wys??a?? zaproszenia do samego siebie!");
        cfg.addDefault("self_accept", "&cNie mo??esz zaakceptowa?? pro??by od samego siebie!");
        cfg.addDefault("player_already_added", "&cTen gracz jest ju?? dodany!");
        cfg.addDefault("player_already_invited", "&cZaprosi??e?? ju?? tego gracza!");
        cfg.addDefault("player_invite_success", "&aPomy??lnie zaproszono gracza %player% do dzia??ki!");
        List<String> inviteMessagesList = cfg.getStringList("invite_received_messages");
        inviteMessagesList.add("&aOtrzymano zaproszenie do dzia??ki gracza %player%!");
        inviteMessagesList.add("&aAby zaakceptowa??, kliknij przycisk &a&lAKCEPTUJ");
        inviteMessagesList.add("&aAby odrzuci??, kliknij przycisk &c&lODRZU??");
        cfg.set("invite_received_messages", inviteMessagesList);
        cfg.addDefault("player_join_cuboid", "&aGracz %player% do????czy?? do dzia??ki!");
        cfg.addDefault("cuboid_join_success", "&aPomy??lnie zaakceptowano zaproszenie do dzia??ki gracza %player%!");
        cfg.addDefault("no_invite_from", "&cNie masz zaproszenia od gracza %player%!");
        cfg.addDefault("self_cuboid_remove", "&cNie mo??esz usun???? samego siebie z dzia??ki!");
        cfg.addDefault("player_kick_success", "&aPomy??lnie wyrzucono gracza %player% z dzia??ki!");
        cfg.addDefault("self_invite_deny", "&cNie mo??esz odrzuci?? zaproszenia od samego siebie!");
        cfg.addDefault("invite_deny_success", "&aPomy??lnie odrzucono pro??b?? o do????czenie do dzia??ki od gracza %player%!");
        cfg.addDefault("invite_deny_message", "&cGracz %player% odrzuci?? Twoje zaproszenie o do????czenie do dzia??ki!");
        cfg.addDefault("cant_leave_own_cuboid", "&cNie mo??esz opu??ci?? swojej dzia??ki!");
        cfg.addDefault("not_member_of_cuboid", "&cNie jeste?? dodany do tej dzia??ki!");
        cfg.addDefault("cuboid_not_member", "&cTen gracz nie jest dodany do Twojej dzia??ki!");
        cfg.addDefault("cuboid_leave_success", "&aPomy??lnie opuszczono dzia??k?? gracza %player%!");
        cfg.addDefault("player_cuboid_leave_broadcast", "&cGracz %player% opu??ci?? dzia??k?? gracza %owner%");
        cfg.addDefault("cant_break_other_player_cuboid", "&cNie mo??esz zniszczy?? dzia??ki innego gracza!");
        cfg.addDefault("cant_place_cuboid_on_other_cuboid", "&cNie mo??esz postawi?? dzia??ki na terenie kuboidy innego gracza!");
        cfg.addDefault("cuboid_remove_success", "&aPomy??lnie usuni??to dzia??k??!");
        cfg.addDefault("cant_place_cuboid_in_world", "&cNie mo??esz postawi?? dzia??ki w tym ??wiecie!");
        cfg.addDefault("player_has_cuboid", "&cNie mo??esz postawi?? kolejnej dzia??ki!");
        cfg.addDefault("too_close_to_other_cuboid", "&cNie mo??esz postawi?? tutaj dzia??ki! Znajdujesz si?? zbyt blisko terenu innej dzia??ki!");
        cfg.addDefault("cuboid_create_success", "&aStworzono dzia??k??! Panel dzia??ki znajdziesz pod /dzialka!");
        cfg.addDefault("cuboid_enter_title", "&aWchodzisz na dzia??k??!");
        cfg.addDefault("cuboid_enter_subtitle", "&7Dzia??ka gracza &6%player%");
        cfg.addDefault("cuboid_leave_title", "&cWychodzisz z dzia??ki!");
        cfg.addDefault("cuboid_leave_subtitle", "&7Dzia??ka gracza &6%player%");
        cfg.addDefault("cuboid_remove_title", "&cUsuni??to dzia??k??!");
        cfg.addDefault("cuboid_remove_subtitle", "&7Pomy??lnie usuni??to dzia??k??!");
        cfg.addDefault("cuboid_create_title", "&aStworzono dzia??k??!");
        cfg.addDefault("cuboid_create_subtitle", "&7Pomy??lnie stworzono dzia??k??!");
        cfg.addDefault("cuboid_info_gui_name", "&f  &8Informacje o dzia??ce");
        cfg.addDefault("cuboid_upgrade_gui_name", "&f  &8Ulepszanie dzia??ki");
        cfg.addDefault("cuboid_settings_gui_name", "&f  &8Ustawienia dzia??ki");
        cfg.addDefault("cuboid_main_gui_name", "&f  &8Zarz??dzanie dzia??k??");
        cfg.addDefault("cant_walk_into_cuboid", "&cNie mo??esz wej???? na teren tej dzia??ki!");
        cfg.addDefault("invite_to_expired", "&cZaproszenie do gracza %player% wygas??o!");
        cfg.addDefault("invite_from_expired", "&cZaproszenie od gracza %player% wygas??o!");
        cfg.addDefault("config_reload", "&aOd??wie??anie plik??w konfiguracyjnych...");

        cfg.addDefault("gui_orange_glass_name", "&f");
        cfg.addDefault("gui_yellow_glass_name", "&f");
        cfg.addDefault("gui_back_item_name", "&cPowr??t");
        cfg.addDefault("gui_close_item_name", "&cZamknij");

        cfg.options().copyDefaults(true);
        this.saveMessagesFile();

    }

    private void generateConfigForPlayerDataFile(){
        var cfg = this.getPlayerDataFile();
        cfg.createSection("players");
        cfg.options().copyDefaults(true);
        this.savePlayerDataFile();

    }
}
