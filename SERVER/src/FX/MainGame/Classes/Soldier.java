package FX.MainGame.Classes;

import java.io.Serializable;

public abstract class Soldier implements Serializable {
    private String name;
    private int team;
    private int initialHealth;
    private int currentHealth;
    private int attack;
    private int defense;
    private String type;
    private String typeFile;

    public Soldier(String name, int team, int initialHealth, int attack, int defense, String type, String typeFile) {
        this.name = name;
        this.team = team;
        this.initialHealth = initialHealth;
        this.currentHealth = initialHealth;
        this.attack = attack;
        this.defense = defense;
        this.type = type;
        this.typeFile = typeFile;
    }

    public String getName() {
        return name;
    }

    public int getTeam() {
        return team;
    }

    public int getInitialHealth() {
        return initialHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getType() {
        return type;
    }

    public String getTypeFile() {
        return typeFile;
    }

    public void heal() {
        currentHealth++;
    }

    public int attack(Soldier other) {
        int damage = Math.max(1, attack - other.getDefense() / 2);
        other.hurt(damage);
        return damage;
    }

    public void hurt(int damage) {
        currentHealth -= damage;
    }

    public void modifyAttack(int change) {
        attack += change;
    }

    public void modifyDefense(int change) {
        defense += change;
    }

    public String toString() {
        return name;
    }
}
