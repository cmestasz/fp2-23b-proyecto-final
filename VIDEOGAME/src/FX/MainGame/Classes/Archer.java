package FX.MainGame.Classes;

public class Archer extends Soldier {
    private static final int HEALTH = 5;
    private static final int ATTACK = 7;
    private static final int DEFENSE = 3;
    private int arrows = 10;

    public Archer(String name, int team, String type, String typeFile) {
        super(name, team, HEALTH, ATTACK, DEFENSE, type, typeFile);
    }

    // Lamentablemente no llegare a implementar esto
    public void shoot(Soldier other) {
        attack(other);
        arrows--;
    }

    public int getArrows() {
        return arrows;
    }
}
