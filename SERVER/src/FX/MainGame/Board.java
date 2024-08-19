package FX.MainGame;

import FX.MainGame.Classes.*;
import Utils.*;
import java.io.Serializable;
import java.util.*;

public class Board implements Serializable, VideogameConstants {
    private final Random RANDOM = new Random();
    
    private String terrain;
    private String terrainFile;
    private BetterColor background;
    private HashMap<String, Soldier> army1 = new HashMap<String, Soldier>();
    private HashMap<String, Soldier> army2 = new HashMap<String, Soldier>();
    private String kingdomPlayer;
    private String kingdomEnemy;

    public Board(String kingdom1, String kingdom2) {
        int idxTerrain = RANDOM.nextInt(TERRAINS.length);
        terrain = TERRAINS[idxTerrain];
        terrainFile = TERRAIN_FILES[idxTerrain];

        this.kingdomPlayer = kingdom1;
        this.kingdomEnemy = kingdom2;

        initSoldiers(army1, 1);
        initSoldiers(army2, 2);
    }

    public void invertBoard() {
        HashMap<String, Soldier> armyt = army1;
        army1 = army2;
        army2 = armyt;

        String kingdomt = kingdomPlayer;
        kingdomPlayer = kingdomEnemy;
        kingdomEnemy = kingdomt;
    }

    public HashMap<String, Soldier> getArmy1() {
        return army1;
    }

    public HashMap<String, Soldier> getArmy2() {
        return army2;
    }

    public String getTerrain() {
        return terrain;
    }

    public String getTerrainFile() {
        return terrainFile;
    }

    public String getKingdomPlayer() {
        return kingdomPlayer;
    }

    public String getKingdomEnemy() {
        return kingdomEnemy;
    }

    public BetterColor getBackground() {
        return background;
    }

    private void initSoldiers(HashMap<String, Soldier> map, int team) {
        for (int i = 0; i < TOTAL_SOLDIERS; i++) {
            int idx = RANDOM.nextInt(TYPES.length);
            String type = TYPES[idx];
            String fileType = TYPE_FILES[idx];
            int row, col;
            String key;
            do {
                row = RANDOM.nextInt(SIZE);
                col = RANDOM.nextInt(SIZE);
                key = generateKey(row, col);
            } while (army1.containsKey(key) || army2.containsKey(key));
            String name = type + i + "X" + team;
            Soldier soldier = null;
            switch (type) {
                case "CABALLERO":
                    soldier = new Knight(name, team, type, fileType);
                    break;
                case "ARQUERO":
                    soldier = new Archer(name, team, type, fileType);
                    break;
                case "ESPADACHIN":
                    soldier = new Swordsman(name, team, type, fileType);
                    break;
                case "LANCERO":
                    soldier = new Spearman(name, team, type, fileType);
                    break;
            }
            map.put(key, soldier);
        }
    }

    private String generateKey(int i, int j) {
        return i + "," + j;
    }

    public String toString() {
        return "a board!";
    }
}
