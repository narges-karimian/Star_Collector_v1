package BoardObjects;

import javax.swing.*;

// + we can define our elements using this class
public abstract class BoardObjects {

    private boolean[][] is;

    public BoardObjects(boolean[][] is) {
        this.is = is;
    }

    public boolean[][] getIs() {
        return is;
    }

    public void setIs(int i , int j , boolean is) {
        this.is[i][j]=is;
    }

    public abstract void setImage(); // + each element should have an icon

    public abstract ImageIcon getImage(); // + each element should have an icon
}
