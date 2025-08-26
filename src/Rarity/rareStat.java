package Rarity;

public enum rareStat {
    COMMON(0.5),
    UNCOMMON(0.25),
    RARE(0.15),
    LEGENDARY(0.99),
    MYTHICAL(0.01);

    private final double dropRate;
    rareStat(double dropRate)
    {
        this.dropRate = dropRate;
    }
    public double getDroprate() {return dropRate;}
}
