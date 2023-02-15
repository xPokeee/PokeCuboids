package xpoke.space.cuboids.utils;

import net.md_5.bungee.api.*;

import java.util.regex.*;

public class ColorUtil {

    private static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static String hexColor(String text) {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String color = text.substring(matcher.start(), matcher.end());
            text = text.replace(color, String.valueOf(ChatColor.of(color)));
            matcher = pattern.matcher(text);
        }
        return color(text);
    }

}
