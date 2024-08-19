package FX.MainGame.Classes;

public class Knight extends Soldier {
    private static final int HEALTH = 3;
    private static final int ATTACK = 10;
    private static final int DEFENSE = 7;
    private boolean mounted = false;

    public Knight(String name, int team, String type, String typeFile) {
        super(name, team, HEALTH, ATTACK, DEFENSE, type, typeFile);
    }

    // Lamentablemente no llegare a implementar esto
    public void mount() {
        mounted = true;
        modifyAttack(1);
        modifyDefense(-1);
    }

    // Lamentablemente no llegare a implementar esto
    public void dismount() {
        mounted = false;
        modifyAttack(-1);
        modifyDefense(1);
    }

    public boolean isMounted() {
        return mounted;
    }
}
