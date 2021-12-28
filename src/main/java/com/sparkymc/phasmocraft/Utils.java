package com.sparkymc.phasmocraft;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

    public static void log(Level level, String format, Object arg) {
        log(level, format.formatted(arg));
    }

    public static void log(Level level, String format, Object arg0, Object arg1) {
        log(level, format.formatted(arg0, arg1));
    }

    public static void log(Level level, String format, Object... args) {
        log(level, format.formatted(args));
    }

    public static void log(String format, Object arg) {
        log(Level.INFO, format, arg);
    }

    public static void log(String format, Object arg0, Object arg1) {
        log(Level.INFO, format, arg0, arg1);
    }

    public static void log(String format, Object... args) {
        log(Level.INFO, format, args);
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

    public static void sendInfo(CommandSender sender, String message) {
        sender.sendMessage(colorize("&5Phasmocraft &7≫ &b" + message));
    }

    public static void sendInfo(CommandSender sender, String format, Object arg) {
        sendInfo(sender, format.formatted(arg));
    }

    public static void sendInfo(CommandSender sender, String format, Object arg0, Object arg1) {
        sendInfo(sender, format.formatted(arg0, arg1));
    }

    public static void sendInfo(CommandSender sender, String format, Object... args) {
        sendInfo(sender, format.formatted(args));
    }

    public static void sendSuccess(CommandSender sender, String message) {
        sender.sendMessage(colorize("&5Phasmocraft &7≫ &a" + message));
    }

    public static void sendSuccess(CommandSender sender, String format, Object arg) {
        sendSuccess(sender, format.formatted(arg));
    }

    public static void sendSuccess(CommandSender sender, String format, Object arg0, Object arg1) {
        sendSuccess(sender, format.formatted(arg0, arg1));
    }

    public static void sendSuccess(CommandSender sender, String format, Object... args) {
        sendSuccess(sender, format.formatted(args));
    }

    public static void sendWarning(CommandSender sender, String message) {
        sender.sendMessage(colorize("&5Phasmocraft &7≫ &cWarning: " + message));
    }

    public static void sendWarning(CommandSender sender, String format, Object arg) {
        sendWarning(sender, format.formatted(arg));
    }

    public static void sendWarning(CommandSender sender, String format, Object arg0, Object arg1) {
        sendWarning(sender, format.formatted(arg0, arg1));
    }

    public static void sendWarning(CommandSender sender, String format, Object... args) {
        sendWarning(sender, format.formatted(args));
    }

    public static void sendError(CommandSender sender, Throwable t) {
        sender.sendMessage(colorize("&5Phasmocraft &7≫ &4Error: " + t.getMessage()));
    }

    public static void sendError(CommandSender sender, String message) {
        sender.sendMessage(colorize("&5Phasmocraft &7≫ &4Error: " + message));
    }

    public static void sendError(CommandSender sender, String format, Object arg) {
        sendError(sender, format.formatted(arg));
    }

    public static void sendError(CommandSender sender, String format, Object arg0, Object arg1) {
        sendError(sender, format.formatted(arg0, arg1));
    }

    public static void sendError(CommandSender sender, String format, Object... args) {
        sendError(sender, format.formatted(args));
    }
}
