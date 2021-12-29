package com.sparkymc.phasmocraft.map;

import com.sparkymc.phasmocraft.util.IntTuple;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class PhasmoMap {
    private final String title;
    private final Map<IntTuple, BlockData> blocks;
    private final IntTuple spawnLocation;

    public PhasmoMap(String title, Map<IntTuple, BlockData> map, IntTuple spawnLocation) {
        this.title = title;
        this.blocks = Map.copyOf(map);
        this.spawnLocation = spawnLocation;
    }

    public String getTitle() {
        return title;
    }

    public IntTuple getSpawnLocation() {
        return spawnLocation;
    }

    public void load(World world, @Nullable IntTuple offset) {
        var finalOffset = offset != null ? offset : IntTuple.of(0, 0, 0);
        blocks.forEach((loc, blockData) -> {
            world.setBlockData(loc.getX() + finalOffset.getX(), loc.getY() + finalOffset.getY(), loc.getZ() + finalOffset.getZ(), blockData);
        });
    }

    public Map<IntTuple, BlockData> getBlocks() {
        return blocks;
    }
}
