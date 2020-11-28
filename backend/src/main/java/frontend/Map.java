//Author: Selin

package frontend;

import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.OverlayLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import backend.*;

public class Map extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JLayeredPane settingPane;
    private JPanel panel1 = new JPanel();
    private JLabel player1 = new JLabel();
    private JLabel player2 = new JLabel();
    private JLabel territoryName;
    private JLabel background;
    private JLabel avatar1,avatar2,avatar3,avatar4;

    JButton buttonterritory,buttonterritory2,buttonterritory3,buttonterritory4,buttonterritory5,
            buttonterritory6,buttonterritory7,buttonterritory8,buttonterritory9,buttonterritory10,buttonterritory11;
    JButton attackButton,retreatButton;
    JButton pauseButton;
    JButton draftButton;
    JButton cancelAttack,rollDiceButton,allianceButton,decreaseDice,increaseDice;
    String chosenTerritory;
    JLabel firstDiceSet,secondDiceSet,thirdDiceSet,forthDiceSet,fifthDiceSet;
    int diceNumberLeft = 3;

    public static void main(String[] args) {
        Map frame = new Map();
        frame.setVisible(true);
        frame.setTitle("Risk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1150,830)); //1570,800
        frame.setResizable(false);
        frame.pack();
        /*
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
         */

    }

    public Map()
    {
        territoryName = new JLabel("");
        territoryName.setBounds(500,20,300,50);

        firstDiceSet = new JLabel("");
        firstDiceSet.setBounds(40, 280, 100, 100);
        firstDiceSet.setIcon(new ImageIcon("./src/main/resources/images/dicered1.png"));

        secondDiceSet = new JLabel("");
        secondDiceSet.setBounds(100, 280, 100, 100);
        secondDiceSet.setIcon(new ImageIcon("./src/main/resources/images/dicered1.png"));

        thirdDiceSet = new JLabel("");
        thirdDiceSet.setBounds(160, 280, 100, 100);
        thirdDiceSet.setIcon(new ImageIcon("./src/main/resources/images/dicered1.png"));

        forthDiceSet = new JLabel("");
        forthDiceSet.setBounds(920, 280, 100, 100);
        forthDiceSet.setIcon(new ImageIcon("./src/main/resources/images/diceblue1.png"));

        fifthDiceSet = new JLabel("");
        fifthDiceSet.setBounds(980, 280, 100, 100);
        fifthDiceSet.setIcon(new ImageIcon("./src/main/resources/images/diceblue1.png"));

        rollDiceButton = new JButton("ROLL DICE");
        rollDiceButton.setBounds(520,720,100,50);
        rollDiceButton.addActionListener(this);

        decreaseDice = new JButton("DECREASE DICE NUMBER");
        decreaseDice.setBounds(720,720,200,50);
        decreaseDice.addActionListener(this);

        increaseDice = new JButton("INCREASE DICE NUMBER");
        increaseDice.setBounds(920,720,200,50);
        increaseDice.addActionListener(this);

        allianceButton = new JButton("ALLIANCE");
        allianceButton.setBounds(20,720,100,50);
        allianceButton.addActionListener(this);

        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(300,300));
        mainPanel.setLayout(null);


        background = new JLabel("");
        background.setIcon(new ImageIcon("./src/main/resources/images/map.v1.jpg"));
        background.setBounds(0, -165, 1580, 1100); //1860,1200

        avatar1 = new JLabel("");
        avatar2 = new JLabel("");
        avatar3 = new JLabel("");
        avatar4 = new JLabel("");

        avatar1.setIcon(new ImageIcon("./src/main/resources/images/alexander_p.png"));
        avatar1.setBounds(80,180,80,80);
        background.add(avatar1);

        avatar3.setIcon(new ImageIcon("./src/main/resources/images/hannibal_p.png"));
        avatar3.setBounds(980,180,80,80);
        background.add(avatar3);

        avatar2.setIcon(new ImageIcon("./src/main/resources/images/cengiz_p.png"));
        avatar2.setBounds(80,860,80,80);
        background.add(avatar2);

        avatar4.setIcon(new ImageIcon("./src/main/resources/images/caesar_p.png"));
        avatar4.setBounds(980,860,80,80);
        background.add(avatar4);

        cancelAttack = new JButton("BACK");
        cancelAttack.addActionListener(this);
        cancelAttack.setBounds(20,20,100,50);

        buttonterritory = new JButton("");
        buttonterritory.setBounds(155, 330, 60, 60);
        buttonterritory.setContentAreaFilled(false);
        buttonterritory.setBorderPainted(true);
        buttonterritory.addActionListener(this);
        buttonterritory.setName("Klinos");
        background.add(buttonterritory);

        buttonterritory2 = new JButton("");
        buttonterritory2.setBounds(295, 270, 150, 60);
        buttonterritory2.setContentAreaFilled(false);
        buttonterritory2.setBorderPainted(true);
        buttonterritory2.addActionListener(this);
        buttonterritory2.setName("Nord-Ziegelsand");
        mainPanel.add(buttonterritory2);

        buttonterritory9 = new JButton("");
        buttonterritory9.setBounds(350, 360, 100, 60);
        buttonterritory9.setContentAreaFilled(false);
        buttonterritory9.setBorderPainted(true);
        buttonterritory9.addActionListener(this);
        buttonterritory9.setName("Ziegelsand");
        mainPanel.add(buttonterritory9);

        buttonterritory3 = new JButton("");
        buttonterritory3.setBounds(510, 210, 60, 60);
        buttonterritory3.setContentAreaFilled(false);
        buttonterritory3.setBorderPainted(true);
        buttonterritory3.addActionListener(this);
        buttonterritory3.setName("Graue Ebene");
        mainPanel.add(buttonterritory3);

        buttonterritory4 = new JButton("");
        buttonterritory4.setBounds(655, 100, 150, 60);
        buttonterritory4.setContentAreaFilled(false);
        buttonterritory4.setBorderPainted(true);
        buttonterritory4.addActionListener(this);
        buttonterritory4.setName("Rorschachwall");
        mainPanel.add(buttonterritory4);

        buttonterritory5 = new JButton("");
        buttonterritory5.setBounds(655, 160, 100, 60);
        buttonterritory5.setContentAreaFilled(false);
        buttonterritory5.setBorderPainted(true);
        buttonterritory5.addActionListener(this);
        buttonterritory5.setName("Bakanwald");
        mainPanel.add(buttonterritory5);

        buttonterritory8 = new JButton("");
        buttonterritory8.setBounds(915, 250, 100, 60);
        buttonterritory8.setContentAreaFilled(false);
        buttonterritory8.setBorderPainted(true);
        buttonterritory8.addActionListener(this);
        buttonterritory8.setName("Waithfels");
        mainPanel.add(buttonterritory8);

        buttonterritory6 = new JButton("");
        buttonterritory6.setBounds(800, 150, 110, 60);
        buttonterritory6.setContentAreaFilled(false);
        buttonterritory6.setBorderPainted(true);
        buttonterritory6.addActionListener(this);
        buttonterritory6.setName("Schwarzfeld");
        mainPanel.add(buttonterritory6);

        buttonterritory7 = new JButton("");
        buttonterritory7.setBounds(950, 140, 150, 60);
        buttonterritory7.setContentAreaFilled(false);
        buttonterritory7.setBorderPainted(true);
        buttonterritory7.addActionListener(this);
        buttonterritory7.setName("Mimiken");
        mainPanel.add(buttonterritory7);

        buttonterritory10 = new JButton("");
        buttonterritory10.setBounds(605, 520, 150, 60);
        buttonterritory10.setContentAreaFilled(false);
        buttonterritory10.setBorderPainted(true);
        buttonterritory10.addActionListener(this);
        buttonterritory10.setName("Bürthal");
        mainPanel.add(buttonterritory10);

        buttonterritory11 = new JButton("");
        buttonterritory11.setBounds(945, 510, 150, 60);
        buttonterritory11.setContentAreaFilled(false);
        buttonterritory11.setBorderPainted(true);
        buttonterritory11.addActionListener(this);
        buttonterritory11.setName("Morgenfeld");
        mainPanel.add(buttonterritory11);

        attackButton = new JButton("ATTACK");
        attackButton.setName("ATTACK");
        attackButton.setBounds(200, 880, 150, 50);
        attackButton.setContentAreaFilled(true);
        attackButton.setBorderPainted(true);
        attackButton.setEnabled(false);
        attackButton.addActionListener(this);
        background.add(attackButton);

        draftButton = new JButton("DRAFT");
        draftButton.setName("DRAFT");
        draftButton.setBounds(200, 940, 150, 50);
        draftButton.setContentAreaFilled(true);
        draftButton.setBorderPainted(true);
        draftButton.setEnabled(false);
        draftButton.addActionListener(this);
        background.add(draftButton);

        retreatButton = new JButton("RETREAT");
        retreatButton.setBounds(800, 880, 150, 50);
        retreatButton.setContentAreaFilled(true);
        retreatButton.setBorderPainted(true);
        retreatButton.setEnabled(false);
        background.add(retreatButton);

        pauseButton = new JButton("Pause");
        pauseButton.setBounds(5, 180, 50, 50);
        pauseButton.setContentAreaFilled(true);
        pauseButton.setBorderPainted(true);
        pauseButton.addActionListener(this);
        background.add(pauseButton);
        settingPane = new JLayeredPane();
        settingPane.setBounds(250,150,700,600);
        settingPane.setPreferredSize(new Dimension(500,700));
        JLabel label1 = new JLabel();
        label1.setOpaque(true);
        label1.setBackground(Color.WHITE);
        label1.setBounds(200,100,200,1200);
        JButton buttonback = new JButton("BACK");
        buttonback.setBounds(250,250,100,60);

        settingPane.add(label1);
        settingPane.add(buttonback);

        mainPanel.add(background);
        add(mainPanel);

    }

    public void playSound(String soundName,float volume)
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
            Clip clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume); // Reduce volume by 10 decibels.
            clip.start( );
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace( );
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton tmp = (JButton) e.getSource();
        if(tmp!= null || e.getSource() != pauseButton|| e.getSource() != attackButton|| e.getSource() != retreatButton)
        {
            attackButton.setEnabled(true);
            retreatButton.setEnabled(true);
            draftButton.setEnabled(true);
            if(tmp.getName() != "ATTACK") {
                chosenTerritory = tmp.getName();
                System.out.println(chosenTerritory);
            }
        }
        if(e.getSource()==pauseButton)
        {
            int chosenoption;
            Object[] options = { "CONTINUE", "HOW TO PLAY","SETTINGS","QUIT" };
            chosenoption = JOptionPane.showOptionDialog(null, "GAME PAUSED", "GAME PAUSED",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if(chosenoption == 3)
            {
                playSound("./src/main/resources/sounds/snd_quitgame.wav",-10.0f);
                int quit = JOptionPane.showConfirmDialog(null,
                        "ARE YOU A LOSER?", "QUIT?", JOptionPane.YES_NO_OPTION);
                if(quit == 0)
                {
                    //dispose();
                }
            }else if(chosenoption == 2)
            {
                background.add(settingPane);
                mainPanel.setEnabled(false);
            }
        }
        if(e.getSource() == attackButton)
        {
            playSound("./src/main/resources/sounds/snd_howtoplay.wav",-10.0f);
            rollDiceButton.setEnabled(true);
            allianceButton.setEnabled(true);
            decreaseDice.setEnabled(true);
            increaseDice.setEnabled(true);
            mainPanel.setVisible(false);
            panel1.setVisible(true);
            panel1.setLayout(null);
            player1.setIcon(new ImageIcon("./src/main/resources/images/cengiz_p.png"));
            player1.setBounds(70, 200, 100, 100);
            panel1.add(player1);

            player2.setIcon(new ImageIcon("./src/main/resources/images/alexander_p.png"));
            player2.setBounds(920, 200, 100, 100);
            panel1.add(player2);
            panel1.add(cancelAttack);

            System.out.println(chosenTerritory);
            territoryName.setText("BATTLE FOR "+chosenTerritory);
            panel1.add(territoryName);

            panel1.add(rollDiceButton);

            panel1.add(decreaseDice);

            panel1.add(allianceButton);

            panel1.add(increaseDice);

            panel1.add(firstDiceSet);
            panel1.add(secondDiceSet);
            panel1.add(thirdDiceSet);
            panel1.add(forthDiceSet);
            panel1.add(fifthDiceSet);
            add(panel1);

        }
        if( e.getSource() == draftButton ) {
            System.out.println( "Drafting " + chosenTerritory );
        }
        if(e.getSource()==cancelAttack)
        {
            remove(panel1);
            mainPanel.setVisible(true);
            attackButton.setEnabled(false);
            retreatButton.setEnabled(false);
        }
        if(e.getSource() == rollDiceButton)
        {
            int firstdice = (int) (Math.random() * 6) + 1;
            int seconddice = (int) (Math.random() * 6) + 1;
            int thirddice = (int) (Math.random() * 6) + 1;
            int forthdice = (int) (Math.random() * 6) + 1;
            int fifthdice = (int) (Math.random() * 6) + 1;
            int max = Math.max(firstdice,seconddice);
            max = Math.max(max,thirddice);
            int min = Math.min(firstdice,seconddice);
            min = Math.min(min,thirddice);
            int medium = Math.min(firstdice,seconddice);
            medium = Math.max(medium,thirddice);

            firstDiceSet.setIcon(new ImageIcon("./src/main/resources/images/dicered" + min + ".png"));
            secondDiceSet.setIcon(new ImageIcon("./src/main/resources/images/dicered" + medium + ".png"));
            thirdDiceSet.setIcon(new ImageIcon("./src/main/resources/images/dicered" + max + ".png"));
            max = Math.max(forthdice,fifthdice);
            min = Math.min(fifthdice,forthdice);
            forthDiceSet.setIcon(new ImageIcon("./src/main/resources/images/diceblue" + min + ".png"));
            fifthDiceSet.setIcon(new ImageIcon("./src/main/resources/images/diceblue" + max + ".png"));
            rollDiceButton.setEnabled(false);
            allianceButton.setEnabled(false);
            decreaseDice.setEnabled(false);
            increaseDice.setEnabled(false);
        }
        if(e.getSource() == decreaseDice)
        {
            thirdDiceSet.setVisible(false);
            if(diceNumberLeft == 2)
            {
                secondDiceSet.setVisible(false);
            }
            increaseDice.setEnabled(true);
            diceNumberLeft--;
            if(diceNumberLeft == 1)
            {
                decreaseDice.setEnabled(false);
            }
        }
        if(e.getSource() == increaseDice)
        {
            secondDiceSet.setVisible(true);
            if(diceNumberLeft == 2)
            {
                thirdDiceSet.setVisible(true);
            }
            decreaseDice.setEnabled(true);
            diceNumberLeft++;
            if(diceNumberLeft == 3)
            {
                increaseDice.setEnabled(false);
            }
        }
        repaint();
        //pack();
    }
}


