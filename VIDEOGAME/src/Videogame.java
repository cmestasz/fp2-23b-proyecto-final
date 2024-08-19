
import java.io.*;
import FX.MainMenu.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.*;

public class Videogame extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            // Carga el archivo FXML del menú principal y configura la escena
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FX/MainMenu/MainMenu.fxml"));
            Parent root = loader.load();

            MainMenuController controller = loader.getController();
            controller.setStage(primaryStage);

            primaryStage.setTitle("Main Menu");
            primaryStage.setScene(new Scene(root, 325, 600));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            FileWriter writer = new FileWriter("error.log");
            writer.write(e.getMessage());
            writer.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}