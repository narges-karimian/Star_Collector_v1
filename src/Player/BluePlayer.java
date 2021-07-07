package Player;

import javax.swing.*;

public class BluePlayer extends Player{
    private ImageIcon blueImage;

    public BluePlayer(){
        setImage();
    }

    // + we use try catch in order to prevent the errors
    @Override
    public void setImage() {
        try{
            blueImage = new ImageIcon("E:\\MidtermProject\\Component images\\bluePlayer.jpg");
        }
        catch (Exception e){
            System.out.println("imageComponent was not found");
        }
    }

    @Override
    public ImageIcon getImage() {
        return blueImage;
    }
}
