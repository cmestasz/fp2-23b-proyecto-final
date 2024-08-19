package FX.MainGame;

import java.io.*;
import java.util.HashMap;
import FX.MainGame.Classes.Soldier;
import FX.MainMenu.MainMenuController;
import Utils.*;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class MainGameController implements MainGameOperation, VideogameConstants {
    private Stage gameStage;
    private Stage menuStage;
    private Resolution resolution;
    private int width;
    private int height;
    private MainMenuController menuController;
    private Board board;
    private String kingdomPlayer;
    private String kingdomEnemy;
    private File connectionFile;
    private String path;
    private int idConnection;
    private int idPlayer;
    private int idEnemy;
    private DataReceiver dataReceiver;
    private String matchCode;
    private String pName;
    private String eName;
    private Tile[][] tiles = new Tile[SIZE][SIZE];
    private String selectedAction = "MOVER";
    private Tile selectedTile;
    private HashMap<String, Soldier> army1;
    private HashMap<String, Soldier> army2;
    private DBConnector dbConnector;
    private boolean gameEnded;
    private boolean playerTurn = true;

    @FXML
    private GridPane uiBoard;
    @FXML
    private ImageView boardBackground;
    @FXML
    private ImageView dataBackground;
    @FXML
    private TextArea playerData;
    @FXML
    private TextArea enemyData;
    @FXML
    private ScrollPane chatOutputPane;
    @FXML
    private VBox chatOutput;
    @FXML
    private TextField chatInput;
    @FXML
    private TilePane actionsPane;
    @FXML
    private Pane messagePane;
    @FXML
    private TextArea messageOutput;
    @FXML
    private VBox moveActionPane;
    @FXML
    private VBox attackActionPane;
    @FXML
    private TextField fileNameInput;

    public void init(MainMenuController menuController, Resolution resolution, Stage menuStage, Stage gameStage,
            Board board,
            int idConnection, String matchCode, String pName, String eName, int idPlayer, int idEnemy) {
        this.menuController = menuController;
        this.resolution = resolution;
        this.width = resolution.getWidth();
        this.height = resolution.getHeight();
        this.menuStage = menuStage;
        this.gameStage = gameStage;
        this.board = board;
        army1 = board.getArmy1();
        army2 = board.getArmy2();
        this.idConnection = idConnection;
        this.kingdomPlayer = board.getKingdomPlayer();
        this.kingdomEnemy = board.getKingdomEnemy();
        this.matchCode = matchCode;
        this.pName = pName;
        this.eName = eName;
        this.idPlayer = idPlayer;
        this.idEnemy = idEnemy;

        initButtons();
        initBackground();
        initDataFields();
        initChat();

        actionsPane.setPrefWidth(width * 0.15);
        actionsPane.setPrefHeight(width * 0.05);
        setStyleColor(moveActionPane, SELECTED_COLOR);

        dbConnector = new DBConnector();
        setConnection();
    }

    public void initialize() {

    }

    public void sendMessage() {
        String message = String.format("%s: %s%n", pName, chatInput.getText());
        printMessage(message, PLAYER_COLOR);
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(connectionFile));
            out.writeInt(OPERATION_CHAT);
            Utils.writeStrings(out, new String[] { matchCode, message });
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        chatInput.setText("");
    }

    public void setActionMove() {
        setStyleColor(moveActionPane, SELECTED_COLOR);
        setStyleColor(attackActionPane, null);
        selectedAction = "MOVER";
    }

    public void setActionAttack() {
        setStyleColor(attackActionPane, SELECTED_COLOR);
        setStyleColor(moveActionPane, null);
        selectedAction = "ATACAR";
    }

    public void closeMessage() {
        messagePane.setVisible(false);
        if (gameEnded) {
            dataReceiver.endGame();
            menuStage.show();
            menuController.restartMenu();
            gameStage.close();
        }
    }

    private void initButtons() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                String key = generateKey(i, j);
                double size = 1.0 * resolution.getHeight() / SIZE;
                HashMap<String, Soldier> army1 = board.getArmy1();
                HashMap<String, Soldier> army2 = board.getArmy2();
                Tile tile;
                Soldier soldier;

                if (army1.containsKey(key)) {
                    soldier = army1.get(key);
                    tile = new Tile(soldier.getCurrentHealth(), soldier.getTypeFile(), size, i, j);
                    setStyleColor(tile, PLAYER_COLOR_TRANS);
                } else if (army2.containsKey(key)) {
                    soldier = army2.get(key);
                    tile = new Tile(soldier.getCurrentHealth(), soldier.getTypeFile(), size, i, j);
                    setStyleColor(tile, ENEMY_COLOR_TRANS);
                } else {
                    tile = new Tile(0, "tile", size, i, j);
                }
                tiles[i][j] = tile;

                tile.setOnMouseClicked(this::handleClick);
                uiBoard.add(tile, i, j);
            }
        }
    }

    public void saveMatch() {
        try {
            String fileName = fileNameInput.getText();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(String.format("data/%s.sav", fileName)));
            out.writeObject(board);
            out.close();

            showMessage("Partida guardada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setStyleColor(Region pane, BetterColor color) {
        if (color == null) {
            pane.setStyle("-fx-background-color: none;");
        } else {
            pane.setStyle(String.format("-fx-background-color: %s;", color.getRGBA()));
        }
    }

    private void initBackground() {
        boardBackground.setFitWidth(width);
        boardBackground.setFitHeight(height);
        boardBackground.setImage(new Image(String.format("img/background_%s.png", board.getTerrainFile())));

        dataBackground.setFitWidth(width - height);
        dataBackground.setFitHeight(height);
        dataBackground.setImage(new Image("img/background_data.png"));
    }

    private void initDataFields() {
        playerData.setText(String.format("%s: %s%n", pName, kingdomPlayer));
        enemyData.setText(String.format("%s: %s%n", eName, kingdomEnemy));
    }

    private void initChat() {
        chatOutput.setPrefHeight(height * 0.4);
    }

    private void handleClick(MouseEvent event) {
        Tile tile = (Tile) event.getSource();

        if (!tryDoAction(tile)) {
            if (board.getArmy1().containsKey(tile.getKey())) {
                selectedTile = tile;
                showActionsMenu();
            } else {
                selectedTile = null;
                removeActionsMenu();
            }
        }
    }

    private void showActionsMenu() {
        actionsPane.setVisible(true);
    }

    private void removeActionsMenu() {
        actionsPane.setVisible(false);
    }

    private boolean tryDoAction(Tile otherTile) {
        if (selectedTile != null && playerTurn) {
            String otherKey = otherTile.getKey();
            try {
                DataOutputStream out;
                int sI = selectedTile.getI();
                int sJ = selectedTile.getJ();
                int oI = otherTile.getI();
                int oJ = otherTile.getJ();
                Soldier selectedSoldier = army1.get(generateKey(sI, sJ));
                int distance;
                switch (selectedAction) {
                    case "MOVER":
                        distance = selectedSoldier.getTypeFile().equals("knight") ? 2 : 1;
                        if (selectedTile.isConnected(otherTile, distance) && !army1.containsKey(otherKey)
                                && !army2.containsKey(otherKey)) {
                            moveSoldier(true, sI, sJ, oI, oJ);
                            removeActionsMenu();

                            out = new DataOutputStream(new FileOutputStream(connectionFile));
                            out.writeInt(OPERATION_MOVE);
                            Utils.writeString(out, matchCode);
                            Utils.writeIdxs(out, sI, sJ, oI, oJ);
                            out.close();

                            playerTurn = false;
                            selectedTile = null;
                            out.close();
                            return true;
                        }
                        showMessage("Movimiento no valido.");
                        break;
                    case "ATACAR":
                        distance = selectedSoldier.getTypeFile().equals("archer") ? 2 : 1;
                        if (selectedTile.isConnected(otherTile, distance) && army2.containsKey(otherKey)) {
                            attackSoldier(true, sI, sJ, oI, oJ);
                            removeActionsMenu();

                            out = new DataOutputStream(new FileOutputStream(connectionFile));
                            out.writeInt(OPERATION_ATTACK);
                            Utils.writeString(out, matchCode);
                            Utils.writeIdxs(out, sI, sJ, oI, oJ);
                            out.close();

                            playerTurn = false;
                            selectedTile = null;
                            out.close();
                            return true;
                        }
                        showMessage("Ataque no valido.");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Métodos que funcionan en ambos sentidos, host -> guest o guest -> host
    private void moveSoldier(boolean isPlayer, int iSelected, int jSelected, int iOther, int jOther) {
        Tile selectedTile = tiles[iSelected][jSelected];
        Tile otherTile = tiles[iOther][jOther];
        String selectedKey = selectedTile.getKey();
        String otherKey = otherTile.getKey();

        HashMap<String, Soldier> army = null;
        BetterColor color = null;
        if (isPlayer) {
            army = army1;
            color = PLAYER_COLOR_TRANS;
        } else {
            army = army2;
            color = ENEMY_COLOR_TRANS;
        }

        Soldier soldier = army.remove(selectedKey);
        army.put(otherKey, soldier);
        selectedTile.setImageAndhealth("tile", 0);
        setStyleColor(selectedTile, null);
        otherTile.setImageAndhealth(soldier.getTypeFile(), soldier.getCurrentHealth());
        setStyleColor(otherTile, color);

        String message = soldier + " se mueve." + "\n";
        if (isPlayer)
            playerData.appendText(message);
        else
            enemyData.appendText(message);
    }

    private void attackSoldier(boolean isPlayer, int iSelected, int jSelected, int iOther, int jOther) {
        Tile selectedTile = tiles[iSelected][jSelected];
        Tile otherTile = tiles[iOther][jOther];
        String selectedKey = selectedTile.getKey();
        String otherKey = otherTile.getKey();

        Soldier soldierAttacks = null;
        Soldier soldierReceives = null;
        if (isPlayer) {
            soldierAttacks = army1.get(selectedKey);
            soldierReceives = army2.get(otherKey);
        } else {
            soldierAttacks = army2.get(selectedKey);
            soldierReceives = army1.get(otherKey);
        }

        int damage = soldierAttacks.attack(soldierReceives);
        otherTile.setImageAndhealth(soldierReceives.getTypeFile(), soldierReceives.getCurrentHealth());
        String message = String.format("%s ataca a %s con %d de daño%n", soldierAttacks, soldierReceives, damage);
        if (isPlayer)
            playerData.appendText(message);
        else
            enemyData.appendText(message);

        if (soldierReceives.getCurrentHealth() <= 0) {
            soldierAttacks.heal();
            selectedTile.setImageAndhealth(soldierAttacks.getTypeFile(), soldierAttacks.getCurrentHealth());
            otherTile.setImageAndhealth("tile", 0);
            setStyleColor(otherTile, null);

            message = soldierReceives + " ha muerto!\n";
            if (isPlayer) {
                playerData.appendText(message);
                army2.remove(otherKey);
                if (army2.size() == 0) {
                    if (idEnemy != 0)
                        dbConnector.createMatch(idPlayer, idEnemy);
                    endGame(pName, kingdomPlayer);
                }
            } else {
                enemyData.appendText(message);
                army1.remove(otherKey);
                if (army1.size() == 0) {
                    if (idEnemy != 0)
                        dbConnector.createMatch(idEnemy, idPlayer);
                    endGame(eName, kingdomEnemy);
                }
            }
        }

    }

    private void endGame(String name, String kingdom) {
        showMessage(String.format("%s ha ganado con el reino %s!", name, kingdom));
        gameEnded = true;
    }

    private void printMessage(String message, BetterColor color) {
        Text messageText = new Text(message);
        messageText.setFont(Font.font("Book Antiqua"));
        messageText.setFill(color.getColor());
        messageText.setWrappingWidth(width - height);

        ObservableList<Node> children = chatOutput.getChildren();
        children.add(children.size() - 1, messageText);
        chatOutputPane.setVvalue(1);
    }

    private void setConnection() {
        path = "connections/" + idConnection + ".dat";
        connectionFile = new File(path);
        try {
            connectionFile.createNewFile();
            dataReceiver = new DataReceiver();
            dataReceiver.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String generateKey(int i, int j) {
        return i + "," + j;
    }

    private void showMessage(String message) {
        messagePane.setVisible(true);
        messageOutput.setText(message);
    }

    private class DataReceiver extends Thread {
        private File matchFile = new File(path);
        private long lastModified = matchFile.lastModified();
        private boolean gameEnded;

        public void run() {
            try {
                while (!gameEnded) {
                    // Comprueba si el archivo de la partida ha sido modificado
                    if (matchFile.lastModified() != lastModified) {
                        DataInputStream in = new DataInputStream(new FileInputStream(matchFile));
                        int response = in.readInt();
                        switch (response) {
                            // Mensaje de chat
                            case RESPONSE_CHAT:
                                String message = Utils.readString(in);
                                Platform.runLater(() -> {
                                    printMessage(message, ENEMY_COLOR);
                                });
                                break;

                            // Movimientos y ataques
                            case RESPONSE_MOVE:
                            case RESPONSE_ATTACK:
                                int sI = in.readInt();
                                int sJ = in.readInt();
                                int oI = in.readInt();
                                int oJ = in.readInt();

                                Platform.runLater(() -> {
                                    showActionsMenu();
                                    playerTurn = true;
                                    if (response == RESPONSE_MOVE)
                                        moveSoldier(false, sI, sJ, oI, oJ);
                                    else
                                        attackSoldier(false, sI, sJ, oI, oJ);
                                });
                                break;
                        }
                        lastModified = matchFile.lastModified();
                        in.close();
                    }

                    sleep(500);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void endGame() {
            gameEnded = true;
        }
    }
}