package com.sparkymc.phasmocraft;

import com.sparkymc.phasmocraft.items.ItemListener;
import com.sparkymc.phasmocraft.items.TypeRegistry;
import com.sparkymc.phasmocraft.map.MapLoader;
import com.sparkymc.phasmocraft.map.PhasmoMap;
import com.sparkymc.phasmocraft.util.IntTuple;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIConfig;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

import static com.sparkymc.phasmocraft.Utils.log;
import static com.sparkymc.phasmocraft.Utils.range;

public class Phasmocraft extends JavaPlugin {
    public static final NamespacedKey ITEM_KEY = new NamespacedKey("phasmocraft", "item");
    private static Phasmocraft instance;

    private RoundHandler roundHandler;

    private final String[] startupMessages = new String[] {
            "Ghost go boo!",
            "Ghosts target those who stay in the light for too long.",
            "Did you know the EMF reader can read EMF? Cool, huh?",
            "The paramic is useless. Best to lick it.",
            "What are you gonna do, bleed on me?",
            "Wait- this isn't a ghost game!",
            "Also try Phasmophobia!",
            "Spoooooooooooooooooooooky ~o~",
            "Why is my flashlight freaking out? *dies*",
            "Hey ghost, want some beer?",
            "Ummmm... why is the phone ringing?",
            "You said you were in the dining room, so explain why you wrote in a book in the basement."
    };

    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIConfig().silentLogs(true));
    }

    @Override
    public void onEnable() {
        instance = this;
        log(Level.INFO, startupMessages[range(startupMessages.length)]);

        CommandAPI.onEnable(this);

        roundHandler = new RoundHandler();

        Commands.register();

        // Register listeners for items
        getServer().getPluginManager().registerEvents(new ItemListener(this), this);

        // Every 10 ticks, tick items in player inventories
        getServer().getScheduler().runTaskTimer(this, () -> getServer().getOnlinePlayers().forEach(player -> {
            var round = roundHandler.getRoundFromPlayer(player);
            if (round == null) return;

            player.getInventory().forEach(stack -> {
                if (stack == null) return;
                var stackKeyS = stack.getItemMeta().getPersistentDataContainer().get(ITEM_KEY, PersistentDataType.STRING);
                if (stackKeyS == null) return;

                var stackType = TypeRegistry.ITEMS.get(NamespacedKey.fromString(stackKeyS));
                if (stackType == null) return;

                stackType.onTick(round, player, stack, null);
            });
        }), 10, 10);

        // Every 10 ticks, tick ground items
        getServer().getScheduler().runTaskTimer(this, () -> {
            // TODO: get entities and run tick. method should end up being "stackType.onTick(round, null, stack, entity);"
        }, 5, 10);
        getServer().getScheduler().runTaskTimer(this, () -> {
            for (var round : roundHandler) round.tickRound();
        }, 10, 10);
        log(Level.INFO, TypeRegistry.ITEMS.size() + " items were successfully registered.");
        log(Level.INFO, "Successfully enabled Phasmocraft.");
    }

    @Override
    public void onDisable() {
        roundHandler.dispose();

        log(Level.INFO, "Successfully disabled Phasmocraft.");
    }

    public static Phasmocraft getInstance() {
        return instance;
    }

    public RoundHandler getRoundHandler() {
        return roundHandler;
    }
}
