package BoardObjects;

import Game.Constants;

import javax.swing.*;
import java.awt.*;

public class SpeedBumper extends BoardObjects {
    private ImageIcon speedBumperImage;

    public SpeedBumper(boolean[][] is) {
        super(is);
        setImage();
    }

    // + we use try catch in order to prevent the errors
    @Override
    public void setImage() {
        try {
            speedBumperImage = new ImageIcon("src/Component images/speedBumper.jpg");
        }
        catch (Exception e){
            System.out.println("imageComponent was not found");
        }
        Image image = speedBumperImage.getImage().getScaledInstance(Constants.BUTTON_SIZE, Constants.BUTTON_SIZE, Image.SCALE_SMOOTH);
        speedBumperImage = new ImageIcon(image);

    }

    @Override
    public ImageIcon getImage() {
        return speedBumperImage;
    }
}
