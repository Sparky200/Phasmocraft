package com.sparkymc.phasmocraft.objects;

public enum Difficulties implements Difficulty {
    AMATEUR(0,6000,100,1200,true,1.0f,1.0f,40,1.0f),
    INTERMEDIATE(10,3000,80,3600,false,1.5f,2f,35,1.5f),
    PROFESSIONAL(15,0,60,6000,false,2,3,30,2),
    NIGHTMARE(20,0, 40,6600,false,2.5f,4,25,2.5f);

    private final int levelRequired;

    private final long setupTime;
    private final long huntGracePeriod;
    private final long huntLength;
    private final boolean hasInsurance;

    private final float experienceMultiplier;
    private final float cashMultiplier;

    private final int sanityPillRestoreAmount;

    private final float sanityDrain;

    Difficulties(int levelRequired, long setupTime, long huntGracePeriod, long huntLength, boolean hasInsurance, float experienceMultiplier, float cashMultiplier, int sanityPillRestoreAmount, float sanityDrain) {
        this.levelRequired = levelRequired;
        this.setupTime = setupTime;
        this.huntGracePeriod = huntGracePeriod;
        this.huntLength = huntLength;
        this.hasInsurance = hasInsurance;

        this.experienceMultiplier = experienceMultiplier;
        this.cashMultiplier = cashMultiplier;
        this.sanityPillRestoreAmount = sanityPillRestoreAmount;
        this.sanityDrain = sanityDrain;
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
    public long getHuntGracePeriod() {
        return huntGracePeriod;
    }

    @Override
    public boolean getHasInsurance() {
        return hasInsurance;
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
