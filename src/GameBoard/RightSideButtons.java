package GameBoard;

import Game.Constants;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// + in order to keep the game board class more understandable
// + we separated the right side buttons of the game board and we defined them in this class
public class RightSideButtons {
    private JButton starSelectBtn;
    private JButton wallSelectBtn;
    private JButton speedBumperSelectBtn;
    private JButton redPlayerSelectBtn;
    private JButton bluePlayerSelectBtn;
    private JButton remove;

    private JPanel panel;
    private boolean isStarSelectedBtn;
    private boolean isWallSelectedBtn;
    private boolean isSpeedBumperSelectedBtn;
    private boolean isRedSelectedBtn;
    private boolean isBlueSelectedBtn;
    private boolean isRemoveBtnSelected;

    public RightSideButtons(JPanel panel) {
        this.panel = panel;

        setStarSelectedBtn(new JButton("Set Stars"));
        setWallSelectedBtn(new JButton("Set Walls"));
        setSpeedBumperSelectBtn(new JButton("Set SpeedBumpers"));
        setBluePlayerSelectBtn(new JButton("Set Blue Player"));
        setRedPlayerSelectBtn(new JButton("Set Red Player"));
        setRemove(new JButton("Remove Icon"));
    }

    // + star selection
    public void setStarSelectedBtn(JButton starSelectedBtn) {
        this.starSelectBtn = starSelectedBtn;
        this.starSelectBtn.setBounds(Constants.COLUMN * (Constants.BUTTON_SIZE + Constants.SPACING) + 20, 10, 145, 30);
        panel.add(this.starSelectBtn, 0);
        this.starSelectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStarSelectedBtn = true;
                isWallSelectedBtn = false;
                isSpeedBumperSelectedBtn = false;
                isRedSelectedBtn = false;
                isBlueSelectedBtn = false;
                isRemoveBtnSelected = false;
            }
        });
    }

    // + wall selection
    public void setWallSelectedBtn(JButton wallSelectedBtn) {
        this.wallSelectBtn = wallSelectedBtn;
        this.wallSelectBtn.setBounds(Constants.COLUMN * (Constants.BUTTON_SIZE + Constants.SPACING) + 20, 10 * 2 + 30, 145, 30);
        panel.add(this.wallSelectBtn, 0);
        this.wallSelectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStarSelectedBtn = false;
                isWallSelectedBtn = true;
                isSpeedBumperSelectedBtn = false;
                isRedSelectedBtn = false;
                isBlueSelectedBtn = false;
                isRemoveBtnSelected = false;
            }
        });
    }

    // + speedBumper selection
    public void setSpeedBumperSelectBtn(JButton speedBumperSelectBtn) {
        this.speedBumperSelectBtn = speedBumperSelectBtn;
        this.speedBumperSelectBtn.setBounds(Constants.COLUMN * (Constants.BUTTON_SIZE + Constants.SPACING) + 20, 10 * 3 + 30 * 2, 145, 30);
        panel.add(this.speedBumperSelectBtn, 0);
        this.speedBumperSelectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStarSelectedBtn = false;
                isWallSelectedBtn = false;
                isSpeedBumperSelectedBtn = true;
                isRedSelectedBtn = false;
                isBlueSelectedBtn = false;
                isRemoveBtnSelected = false;
            }
        });
    }

    // + bluePlayer selection
    public void setBluePlayerSelectBtn(JButton bluePlayerSelectBtn) {
        this.bluePlayerSelectBtn = bluePlayerSelectBtn;
        this.bluePlayerSelectBtn.setBounds(Constants.COLUMN * (Constants.BUTTON_SIZE + Constants.SPACING) + 20, 10 * 4 + 30 * 3, 145, 30);
        panel.add(this.bluePlayerSelectBtn, 0);
        this.bluePlayerSelectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStarSelectedBtn = false;
                isWallSelectedBtn = false;
                isSpeedBumperSelectedBtn = false;
                isRedSelectedBtn = false;
                isBlueSelectedBtn = true;
                isRemoveBtnSelected = false;
            }
        });
    }

    // + redPlayer selection
    public void setRedPlayerSelectBtn(JButton redPlayerSelectBtn) {
        this.redPlayerSelectBtn = redPlayerSelectBtn;
        this.redPlayerSelectBtn.setBounds(Constants.COLUMN * (Constants.BUTTON_SIZE + Constants.SPACING) + 20, 10 * 5 + 30 * 4, 145, 30);
        panel.add(this.redPlayerSelectBtn, 0);
        this.redPlayerSelectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStarSelectedBtn = false;
                isWallSelectedBtn = false;
                isSpeedBumperSelectedBtn = false;
                isRedSelectedBtn = true;
                isBlueSelectedBtn = false;
                isRemoveBtnSelected = false;
            }
        });
    }

    // + remove selection
    public void setRemove(JButton remove) {
        this.remove = remove;
        this.remove.setBounds(Constants.COLUMN * (Constants.BUTTON_SIZE + Constants.SPACING) + 20, 10 * 6 + 30 * 5, 145, 30);
        panel.add(this.remove, 0);
        this.remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isStarSelectedBtn = false;
                isWallSelectedBtn = false;
                isSpeedBumperSelectedBtn = false;
                isRedSelectedBtn = false;
                isBlueSelectedBtn = false;
                isRemoveBtnSelected = true;
            }
        });
    }

    // + all getter and setters boolean values and buttons

    // + getter of buttons
    public JButton getStarSelectBtn() {
        return starSelectBtn;
    }
    public JButton getWallSelectBtn() {
        return wallSelectBtn;
    }
    public JButton getSpeedBumperSelectBtn() {
        return speedBumperSelectBtn;
    }
    public JButton getRedPlayerSelectBtn() {
        return redPlayerSelectBtn;
    }
    public JButton getBluePlayerSelectBtn() {
        return bluePlayerSelectBtn;
    }
    public JButton getRemove() {
        return remove;
    }

    // + getter of boolean values
    public boolean isRemoveBtnSelected() {
        return isRemoveBtnSelected;
    }
    public boolean isStarSelectedBtn() {
        return isStarSelectedBtn;
    }
    public boolean isWallSelectedBtn() {
        return isWallSelectedBtn;
    }
    public boolean isSpeedBumperSelectedBtn() {
        return isSpeedBumperSelectedBtn;
    }
    public boolean isRedSelectedBtn() {
        return isRedSelectedBtn;
    }
    public boolean isBlueSelectedBtn() {
        return isBlueSelectedBtn;
    }

    // + setter of boolean values
    public void setRemoveBtnSelected(boolean removeBtnSelected) {
        isRemoveBtnSelected = removeBtnSelected;
    }
    public void setStarSelectBtn(boolean starSelectBtn) {
        this.isStarSelectedBtn = starSelectBtn;
    }
    public void setWallSelectBtn(boolean wallSelectBtn) {
        this.isWallSelectedBtn = wallSelectBtn;
    }
    public void setSpeedBumperSelectedBtn(boolean speedBumperSelectedBtn) {
        isSpeedBumperSelectedBtn = speedBumperSelectedBtn;
    }
    public void setRedSelectedBtn(boolean redSelectedBtn) {
        this.isRedSelectedBtn = redSelectedBtn;
    }
    public void setBlueSelectedBtn(boolean blueSelectedBtn) {
        this.isBlueSelectedBtn = blueSelectedBtn;
    }
}
