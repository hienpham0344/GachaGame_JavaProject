package com.common;

public enum Rarity {
    COMMON(0.7),
    RARE(0.29),
    LEGENDARY(0.01);

    private final double DropRate;
    Rarity(double DropRate){
        this.DropRate = DropRate;
    }
    public double getDropRate(){ return DropRate; }
}
