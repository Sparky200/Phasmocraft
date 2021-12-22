package com.sparkymc.phasmocraft;

import com.sparkymc.phasmocraft.items.ItemListener;
import com.sparkymc.phasmocraft.items.TypeRegistry;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.logging.Level;

import static com.sparkymc.phasmocraft.Utils.*;

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
    public void onEnable() {
        instance = this;

        String message = startupMessages[range(startupMessages.length)];
        log(Level.INFO, message);

        roundHandler = new RoundHandler();
        getServer().getPluginManager().registerEvents(new ItemListener(this), this);
        getServer().getScheduler().runTaskTimer(this, () -> {
            getServer().getOnlinePlayers().forEach(player -> {
                var round = roundHandler.getRoundFromPlayer(player);
                log(Level.INFO, round);
                if (round == null) return;

                player.getInventory().forEach(stack -> {
                    var stackKeyS = stack.getItemMeta().getPersistentDataContainer().get(ITEM_KEY, PersistentDataType.STRING);
                    log(Level.INFO, stackKeyS);
                    if (stackKeyS == null) return;

                    var stackType = TypeRegistry.ITEMS.get(NamespacedKey.fromString(stackKeyS));
                    if (stackType == null) return;
                    log(Level.INFO, stackType.toString());

                    stackType.onTick(round, player, stack, null);
                });
            });
        }, 10, 10);
        getServer().getScheduler().runTaskTimer(this, () -> {
            // TODO: get entities and run tick. method should end up being "stackType.onTick(round, null, stack, entity);"
        }, 5, 10);

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

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return true;

        switch (command.getName()) {
            case "giveitem" -> {
                if (args.length != 1) throw new IllegalStateException();
                var key = NamespacedKey.fromString(args[0]);
                var itemType = TypeRegistry.ITEMS.get(key);
                player.getInventory().addItem(itemType.create());
            }
            case "startround" -> {
                roundHandler.startRound(player);
            }
        }
        return true;
    }
}
