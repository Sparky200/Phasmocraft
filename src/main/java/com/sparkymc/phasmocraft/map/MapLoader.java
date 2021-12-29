package com.sparkymc.phasmocraft.map;

import com.sparkymc.phasmocraft.util.IntTuple;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.nio.file.Files.*;

/**
 * File format:
 * File Format Version (12 bytes, 3 ints) (ex: 1.2.1)
 * Map Title (? bytes, stops when given {@link Character#MIN_VALUE})
 * Map Spawn Location (12 bytes, 3 ints)
 * Blocks (not known yet, at least ? bytes (material name) + 12 bytes (block location))
 */
public final class MapLoader {
    private static final Path MAP_DIRECTORY = Path.of("./plugins/Phasmocraft/maps");

    private MapLoader() {
        //no instance
    }

    public static PhasmoMap loadMap(String mapName) throws IOException {
        createDirectoryIfEmpty(MAP_DIRECTORY);
        var mapPath = Path.of(MAP_DIRECTORY.toString(), mapName + ".pcmap");
        if (!exists(mapPath)) throw new FileNotFoundException("Map %s not found!".formatted(mapName));

        var bytes = ByteBuffer.wrap(Files.readAllBytes(mapPath));

        var formatVersion = IntTuple.of(bytes.getInt(), bytes.getInt(), bytes.getInt());

        var mapTitle = new StringBuilder();
        readString(bytes, mapTitle);

        var mapSpawnLocation = IntTuple.of(bytes.getInt(), bytes.getInt(), bytes.getInt());

        var blocks = new HashMap<IntTuple, BlockData>();
        while (bytes.hasRemaining()) {
            var blockLocation = IntTuple.of(bytes.getInt(), bytes.getInt(), bytes.getInt());

            var blockName = new StringBuilder();
            readString(bytes, blockName);



            var material = Material.getMaterial(blockName.toString());
            if (material == null) material = Material.STONE;

            blocks.put(blockLocation, material.createBlockData());
        }

        return new PhasmoMap(mapTitle.toString(), blocks, mapSpawnLocation);
    }

    public static void saveMap(PhasmoMap map, String fileName) throws IOException {
        createDirectoryIfEmpty(MAP_DIRECTORY);
        var mapPath = Path.of(MAP_DIRECTORY.toString(), fileName + ".pcmap");
        if (!exists(mapPath)) createFile(mapPath);

        var blocksSize = new AtomicInteger();
        map.getBlocks().forEach((tuple, block) -> {
            blocksSize.set(blocksSize.get() + Integer.BYTES * 3 + block.getMaterial().name().length() * Character.BYTES + Character.BYTES);
        });

        var buffer = ByteBuffer.allocate(12 /* Format Version */ + map.getTitle().length() * Character.BYTES + Character.BYTES /* Map Title + character min value */ + 12 /* Map Spawn */ + blocksSize.get() /* Blocks */);
        buffer.putInt(1).putInt(0).putInt(0);
        for (var c : map.getTitle().toCharArray()) buffer.putChar(c);
        buffer.putChar(Character.MIN_VALUE);

        var spawnLocation = map.getSpawnLocation();
        buffer.putInt(spawnLocation.getX()).putInt(spawnLocation.getY()).putInt(spawnLocation.getZ());

        map.getBlocks().forEach((tuple, block)  -> {
            buffer.putInt(tuple.getX()).putInt(tuple.getY()).putInt(tuple.getZ());
            for (var c : block.getMaterial().name().toCharArray()) buffer.putChar(c);
            buffer.putChar(Character.MIN_VALUE);
        });

        Files.write(mapPath, buffer.array());
    }

    private static void createDirectoryIfEmpty(Path directory) throws IOException {
        if (!exists(directory)) createDirectories(directory);
    }

    private static void readString(ByteBuffer buffer, StringBuilder out) {
        char c;
        while ((c = buffer.getChar()) != Character.MIN_VALUE) out.append(c);
    }
}
