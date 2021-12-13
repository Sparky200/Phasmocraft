package com.sparkymc.phasmocraft.objects;

import org.bukkit.block.Block;

public class EMFSource {


    /**
     * Constructs a new EMF source with the provided information.
     * @param source The source of the EMF change
     * @param cause The cause of the EMF change
     * @param level The level, from 1-5, of the EMF change
     * @param range The range/radius of the EMF change, that will be picked up by the EMF reader.
     * @param fadeTime The time (in ticks) until the source fades
     */
    public EMFSource(Block source, EMFCause cause, int level, float range, long fadeTime) {

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
}
