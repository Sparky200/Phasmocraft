package com.sparkymc.phasmocraft.objects;

import org.bukkit.block.Block;

public class EMFSource {

    private final Block source;
    private final EMFCause cause;
    private final int level;
    private final float range;
    private final long fadeTime;

    /**
     * Constructs a new EMF source with the provided information.
     * @param source The source of the EMF change
     * @param cause The cause of the EMF change
     * @param level The level, from 1-5, of the EMF change
     * @param range The range/radius of the EMF change, that will be picked up by the EMF reader.
     * @param fadeTime The time (in ticks) until the source fades
     */
    public EMFSource(Block source, EMFCause cause, int level, float range, long fadeTime) {
        this.source = source;
        this.cause = cause;
        this.level = level;
        this.range = range;
        this.fadeTime = fadeTime;
    }

    /**
     * Constructs a new EMF source with the provided information. The fade time will be taken from the cause.
     * @param source The source of the EMF change
     * @param cause The cause of the EMF change
     * @param level The level, from 1-5, of the EMF change
     * @param range The range/radius of the EMF change, that will be picked up by the EMF reader.
     */
    public EMFSource(Block source, EMFCause cause, int level, float range) {
        this(source, cause, level, range, cause.getDefaultTime());
    }

    /**
     * Gets the source of the EMF.
     */
    public Block getSource() {
        return source;
    }

    /**
     * Gets the cause of the EMF.
     */
    public EMFCause getCause() {
        return cause;
    }

    /**
     * Gets the level of the EMF.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Gets the range of the EMF.
     */
    public float getRange() {
        return range;
    }

    /**
     * Gets the fade time of the EMF.
     */
    public long getFadeTime() {
        return fadeTime;
    }
}
