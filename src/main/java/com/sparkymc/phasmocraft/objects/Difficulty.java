package com.sparkymc.phasmocraft.objects;

public interface Difficulty {
    int getLevelRequired();

    long getSetupTime();

    long getHuntGracePeriod();

    long getHuntLength();

    int getInsurancePercentage();

    Rarity getGhostEventRarity();

    Rarity getGhostInteractionRarity();

    Rarity getDoorsOpenOnStartRarity();

    Rarity getGhostRoomChangeRarity();

    boolean getFuseBoxOnAtStart();

    boolean getShowGhostResponsiveness();

    boolean getSuccessfulKillExtendsHuntDuration();

    boolean getFuseBoxShownOnMap();

    int getSuccessfulKillExtendsHuntDurationAmount();

    float getExperienceMultiplier();

    float getCashMultiplier();

    int getSanityPillRestoreAmount();

    float getSanityDrain();
}
