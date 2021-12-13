package com.sparkymc.phasmocraft.objects;

public interface Difficulty {
    int getLevelRequired();

    long getSetupTime();
    long getHuntGracePeriod();
    long getHuntLength();
    boolean getHasInsurance();

    float getExperienceMultiplier();
    float getCashMultiplier();

    int getSanityPillRestoreAmount();

    float getSanityDrain();
}
