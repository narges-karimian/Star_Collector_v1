package Player;

import Game.Constants;

import javax.swing.*;
import java.util.ArrayList;

// + we can define our players using this class
public abstract class Player {

    private int i = Constants.ROW;
    private int j;
    private int star;
    private ArrayList speedBumper;

    public Player(){
        this.speedBumper = new ArrayList();
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star+=star;
    }

    public ArrayList getSpeedBumper() {
        return speedBumper;
    }

    // + all players should have imageIcons
    public abstract void setImage();

    public abstract ImageIcon getImage();
}
