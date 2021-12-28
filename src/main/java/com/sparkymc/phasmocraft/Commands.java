package com.sparkymc.phasmocraft;

import com.sparkymc.phasmocraft.items.TypeRegistry;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicInteger;

import static com.sparkymc.phasmocraft.Utils.sendInfo;
import static com.sparkymc.phasmocraft.Utils.sendSuccess;

public class Commands {
    private static final Phasmocraft main = Phasmocraft.getInstance();

    public static void register() {
        var roundHandler = main.getRoundHandler();
        new CommandAPICommand("phasmocraft")
                .withAliases("phasmo")
                .executes((sender, objects) -> {
                    // Send help here
                })
                .withSubcommand(new CommandAPICommand("reload")
                        .withPermission("phasmocraft.admin.reload")
                        .executes((sender, objects) -> {
                            // reload shits
                        }))
                /*
                 * ITEM COMMANDS
                 */
                .withSubcommand(new CommandAPICommand("items")
                        .executes((sender, objects) -> {
                            // Items help here
                        })
                        .withSubcommand(new CommandAPICommand("give")
                                .withPermission("phasmocraft.admin.items.give.others")
                                .withArguments(new StringArgument("item").replaceSuggestions(suggestionInfo -> {
                                    String[] strings = new String[TypeRegistry.ITEMS.size()];
                                    AtomicInteger i = new AtomicInteger();
                                    TypeRegistry.ITEMS.forEach(key -> strings[i.getAndIncrement()] = key.getKey().getKey());
                                    return strings;
                                }), new PlayerArgument("target"))
                                .executes((sender, objects) -> {
                                    var keyStr = (String) objects[0];
                                    var target = (Player) objects[1];
                                    var key = NamespacedKey.fromString("phasmocraft:" + keyStr);
                                    var itemType = TypeRegistry.ITEMS.get(key);
                                    target.getInventory().addItem(itemType.create());
                                }))
                        .withSubcommand(new CommandAPICommand("give")
                                .withPermission("phasmocraft.admin.items.give")
                                .withArguments(new StringArgument("item").replaceSuggestions(suggestionInfo -> {
                                    String[] strings = new String[TypeRegistry.ITEMS.size()];
                                    AtomicInteger i = new AtomicInteger();
                                    TypeRegistry.ITEMS.forEach(key -> strings[i.getAndIncrement()] = key.getKey().getKey());
                                    return strings;
                                }))
                                .executesPlayer((sender, objects) -> {
                                    var keyStr = (String) objects[0];
                                    var key = NamespacedKey.fromString("phasmocraft:" + keyStr);
                                    var itemType = TypeRegistry.ITEMS.get(key);
                                    sender.getInventory().addItem(itemType.create());
                                })))
                /*
                 * ROUND COMMANDS
                 */
                .withSubcommand(new CommandAPICommand("round")
                        .withSubcommand(new CommandAPICommand("create")
                                .executesPlayer((sender, objects) -> {
                                    sendInfo(sender, "Creating new round");
                                    var round = roundHandler.createRound(sender);
                                    sendSuccess(sender, "Created round with ID %s", round.getId());
                                }))
                        .withSubcommand(new CommandAPICommand("edit")
                                .executesPlayer((sender, objects) -> {
                                    // Edit round
                                }))
                        .withSubcommand(new CommandAPICommand("start")
                                .executesPlayer((sender, objects) -> {
                                    // Start round
                                }))).register();
    }
}
