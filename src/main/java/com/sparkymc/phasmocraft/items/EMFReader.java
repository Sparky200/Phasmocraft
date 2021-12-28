package com.sparkymc.phasmocraft.items;

import com.sparkymc.phasmocraft.Phasmocraft;
import com.sparkymc.phasmocraft.objects.EMFSource;
import com.sparkymc.phasmocraft.objects.Round;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Comparator;
import java.util.logging.Level;

import static com.sparkymc.phasmocraft.Utils.colorize;
import static com.sparkymc.phasmocraft.Utils.log;

public class EMFReader extends PhasmoItem {
    /**
     * The offset for the custom model data, if using a resource pack.
     * An offset of 0 will put the 5 EMF Reader textures at 1-5.
     */
    private static final int CUSTOM_MODEL_DATA_OFFSET = 0;

    /**
     * The persistent data key for EMF level on an item.
     */
    private static final NamespacedKey EMF_LEVEL = new NamespacedKey(Phasmocraft.getInstance(), "emf_level");

    public EMFReader() {
        super(Material.STICK);
    }

    @Override
    public ItemStack create() {
        var stack = new ItemStack(getBase());
        var meta = stack.getItemMeta();
        assert meta != null;

        meta.displayName(Component.text(colorize("&fEMF Reader")));
        meta.getPersistentDataContainer().set(Phasmocraft.ITEM_KEY, PersistentDataType.STRING, "phasmocraft:emf_reader");

        stack.setItemMeta(meta);
        return stack;
    }

    @Override
    public void onTick(Round round, Player owner, ItemStack stack, Item entity) {
        if (round == null) return;
        var meta = stack.getItemMeta();
        var container = meta.getPersistentDataContainer();

        Entity actual = owner != null ? owner : entity;
        assert actual != null;
        round.getEmfSources().stream()
                .filter(source ->
                        Math.sqrt(source.getSource().getLocation().distanceSquared(actual.getLocation())) <= source.getRange())
                .max(Comparator.comparingInt(EMFSource::getLevel))
                .ifPresent(highestSource ->
                        container.set(EMF_LEVEL, PersistentDataType.INTEGER, highestSource.getLevel()));

        var emfLevel = container.get(EMF_LEVEL, PersistentDataType.INTEGER);
        if (emfLevel == null) container.set(EMF_LEVEL, PersistentDataType.INTEGER, emfLevel = 1);
        if (meta.hasCustomModelData() && meta.getCustomModelData() != emfLevel) {
            meta.setCustomModelData(CUSTOM_MODEL_DATA_OFFSET + emfLevel);
            stack.setItemMeta(meta);
        }

        if (emfLevel == 1) return;
        Location location = actual.getLocation();
        round.getWorld().playSound(location, Sound.BLOCK_NOTE_BLOCK_FLUTE, 0.5f, emfLevel / 5.0f);
    }
}
