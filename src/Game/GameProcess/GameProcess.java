package Game.GameProcess;

import Game.Menu;
import Player.*;
import BoardObjects.BoardObjects;
import Game.Constants;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameProcess {

    private JFrame frame;
    private static boolean redPlayerTurn;

    public GameProcess(JFrame frame, Player bluePlayer, Player redPlayer, JButton[][] buttons, BoardObjects stars, BoardObjects walls, BoardObjects speedBumpers) {
        setFrame(frame);
        Check.buttons = buttons;
        showDetails(bluePlayer, redPlayer);
        new GameProgress(bluePlayer, redPlayer, stars, walls, speedBumpers, buttons);
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
        frame.setVisible(true);
    }

    public void showDetails(Player bluePlayer, Player redPlayer) {

        JLabel redStarLabel = new JLabel("Red Star: " + redPlayer.getStar());
        redStarLabel.setFont(new Font("Tekton Pro Ext", Font.ITALIC, 15));
        redStarLabel.setForeground(Color.RED);

        JLabel redLimitLabel = new JLabel("Red Limit: " + ((bluePlayer.getSpeedBumper().size() == 0) ? 0 : bluePlayer.getSpeedBumper().get(0)));
        redLimitLabel.setFont(new Font("Tekton Pro Ext", Font.ITALIC, 15));
        redLimitLabel.setForeground(Color.RED);

        JLabel blueStarLabel = new JLabel("Blue Star: " + bluePlayer.getStar());
        blueStarLabel.setFont(new Font("Tekton Pro Ext", Font.ITALIC, 15));
        blueStarLabel.setForeground(Color.BLUE);

        JLabel blueLimitLabel = new JLabel("Blue Limit: " + ((redPlayer.getSpeedBumper().size() == 0) ? 0 : redPlayer.getSpeedBumper().get(0)));
        blueLimitLabel.setFont(new Font("Tekton Pro Ext", Font.ITALIC, 15));
        blueLimitLabel.setForeground(Color.BLUE);

        JLabel turnLabel = new JLabel(((redPlayerTurn) ? "red" : "blue") + "player turn");
        turnLabel.setFont(new Font("Tekton Pro Ext", Font.ITALIC, 15));
        turnLabel.setForeground((redPlayerTurn) ? Color.RED : Color.BLUE);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(2, 2));
        detailsPanel.setBounds(10, Constants.ROW * (Constants.BUTTON_SIZE + Constants.SPACING) + 10 + 20, Constants.COLUMN * (Constants.BUTTON_SIZE + Constants.SPACING) - 10, 60);
        detailsPanel.setBackground(Color.BLACK);
        detailsPanel.setVisible(true);

        JPanel turnPanel = new JPanel();
        turnPanel.setLayout(new GridLayout(1, 1));
        turnPanel.setBounds(10, Constants.ROW * (Constants.BUTTON_SIZE + Constants.SPACING) + 10, Constants.COLUMN * (Constants.BUTTON_SIZE + Constants.SPACING) - 10, 20);
        turnPanel.setBackground(Color.BLACK);
        turnPanel.setVisible(true);


        redStarLabel.setHorizontalAlignment(JLabel.CENTER);
        blueStarLabel.setHorizontalAlignment(JLabel.CENTER);
        redLimitLabel.setHorizontalAlignment(JLabel.CENTER);
        blueLimitLabel.setHorizontalAlignment(JLabel.CENTER);
        turnLabel.setHorizontalAlignment(JLabel.CENTER);
        detailsPanel.add(redStarLabel);
        detailsPanel.add(blueStarLabel);
        detailsPanel.add(redLimitLabel);
        detailsPanel.add(blueLimitLabel);
        turnPanel.add(turnLabel);

        frame.getContentPane().add(detailsPanel);
        frame.getContentPane().add(turnPanel);

    }

    // + if the number of stars reaches to '0' the game will be finished
    public boolean endGame(BoardObjects stars) {
        for (int i = 0; i < stars.getIs().length; i++) {
            for (int j = 0; j < stars.getIs()[i].length; j++) {
                if (stars.getIs()[i][j]) return false;
            }
        }
        return true;
    }

    // + choosing the winner of game
    public int whoWin(boolean endGame, Player bluePlayer, Player redPlayer) {
        if (endGame) {

            if (redPlayer.getStar() > bluePlayer.getStar()) {
                JOptionPane.showMessageDialog(new JFrame(), "Red Player Win!", "Winner", JOptionPane.INFORMATION_MESSAGE);
            } else if (bluePlayer.getStar() > redPlayer.getStar()) {
                JOptionPane.showMessageDialog(new JFrame(), "Blue Player Win!", "Winner", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "The Game Ended In A Draw", "Winner", JOptionPane.INFORMATION_MESSAGE);
            }

            return JOptionPane.OK_OPTION;
        } else return -1;
    }

    // + we define an inner class in order to have better access to fields which we need in our methods and action listeners
    public class GameProgress {
        Player bluePlayer;
        Player redPlayer;
        BoardObjects stars;
        BoardObjects walls;
        BoardObjects speedBumpers;
        JButton[][] buttons;

        // + we set our fields  in constructor and then we call gameProcess method
        public GameProgress(Player bluePlayer, Player redPlayer, BoardObjects stars, BoardObjects walls, BoardObjects speedBumpers, JButton[][] buttons) {
            this.bluePlayer = bluePlayer;
            this.redPlayer = redPlayer;
            this.stars = stars;
            this.walls = walls;
            this.speedBumpers = speedBumpers;
            this.buttons = buttons;
            gameProcess();
        }

        // + main functions of games will process in here
        public void gameProcess() {
            for (int i = 0; i < Constants.ROW; i++) {
                for (int j = 0; j < Constants.COLUMN; j++) {

                    int finalI = i;
                    int finalJ = j;
                    buttons[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            Player player;// + in order to have less coding for each player , based on the turn of the players we set their details in a new Player 'player'
                            int thisTurnBumper = 0;// + limit of this turn for the player
                            boolean checkForAnotherPlayer = false;

                            // + based on the turn of play the red or blue player will be set on player & the amount of the limit for the player as well
                            if (redPlayerTurn) {

                                player = redPlayer;
                                if (bluePlayer.getSpeedBumper().size() != 0) {
                                    thisTurnBumper = (int)bluePlayer.getSpeedBumper().get(0);
                                }

                                if ((bluePlayer.getI() == finalI && bluePlayer.getJ() == finalJ)||(redPlayer.getI() == finalI && redPlayer.getJ() == finalJ))
                                    checkForAnotherPlayer = true;
                            }

                            else {

                                player = bluePlayer;
                                if (redPlayer.getSpeedBumper().size() != 0) {
                                    thisTurnBumper = (int)redPlayer.getSpeedBumper().get(0);
                                }

                                if ((redPlayer.getI() == finalI && redPlayer.getJ() == finalJ)||(bluePlayer.getI() == finalI && bluePlayer.getJ() == finalJ))
                                    checkForAnotherPlayer = true;
                            }

                            // + check that this hasn't been taken by another player
                            if (!(checkForAnotherPlayer)) {

                                // + checking the limit of the player
                                if ((Math.abs(player.getI() - finalI) <= thisTurnBumper && Math.abs(player.getJ() - finalJ) <= thisTurnBumper)||thisTurnBumper==0) {

                                    // + cheking that the selected button should be at the same row or the same column with the last place of the player
                                    if (player.getI() == finalI || player.getJ() == finalJ) {

                                        // + if there is no wall in front of the player , the player will collect the stars on the way
                                        if (!Check.checkForWalls(player.getI(), finalI, player.getJ(), finalJ, walls)) {

                                            player.setStar(Check.checkForStars(player.getI(), finalI, player.getJ(), finalJ, stars));
                                            player = Check.checkForSpeedBumpers(player.getI(), finalI, player.getJ(), finalJ, speedBumpers, player);

                                            // + after the upper steps the we need to really move the player and set it's new position
                                            buttons[player.getI()][player.getJ()].setIcon(null);
                                            player.setI(finalI);
                                            player.setJ(finalJ);

                                            // + based on the turn of players we set back the player in red or blue player
                                            if (redPlayerTurn) {
                                                buttons[finalI][finalJ].setIcon(redPlayer.getImage());
                                                redPlayer = player;
                                                if(bluePlayer.getSpeedBumper().size()!=0)
                                                    bluePlayer.getSpeedBumper().remove(0);
                                                redPlayerTurn = false;
                                                showDetails(bluePlayer, redPlayer);// + updating the board of game after player move
                                            }
                                            else {
                                                buttons[finalI][finalJ].setIcon(bluePlayer.getImage());
                                                bluePlayer = player;
                                                if(redPlayer.getSpeedBumper().size()!=0)
                                                    redPlayer.getSpeedBumper().remove(0);
                                                redPlayerTurn = true;
                                                showDetails(bluePlayer, redPlayer);// + updating the board of game after player move
                                            }
                                        }
                                        else {
                                            JOptionPane.showMessageDialog(frame, "There is a wall in front of you!\ntry another move"
                                                    , "ERROR", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(frame, "Only vertical or horizontal are accepted"
                                                , "ERROR", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                                else {
                                    JOptionPane.showMessageDialog(frame, "pls pay attention to your limit on the board\n" +
                                            "you have passed your limit", "WARNING", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                            else {
                                    JOptionPane.showMessageDialog(frame, "not a valid move\ntry another move"
                                            , "ERROR", JOptionPane.ERROR_MESSAGE);
                            }

                            // + checking that if the game is finished or not and if so which player will win
                            if (whoWin(endGame(stars), bluePlayer, redPlayer) == 0) {
                                frame.dispose();
                                new Menu();
                            }
                        }
                    });
                }

            }
        }

    }

}
