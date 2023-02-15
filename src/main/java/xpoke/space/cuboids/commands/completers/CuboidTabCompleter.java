package xpoke.space.cuboids.commands.completers;

import org.bukkit.*;
import org.bukkit.command.*;
import org.jetbrains.annotations.*;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.*;

public class CuboidTabCompleter implements TabCompleter {
    private final Set<String> arguments = new HashSet<>(Arrays.asList("dodaj", "akceptuj", "info", "opusc", "panel", "odrzuc",
            "wyrzuc", "tp", "ulepszanie"));

    @Nullable
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            if(commandSender.hasPermission("cuboids.admin")) {
                arguments.add("reload");
                arguments.add("daj");
                arguments.add("usun");
            }
            return this.arguments
                    .stream()
                    .filter(arg -> arg.toLowerCase().startsWith(args[0].toLowerCase())).collect(Collectors.toList());
        }
        return Bukkit.getOnlinePlayers()
                .stream()
                .map(CommandSender::getName).toList()
                .stream()
                .filter(arg -> arg.toLowerCase().startsWith(args[0].toLowerCase())).toList();
    }
}
