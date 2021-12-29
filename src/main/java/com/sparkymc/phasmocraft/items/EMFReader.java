package com.sparkymc.phasmocraft.items;

import com.sparkymc.phasmocraft.Phasmocraft;
import com.sparkymc.phasmocraft.objects.EMFSource;
import com.sparkymc.phasmocraft.objects.Round;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Comparator;

import static com.sparkymc.phasmocraft.Utils.colorize;

public class EMFReader extends PhasmoItem {
    /**
     * The offset for the custom model data, if using a resource pack.
     * An offset of 0 will put the 5 EMF Reader textures at 1-6.
     * Custom Model Data:
     * - 1: Off
     * - 2: No EMF
     * - 3: EMF Level 2
     * - 4: EMF Level 3
     * - 5: EMF Level 4
     * - 6: EMF Level 5
     */
    private static final int CUSTOM_MODEL_DATA_OFFSET = 0;

    /**
     * The persistent data key for EMF level on an item.
     */
    private static final NamespacedKey EMF_LEVEL = new NamespacedKey(Phasmocraft.getInstance(), "emf_level");

    /**
     * The persistent data key for whether the EMF Reader is on.
     */
    private static final NamespacedKey EMF_ON = new NamespacedKey(Phasmocraft.getInstance(), "emf_on");

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

        meta.setCustomModelData(CUSTOM_MODEL_DATA_OFFSET + 1);
        stack.setItemMeta(meta);
        return stack;
    }

    @Override
    public void onTick(Round round, Player owner, ItemStack stack, Item entity) {
        if (round == null) return;
        var meta = stack.getItemMeta();
        var container = meta.getPersistentDataContainer();

        if (container.getOrDefault(EMF_ON, PersistentDataType.INTEGER, 0) == 0) return;

        Entity actual = owner != null ? owner : entity;
        assert actual != null;
        round.getEmfSources().stream()
                .filter(source ->
                        Math.sqrt(source.getSource().getLocation().distanceSquared(actual.getLocation())) <= source.getRange())
                .max(Comparator.comparingInt(EMFSource::getLevel))
                .ifPresentOrElse(highestSource ->
                        container.set(EMF_LEVEL, PersistentDataType.INTEGER, highestSource.getLevel()), () -> container.set(EMF_LEVEL, PersistentDataType.INTEGER, 1));

        var emfLevel = container.get(EMF_LEVEL, PersistentDataType.INTEGER);
        if (emfLevel == null) container.set(EMF_LEVEL, PersistentDataType.INTEGER, emfLevel = 1);
        if ((meta.hasCustomModelData() && meta.getCustomModelData() != emfLevel + 1) || !meta.hasCustomModelData()) {
            meta.setCustomModelData(CUSTOM_MODEL_DATA_OFFSET + emfLevel + 1);
            stack.setItemMeta(meta);
        }

        if (emfLevel == 1) return;
        Location location = actual.getLocation();
        round.getWorld().playSound(location, Sound.BLOCK_NOTE_BLOCK_FLUTE, 0.5f, emfLevel / 5.0f);
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {
        var player = event.getPlayer();
        var item = event.getItem();
        assert item != null;
        var meta = item.getItemMeta();
        if (event.getHand() != EquipmentSlot.HAND) return;
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            int readerOn = meta.getPersistentDataContainer().getOrDefault(EMF_ON, PersistentDataType.INTEGER, 0);
            if (readerOn == 0) {
                readerOn = 1;
                meta.setCustomModelData(2);
                player.getWorld().playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, SoundCategory.PLAYERS, 1, 1);
            } else {
                readerOn = 0;
                meta.setCustomModelData(1);
                player.getWorld().playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, SoundCategory.PLAYERS, 1, 1);
            }
            meta.getPersistentDataContainer().set(EMF_ON, PersistentDataType.INTEGER, readerOn);
            item.setItemMeta(meta);
        }
    }
}
