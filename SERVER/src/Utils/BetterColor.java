package Utils;

import java.io.Serializable;
import javafx.scene.paint.Color;

public class BetterColor implements Serializable {
    private double redF;
    private double greenF;
    private double blueF;
    private double alphaF;
    private int redD;
    private int greenD;
    private int blueD;
    private int alphaD;

    public BetterColor(double red, double green, double blue, double alpha) {
        this.redF = red;
        this.greenF = green;
        this.blueF = blue;
        this.alphaF = alpha;
        
        this.redD = (int) (redF * 255);
        this.greenD = (int) (greenF * 255);
        this.blueD = (int) (blueF * 255);
        this.alphaD = (int) (alphaF * 255);
    }

    public Color getColor() {
        return new Color(redF, greenF, blueF, alphaF);
    }

    public String getRGBA() {
        return String.format("rgba(%d, %d, %d, %d)", redD, blueD, greenD, alphaD);
    }
}
