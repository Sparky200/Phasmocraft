package com.sparkymc.phasmocraft.items;

import org.bukkit.Material;
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

    public void onTick() {

    }
    public abstract ItemStack create();
}
