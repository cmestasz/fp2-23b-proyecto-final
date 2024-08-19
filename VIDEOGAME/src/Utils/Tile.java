package Utils;

import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class Tile extends Pane implements VideogameConstants {
    private HashMap<String, ImageView> images = new HashMap<String, ImageView>();
    private Label health;
    private ImageView image;
    private String type;
    private int i;
    private int j;

    public Tile(int health, String type, double size, int i, int j) {
        this.i = i;
        this.j = j;
        this.type = type;
        for (int n = 0; n < TYPE_FILES.length; n++)
            images.put(TYPE_FILES[n], generateImageView(size, TYPE_FILES[n]));
        this.health = generateHealthLabel(size);
        setImageAndhealth(type, health);
    }

    public void setImageAndhealth(String type, int hp) {
        while (getChildren().size() > 0)
            getChildren().remove(0);

        image = images.get(type);
        getChildren().add(image);

        if (type.equals("tile"))
            health.setText("");
        else
            health.setText(hp + "");
        getChildren().add(health);

    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public String getType() {
        return type;
    }

    public boolean isConnected(Tile other, int distance) {
        return Math.abs(other.getI() - i) <= distance && Math.abs(other.getJ() - j) <= distance;
    }

    private ImageView generateImageView(double size, String type) {
        Image image = new Image(String.format("img/tile_%s.png", type));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(size);
        imageView.setFitHeight(size);
        images.put(type, imageView);
        return imageView;
    }

    private Label generateHealthLabel(double size) {
        Label label = new Label();
        label.setLayoutX(size / 18);
        label.setLayoutY(size / 18);
        label.setTextFill(BACKGROUND_COLOR.getColor());
        return label;
    }

    public String getKey() {
        return i + "," + j;
    }

    public String toString() {
        return "a " + type + "!: " + i + ", " + j;
    }
}
