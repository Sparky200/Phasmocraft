package com.sparkymc.phasmocraft.objects;

public enum EMFCauses implements EMFCause {
    GHOST_EVENT(200),
    GHOST_INTERACTION(100),
    HUNT(600);

    private final long defaultTime;

    EMFCauses(long defaultTime) {
        this.defaultTime = defaultTime;
    }

    @Override
    public long getDefaultTime() {
        return defaultTime;
    }
}
