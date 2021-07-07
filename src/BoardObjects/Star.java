package BoardObjects;

import Game.Constants;

import javax.swing.*;
import java.awt.*;

public class Star extends BoardObjects {
    private ImageIcon starImage;

    public Star(boolean[][] is) {
        super(is);
        setImage();
    }

    // + we use try catch in order to prevent the errors
    @Override
    public void setImage() {
        try {
            starImage = new ImageIcon("src/Component images/star.jpg");
        }
        catch (Exception e){
            System.out.println("imageComponent was not found");
        }
        Image image = starImage.getImage().getScaledInstance(Constants.BUTTON_SIZE, Constants.BUTTON_SIZE, Image.SCALE_SMOOTH);
        starImage = new ImageIcon(image);

    }

    @Override
    public ImageIcon getImage() {
        return starImage;
    }
}
