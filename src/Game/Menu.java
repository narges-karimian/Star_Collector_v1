package Game;

import GameBoard.GameBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JButton[] buttons;

    //+ setting the menu of the game
    public Menu() {
        setFrame(new JFrame("MainMenu"));
        setButtons(new JButton[3]);
        frame.setVisible(true);
    }

    // + a frame has been considered and the panel that contains the buttons and components will be added to it
    private void setFrame(JFrame jFrame) {

        frame = jFrame;
        frame.setSize(Constants.FRAME_WIDTH + 15, Constants.FRAME_HEIGHT + 35);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        setPanel();
        setTitle();

        JLabel lbProducers = new JLabel();
        lbProducers.setText("GAME BY : MOHAMMAD & NARGES & FATEMEH                              V:1.0  ");
        lbProducers.setHorizontalAlignment(JLabel.CENTER);
        lbProducers.setForeground(new Color(218, 164, 238, 255));
        lbProducers.setBounds(10, Constants.FRAME_HEIGHT - 45, Constants.FRAME_WIDTH, 20);
        panel.add(lbProducers,0);

        frame.getContentPane().add(panel);
    }

    // + setting the title
    public void setTitle() {
        JLabel title = new JLabel("STAR COLLECTOR");
        title.setBounds(50, 15, 500, 150);
        title.setFont(new Font("Curlz MT", Font.BOLD, 50));
        title.setForeground(new Color(45, 185, 197, 255));
        panel.add(title, 0);

        JLabel starLabel = new JLabel();
        starLabel.setBounds(300, 85, 200, 200);
        ImageIcon starIcon = new ImageIcon("src/Component images/starTitle.png");
        Image image = starIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        starIcon = new ImageIcon(image);
        starLabel.setIcon(starIcon);
        panel.add(starLabel, 0);
    }

    // + setting panel and adding background
    public void setPanel() {
        panel = new JPanel();
        panel.setBounds(0, 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        panel.setLayout(null);
        panel.setVisible(true);

        ImageIcon icon = new ImageIcon("src/Component images/background1.jpg");// + background of menu

        Image image = icon.getImage().getScaledInstance(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);//+ the easy way to set the background is using a label , we set the labels icon as our background
        JLabel label = new JLabel();
        label.setBounds(0, 0, Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        label.setIcon(icon);
        panel.add(label);

    }

    // + setting the position of the menu buttons
    private void setButtons(JButton[] jButtons) {
        final String[] button = new String[]{"START GAME", "HOW TO PLAY", "EXIT"};// + name of the buttons on the menu
        buttons = jButtons;
        int width = 120;
        int height = 40;
        int x = (frame.getWidth() / 2) - (width / 2) - 5, y = frame.getHeight() / 3 + 10;
        for (int i = 0; i < button.length; i++) {
            buttons[i] = new JButton(button[i]);
            buttons[i].setBounds(x, y, width, height);
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setFont(new Font("Times New Roman", Font.BOLD, 12));
            buttons[i].setForeground(new Color(45, 185, 197, 255));
            buttons[i].addActionListener(this);
            panel.add(buttons[i], 0);
            y += height + 10;
        }
    }

    @Override// + adjusting the action listener of the menu buttons
    public void actionPerformed(ActionEvent e) {
        String btnLabel = ((JButton) e.getSource()).getText();

        if (btnLabel.equals("START GAME")) {
//            frame.dispose();
            getRowsAndColumns();
        } else if (btnLabel.equals("HOW TO PLAY")) {
            HowToPlay();
        } else if (btnLabel.equals("EXIT")) {
            System.exit(0);
        }
    }

    // + receiving the number of rows & columns
    private void getRowsAndColumns() {
        for (int i = 0; i < buttons.length; i++) {
            panel.remove(buttons[i]);
        }

        JLabel colLabel = new JLabel("Enter the number of columns:");
        colLabel.setBounds(80,Constants.FRAME_HEIGHT/3+20,180,20);
        colLabel.setForeground(new Color(45, 185, 197, 255));

        JTextField colField = new JTextField();
        colField.setBounds(90,Constants.FRAME_HEIGHT/3+40,150,15);

        JLabel rowLabel = new JLabel("Enter the number of rows:");
        rowLabel.setBounds(80,Constants.FRAME_HEIGHT/3+60,150,20);
        rowLabel.setForeground(new Color(45, 185, 197, 255));

        JTextField rowField = new JTextField();
        rowField.setBounds(90,Constants.FRAME_HEIGHT/3+80,150,15);

        JButton start = new JButton("START");
        start.setBounds(Constants.FRAME_WIDTH/2-40,Constants.FRAME_HEIGHT*3/4-10,75,30);
        start.setBackground(Color.BLACK);
        start.setForeground(new Color(45, 185, 197, 255));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String pattern = "[0-9]+";// + our rows & columns should match this pattern & can't be 0

                // + checking for the right conditions
                boolean rowsMatches = false, columnsMatches = false;
                if (rowField.getText().matches(pattern) && Integer.parseInt(rowField.getText()) != 0)
                    rowsMatches = true;
                if (colField.getText().matches(pattern) && Integer.parseInt(colField.getText()) != 0)
                    columnsMatches = true;

                if (rowsMatches && columnsMatches) {
                    Constants.ROW = Integer.parseInt(rowField.getText());
                    Constants.COLUMN = Integer.parseInt(colField.getText());
                    frame.dispose();
                    new GameBoard();
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "you didn't fill the fields or you entered" +
                            "\n a wrong character\n only numbers expected!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    getRowsAndColumns();
                }
            }
        });

        panel.add(start,0);
        panel.add(rowLabel,0);
        panel.add(rowField,0);
        panel.add(colLabel,0);
        panel.add(colField,0);

        frame.setSize(Constants.FRAME_WIDTH + 10, Constants.FRAME_HEIGHT + 30);
    }

    // + simple instruction for playing the game
    private void HowToPlay() {
        JFrame frame = new JFrame("How to play?");
        frame.setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT - 35);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.BLACK);

        JTextArea text = new JTextArea("**************** Welcome to STAR COLLECTOR ********************\n" +
                "A new game that will amaze you and attract you with it's simplicity &\nExcitement features\n" +
                "The rules are simple :\n" +
                "Players can't pass throw the walls \nthey will collect stars throw the game\n" +
                "Player which collects the most stars to the end of the game will be the winner of the game\n" +
                "Pay attention to the speedBumpers in the game \n" +
                "If a player collect a speedBumper the next players move will be limited to number of that speed bumper or less than that\n" +
                "Go have fun!");
        text.setLineWrap(true);
        text.setEditable(false);
        text.setFont(new Font("Tekton Pro Ext", Font.PLAIN,15));
        text.setBackground(Color.BLACK);
        text.setForeground(new Color(195, 139, 255));
        text.setBounds(5, 5, frame.getWidth(), frame.getHeight()-70);

        JButton ok = new JButton("BACK");
        ok.setBounds(frame.getWidth() / 2 - 40, frame.getHeight() - 65, 70, 20);
        ok.setForeground(Color.CYAN);
        ok.setBackground(Color.BLACK);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.getContentPane().add(text);
        frame.getContentPane().add(ok);
        frame.setVisible(true);

    }

}
