package com.sparkymc.phasmocraft.items;

import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

import static com.sparkymc.phasmocraft.Utils.log;

public class TypeRegistry<T> implements Iterable<Map.Entry<NamespacedKey, T>> {

    public static final TypeRegistry<PhasmoItem> ITEMS = new TypeRegistry<>();

    public static final EMFReader EMF_READER = register(new NamespacedKey("phasmocraft", "emf_reader"), new EMFReader());

    private static <T extends PhasmoItem> T register(NamespacedKey key, T item) {
        log(Level.INFO, key.asString() + " << " + item.getClass().getSimpleName());
        ITEMS.set(key, item);
        return item;
    }

    private final Map<NamespacedKey, T> map = new HashMap<>();

    public T get(NamespacedKey key) {
        return map.get(key);
    }

    public <T1 extends T> T get(NamespacedKey key, Class<T1> clazz) {
        return clazz.cast(map.get(key));
    }

    public T set(NamespacedKey key, T t) {
        map.put(key, t);
        return t;
    }

    public int size() {
        return map.size();
    }

    @NotNull
    @Override
    public Iterator<Map.Entry<NamespacedKey, T>> iterator() {
        return map.entrySet().iterator();
    }
}
