package Game.GameProcess;

import Player.Player;
import BoardObjects.BoardObjects;
import javax.swing.*;

// + this class contains the rules and conditions of different situation that's why that all methods and fields are static and it's an abstract class
public abstract class Check {

    public static JButton[][] buttons;

    // + this method will check for walls , if there is a wall it returns 'true'
    public static boolean checkForWalls(int lastI, int i, int lastJ, int j, BoardObjects walls) {

        if (lastI >= i) {
            if (lastJ >= j) {
                for (int x = i; x <= lastI; x++) {
                    for (int y = j; y <= lastJ; y++) {
                        if (walls.getIs()[x][y]) return true;
                    }
                }
                return false;
            } else if (j >= lastJ) {
                for (int x = i; x <= lastI; x++) {
                    for (int y = lastJ; y <= j; y++) {
                        if (walls.getIs()[x][y]) return true;
                    }
                }
                return false;
            }
        } else if (i >= lastI) {
            if (lastJ >= j) {
                for (int x = lastI; x <= i; x++) {
                    for (int y = j; y <= lastJ; y++) {
                        if (walls.getIs()[x][y]) return true;
                    }
                }
                return false;
            } else if (j >= lastJ) {
                for (int x = lastI; x <= i; x++) {
                    for (int y = lastJ; y <= j; y++) {
                        if (walls.getIs()[x][y]) return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    // + counts the number of stars and returns it
    public static int checkForStars(int lastI, int i, int lastJ, int j,BoardObjects stars) {

        int starNumbers=0;

        if (lastI >= i) {
            if (lastJ >= j) {
                for (int x = i; x <= lastI; x++) {
                    for (int y = j; y <= lastJ; y++) {
                        starNumbers=checkStar(x,y,stars,starNumbers);
                    }
                }
            } else if (j >= lastJ) {
                for (int x = i; x <= lastI; x++) {
                    for (int y = lastJ; y <= j; y++) {
                        starNumbers=checkStar(x,y,stars,starNumbers);
                    }
                }
            }
        } else if (i >= lastI) {
            if (lastJ >= j) {
                for (int x = lastI; x <= i; x++) {
                    for (int y = j; y <= lastJ; y++) {
                        starNumbers=checkStar(x,y,stars,starNumbers);
                    }
                }
            } else if (j >= lastJ) {
                for (int x = lastI; x <= i; x++) {
                    for (int y = lastJ; y <= j; y++) {
                        starNumbers=checkStar(x,y,stars,starNumbers);
                    }
                }
            }
        }
        return starNumbers;
    }

    // + checks the speed bumpers and returns a player
    public static Player checkForSpeedBumpers(int lastI, int i, int lastJ, int j, BoardObjects speedBumpers, Player player) {

        if (lastI >= i) {
            if (lastJ >= j) {
                for (int x = lastI; x >= i; x--) {
                    for (int y = lastJ; y >= j; y--) {
                        checkBumpers(x,y,speedBumpers,player);
                    }
                }
            } else if (j >= lastJ) {
                for (int x = lastI; x >= i; x--) {
                    for (int y = lastJ; y <= j; y++) {
                        checkBumpers(x,y,speedBumpers,player);
                    }
                }
            }
        } else if (i >= lastI) {
            if (lastJ >= j) {
                for (int x = lastI; x <= i; x++) {
                    for (int y = lastJ; y >= j; y--) {
                        checkBumpers(x,y,speedBumpers,player);
                    }
                }
            } else if (j >= lastJ) {
                for (int x = lastI; x <= i; x++) {
                    for (int y = lastJ; y <= j; y++) {
                        checkBumpers(x,y,speedBumpers,player);
                    }
                }
            }
        }

        return player;
    }

    // + this method helps avoiding the rewriting of the same codes on 'checkForSpeedBumpers' method
    public static Player checkBumpers(int x, int y,BoardObjects speedBumpers,Player player){
        if (speedBumpers.getIs()[x][y]) {
            player.getSpeedBumper().add(Integer.parseInt(buttons[x][y].getText()));
            speedBumpers.setIs(x,y,false);
            buttons[x][y].setIcon(null);
            buttons[x][y].setText("");
        }
        return player;
    }

    // + this method helps avoiding the rewriting of same codes related to 'checkForStars' method
    public static int checkStar(int x, int y,BoardObjects stars,int starNumbers){
        if (stars.getIs()[x][y]) {
            starNumbers++;
            stars.setIs(x,y,false);
            buttons[x][y].setIcon(null);
        }
        return starNumbers;
    }
}
