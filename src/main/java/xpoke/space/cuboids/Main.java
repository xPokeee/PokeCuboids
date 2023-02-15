package xpoke.space.cuboids;

import net.milkbowl.vault.economy.*;
import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.inventory.*;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import xpoke.space.cuboids.commands.*;
import xpoke.space.cuboids.configuration.*;
import xpoke.space.cuboids.managers.*;
import xpoke.space.cuboids.utils.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.*;

public final class Main extends JavaPlugin {

    public static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public static Economy econ = null;
    public CommandHandler commandHandler;
    public FileManager fileManager;
    public Logger getLog(){
        return log;
    }
    private static final Logger log = Logger.getLogger("Minecraft");

    @Override
    public void onEnable() {
        instance = this;
        if (!setupEconomy() ) {
            log.info(String.format("[%s] - Wyłączanie pluginu! Brak pluginu Vault!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        log.info(String.format("[%s] - Włączanie pluginu!",getDescription().getName()));
        if(getDataFolder().mkdir()){
            log.info(String.format("[%s] - Stworzono folder pluginu!", getDescription().getName()));
        }
        try {
            initializeConfigurationFiles();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info(String.format("[%s] - Inicjalizowanie eventów...",getDescription().getName()));
        for (Class<? extends Listener> listenerClass : (new Reflections(getClass().getPackage().getName() + ".events")).getSubTypesOf(Listener.class)) {
            try {
                getServer().getPluginManager().registerEvents(listenerClass.getDeclaredConstructor(new Class[0]).newInstance(), this);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
                     InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        log.info(String.format("[%s] - Inicjalizowanie komend...",getDescription().getName()));
        this.commandHandler = new CommandHandler();
        commandHandler.initializeCommands();
        log.info(String.format("[%s] - Dodawanie craftingów...",getDescription().getName()));
        ItemStack cuboid = ItemUtil.getCuboidBlock();
        NamespacedKey key = new NamespacedKey(instance, "cuboid");
        ShapedRecipe recipe = new ShapedRecipe(key, cuboid);
        recipe.shape("SSS", "SDS", "SSS");
        recipe.setIngredient('S', Material.STONE);
        recipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(recipe);
        log.info(String.format("[%s] - Ładowanie działek...",getDescription().getName()));
        CuboidManager.loadCuboids();
        DataManager.loadData();
    }

    @Override
    public void onDisable() {
        log.info(String.format("[%s] - Zapisywanie działek...",getDescription().getName()));
        CuboidManager.saveCuboids();
        DataManager.clearData();
        log.info(String.format("[%s] - Wyłączanie pluginu!", getDescription().getName()));
    }


    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

    private void initializeConfigurationFiles() throws IOException {
        this.fileManager = new FileManager();
    }

    public Economy getEcon() {
        return econ;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

}
