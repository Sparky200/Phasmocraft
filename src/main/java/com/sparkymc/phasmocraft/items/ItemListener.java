package com.sparkymc.phasmocraft.items;

import com.sparkymc.phasmocraft.Phasmocraft;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

/**
 * Event listener for events related to a {@link PhasmoItem}.
 */
public class ItemListener implements Listener {
    private final Phasmocraft plugin;

    public ItemListener(Phasmocraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        var roundHandler = plugin.getRoundHandler();

        if (!roundHandler.inRound(event.getPlayer())) return;
        var round = roundHandler.getRoundFromPlayer(event.getPlayer());
        assert round != null;

        if (event.getItem() == null) return;

        var itemKeyS = event.getItem().getItemMeta().getPersistentDataContainer().get(Phasmocraft.ITEM_KEY, PersistentDataType.STRING);
        if (itemKeyS == null) return;

        var itemKey = NamespacedKey.fromString(itemKeyS);

        var itemType = TypeRegistry.ITEMS.get(itemKey);

        itemType.onInteract(event);
    }

    @EventHandler
    public void onInteractAtEntity(PlayerInteractAtEntityEvent event) {
        var roundHandler = plugin.getRoundHandler();

        if (!roundHandler.inRound(event.getPlayer())) return;
        var round = roundHandler.getRoundFromPlayer(event.getPlayer());
        assert round != null;

        var item = event.getPlayer().getItemInUse();

        if (item == null) return;

        var itemKeyS = item.getItemMeta().getPersistentDataContainer().get(Phasmocraft.ITEM_KEY, PersistentDataType.STRING);
        if (itemKeyS == null) return;

        var itemKey = NamespacedKey.fromString(itemKeyS);

        var itemType = TypeRegistry.ITEMS.get(itemKey);

        itemType.onInteractAtEntity(event);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        var roundHandler = plugin.getRoundHandler();

        if (!roundHandler.inRound(event.getPlayer())) return;
        var round = roundHandler.getRoundFromPlayer(event.getPlayer());
        assert round != null;

        var item = event.getPlayer().getItemInUse();

        if (item == null) return;

        var itemKeyS = item.getItemMeta().getPersistentDataContainer().get(Phasmocraft.ITEM_KEY, PersistentDataType.STRING);
        if (itemKeyS == null) return;

        var itemKey = NamespacedKey.fromString(itemKeyS);

        var itemType = TypeRegistry.ITEMS.get(itemKey);

        itemType.onBlockBreak(event);
    }
}
