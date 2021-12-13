package com.sparkymc.phasmocraft;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

import static com.sparkymc.phasmocraft.Utils.*;

public class Phasmocraft extends JavaPlugin {
    private static Phasmocraft instance;

    private final String[] startupMessages = new String[] {
            "Ghost go boo!",
            "Ghosts target those who stay in the light for too long.",
            "Did you know the EMF reader can read EMF? Cool, huh?",
            "The paramic is useless. Best to lick it."
    };

    @Override
    public void onEnable() {
        instance = this;

        log(Level.INFO, "Successfully enabled Phasmocraft.");

        String message = startupMessages[range(startupMessages.length)];
        log(Level.INFO, message);
    }

    @Override
    public void onDisable() {
        log(Level.INFO, "Successfully disabled Phasmocraft.");
    }

    public static Phasmocraft getInstance() {
        return instance;
    }
}
