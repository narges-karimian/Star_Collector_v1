package GameBoard;

import BoardObjects.SpeedBumper;
import BoardObjects.Star;
import BoardObjects.Wall;
import Game.Constants;
import Game.GameProcess.GameProcess;
import Player.RedPlayer;
import Player.BluePlayer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard {

    private JFrame frame;
    private JPanel panel;
    private JButton[][] buttons;
    private JButton startBtn;
    private RightSideButtons rightSideButtons;
    private Star stars;
    private Wall walls;
    private SpeedBumper speedBumpers;
    private RedPlayer redPlayer;
    private BluePlayer bluePlayer;

    // + at here we create the elements of the game 'star' & 'wall' & 'speedBumper' & 'redPlayer' & 'bluePlayer'
    public GameBoard() {
        setFrame(new JFrame());
        setStartBtn(new JButton());

        redPlayer = new RedPlayer();
        bluePlayer = new BluePlayer();

        stars = new Star(new boolean[Constants.ROW][Constants.COLUMN]);
        walls = new Wall(new boolean[Constants.ROW][Constants.COLUMN]);
        speedBumpers = new SpeedBumper(new boolean[Constants.ROW][Constants.COLUMN]);
        rightSideButtons = new RightSideButtons(panel);
        setButtons(new JButton[Constants.ROW + 1][Constants.COLUMN]);// + we create an additional row in order to set the default player place on there

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
        this.frame.setTitle("*** STAR COLLECTOR ***");
        this.frame.setSize(Constants.COLUMN * (Constants.BUTTON_SIZE + Constants.SPACING) + 130 + 70, Constants.ROW * (Constants.BUTTON_SIZE + Constants.SPACING) + 150);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(null);
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        panel.setLayout(null);
        panel.setVisible(true);

        ImageIcon icon = null;
        try {
            icon = new ImageIcon("src/Component images/background2.jpg");
        }
        catch (Exception e){
            System.out.println("image component was not found");
        }
        Image image = icon.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        JLabel label = new JLabel();
        label.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        label.setIcon(icon);
        panel.add(label);
    }

    public void setButtons(JButton[][] buttons) {
        this.buttons = buttons;
        this.buttons[Constants.ROW][0] = new JButton();
        int x = 10, y = 10;
        for (int i = 0; i < Constants.ROW; i++) {
            for (int j = 0; j < Constants.COLUMN; j++) {
                this.buttons[i][j] = new JButton();
                this.buttons[i][j].setBounds(x, y, Constants.BUTTON_SIZE, Constants.BUTTON_SIZE);
                x += Constants.BUTTON_SIZE + Constants.SPACING;
                buttonsAction(i, j);
                panel.add(this.buttons[i][j], 0);
            }
            y += Constants.BUTTON_SIZE + Constants.SPACING;
            x = 10;
        }
    }

    public void setStartBtn(JButton startBtn) {
        this.startBtn = startBtn;
        this.startBtn.setText("Start");
        this.startBtn.setBounds((frame.getWidth() / 2) - 35 - 15, Constants.ROW * (Constants.BUTTON_SIZE + Constants.SPACING) + 40, 70, 30);
        panel.add(this.startBtn, 0);
        this.startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean check = false;
                for (int i = 0; i < Constants.ROW; i++) {
                    for (int j = 0; j < Constants.COLUMN; j++) {
                        // + you need at least one star & players to start the game
                        if (stars.getIs()[i][j] && bluePlayer.getI() != Constants.ROW && redPlayer.getI() != Constants.ROW)
                            check = true;
                    }
                }
                // + if 'check' is true the game will begin
                if (check) {
                    frame.dispose();
                    removeSelectBtn();
                    new GameProcess(frame, bluePlayer, redPlayer
                            , buttons, stars, walls, speedBumpers);
                } else {
                    JOptionPane.showMessageDialog(frame, "the requirements for beginning the game are at least 1 star and the players" +
                                    "\npls set them in your favorite place"
                            , "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // + defining the action listener for game buttons and based on the mode that has been selected by the user
    public void buttonsAction(int i, int j) {
        buttons[i][j].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // + star select mode
                if (rightSideButtons.isStarSelectedBtn()) {
                    if (buttons[i][j].getIcon() == null && buttons[i][j].getText().equals("")) {
                        buttons[i][j].setIcon(stars.getImage());
                        stars.setIs(i, j, true);
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "this place has been chosen\n pls remove first an then try",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }

                // + wall select mode
                if (rightSideButtons.isWallSelectedBtn()) {
                    if (buttons[i][j].getIcon() == null && buttons[i][j].getText() == "") {
                        buttons[i][j].setIcon(walls.getImage());
                        walls.setIs(i, j, true);
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "this place has been chosen\n pls remove first an then try",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }

                // + speedBumper select button
                if (rightSideButtons.isSpeedBumperSelectedBtn()) {
                    if (buttons[i][j].getIcon() == null && buttons[i][j].getText() == "") {

                        JPanel panel = new JPanel(new GridLayout(2, 1, 2, 2));
                        JTextField textField = new JTextField();
                        panel.add(new JLabel("pls enter the number of speed bumper:"));
                        panel.add(textField);
                        int option = JOptionPane.showConfirmDialog(frame, panel, "number of this speed bumper", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

                        // + only when clicked on ok button the process will go on
                        if (option == JOptionPane.OK_OPTION) {
                            String pattern = "[0-9]+";
                            String speedBumperNumber = textField.getText();
                            // + our speedBumper number should not be 'null' & should match our pattern & should be smaller than our row or columns & it can't be '0'
                            if (speedBumperNumber == "" | !speedBumperNumber.matches(pattern) ||
                                    Integer.parseInt(speedBumperNumber) >= ((Constants.COLUMN > Constants.ROW) ? Constants.COLUMN : Constants.ROW)
                                    || Integer.parseInt(speedBumperNumber) == 0) {
                                JOptionPane.showMessageDialog(frame, "you passed the max limit of the speed bumper number ("
                                                + ((Constants.COLUMN > Constants.ROW) ? Constants.COLUMN : Constants.ROW) + ") \nor you entered the WRONG character",
                                        "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                                speedBumpers.setIs(i, j, true);
                                buttons[i][j].setIcon(speedBumpers.getImage());
                                buttons[i][j].setText(speedBumperNumber);
                                buttons[i][j].setHorizontalTextPosition(SwingConstants.CENTER);
                                buttons[i][j].setVerticalTextPosition(SwingConstants.CENTER);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "this place has been chosen\n pls remove first an then try",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }

                // + red player select mode
                if (rightSideButtons.isRedSelectedBtn()) {
                    if (buttons[i][j].getIcon() == null && buttons[i][j].getText() == "") {
                        buttons[redPlayer.getI()][redPlayer.getJ()].setIcon(null);// + clears the last place of player in order to have just one place for each player
                        buttons[i][j].setIcon(redPlayer.getImage());
                        redPlayer.setI(i);
                        redPlayer.setJ(j);
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "this place has been chosen\n pls remove first an then try",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }

                // + blue player select mode
                if (rightSideButtons.isBlueSelectedBtn()) {
                    if (buttons[i][j].getIcon() == null && buttons[i][j].getText() == "") {
                        buttons[bluePlayer.getI()][bluePlayer.getJ()].setIcon(null);// + clears the last place of player in order to have just one place for each player
                        buttons[i][j].setIcon(bluePlayer.getImage());
                        bluePlayer.setI(i);
                        bluePlayer.setJ(j);
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "this place has been chosen\n pls remove first an then try",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }

                // + remove elements mode
                if (rightSideButtons.isRemoveBtnSelected()) {

                    buttons[i][j].setIcon(null);
                    buttons[i][j].setText("");
                    if (redPlayer.getI() == i && redPlayer.getJ() == j) {
                        redPlayer.setI(Constants.ROW);
                        redPlayer.setJ(0);
                    }
                    if (bluePlayer.getI() == i && bluePlayer.getJ() == j) {
                        bluePlayer.setI(Constants.ROW);
                        bluePlayer.setJ(0);
                    }
                    walls.setIs(i, j, false);
                    stars.setIs(i, j, false);
                    speedBumpers.setIs(i, j, false);
                }
            }
        });
    }

    // + this method will be needed when we have done all preferences of gameBoard and now we want to start the game
    public void removeSelectBtn() {
        panel.remove(rightSideButtons.getStarSelectBtn());
        panel.remove(rightSideButtons.getWallSelectBtn());
        panel.remove(rightSideButtons.getSpeedBumperSelectBtn());
        panel.remove(rightSideButtons.getBluePlayerSelectBtn());
        panel.remove(rightSideButtons.getRedPlayerSelectBtn());
        panel.remove(rightSideButtons.getRemove());
        panel.remove(this.startBtn);

        rightSideButtons.setStarSelectBtn(false);
        rightSideButtons.setWallSelectBtn(false);
        rightSideButtons.setSpeedBumperSelectedBtn(false);
        rightSideButtons.setRedSelectedBtn(false);
        rightSideButtons.setBlueSelectedBtn(false);
        rightSideButtons.setRemoveBtnSelected(false);

        frame.resize(Constants.COLUMN * (Constants.BUTTON_SIZE + Constants.SPACING) + 30, Constants.ROW * (Constants.BUTTON_SIZE + Constants.SPACING) + 70 + 2 * 15 + 2 * 10 + 20);
    }

}


