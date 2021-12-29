package com.sparkymc.phasmocraft.util;

/**
 * Due to {@link org.bukkit.Location} including a {@link org.bukkit.World}, this class is used for locations not
 * attached to a world in any way.
 */
public interface IntTuple {
    int getX();
    int getY();
    int getZ();

    static IntTuple of(int x, int y, int z) {
        return new IntTuple() {
            @Override
            public int getX() {
                return x;
            }

            @Override
            public int getY() {
                return y;
            }

            @Override
            public int getZ() {
                return z;
            }

            @Override
            public String toString() {
                return "[" + getX() + ", " + getY() + ", " + getZ() + "]";
            }

            @Override
            public boolean equals(Object obj) {
                if (!(obj instanceof IntTuple other)) return false;
                return other.getX() == getX() && other.getY() == getY() && other.getZ() == getZ();
            }
        };
    }
}
