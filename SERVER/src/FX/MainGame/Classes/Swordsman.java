package FX.MainGame.Classes;

public class Swordsman extends Soldier {
    private static final int HEALTH = 10;
    private static final int ATTACK = 10;
    private static final int DEFENSE = 8;

    public Swordsman(String name, int team, String type, String typeFile) {
        super(name, team, HEALTH, ATTACK, DEFENSE, type, typeFile);
    }

    // Lamentablemente no llegare a implementar esto
    public void swordDance() {
        modifyAttack(1);
    }
}
