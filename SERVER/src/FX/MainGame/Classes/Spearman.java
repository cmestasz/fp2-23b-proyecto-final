package FX.MainGame.Classes;

public class Spearman extends Soldier {
    private static final int HEALTH = 8;
    private static final int ATTACK = 5;
    private static final int DEFENSE = 10;

    public Spearman(String name, int team, String type, String typeFile) {
        super(name, team, HEALTH, ATTACK, DEFENSE, type, typeFile);
    }

    // Lamentablemente no llegare a implementar esto
    public void schiltrom() {
        modifyDefense(1);
    }
}
