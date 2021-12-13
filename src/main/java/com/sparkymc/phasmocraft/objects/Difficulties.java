package com.sparkymc.phasmocraft.objects;

public enum Difficulties implements Difficulty {
    AMATEUR(0, 6000, 100, 1200, 50, Rarity.UNCOMMON, Rarity.VERY_COMMON, Rarity.VERY_RARE, Rarity.VERY_RARE, 1.0f, 1.0f, 40, 1.0f, true, true, false, true, 0),
    INTERMEDIATE(10, 3000, 80, 3600, 25, Rarity.COMMON, Rarity.COMMON, Rarity.UNCOMMON, Rarity.RARE, 1.5f, 2f, 35, 1.5f, false, true, false, true, 0),
    PROFESSIONAL(15, 0, 60, 6000, 0, Rarity.VERY_COMMON, Rarity.UNCOMMON, Rarity.COMMON, Rarity.RARE, 2, 3, 30, 2, false, false, false, true, 0),
    NIGHTMARE(20, 0, 40, 6600, 0, Rarity.VERY_COMMON, Rarity.UNCOMMON, Rarity.VERY_COMMON, Rarity.COMMON, 2.5f, 4, 25, 2.5f, false, false, true, false, 300);

    private final int levelRequired;
    private final long setupTime;
    private final long huntGracePeriod;
    private final long huntLength;
    private final int insurancePercentage;
    private final Rarity ghostEventRarity;
    private final Rarity ghostInteractionRarity;
    private final Rarity doorsOpenAtStartRarity;
    private final Rarity ghostRoomChangeRarity;
    private final float experienceMultiplier;
    private final float cashMultiplier;
    private final int sanityPillRestoreAmount;
    private final float sanityDrain;
    private final boolean fuseboxOnAtStart;
    private final boolean showGhostResponsiveness;
    private final boolean successfulKillExtendsHuntDuration;
    private final boolean fuseBoxShownOnMap;
    private final int successfulKillExtendsHuntAmount;


    Difficulties(int levelRequired,
                 long setupTime,
                 long huntGracePeriod,
                 long huntLength,
                 int insurancePercentage,
                 Rarity ghostEventRarity,
                 Rarity ghostInteractionRarity,
                 Rarity doorsOpenAtStartRarity,
                 Rarity ghostRoomChangeRarity,
                 float experienceMultiplier,
                 float cashMultiplier,
                 int sanityPillRestoreAmount,
                 float sanityDrain,
                 boolean fuseboxOnAtStart,
                 boolean showGhostResponsiveness,
                 boolean successfulKillExtendsHuntDuration,
                 boolean fuseBoxShownOnMap, int successfulKillExtendsHuntAmount) {
        this.levelRequired = levelRequired;
        this.setupTime = setupTime;
        this.huntGracePeriod = huntGracePeriod;
        this.huntLength = huntLength;
        this.insurancePercentage = insurancePercentage;
        this.ghostEventRarity = ghostEventRarity;
        this.ghostInteractionRarity = ghostInteractionRarity;
        this.doorsOpenAtStartRarity = doorsOpenAtStartRarity;
        this.ghostRoomChangeRarity = ghostRoomChangeRarity;

        this.experienceMultiplier = experienceMultiplier;
        this.cashMultiplier = cashMultiplier;
        this.sanityPillRestoreAmount = sanityPillRestoreAmount;
        this.sanityDrain = sanityDrain;
        this.fuseboxOnAtStart = fuseboxOnAtStart;
        this.showGhostResponsiveness = showGhostResponsiveness;
        this.successfulKillExtendsHuntDuration = successfulKillExtendsHuntDuration;
        this.fuseBoxShownOnMap = fuseBoxShownOnMap;
        this.successfulKillExtendsHuntAmount = successfulKillExtendsHuntAmount;
    }

    @Override
    public int getLevelRequired() {
        return levelRequired;
    }

    @Override
    public float getSanityDrain() {
        return sanityDrain;
    }

    @Override
    public long getSetupTime() {
        return setupTime;
    }

    @Override
    public long getHuntLength() {
        return huntLength;
    }

    @Override
    public int getInsurancePercentage() {
        return insurancePercentage;
    }

    @Override
    public Rarity getGhostEventRarity() {
        return ghostEventRarity;
    }

    @Override
    public Rarity getGhostInteractionRarity() {
        return ghostInteractionRarity;
    }

    @Override
    public Rarity getDoorsOpenOnStartRarity() {
        return doorsOpenAtStartRarity;
    }

    @Override
    public Rarity getGhostRoomChangeRarity() {
        return ghostRoomChangeRarity;
    }

    @Override
    public boolean getFuseBoxOnAtStart() {
        return fuseboxOnAtStart;
    }

    @Override
    public boolean getShowGhostResponsiveness() {
        return showGhostResponsiveness;
    }

    @Override
    public boolean getSuccessfulKillExtendsHuntDuration() {
        return successfulKillExtendsHuntDuration;
    }

    @Override
    public boolean getFuseBoxShownOnMap() {
        return fuseBoxShownOnMap;
    }

    @Override
    public int getSuccessfulKillExtendsHuntDurationAmount() {
        return successfulKillExtendsHuntAmount;
    }

    @Override
    public long getHuntGracePeriod() {
        return huntGracePeriod;
    }

    @Override
    public float getCashMultiplier() {
        return cashMultiplier;
    }

    @Override
    public float getExperienceMultiplier() {
        return experienceMultiplier;
    }

    @Override
    public int getSanityPillRestoreAmount() {
        return sanityPillRestoreAmount;
    }
}
