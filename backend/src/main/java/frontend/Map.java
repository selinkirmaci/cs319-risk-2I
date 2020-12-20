//Author: Selin

package frontend;

import javax.imageio.ImageIO;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

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
import javax.swing.text.html.HTMLEditorKit;

import backend.*;

public class Map extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private HowToPlayPanel howToPlayPanel;
    private SoundManager soundManager;
    private SecretMissionFrame secretMissionFrame;
    private CursedCardsFrame cursedCardsFrame;
    private JLayeredPane settingPane;
    private JPanel panel1 = new JPanel();
    private JLabel player1 = new JLabel();
    private JLabel player2 = new JLabel();
    private JLabel attackerLabel;
    private JLabel defenderLabel;
    private JLabel territoryName;
    private JLabel background;
    private JLabel avatar1,avatar2,avatar3,avatar4;
    private JLabel player1name,player2name,player3name,player4name;
    private JButton miniGame;
    private String overallWinner;
    private int timeLeft = 60;
    private int gainAmt = 0;
    private int draftAmt = 0;
    private int attackAmt = 0;
    private JButton fromButton;
    private JButton toButton,emptyButton;
    private Clip clipMusic;
    private Clip miniGameMusic;

    Image backgroundForAttack;

    JLabel attackTimerLabel;
    private int attackSeconds = 30;
    Timer attackTimer = new Timer();

    JLabel lblTimer;
    private int seconds = timeLeft;
    Timer timer1 = new Timer();
    TimerTask task1 = new TimerTask() {
        @Override
        public void run() {
            seconds--;
            timeLeft = seconds;
            lblTimer.setText(""+seconds);
            if(seconds == 0)
            {
                nextPlayerButton.doClick();
                seconds = 60;
            }

            if (currentPlayer == 0)
            {
                lblTimer.setForeground(players[0].getColor());
            }

            else if (currentPlayer == 1)
            {
                lblTimer.setForeground(players[1].getColor());
            }

            else if (currentPlayer == 2)
            {
                lblTimer.setForeground(players[2].getColor());
            }

            else if (currentPlayer == 3)
            {
                lblTimer.setForeground(players[3].getColor());
            }
        }
    };

    CircleComponent component1 = new CircleComponent(90);
    private JPanel settingsPanel;
    JButton[] territories;
    JButton attackButton,retreatButton;
    JButton pauseButton;
    JButton draftButton;
    JButton secretMissionCard;
    JButton nextPlayerButton;
    JButton fortifyButton;
    JButton rollDiceButton,allianceButton,decreaseDice,increaseDice,decreaseDiceDef,increaseDiceDef;
    String chosenTerritory;
    JLabel firstDiceSet,secondDiceSet,thirdDiceSet,forthDiceSet,fifthDiceSet;
    JButton cardInfoPanelButton,cursedCardInfoFrameButton;
    int diceNumberLeft = 3;
    int diceNumberLeftDef = 2;
    CardPanel cardPanel;
    private SettingsPanel setPanel;

    String from = "";
    String to = "";
    boolean setFrom = true;

    private GameManager gameManager;
    private Game game;
    private backend.Map map;
    Player[] players;
    int[] playerAvatars;
    int currentPlayer;
    int noOfPlayers;


    public Map(GameManager gameManager , SoundManager soundManager)
    {

        playMusic("./src/main/resources/sounds/warmusic.wav",-20.0f);
        this.gameManager = gameManager;
        this.soundManager = soundManager;
        game = gameManager.getGame();
        map = game.getMap();
        players = game.getPlayers();
        currentPlayer = game.getCurrentPlayerTurn();
        territories = new JButton[45];

        overallWinner = "";

        fromButton = new JButton();
        toButton = new JButton();

        attackTimerLabel = new JLabel();
        attackTimerLabel.setBounds(400,50,40,40);
        attackTimerLabel.setFont(new Font(Font.SERIF,Font.BOLD,40));


        settingsPanel = new JPanel();
        settingsPanel.setPreferredSize(new Dimension(300,300));
        settingsPanel.setBounds(100,100,300,300);
        settingsPanel.setBackground(Color.BLUE);


        noOfPlayers = players.length;
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
        decreaseDice.setBounds(10,720,200,50);
        decreaseDice.addActionListener(this);

        increaseDice = new JButton("INCREASE DICE NUMBER");
        increaseDice.setBounds(210,720,200,50);
        increaseDice.addActionListener(this);

        decreaseDiceDef = new JButton("DECREASE DICE NUMBER");
        decreaseDiceDef.setBounds(720,720,200,50);
        decreaseDiceDef.addActionListener(this);

        increaseDiceDef = new JButton("INCREASE DICE NUMBER");
        increaseDiceDef.setBounds(920,720,200,50);
        increaseDiceDef.addActionListener(this);


        allianceButton = new JButton("ALLIANCE");
        allianceButton.setBounds(20,520,100,50);
        allianceButton.addActionListener(this);

        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(300,400));
        mainPanel.setLayout(null);


        background = new JLabel("");
        background.setIcon(new ImageIcon("./src/main/resources/images/map_hq_v2.jpg"));
        background.setBounds(0, -165, 1580, 1100); //1860,1200

        avatar1 = new JLabel("");
        avatar2 = new JLabel("");
        avatar3 = new JLabel("");
        avatar4 = new JLabel("");

        lblTimer = new JLabel("");
        lblTimer.setBounds(640, 163, 800, 80);
        lblTimer.setFont(new Font(Font.SERIF,Font.BOLD,40));
        background.add(lblTimer);

        avatar1.setIcon(new ImageIcon(players[0].getAvatar().getImageFileName()));
        avatar1.setBounds(10,180,80,80);
        player1name = new JLabel(players[0].getName()+" Infantry numbers:" + players[0].getInfantryAmt());
        player1name.setBounds(0,230,800,80);
        background.add(avatar1);

        avatar3.setIcon(new ImageIcon(players[1].getAvatar().getImageFileName()));
        avatar3.setBounds(1060,180,80,80);
        player2name = new JLabel(players[1].getName()+" Infantry numbers:" + players[1].getInfantryAmt());
        player2name.setBounds(960,230,200,80);
        background.add(avatar3);

        if(noOfPlayers >=3)
            avatar2.setIcon(new ImageIcon(players[2].getAvatar().getImageFileName()));
        avatar2.setBounds(10,860,80,80);
        if(noOfPlayers >=3) {
            player3name = new JLabel(players[2].getName()+" Infantry numbers:" + players[2].getInfantryAmt());
        }else
            player3name = new JLabel("");
        player3name.setBounds(0,910,200,80);
        background.add(avatar2);

        if(noOfPlayers >=4)
            avatar4.setIcon(new ImageIcon(players[3].getAvatar().getImageFileName()));
        avatar4.setBounds(1060,860,80,80);

        if(noOfPlayers >=4) {
            player4name = new JLabel(players[3].getName()+" Infantry numbers:" + players[3].getInfantryAmt());
        }else
            player4name = new JLabel("");
        player4name.setBounds(960,910,200,80);
        background.add(avatar4);
        background.add(player1name);
        background.add(player2name);
        background.add(player3name);
        background.add(player4name);

        //initialize buttons for territories
        for(int i = 0; i < 45; i++)
        {
            territories[i] = new JButton("");
            territories[i].setBorderPainted(true);
            territories[i].addActionListener(this);
            territories[i].setContentAreaFilled(false);
            territories[i].setForeground(Color.white);
            territories[i].setFont(new Font(Font.SERIF,Font.BOLD,25));
        }
        //set names of the buttons
        territories[0].setName("Raken");
        territories[1].setName("Perleninsel");
        territories[2].setName("Grimmspitze");
        territories[3].setName("Ziegelherz");
        territories[4].setName("Nosra");
        territories[5].setName("Klinos");
        territories[6].setName("Ost-Klina");
        territories[7].setName("Nord-Ziegelsand");
        territories[8].setName("Ziegelsand");
        territories[9].setName("Hohe Hinkel");

        territories[10].setName("Südforstheim");
        territories[11].setName("Eishaiz");
        territories[12].setName("Zankostane");
        territories[13].setName("Silbertal");
        territories[14].setName("Zamuria");

        territories[15].setName("Eden");
        territories[16].setName("TautonischeHochebene");
        territories[17].setName("Wadland");
        territories[18].setName("Prachthafen");
        territories[19].setName("Bürthal");
        territories[20].setName("Hochlaub");
        territories[21].setName("Ostesteinburg");
        territories[22].setName("TeutonischerHammer");

        territories[23].setName("Nosptenburg");
        territories[24].setName("Graue Ebene");
        territories[25].setName("Hoiawald");

        territories[26].setName("Bakanwald");
        territories[27].setName("Rorschachwall");
        territories[28].setName("Schwarzfeld");
        territories[29].setName("Mimiken");
        territories[30].setName("Waithenland");
        territories[31].setName("Windbergen");

        territories[32].setName("Sonnenwiese");
        territories[33].setName("Haus der Sonne");
        territories[34].setName("Morgenfeld");
        territories[35].setName("Morgenrücken");
        territories[36].setName("Uppenwald");
        territories[37].setName("Waithfels");

        territories[38].setName("Donnerwald");
        territories[39].setName("Wuselwiese");
        territories[40].setName("Goldbach");
        territories[41].setName("Niederswith");
        territories[42].setName("Sumbaspitze");
        territories[43].setName("Sandwacht");
        territories[44].setName("Nord-forstheim");

        //set the bound of the territory buttons
        territories[0].setBounds(35, 250, 60, 60);
        territories[1].setBounds(35, 365, 60, 60);
        territories[2].setBounds(150, 450, 110, 60);
        territories[3].setBounds(200, 330, 110, 60);
        territories[4].setBounds(200, 270, 60, 60);
        territories[5].setBounds(124, 186, 60, 60);
        territories[6].setBounds(231, 173, 60, 60);
        territories[7].setBounds(295, 270, 150, 60);
        territories[8].setBounds(350, 360, 60, 60);
        //random
        territories[9].setBounds(273,434,60,60);
        territories[10].setBounds(77, 613, 60, 60);
        territories[11].setBounds(180, 574, 60, 60);
        territories[12].setBounds(280, 555, 60, 60);
        territories[13].setBounds(226, 657, 60, 60);
        territories[14].setBounds(383, 610, 60, 60);
        territories[15].setBounds(606, 345, 60, 60);
        territories[16].setBounds(586, 436, 60, 60);
        territories[17].setBounds(543, 502, 60, 60);
        territories[18].setBounds(451, 545, 60, 60);
        //random ends
        territories[19].setBounds(605, 520, 150, 60);
        //random starts
        territories[20].setBounds(714, 496, 60, 60);
        territories[21].setBounds(765, 449, 60, 60);
        territories[22].setBounds(711, 388, 60, 60);
        territories[23].setBounds(472, 139, 60, 60);
        //random ends
        territories[24].setBounds(490, 210, 60, 60);
        //random starts
        territories[25].setBounds(572, 120, 60, 60);
        //random ends
        territories[26].setBounds(655, 160, 100, 60);
        territories[27].setBounds(655, 100, 150, 60);
        territories[28].setBounds(800, 150, 110, 60);
        territories[29].setBounds(950, 140, 150, 60);
        //random starts
        territories[30].setBounds(843, 259, 60, 60);
        territories[31].setBounds(1040, 274, 60, 60);
        territories[32].setBounds(899, 649, 60, 60);
        territories[33].setBounds(1009, 662, 60, 60);
        //random ends
        territories[34].setBounds(945, 510, 150, 60);
        //random starts
        territories[35].setBounds(1035, 467, 60, 60);
        territories[36].setBounds(891, 386, 60, 60);
        //random ends
        territories[37].setBounds(932, 336, 100, 60);
        //random starts
        territories[38].setBounds(611, 669, 60, 60);
        territories[39].setBounds(615, 612, 60, 60);
        territories[40].setBounds(699, 656, 60, 60);
        territories[41].setBounds(782, 333, 60, 60);
        territories[42].setBounds(354, 89, 60, 60);
        territories[43].setBounds(436, 378, 60, 60);
        territories[44].setBounds(63, 557, 60, 60);

        //get army values
        createTerritoryColors();
        updateTurnColor();
        updateTurnColor();

        attackButton = new JButton("ATTACK");
        attackButton.setName("ATTACK");
        attackButton.setBounds(200, 880, 150, 50);
        attackButton.setContentAreaFilled(true);
        attackButton.setBorderPainted(true);
        attackButton.setEnabled(false);
        attackButton.addActionListener(this);
        background.add(attackButton);


        cardInfoPanelButton = new JButton("CARD INFO");
        cardInfoPanelButton.setName("cardInfoPanelButton");
        cardInfoPanelButton.setBounds(500, 880, 150, 50);
        cardInfoPanelButton.setContentAreaFilled(true);
        cardInfoPanelButton.setBorderPainted(true);
        cardInfoPanelButton.setEnabled(true);
        cardInfoPanelButton.addActionListener(this);
        background.add(cardInfoPanelButton);

        miniGame = new JButton("MINI GAME");
        miniGame.setName("miniGame");
        miniGame.setBounds(650, 880, 150, 50);
        miniGame.setContentAreaFilled(true);
        miniGame.setBorderPainted(true);
        miniGame.setEnabled(true);
        miniGame.addActionListener(this);
        background.add(miniGame);

        fortifyButton = new JButton("FORTIFY");
        fortifyButton.setName("fortifyButton");
        fortifyButton.setBounds(350, 930, 150, 50);
        fortifyButton.setContentAreaFilled(true);
        fortifyButton.setBorderPainted(true);
        fortifyButton.setEnabled(true);
        fortifyButton.addActionListener(this);
        background.add(fortifyButton);

        draftButton = new JButton("DRAFT");
        draftButton.setName("DRAFT");
        draftButton.setBounds(200, 930, 150, 50);
        draftButton.setContentAreaFilled(true);
        draftButton.setBorderPainted(true);
        draftButton.setEnabled(false);
        draftButton.addActionListener(this);
        background.add(draftButton);


        nextPlayerButton = new JButton("NEXT PLAYER");
        nextPlayerButton.setName("NEXT PLAYER");
        nextPlayerButton.setBounds(350, 880, 150, 50);
        nextPlayerButton.setContentAreaFilled(true);
        nextPlayerButton.setBorderPainted(true);
        nextPlayerButton.setEnabled(true);
        nextPlayerButton.addActionListener(this);
        background.add(nextPlayerButton);

        secretMissionCard = new JButton("SECRET MISSION");
        secretMissionCard.setName("SECRET MISSION");
        secretMissionCard.setBounds(650, 930, 150, 50);
        secretMissionCard.setContentAreaFilled(true);
        secretMissionCard.setBorderPainted(true);
        secretMissionCard.setEnabled(true);
        secretMissionCard.addActionListener(this);
        secretMissionCard.setVisible(game.getSecretMissionMod());
        background.add(secretMissionCard);

        cursedCardInfoFrameButton = new JButton("CURSED CARDS");
        cursedCardInfoFrameButton.setName("CURSED CARDS");
        cursedCardInfoFrameButton.setBounds(500, 930, 150, 50);
        cursedCardInfoFrameButton.setContentAreaFilled(true);
        cursedCardInfoFrameButton.setBorderPainted(true);
        cursedCardInfoFrameButton.setEnabled(true);
        cursedCardInfoFrameButton.addActionListener(this);
        background.add(cursedCardInfoFrameButton);

        retreatButton = new JButton("RETREAT");
        retreatButton.setBounds(880, 880, 150, 50);
        retreatButton.setContentAreaFilled(true);
        retreatButton.setBorderPainted(true);
        retreatButton.setEnabled(false);
        retreatButton.addActionListener(this);
        background.add(retreatButton);

        pauseButton = new JButton("Pause");
        pauseButton.setBounds(505, 180, 100, 50);
        pauseButton.setContentAreaFilled(true);
        pauseButton.setBorderPainted(true);
        pauseButton.addActionListener(this);
        background.add(pauseButton);
        settingPane = new JLayeredPane();
        settingPane.setPreferredSize(new Dimension(500,700));
        settingPane.setBounds(250,150,700,600);
        settingPane.setBackground(Color.BLUE);
        settingPane.setVisible(true);
        JLabel label1 = new JLabel();
        label1.setOpaque(true);
        label1.setBackground(Color.WHITE);
        label1.setBounds(200,100,200,1200);
        JButton buttonback = new JButton("BACK");
        buttonback.setBounds(250,250,100,60);

        settingPane.add(label1);
        settingPane.add(buttonback);
        component1.setLocation(80 ,175);
        component1.setSize(component1.getPreferredSize());
        background.add(component1);
        background.repaint();
        background.revalidate();
        mainPanel.add(background);
        mainPanel.setBackground(new Color(199,162,110));


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
    public void playMusic(String soundName,float volume)
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
            clipMusic = AudioSystem.getClip( );
            clipMusic.open(audioInputStream);
            FloatControl gainControl =
                    (FloatControl) clipMusic.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume); // Reduce volume by 10 decibels.
            clipMusic.loop(Clip.LOOP_CONTINUOUSLY);
            clipMusic.start( );
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace( );
        }
    }
    public void playMusicMini(String soundName,float volume)
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
            miniGameMusic = AudioSystem.getClip( );
            miniGameMusic.open(audioInputStream);
            FloatControl gainControl =
                    (FloatControl) miniGameMusic.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume); // Reduce volume by 10 decibels.
            miniGameMusic.loop(Clip.LOOP_CONTINUOUSLY);
            miniGameMusic.start( );
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace( );
        }
    }

    public void actionPerformed(ActionEvent e)
    {

        if( (e.getSource() != pauseButton) &&
                (e.getSource() != attackButton) && (e.getSource() != retreatButton)
                && (e.getSource() != rollDiceButton) && (e.getSource() != allianceButton)
                && (e.getSource() != secretMissionCard)&& (e.getSource() != cursedCardInfoFrameButton)
                && (e.getSource() != decreaseDice) && (e.getSource() != increaseDice)
                && (e.getSource() != decreaseDiceDef) && (e.getSource() != increaseDiceDef)
                && (e.getSource() != cardInfoPanelButton && e.getSource() != fortifyButton)
                && (e.getSource() != retreatButton) && (e.getSource() != miniGame)
        ) {
            JButton tmp = (JButton) e.getSource();
            //tmp.setBackground(Color.RED);
            if(setFrom) {
                System.out.println("first");
                from = tmp.getName();
                setFrom = !setFrom;
                fromButton.setBorder(territories[0].getBorder());
                fromButton = tmp;
                Territory fromTerrTmp = game.getMap().getTerritoryFromName(from);
                Color cF;
                if(game.getMap().getTerritoryFromName(from)!=null){
                    if(fromTerrTmp.getArmy() == null) {
                        fromButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        return;
                    }
                    cF = fromTerrTmp.getArmy().getOwner().getColor();

                    fromButton.setBorder(BorderFactory.createLineBorder(cF));
                }

            } else if( from != "" ) {
                System.out.println("second");
                to = tmp.getName();
                toButton.setBorder(territories[0].getBorder());
                toButton = tmp;
                Territory toTerrTmp = game.getMap().getTerritoryFromName(to);
                Color cT;
                if(game.getMap().getTerritoryFromName(to)!=null){
                    cT = toTerrTmp.getArmy().getOwner().getColor();
                    toButton.setBorder(BorderFactory.createLineBorder(cT));
                }
                setFrom = !setFrom;

            } else {
                System.out.println("third");
                from = tmp.getName();
                Territory fromTerrTmp = game.getMap().getTerritoryFromName(from);
                Color cF;
                fromButton.setBorder(territories[0].getBorder());
                fromButton = tmp;
                if(game.getMap().getTerritoryFromName(from)!=null){
                    cF = fromTerrTmp.getArmy().getOwner().getColor();
                    fromButton.setBorder(BorderFactory.createLineBorder(cF));
                }

            }

            if( from != "" && to != "" && !setFrom ) {
                from = "";
                to = "";
                setFrom = !setFrom;
                toButton.setBorder(territories[0].getBorder());
                fromButton.setBorder(territories[0].getBorder());
            }

            if( !from.equals("") && !to.equals("") ) {
                attackButton.setEnabled(true);
                fortifyButton.setEnabled(true);
                retreatButton.setEnabled(true);
            } else {

                attackButton.setEnabled(false);
                fortifyButton.setEnabled(false);
                retreatButton.setEnabled(false);
            }


            draftButton.setEnabled(true);

            if(tmp.getName() == "DRAFT") {
                System.out.println(chosenTerritory);
            }
            else if(tmp.getName() != "ATTACK") {
                chosenTerritory = tmp.getName();
                System.out.println(chosenTerritory);
            }

            System.out.println("from: " + from);
            System.out.println("to: " + to);
            System.out.println("setfrom: " + setFrom);

        }
        if(e.getSource()==pauseButton)
        {
            // stop the timer and continue when the player chooses to continue
            timer1.cancel();
            timer1.purge();
            // TODO: there is a bug where the timer does not start when player clicks setting and continues

            //mainPanel.setEnabled(false);
            int chosenoption;
            Object[] options = { "CONTINUE", "HOW TO PLAY","SETTINGS","SAVE GAME","QUIT" };
            chosenoption = JOptionPane.showOptionDialog(null, "GAME PAUSED", "GAME PAUSED",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if(chosenoption == 4)
            {
                playSound("./src/main/resources/sounds/snd_quitgame.wav",-10.0f);
                int quit = JOptionPane.showConfirmDialog(null,
                        "ARE YOU A LOSER?", "QUIT?", JOptionPane.YES_NO_OPTION);
                if(quit == 0)
                {
                    dispose();
                }
            }else if(chosenoption == 3)
            {
                gameManager.saveGame();
                timer1 = new Timer();
                timer1.scheduleAtFixedRate(createTimerTask(),1000, 1000);
            }
            else if(chosenoption == 2) //settings
            {
                //setVisible(false);
                mainPanel.setVisible(false);
                setPanel = new SettingsPanel(mainPanel,soundManager,null);
                add(setPanel);
            }else if(chosenoption == 1) //how to playe
            {
                mainPanel.setVisible(false);
                howToPlayPanel = new HowToPlayPanel(mainPanel,soundManager);
                add(howToPlayPanel);
            }else if(chosenoption == 0) //continue
            {
                mainPanel.setEnabled(true);
                timer1 = new Timer();
                timer1.scheduleAtFixedRate(createTimerTask(),1000, 1000);
            } else if(chosenoption == JOptionPane.CLOSED_OPTION) {
                timer1 = new Timer();
                timer1.scheduleAtFixedRate(createTimerTask(),1000, 1000);
            }
        }

        if(e.getSource() == secretMissionCard)
        {
            int secretMission = players[currentPlayer].getSecretMission();
            secretMissionFrame = new SecretMissionFrame(secretMission);
            secretMissionFrame.setVisible(true);
            secretMissionFrame.setTitle("Risk");
            secretMissionFrame.setResizable(false);
            secretMissionFrame.pack();
        }
        if(e.getSource() == retreatButton)
        {
            Territory fromTerr = gameManager.getGame().getMap().getTerritoryFromName(from);
            Territory toTerr = gameManager.getGame().getMap().getTerritoryFromName(to);
            boolean retreatSuccessfull = game.retreat(fromTerr,toTerr);

            if(retreatSuccessfull)
            {
                updateTerritories();
                from = "";
                to = "";
                toButton.setBorder(territories[0].getBorder());
                fromButton.setBorder(territories[0].getBorder());

                retreatButton.setEnabled(false);
            }else
            {
                JOptionPane.showMessageDialog(null,"Both territories should be your territory");
            }
            Player potentialWinner = game.checkIfAnyoneWon();
            if(potentialWinner != null)
            {
                overallWinner = potentialWinner.getName();
                endTheGame();
                JOptionPane.showMessageDialog(null,potentialWinner.getName()+" has won the game");
            }

        }
        if(e.getSource() == attackButton)
        {
            if( noOfPlayers == 2 ) {
                allianceButton.setEnabled(false);
            } else {
                allianceButton.setEnabled(true);
            }
            
            if(attackAmt >= 3) {
                JOptionPane.showMessageDialog(null, "Cannot attack for more than 3 times in a turn!");
                return;
            }

            Territory fromTerr = gameManager.getGame().getMap().getTerritoryFromName(from);
            Territory toTerr = gameManager.getGame().getMap().getTerritoryFromName(to);

            if( fromTerr.getArmy() == null || toTerr.getArmy() == null ) {
                System.out.println("One of the armies are null!");
                JOptionPane.showMessageDialog(null, "You cannot attack from/to an empty country!");
                from = "";
                to = "";
                toButton.setBorder(territories[0].getBorder());
                fromButton.setBorder(territories[0].getBorder());
                return;
            }

            // cannot attack to immune territory
            if( toTerr.getArmy().isImmune() ) {
                JOptionPane.showMessageDialog(null, toTerr.getName() + " is immune!");
                return;
            }

            if( fromTerr.getArmy().getTotalValue() <= 1 ) {
                JOptionPane.showMessageDialog(null, "You need to have at least 2 infantries to attack!");
                return;
            }

            if( ( !players[currentPlayer].hasTerritory( fromTerr ) )
                    || ( players[currentPlayer].hasTerritory( toTerr ) ) ) {
                System.out.println("Invalid attack!");
                JOptionPane.showMessageDialog(null, "Invalid attack!");
                from = "";
                to = "";
                toButton.setBorder(territories[0].getBorder());
                fromButton.setBorder(territories[0].getBorder());
                return;
            } else if(fromTerr.isNeighbor(toTerr)) {
                attackAmt++;

                startAttackTimer();
                timer1.cancel();
                timer1.purge();

                Player attacker = fromTerr.getArmy().getOwner();
                Player defender = toTerr.getArmy().getOwner();
                attackerLabel = new JLabel(attacker.getName() + " has " + fromTerr.getArmy().getTotalValue() + " soldiers");
                defenderLabel = new JLabel(defender.getName() + " has " + toTerr.getArmy().getTotalValue() + " soldiers");

                ImageIcon attackerIcon = new ImageIcon(attacker.getAvatar().getImageFileName());
                ImageIcon defenderIcon = new ImageIcon(defender.getAvatar().getImageFileName());
                playSound("./src/main/resources/sounds/snd_sword1.wav",-10.0f);

                System.out.println("Executing war from "+from + " to " + to + "!");

                playSound("./src/main/resources/sounds/snd_howtoplay.wav",-10.0f);

                rollDiceButton.setEnabled(true);

                decreaseDice.setEnabled(true);
                increaseDice.setEnabled(true);
                decreaseDiceDef.setEnabled(true);
                increaseDiceDef.setEnabled(true);
                mainPanel.setVisible(false);
                panel1.setVisible(true);
                panel1.setLayout(null);
                player1.setIcon(attackerIcon);
                player1.setBounds(70, 200, 100, 100);
                attackerLabel.setBounds(60,100,300,100);
                panel1.add(player1);
                panel1.add(attackerLabel);

                player2.setIcon(defenderIcon);
                player2.setBounds(920, 200, 100, 100);
                defenderLabel.setBounds(910,100,300,100);
                panel1.add(player2);
                panel1.add(defenderLabel);
                panel1.add(attackTimerLabel);
                System.out.println(chosenTerritory);
                territoryName.setText("BATTLE FROM " + from + " TO " + to);
                panel1.add(territoryName);

                panel1.add(rollDiceButton);

                panel1.add(decreaseDice);

                panel1.add(allianceButton);

                // cannot request alliance if there are 2 players
                if( noOfPlayers == 2 ) {
                    allianceButton.setEnabled(false);
                }

                panel1.add(increaseDice);

                panel1.add(decreaseDiceDef);
                panel1.add(increaseDiceDef);

                panel1.add(firstDiceSet);
                panel1.add(secondDiceSet);
                panel1.add(thirdDiceSet);
                panel1.add(forthDiceSet);
                panel1.add(fifthDiceSet);

                //panel1.setBackground(new Color(182,115,45));
                add(panel1);

            }else
            {
                JOptionPane.showMessageDialog(null, "Territories should be neighbors!");
                from = "";
                to = "";
                toButton.setBorder(territories[0].getBorder());
                fromButton.setBorder(territories[0].getBorder());
            }

            attackButton.setEnabled(false);

        }

        if(e.getSource() == rollDiceButton)
        {
            int[] dices;
            playSound("./src/main/resources/sounds/snd_dicethrow.wav",-10.0f);

            /*
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
*/
            //firstDiceSet.setIcon(new ImageIcon("./src/main/resources/images/dicered" + 1 + ".png"));
            secondDiceSet.setIcon(new ImageIcon("./src/main/resources/images/dicered" + 1 + ".png"));
            thirdDiceSet.setIcon(new ImageIcon("./src/main/resources/images/dicered" + 1 + ".png"));
            //forthDiceSet.setIcon(new ImageIcon("./src/main/resources/images/diceblue" + 1 + ".png"));
            fifthDiceSet.setIcon(new ImageIcon("./src/main/resources/images/diceblue" + 1 + ".png"));


            allianceButton.setEnabled(false);
            decreaseDice.setEnabled(false);
            increaseDice.setEnabled(false);
            decreaseDiceDef.setEnabled(false);
            increaseDiceDef.setEnabled(false);

            Territory fromTerr = gameManager.getGame().getMap().getTerritoryFromName(from);
            Territory toTerr = gameManager.getGame().getMap().getTerritoryFromName(to);
            Army attacker = fromTerr.getArmy();
            Army defender = toTerr.getArmy();

            // ********************* Single Attack **********************//

            //I think this part should go above close panel operations

            dices = game.getCombatManager().singleAttack( attacker, defender ,diceNumberLeft,diceNumberLeftDef);

            attackerLabel.setText( fromTerr.getArmy().getOwner().getName()+" has "+fromTerr.getArmy().getTotalValue()+" soldiers" );
            defenderLabel.setText( toTerr.getArmy().getOwner().getName()+" has "+toTerr.getArmy().getTotalValue()+" soldiers" );
            //firstDiceSet.setIcon(new ImageIcon("./src/main/resources/images/dicered" + dices[0] + ".png"));
            //forthDiceSet.setIcon(new ImageIcon("./src/main/resources/images/diceblue" + dices[1] + ".png"));
            //int i = 0;
            if(dices != null)
            {
                if(diceNumberLeft == 3)
                {
                    firstDiceSet.setIcon(new ImageIcon("./src/main/resources/images/dicered" + dices[0] + ".png"));
                    secondDiceSet.setIcon(new ImageIcon("./src/main/resources/images/dicered" + dices[1] + ".png"));
                    thirdDiceSet.setIcon(new ImageIcon("./src/main/resources/images/dicered" + dices[2] + ".png"));
                    if(diceNumberLeftDef == 2)
                    {
                        forthDiceSet.setIcon(new ImageIcon("./src/main/resources/images/diceblue" + dices[3] + ".png"));
                        fifthDiceSet.setIcon(new ImageIcon("./src/main/resources/images/diceblue" + dices[4] + ".png"));
                    }else{
                        forthDiceSet.setIcon(new ImageIcon("./src/main/resources/images/diceblue" + dices[3] + ".png"));
                    }
                }
                else if(diceNumberLeft == 2)
                {
                    firstDiceSet.setIcon(new ImageIcon("./src/main/resources/images/dicered" + dices[0] + ".png"));
                    secondDiceSet.setIcon(new ImageIcon("./src/main/resources/images/dicered" + dices[1] + ".png"));
                    if(diceNumberLeftDef == 2)
                    {
                        forthDiceSet.setIcon(new ImageIcon("./src/main/resources/images/diceblue" + dices[2] + ".png"));
                        fifthDiceSet.setIcon(new ImageIcon("./src/main/resources/images/diceblue" + dices[3] + ".png"));
                    }else{
                        forthDiceSet.setIcon(new ImageIcon("./src/main/resources/images/diceblue" + dices[2] + ".png"));
                    }
                }else if(diceNumberLeft == 1) {
                    firstDiceSet.setIcon(new ImageIcon("./src/main/resources/images/dicered" + dices[0] + ".png"));
                    if (diceNumberLeftDef == 2) {
                        forthDiceSet.setIcon(new ImageIcon("./src/main/resources/images/diceblue" + dices[1] + ".png"));
                        fifthDiceSet.setIcon(new ImageIcon("./src/main/resources/images/diceblue" + dices[2] + ".png"));
                    } else {
                        forthDiceSet.setIcon(new ImageIcon("./src/main/resources/images/diceblue" + dices[1] + ".png"));
                    }
                }
            }else
            {
                diceNumberLeft = 3;
                diceNumberLeftDef = 2;
            }

            // ********************* End of Single Attack **********************//


            // close panel 1 if the attack turn has ended
            if( game.getCombatManager().warEnded(attacker, defender) != null ) {
                Army winner = game.getCombatManager().warEnded(attacker,defender);
                System.out.println("Combat finish about to enter addAttack");
                System.out.println(winner.getOwner().getName());
                System.out.println(attacker.getOwner().getName());

                //problem here
                if(!winner.getOwner().getName().equals(attacker.getOwner().getName()))
                {
                    System.out.println("entered addAttack");
                    attacker.getOwner().addAttack(defender.getOwner().getName());
                }
                System.out.println("War has ended. Close panel1.");
                game.endAttackTurn(fromTerr, toTerr);
                from = "";
                to = "";
                toButton.setBorder(territories[0].getBorder());
                fromButton.setBorder(territories[0].getBorder());
                int chosenoption;
                Object[] options = { "OK" };
                chosenoption = JOptionPane.showOptionDialog(null, "Winner is "+winner.getOwner().getName(), "WAR ENDED",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);
                if(chosenoption == 0)
                {

                    attackerLabel.setText("");
                    defenderLabel.setText("");
                    updateTerritories();
                    remove(panel1);
                    mainPanel.setVisible(true);
                    attackButton.setEnabled(false);
                    retreatButton.setEnabled(false);
                    attackTimer.cancel();
                    attackTimer.purge();
                    attackSeconds = 30;
                    // continue the timer whenever it is left
                    timer1 = new Timer();
                    timer1.scheduleAtFixedRate(createTimerTask(),1000, 1000);

                    Player potentialWinner = game.checkIfAnyoneWon();
                    if(potentialWinner != null)
                    {
                        overallWinner = potentialWinner.getName();
                        endTheGame();
                        //System.out.println("won the game!!!!!!!!!");
                        //JOptionPane.showMessageDialog(null,players[currentPlayer].getName()+" has won the game!");
                    }
                }

            }
        }


        if(e.getSource() == allianceButton)
        {

            // We need an option pane here that will ask which user the defender wants
            // to request soldiers(should show every player other than the attacker) and ask the soldier amount
            Territory fromTerr = gameManager.getGame().getMap().getTerritoryFromName(from);
            Territory toTerr = gameManager.getGame().getMap().getTerritoryFromName(to);
            Player attacker =fromTerr.getArmy().getOwner();
            Player defender = toTerr.getArmy().getOwner();
            ArrayList<Player> otherPlayers = new ArrayList<Player>();
            for(int i = 0; i < noOfPlayers;i++)
            {
                if(players[i] != attacker && players[i] != defender)
                {
                    otherPlayers.add(players[i]);
                }
            }
            String[] otherPlayersArray = new String[noOfPlayers-2];
            for(int i = 0; i<noOfPlayers-2;i++)
            {
                otherPlayersArray[i] = otherPlayers.get(i).getName();
            }
            Object[] options = otherPlayersArray;
            int selectedPlayer = JOptionPane.showOptionDialog(null, "Who do you want help from?", "Alliance",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);
            String soldierNumber = JOptionPane.showInputDialog("How many soldiers do you want");
            Object[] acceptanceOptions = { "Yes","No" };
            int acceptance = JOptionPane.showOptionDialog(null, otherPlayersArray[selectedPlayer]+" do you accept this alliance", "Alliance",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, acceptanceOptions, acceptanceOptions[0]);
            if(acceptance == 0)
            {
                Player tmp;
                for(int i = 0; i < noOfPlayers;i++)
                {
                    if(players[i].getName() == otherPlayersArray[selectedPlayer]);
                    {
                        tmp = players[i];
                        if( !game.getAlliance( tmp, toTerr, Integer.parseInt(soldierNumber)) ) {
                            JOptionPane.showMessageDialog(null, "Player " + tmp.getName() + " does not have enough soldiers!");
                        }
                        break;
                    }
                }
                attackerLabel.setText(fromTerr.getArmy().getOwner().getName()+" has "+fromTerr.getArmy().getTotalValue()+" soldiers");
                defenderLabel.setText(toTerr.getArmy().getOwner().getName()+" has "+toTerr.getArmy().getTotalValue()+" soldiers");
                updateTerritories();
                updateTurnColor();
            }
            //game.getAlliance( aiderPlayer, defender, infantryAmt);

        }


        if(e.getSource() == decreaseDice)
        {
            thirdDiceSet.setVisible(false);
            if(diceNumberLeft == 2)
            {
                secondDiceSet.setVisible(false);
            }
            diceNumberLeft--;
            increaseDice.setEnabled(true);
            if(diceNumberLeft == 1)
            {
                decreaseDice.setEnabled(false);
            }

        }

        if(e.getSource() == increaseDice)
        {
            secondDiceSet.setVisible(true);
            decreaseDice.setEnabled(true);

            if(diceNumberLeft == 2)
            {
                increaseDice.setEnabled(false);
            }
            diceNumberLeft++;
            if(diceNumberLeft == 3)
            {
                thirdDiceSet.setVisible(true);
            }


        }
        if(e.getSource() == decreaseDiceDef)
        {
            fifthDiceSet.setVisible(false);
            increaseDiceDef.setEnabled(true);
            diceNumberLeftDef--;
            if(diceNumberLeftDef == 1)
            {
                decreaseDiceDef.setEnabled(false);
            }
        }

        if(e.getSource() == increaseDiceDef)
        {
            fifthDiceSet.setVisible(true);
            decreaseDiceDef.setEnabled(true);
            diceNumberLeftDef++;
            if(diceNumberLeftDef == 2)
            {
                increaseDiceDef.setEnabled(false);
            }
        }

        if( e.getSource() == draftButton ) {
            Territory chosen = gameManager.getGame().getMap().getTerritoryFromName(chosenTerritory);

            if( draftAmt >= 3 ) {
                JOptionPane.showMessageDialog(null, "Cannot draft more than 3 in a turn!");
                return;
            }

            if ( chosen.getArmy() != null && chosen.getArmy().getOwner() != players[currentPlayer] ) {
                JOptionPane.showMessageDialog(null, "Cannot draft to enemy territory!");
                return;
            }



            System.out.println("Drafting " + chosenTerritory);
            String m = "";
            m = JOptionPane.showInputDialog("Drafting " + chosenTerritory + ". How many soldiers?");

            int troopAmt = Integer.parseInt(m);
            int playerTurn = gameManager.getGame().getCurrentPlayerTurn();
            Player p = gameManager.getGame().getPlayers()[playerTurn];
            int maxInfAmt = p.getInfantryAmt();

            if (troopAmt > maxInfAmt) {
                    JOptionPane.showMessageDialog(null,"Invalid. Amount of infantries in hand is: " + maxInfAmt );
                    from = "";
                    to = "";
                toButton.setBorder(territories[0].getBorder());
                fromButton.setBorder(territories[0].getBorder());
                    System.out.println();
            } else {
                // TODO: Add a notification panel to show the drafted soldier amount and the territory name.
                gameManager.getGame().draftTurn(chosen, troopAmt);
                draftAmt++;

                from = "";
                to = "";
                toButton.setBorder(territories[0].getBorder());
                fromButton.setBorder(territories[0].getBorder());

                //update army values
                updateTerritories();
                gameManager.getGame().printInfAmt();
                attackButton.setEnabled(false);
                fortifyButton.setEnabled(false);
                player1name.setText(players[0].getName() + " Infantry numbers:" + players[0].getInfantryAmt());
                player2name.setText(players[1].getName() + " Infantry numbers:" + players[1].getInfantryAmt());
                if(noOfPlayers>=3)
                    player3name.setText(players[2].getName() + " Infantry numbers:" + players[2].getInfantryAmt());
                if(noOfPlayers>=4)
                    player4name.setText(players[3].getName() + " Infantry numbers:" + players[3].getInfantryAmt());
                revalidate();
            }
            Player potentialWinner = game.checkIfAnyoneWon();
            if(potentialWinner != null)
            {
                overallWinner = potentialWinner.getName();
                endTheGame();
                //JOptionPane.showMessageDialog(null,potentialWinner.getName()+" has won the game");
            }

        }

        if( e.getSource() == nextPlayerButton ) {

            //pass the turn to the next player
            gainAmt = game.passTurn();
            draftAmt = 0;
            attackAmt = 0;

            if(players[game.getCurrentPlayerTurn()].getMiniGameChance() != 0)
            {
                miniGame.setEnabled(true);
            }else
            {
                miniGame.setEnabled(false);
            }

            Player p = game.checkIfAnyoneWon();
            if( p != null ) {
                overallWinner = p.getName();
                endTheGame();
                //JOptionPane.showMessageDialog(null,"Player " + p.getName() );
                // TODO: end game panel
            }
            game.printInfAmt();
            currentPlayer = game.getCurrentPlayerTurn();
            from = "";
            to = "";
            toButton.setBorder(territories[0].getBorder());
            fromButton.setBorder(territories[0].getBorder());
            attackButton.setEnabled(false);
            updateTerritories();
            updateTurnColor();
            seconds = 60;
            //TODO: add gained inf amt
            JOptionPane.showMessageDialog(null, "Player "+ players[currentPlayer].getName()+" got "+ gainAmt+ " more soldiers");

        }
        if(e.getSource() == cardInfoPanelButton)
        {
            cardPanel = new CardPanel(game,cardPanel);
            cardPanel.setVisible(true);
            cardPanel.setTitle("Risk");
            cardPanel.setResizable(false);
            cardPanel.pack();
            cardPanel.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    updateTurnColor();
                    System.out.println("card panel is closed");
                    Player potentialWinner = game.checkIfAnyoneWon();
                    if(potentialWinner != null)
                    {
                        overallWinner = potentialWinner.getName();
                        endTheGame();
                        //JOptionPane.showMessageDialog(null,potentialWinner.getName()+" has won the game");
                    }
                }
            });
        }
        if(e.getSource() == cursedCardInfoFrameButton)
        {
            cursedCardsFrame = new CursedCardsFrame(game, this);
            cursedCardsFrame.setVisible(true);
            cursedCardsFrame.setTitle("Risk");
            cursedCardsFrame.setResizable(false);
            cursedCardsFrame.pack();
            cursedCardsFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    updateTurnColor();
                    updateTerritories();
                    System.out.println("Cursed card panel is closed");
                    Player potentialWinner = game.checkIfAnyoneWon();
                    if(potentialWinner != null)
                    {
                        overallWinner = potentialWinner.getName();
                        endTheGame();
                        //JOptionPane.showMessageDialog(null,potentialWinner.getName()+" has won the game");
                    }
                }
            });
            cursedCardsFrame.setFrame(cursedCardsFrame);
        }

        if(e.getSource() == fortifyButton) {
            if( (from == "") || (to == "") ) {
                JOptionPane.showMessageDialog(null,"Invalid selection." );
                from = "";
                to = "";
                toButton.setBorder(territories[0].getBorder());
                fromButton.setBorder(territories[0].getBorder());
                return;
            }

            Territory fromTerr = gameManager.getGame().getMap().getTerritoryFromName(from);
            Territory toTerr = gameManager.getGame().getMap().getTerritoryFromName(to);

            if( game.fortifyTurn(fromTerr, toTerr, 0) ) {
                String m="";
                m = JOptionPane.showInputDialog("Fortifying " + chosenTerritory +". How many soldiers?");
                int troopAmt = Integer.parseInt(m);
                if( troopAmt >= fromTerr.getArmy().getTotalValue() ) {
                    from = "";
                    to = "";
                    toButton.setBorder(territories[0].getBorder());
                    fromButton.setBorder(territories[0].getBorder());
                    JOptionPane.showMessageDialog(null,"Not enough soldiers. At least one soldier should be left at the supporting territory" );
                } else {
                    game.fortifyTurn(fromTerr, toTerr, troopAmt);
                    updateTerritories();
                }
            } else {
                JOptionPane.showMessageDialog(null,"Cannot access from " + from + " to " + to );
            }

            from = "";
            to = "";
            toButton.setBorder(territories[0].getBorder());
            fromButton.setBorder(territories[0].getBorder());
            fortifyButton.setEnabled(false);
            attackButton.setEnabled(false);
            retreatButton.setEnabled(false);
            Player potentialWinner = game.checkIfAnyoneWon();
            if(potentialWinner != null)
            {
                overallWinner = potentialWinner.getName();
                endTheGame();
                //JOptionPane.showMessageDialog(null,potentialWinner.getName()+" has won the game");
            }
        }
        if(e.getSource()==miniGame)
        {
            clipMusic.stop();
            playMusicMini("./src/main/resources/sounds/minigamemusic.wav",-15.0f);
            timer1.cancel();
            timer1.purge();
            if(players[game.getCurrentPlayerTurn()].getMiniGameChance() == 1)
            {
                players[game.getCurrentPlayerTurn()].setMiniGameChance(0);
                miniGame.setEnabled(false);
            }
            else
            {
                miniGame.setEnabled(true);
                players[game.getCurrentPlayerTurn()].setMiniGameChance(players[game.getCurrentPlayerTurn()].getMiniGameChance()-1);
            }
            JFrame f = new JFrame("Mini Game");
            try {
                MiniGamePanel miniGamePanel = new MiniGamePanel(f, gameManager);

                Icon bfmg = new ImageIcon("./src/main/resources/images/minigame.jpg");
                JLabel backgroundForMiniGame = new JLabel(bfmg);
                //backgroundForMiniGame.setBounds(0,0,800,600);
                //backgroundForMiniGame.add(miniGamePanel);

                //f.add(backgroundForMiniGame);
                f.add(miniGamePanel);
                f.setVisible(true);
                f.setSize(800, 600);
                f.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        f.dispose();
                        updateTurnColor();
                        System.out.println("minigame is closed");
                        timer1 = new Timer();
                        timer1.scheduleAtFixedRate(createTimerTask(),1000, 1000);
                        miniGameMusic.stop();
                        clipMusic.start();
                    }
                });
            }catch (IOException ioException)
            {
                System.out.println("execption");
            }
        }


        repaint();
    }

    public void updateTurnColor()
    {
        if(currentPlayer == 0)
        {
            player1name.setText(players[0].getName()+" Infantry numbers:" + players[0].getInfantryAmt());
            component1.setLocation(0 ,175);
            component1.setColor(players[0].getColor());
            component1.setSize(component1.getPreferredSize());
            background.add(component1);
            background.repaint();
            background.revalidate();

        }else if(currentPlayer == 1)
        {
            player2name.setText(players[1].getName()+" Infantry numbers:" + players[1].getInfantryAmt());
            component1.setLocation(1050,175);
            component1.setColor(players[1].getColor());
            component1.setSize(component1.getPreferredSize());
            background.add(component1);
            background.repaint();
            background.revalidate();

        }else if(currentPlayer == 2)
        {
            player3name.setText(players[2].getName()+" Infantry numbers:" + players[2].getInfantryAmt());
            component1.setLocation(0,855);
            component1.setColor(players[2].getColor());
            component1.setSize(component1.getPreferredSize());
            background.add(component1);
            background.repaint();
            background.revalidate();

        }else if(currentPlayer == 3)
        {
            player4name.setText(players[3].getName()+" Infantry numbers:" + players[3].getInfantryAmt());
            component1.setLocation(1050,855);
            component1.setColor(players[3].getColor());
            component1.setSize(component1.getPreferredSize());
            background.add(component1);
            background.repaint();
            background.revalidate();
        }
    }

    public void updateTerritories()
    {
        boolean enabled = false;
        for(int i = 0; i < 45; i++ )
        {
            if(map.getTerritoryFromName(territories[i].getName()).getArmy() != null)
            {
                territories[i].setText("" + map.getTerritoryFromName(territories[i].getName()).getArmy().getTotalValue());
                Player p = map.getTerritoryFromName(territories[i].getName()).getArmy().getOwner();
                territories[i].setForeground(p.getColor());
                //territories[i].setEnabled(true);
                enabled = true;
            }else
            {
                territories[i].setText("");
                try{
                ArrayList<Territory> neighbors = map.getTerritoryFromName(territories[i].getName()).getNeighbors();
                for(int j = 0; j < neighbors.size(); j++)
                {
                    if(map.getTerritoryFromName(neighbors.get(j).getName()).getArmy() != null)
                    {
                        //territories[j].setEnabled(true);
                        enabled = true;
                    }
                }}catch (Exception e)
                {
                    System.out.println( "------------------------------------------" );
                    System.out.println(territories[i].getName());
                }
            }
            if(!enabled)
            {
               // territories[i].setEnabled(false);
            }
            enabled = false;
        }
    }
    public void createTerritoryColors()
    {
        for(int i = 0; i < 45; i++ )
        {
            if(map.getTerritoryFromName(territories[i].getName()).getArmy() != null) {
                territories[i].setText("" + map.getTerritoryFromName(territories[i].getName()).getArmy().getTotalValue());
                Player p = map.getTerritoryFromName(territories[i].getName()).getArmy().getOwner();
                territories[i].setForeground(p.getColor());
                if( map.getTerritoryFromName(territories[i].getName()).getArmy().getImmuneTurnCount() >0 )
                    territories[i].setEnabled(false);
                else
                    territories[i].setEnabled(true);
            }
            mainPanel.add(territories[i]);
        }
    }

    public void startTimer(){
        timer1 = new Timer();
        timer1.scheduleAtFixedRate(createTimerTask(),1000,1000);
    }
    private TimerTask createTimerTask() {
        return new TimerTask(){
            @Override
            public void run() {
                seconds--;
                lblTimer.setText(""+seconds);
                if(seconds == 0)
                {
                    nextPlayerButton.doClick();
                    timer1.cancel();
                    if( cardPanel != null ) {
                        cardPanel.dispose();
                        updateTerritories();
                    }
                    if(secretMissionFrame != null) {
                        secretMissionFrame.dispose();
                        updateTerritories();
                    }
                    seconds = 60;
                    startTimer();
                }

                if (currentPlayer == 0)
                {
                    lblTimer.setForeground(players[0].getColor());
                }

                else if (currentPlayer == 1)
                {
                    lblTimer.setForeground(players[1].getColor());
                }

                else if (currentPlayer == 2)
                {
                    lblTimer.setForeground(players[2].getColor());
                }

                else if (currentPlayer == 3)
                {
                    lblTimer.setForeground(players[3].getColor());
                }
            }
        };
    }
    public void startAttackTimer(){
        attackTimer = new Timer();
        attackTimer.scheduleAtFixedRate(createAttackTimerTask(),1000,1000);
    }
    private TimerTask createAttackTimerTask() {
        return new TimerTask() {
            @Override
            public void run() {
                attackSeconds--;
                attackTimerLabel.setText(""+attackSeconds);
                if(attackSeconds == 0)
                {
                    attackTimer.cancel();
                    mainPanel.setVisible(true);
                    remove(panel1);
                    startTimer();
                    attackSeconds = 30;
                }
            }
        };
    }
    private void endTheGame()
    {
        EndGameFrame frame = new EndGameFrame(overallWinner,gameManager,soundManager);
        frame.setVisible(true);
        frame.setTitle("Risk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1200,760)); //1570,800
        frame.setResizable(false);
        frame.pack();
        dispose();
    }
}
