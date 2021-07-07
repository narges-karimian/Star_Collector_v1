package BoardObjects;

import Game.Constants;

import javax.swing.*;
import java.awt.*;

public class Wall extends BoardObjects {
    private ImageIcon wallImage;

    public Wall(boolean[][] is) {
        super(is);
        setImage();
    }

    // + we use try catch in order to prevent the errors
    @Override
    public void setImage() {
        try {
            wallImage = new ImageIcon("src/Component images/wall.jpg");
        }
        catch (Exception e){
            System.out.println("imageComponent was not found");
        }
        Image image = wallImage.getImage().getScaledInstance(Constants.BUTTON_SIZE, Constants.BUTTON_SIZE, Image.SCALE_SMOOTH);
        wallImage = new ImageIcon(image);

    }

    @Override
    public ImageIcon getImage() {
        return wallImage;
    }

}
