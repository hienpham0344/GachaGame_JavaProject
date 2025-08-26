package PlayerItem.Weapon;
import Rarity.rareStat;

public enum Sword {
    WOODEN("Wooden Sword", 5.0, rareStat.COMMON),
    STONE("Stone Sword", 7.5, rareStat.UNCOMMON),
    RUSTED("Rusted Sword", 7.5, rareStat.UNCOMMON),
    IRON("Iron Sword", 10.0, rareStat.RARE),
    DIAMOND("Diamond Sword", 12.5, rareStat.LEGENDARY),
    DRAGON("Dragon Sword", 15.0, rareStat.MYTHICAL);


    Sword(String s, double v, rareStat rareStat) {
        weaponName =
    }
}
