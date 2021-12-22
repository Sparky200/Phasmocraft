package com.sparkymc.phasmocraft;

import net.md_5.bungee.api.ChatColor;

import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static final Pattern HEX_PATTERN = Pattern.compile("<#([A-Fa-f0-9]){6}>");

    static Phasmocraft main = Phasmocraft.getInstance();

    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    public static int range(int max) {
        return range(0, max);
    }

    public static int range(int min, int max) {
        return getRandom().nextInt(min, max);
    }

    public static void log(Level level, String message) {
        main.getLogger().log(level,colorize(message));
    }

    public static void log(Level level, Object object) {
        log(level, String.valueOf(object));
    }

    public static void log(String message) {
        log(Level.INFO, message);
    }

    public static void log(Object object) {
        log(Level.INFO, object);
    }

    public static String colorize(String string) {
        Matcher matcher = HEX_PATTERN.matcher(string);
        while (matcher.find()) {
            final net.md_5.bungee.api.ChatColor hexColor = net.md_5.bungee.api.ChatColor.of(matcher.group().substring(1, matcher.group().length() - 1));
            final String before = string.substring(0, matcher.start());
            final String after = string.substring(matcher.end());
            string = before + hexColor + after;
            matcher = HEX_PATTERN.matcher(string);
        }
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
