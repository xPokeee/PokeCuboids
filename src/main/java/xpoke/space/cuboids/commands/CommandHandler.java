package xpoke.space.cuboids.commands;

import xpoke.space.cuboids.*;
import xpoke.space.cuboids.commands.completers.*;
import xpoke.space.cuboids.commands.implementers.*;

import java.util.*;

public class CommandHandler {

    private final Main instance = Main.getInstance();

    public void initializeCommands() {
        Objects.requireNonNull(instance.getCommand("dzialka")).setExecutor(new CuboidCommand());
        Objects.requireNonNull(instance.getCommand("dzialka")).setTabCompleter(new CuboidTabCompleter());
    }

}
