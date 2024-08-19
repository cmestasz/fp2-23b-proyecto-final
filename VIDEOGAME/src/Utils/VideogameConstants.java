package Utils;

public interface VideogameConstants {
    String[] TERRAINS = { "BOSQUE", "CAMPO ABIERTO", "MONTAÑA", "DESIERTO", "PLAYA" };
    String[] TERRAIN_FILES = { "forest", "meadow", "mountain", "desert", "beach" };
    String[] TYPES = { "CABALLERO", "ARQUERO", "ESPADACHIN", "LANCERO" };
    String[] TYPE_FILES = { "knight", "archer", "swordsman", "spearman", "tile" };

    BetterColor PLAYER_COLOR = new BetterColor(0.27, 0.51, 1, 1); // #4580ff
    BetterColor ENEMY_COLOR = new BetterColor(1, 0.27, 0.27, 1); // #ff4545
    BetterColor PLAYER_COLOR_TRANS = new BetterColor(0.27, 0.51, 1, 0.1); // #4580ff, op 10%
    BetterColor ENEMY_COLOR_TRANS = new BetterColor(1, 0.27, 0.27, 0.1); // #ff4545, op 10%
    BetterColor BACKGROUND_COLOR = new BetterColor(0.1, 0.1, 0.1, 1); // #1a1a1a
    BetterColor SELECTED_COLOR = new BetterColor(0.8, 0.8, 0.8, 0.05); // #cccccc, op 5%
    int TOTAL_SOLDIERS = 5;
    int SIZE = 10;
}
