package FX.MainMenu;

import Utils.*;
import java.io.*;
import FX.MainGame.Board;
import FX.MainGame.MainGameController;
import javafx.application.*;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainMenuController implements MainMenuOperation {
    private final ObservableList<Resolution> RESOLUTIONS = FXCollections.observableArrayList();
    private final ObservableList<String> KINGDOMS = FXCollections.observableArrayList();
    private final int CODE_LENGTH = 6;

    private String pName;
    private String eName;
    private String pKingdom;
    private String eKingdom;
    private Resolution resolution;
    private int idConnection;
    private int idPlayer;
    private int idEnemy;
    private String path;
    private File connectionFile;
    private DataReceiver dataReceiver;
    private Stage stage;
    private DBConnector dbConnector;
    private Board board;
    private String matchCode;

    @FXML
    private TextField nameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private Pane settingsPane;
    @FXML
    private ComboBox<Resolution> resolutionInput;
    @FXML
    private ComboBox<String> kingdomInput;
    @FXML
    private TextField createMatchCode;
    @FXML
    private TextField joinMatchCode;
    @FXML
    private Label playerName;
    @FXML
    private Label enemyName;
    @FXML
    private Label playerKingdom;
    @FXML
    private Label enemyKingdom;
    @FXML
    private Button startButton;
    @FXML
    private TitledPane messagePane;
    @FXML
    private TextArea messageOutput;
    @FXML
    private TextField fileNameInput;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() throws IOException {
        try {
            RESOLUTIONS.addAll(new Resolution(850, 480), new Resolution(1280, 720), new Resolution(1366, 768),
                    new Resolution(1920, 1080));
            resolutionInput.setItems(RESOLUTIONS);
            resolutionInput.setValue(RESOLUTIONS.get(0));
            resolution = resolutionInput.getValue();

            KINGDOMS.addAll("INGLATERRA", "FRANCIA", "CASTILLA-ARAGÓN", "MOROS", "SACRO IMPERIO");
            kingdomInput.setItems(KINGDOMS);

            dbConnector = new DBConnector();
            setConnection();
        } catch (Exception e) {
            FileWriter writer = new FileWriter("error.log");
            writer.write(e.getMessage());
            writer.close();
        }
    }

    public void setKingdom() {
        pKingdom = kingdomInput.getValue();
        playerKingdom.setText(pKingdom);
    }

    public void toggleSettings() {
        settingsPane.setVisible(!settingsPane.isVisible());
    }

    public void setResolution() {
        resolution = resolutionInput.getValue();
    }

    public void createMatch() {
        if (checkName() && checkKingdom()) {
            matchCode = "";
            for (int i = 0; i < CODE_LENGTH; i++)
                matchCode += (char) ('A' + (int) (Math.random() * 26));

            createMatchCode.setText(matchCode);
            try {
                DataOutputStream out = new DataOutputStream(new FileOutputStream(connectionFile));
                out.writeInt(OPERATION_CREATE);
                Utils.writeStrings(out, new String[] { matchCode, pName, pKingdom });
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void joinMatch() {
        matchCode = joinMatchCode.getText();
        if (checkName() && checkKingdom() && matchCode.length() == CODE_LENGTH) {
            try {
                DataOutputStream out = new DataOutputStream(new FileOutputStream(connectionFile));
                out.writeInt(OPERATION_JOIN);
                Utils.writeStrings(out, new String[] { matchCode, pName, pKingdom });
                out.writeInt(idPlayer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void loadMatch() {
        try {
            String fileName = fileNameInput.getText();
            ObjectInputStream out = new ObjectInputStream(new FileInputStream(String.format("data/%s.sav", fileName)));
            board = (Board) out.readObject();
            out.close();

            pKingdom = board.getKingdomPlayer();
            eKingdom = board.getKingdomEnemy();
            playerKingdom.setText(pKingdom);
            enemyKingdom.setText(eKingdom);

            showMessage("Partida cargada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startMatch() {
        if (checkName() && checkEnemy() && checkKingdom()) {
            try {
                DataOutputStream out = new DataOutputStream(new FileOutputStream(connectionFile));
                out.writeInt(OPERATION_START);
                Utils.writeString(out, matchCode);
                out.close();

                if (board == null)
                    board = new Board(pKingdom, eKingdom);

                ObjectOutputStream outObj = new ObjectOutputStream(
                        new FileOutputStream("connections/" + idConnection + ".obj"));
                outObj.writeObject(board);
                outObj.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            createGameStage();
        }
    }

    public void login() {
        String name = nameInput.getText();
        idPlayer = dbConnector.loginPlayer(name, passwordInput.getText());
        if (idPlayer == -1) {
            showMessage("Usuario no encontrado.");
        } else {
            showMessage("Acceso correcto.");
            pName = name;
            playerName.setText(pName);
            nameInput.setText("");
            passwordInput.setText("");
        }
    }

    public void register() {
        pName = nameInput.getText();
        dbConnector.registerPlayer(pName, passwordInput.getText());
        showMessage("Usuario creado correctamente.");
        login();
    }

    public void getStatistics() {
        if (checkName()) {
            int[] status = dbConnector.getWinsLoses(idPlayer);
            showMessage(String.format("W: %d | L: %d", status[0], status[1]));
        }
    }

    public void closeMessage() {
        messagePane.setVisible(false);
    }

    public void restartMenu() {
        createMatchCode.setText("");
        joinMatchCode.setText("");
        enemyName.setText("");
        enemyKingdom.setText("");
        startButton.setDisable(false);
        dataReceiver = new DataReceiver();
        dataReceiver.start();
    }

    private void setConnection() {
        if (connectionFile == null) {
            path = "connections/" + idConnection + ".dat";
            connectionFile = new File(path);
            while (connectionFile.exists()) {
                idConnection++;
                path = "connections/" + idConnection + ".dat";
                connectionFile = new File(path);
            }
        }
        try {
            connectionFile.createNewFile();
            dataReceiver = new DataReceiver();
            dataReceiver.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkName() {
        boolean nameSet = pName != null;
        if (!nameSet)
            showMessage("Crea o accede a tu cuenta!");
        return nameSet;
    }

    private boolean checkEnemy() {
        boolean enemySet = eName != null;
        if (!enemySet)
            showMessage("Crea o únete a una partida!");
        return enemySet;
    }

    private boolean checkKingdom() {
        boolean kingdomSet = pKingdom != null;
        if (!kingdomSet)
            showMessage("Escoge un reino!");
        return kingdomSet;
    }

    private void createGameStage() {
        dataReceiver.startGame();
        stage.hide();
        new MainGame(this);
    }

    private void showMessage(String message) {
        messagePane.setVisible(true);
        messageOutput.setText(message);
    }

    // Clase interna para el receptor de datos en un hilo separado
    private class DataReceiver extends Thread {
        private File matchFile = new File(path);
        private long lastModified = matchFile.lastModified();
        private boolean gameStarted;

        public void run() {
            try {
                while (!gameStarted) {
                    // Comprueba si el archivo de la partida ha sido modificado
                    if (matchFile.lastModified() != lastModified) {
                        DataInputStream in = new DataInputStream(new FileInputStream(matchFile));
                        int response = in.readInt();
                        String name, kingdom;
                        switch (response) {
                            // Respuesta del anfitrión
                            case RESPONSE_HOST:
                                name = Utils.readString(in);
                                kingdom = Utils.readString(in);
                                int idOther = in.readInt();
                                // Actualiza el nombre del oponente en la interfaz de usuario
                                Platform.runLater(() -> {
                                    setEnemy(name, kingdom);
                                    idEnemy = idOther;
                                });
                                break;
                            // Respuesta del invitado
                            case RESPONSE_GUEST:
                                name = Utils.readString(in);
                                kingdom = Utils.readString(in);
                                if (name.equals("")) {
                                    showMessage("La partida no existe.");
                                } else {
                                    // Actualiza el nombre del oponente en la interfaz de usuario y desactiva el
                                    // botón de inicio
                                    Platform.runLater(() -> {
                                        setEnemy(name, kingdom);
                                        startButton.setDisable(true);
                                    });
                                }
                                break;
                            // Respuesta de inicio de la partida
                            case RESPONSE_START:
                                File objFile = new File("connections/" + idConnection + ".obj");
                                ObjectInputStream inObj = new ObjectInputStream(new FileInputStream(objFile));
                                board = (Board) inObj.readObject();
                                inObj.close();
                                objFile.delete();
                                // Inicia el juego principal
                                Platform.runLater(() -> {
                                    createGameStage();
                                });
                        }
                        lastModified = matchFile.lastModified();
                        in.close();
                    }

                    sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void startGame() {
            gameStarted = true;
        }

        private void setEnemy(String name, String kingdom) {
            eName = name;
            eKingdom = kingdom;
            enemyName.setText(eName);
            enemyKingdom.setText(eKingdom);
        }
    }

    private class MainGame {
        public MainGame(MainMenuController mainMenuController) {
            try {
                // Carga el archivo FXML del juego principal y configura la escena
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FX/MainGame/MainGame.fxml"));
                Parent root = loader.load();

                Stage mainGame = new Stage();
                mainGame.setTitle("Main Game");
                mainGame.setScene(new Scene(root, resolution.getWidth(), resolution.getHeight()));
                mainGame.setResizable(false);
                mainGame.show();

                MainGameController controller = loader.getController();
                controller.init(mainMenuController, resolution, stage, mainGame, board, idConnection, matchCode, pName,
                        eName,
                        idPlayer, idEnemy);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
