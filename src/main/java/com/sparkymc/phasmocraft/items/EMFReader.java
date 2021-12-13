package com.sparkymc.phasmocraft.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static com.sparkymc.phasmocraft.Utils.colorize;

public class EMFReader extends PhasmoItem {

    public EMFReader() {
        super(Material.ACACIA_BUTTON);
    }

    @Override
    public ItemStack create() {
        var stack = new ItemStack(getBase());
        var meta = stack.getItemMeta();
        assert meta != null;

        meta.setDisplayName(colorize("&fEMF Reader"));

        stack.setItemMeta(meta);
        return stack;
    }
}
