package com.sparkymc.phasmocraft.items;

import com.sparkymc.phasmocraft.objects.Round;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public abstract class PhasmoItem {
    private final Material base;

    public PhasmoItem(Material base) {
        this.base = base;
    }

    public Material getBase() {
        return base;
    }


    public void onInteract(PlayerInteractEvent event) {

    }

    public void onInteractAtEntity(PlayerInteractAtEntityEvent event) {

    }

    public void onBlockBreak(BlockBreakEvent event) {

    }

    /**
     * Called every 10 ticks to prevent lag
     * @param round The round this item currently belongs to. If null, this item got out of the round somehow.
     * @param owner The owner of the item. Null if not being held by a player.
     * @param stack The stack that is this item.
     * @param entity The item entity, if the item is currently dropped.
     */
    public void onTick(Round round, Player owner, ItemStack stack, Item entity) {

    }

    public abstract ItemStack create();
}
