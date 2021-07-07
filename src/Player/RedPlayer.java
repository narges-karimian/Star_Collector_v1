package Player;

import javax.swing.*;

public class RedPlayer extends Player{

    private ImageIcon redImage;

    public RedPlayer(){
        setImage();
    }

    // + we use try catch in order to prevent the errors
    @Override
    public void setImage() {
        try{
            redImage = new ImageIcon("E:\\MidtermProject\\Component images\\redPlayer.jpg");
        }
        catch (Exception e){
            System.out.println("imageComponent was not found");
        }
    }

    @Override
    public ImageIcon getImage() {
        return redImage;
    }
}
