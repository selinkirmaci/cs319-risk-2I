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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.util.Scanner;

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
    private JLabel player1name,player2name,player3name,player4name;
    CircleComponent component1 = new CircleComponent(90);

    JButton buttonterritory,buttonterritory2,buttonterritory3,buttonterritory4,buttonterritory5,
            buttonterritory6,buttonterritory7,buttonterritory8,buttonterritory9,buttonterritory10,
            buttonterritory11,buttonterritory12,buttonterritory13,buttonterritory14,buttonterritory15,buttonterritory16,
            buttonterritory17,buttonterritory18;
    JButton[] territories;
    JButton attackButton,retreatButton;
    JButton pauseButton;
    JButton draftButton;
    JButton nextPlayerButton;
    JButton cancelAttack,rollDiceButton,allianceButton,decreaseDice,increaseDice;
    String chosenTerritory;
    JLabel firstDiceSet,secondDiceSet,thirdDiceSet,forthDiceSet,fifthDiceSet;
    int diceNumberLeft = 3;

    String from = "";
    String to = "";
    boolean setFrom = true;

    private GameManager gameManager;
    private Game game;
    Player[] players;
    int currentPlayer;
    int noOfPlayers;


/*
*     public static void main(String[] args) {
        Map frame = new Map();
        frame.setVisible(true);
        frame.setTitle("Risk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1150,830)); //1570,800
        frame.setResizable(false);
        frame.pack();
        //EventQueue.invokeLater(new Runnable() {
        //    public void run() {
        //    }
        //});


}
* */

    public Map( GameManager gameManager )
    {

        this.gameManager = gameManager;
        game = gameManager.getGame();
        players = game.getPlayers();
        currentPlayer = game.getCurrentPlayerTurn();
        territories = new JButton[45];

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX()+" "+e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

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
        background.setIcon(new ImageIcon("./src/main/resources/images/map_hq_v2.jpg"));
        background.setBounds(0, -165, 1580, 1100); //1860,1200

        avatar1 = new JLabel("");
        avatar2 = new JLabel("");
        avatar3 = new JLabel("");
        avatar4 = new JLabel("");

        avatar1.setIcon(new ImageIcon("./src/main/resources/images/alexander_p.png"));
        avatar1.setBounds(80,180,80,80);
        player1name = new JLabel(players[0].getName()+" Infantry numbers:" + players[0].getInfantryAmt());
        player1name.setBounds(60,230,800,80);
        background.add(avatar1);

        avatar3.setIcon(new ImageIcon("./src/main/resources/images/hannibal_p.png"));
        avatar3.setBounds(980,180,80,80);
        player2name = new JLabel(players[1].getName()+" Infantry numbers:" + players[1].getInfantryAmt());
        player2name.setBounds(960,230,200,80);
        background.add(avatar3);

        avatar2.setIcon(new ImageIcon("./src/main/resources/images/cengiz_p.png"));
        avatar2.setBounds(80,860,80,80);
        if(noOfPlayers >=3) {
            player3name = new JLabel(players[2].getName()+" Infantry numbers:" + players[2].getInfantryAmt());
        }else
            player3name = new JLabel("");
        player3name.setBounds(60,910,200,80);
        background.add(avatar2);

        avatar4.setIcon(new ImageIcon("./src/main/resources/images/caesar_p.png"));
        avatar4.setBounds(980,860,80,80);
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

        cancelAttack = new JButton("BACK");
        cancelAttack.addActionListener(this);
        cancelAttack.setBounds(20,20,100,50);

        //initialize buttons for territories
        for(int i = 0; i < 45; i++)
        {
            territories[i] = new JButton("");
            territories[i].setBorderPainted(true);
            territories[i].addActionListener(this);
            territories[i].setContentAreaFilled(false);
            territories[i].setForeground(Color.white);
        }
        //set names of the buttons
        territories[0].setName( "Raken");
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
        for(int i = 0; i < 45; i++ )
        {
            if(game.getMap().getTerritoryFromName(territories[i].getName()).getArmy() != null)
                territories[i].setText(""+game.getMap().getTerritoryFromName(territories[i].getName()).getArmy().getTotalValue());
            mainPanel.add(territories[i]);
        }

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
        component1.setLocation(80 ,175);
        component1.setSize(component1.getPreferredSize());
        background.add(component1);
        background.repaint();
        background.revalidate();
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

        if( (e.getSource() != pauseButton) &&
                (e.getSource() != attackButton) && (e.getSource() != retreatButton) )
        {
            JButton tmp = (JButton) e.getSource();
            if(setFrom) {
                from = tmp.getName();
                setFrom = !setFrom;
            } else if( from != "" ) {
                to = tmp.getName();
                setFrom = !setFrom;
            } else {
                from = tmp.getName();
            }

            if( !from.equals("") && !to.equals("") ) {
                attackButton.setEnabled(true);
            } else {
                attackButton.setEnabled(false);
            }

            retreatButton.setEnabled(true);
            draftButton.setEnabled(true);

            if(tmp.getName() == "DRAFT") {
                System.out.println(chosenTerritory);
            }
            else if(tmp.getName() != "ATTACK") {
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
            //TODO
            Territory fromTerr = gameManager.getGame().getMap().getTerritoryFromName(from);
            Territory toTerr = gameManager.getGame().getMap().getTerritoryFromName(to);

            if(!players[currentPlayer].hasTerritory(fromTerr))
            {
                JOptionPane.showMessageDialog(null, "You don't have this territory yet");
            }else if(players[currentPlayer].hasTerritory(toTerr))
            {
                JOptionPane.showMessageDialog(null, "You already own this territory");
            }

            System.out.println("Executing war from "+from + " to " + to + "!");

            if( fromTerr.getArmy() == null || toTerr.getArmy() == null ) {
                System.out.println("One of the armies are null!");
            } else {
                System.out.println("Executing war from "+from + " to " + to + "!");
                boolean attackerWon = gameManager.getGame().attackTurn(fromTerr, toTerr);

                playSound("./src/main/resources/sounds/snd_howtoplay.wav",-10.0f);
                /*
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
                territoryName.setText("BATTLE FROM " + from + " TO " + to);
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
                
                 */

                from = "";
                to = "";
            }

            attackButton.setEnabled(false);

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

        if( e.getSource() == draftButton ) {
            System.out.println( "Drafting " + chosenTerritory );
            String m="";
            m = JOptionPane.showInputDialog("Drafting " + chosenTerritory +". How many soldiers?");

            int troopAmt = Integer.parseInt(m);
            int playerTurn = gameManager.getGame().getCurrentPlayerTurn();
            Player p = gameManager.getGame().getPlayers()[playerTurn];
            int maxInfAmt = p.getInfantryAmt();
            Territory chosen = gameManager.getGame().getMap().getTerritoryFromName(chosenTerritory);

            if( troopAmt > maxInfAmt ) {
                // TODO: Error alert here!
                System.out.println("Amount of infantries in hand is: " + maxInfAmt );
            } else {
                // TODO: Add a notification panel to show the drafted soldier amount and the territory name.
                gameManager.getGame().draftTurn(chosen, troopAmt);

                from = "";
                to = "";

                //update army values
                for (int i = 0; i < 45; i++) {
                    if (game.getMap().getTerritoryFromName(territories[i].getName()).getArmy() != null)
                        territories[i].setText("" + game.getMap().getTerritoryFromName(territories[i].getName()).getArmy().getTotalValue());
                }
                gameManager.getGame().printInfAmt();
                attackButton.setEnabled(false);
                player1name.setText(players[0].getName() + " Infantry numbers:" + players[0].getInfantryAmt());
                player2name.setText(players[1].getName() + " Infantry numbers:" + players[1].getInfantryAmt());
                if(noOfPlayers>=3)
                    player3name.setText(players[2].getName() + " Infantry numbers:" + players[2].getInfantryAmt());
                if(noOfPlayers>=4)
                player4name.setText(players[3].getName() + " Infantry numbers:" + players[3].getInfantryAmt());
                revalidate();
            }

        }

        if( e.getSource() == nextPlayerButton ) {

            //pass the turn to the next player
            game.passTurn();
            game.printInfAmt();
            currentPlayer = game.getCurrentPlayerTurn();
            from = "";
            to = "";
            attackButton.setEnabled(false);
            if(currentPlayer == 0)
            {
                player1name.setText(players[0].getName()+" Infantry numbers:" + players[0].getInfantryAmt());
                component1.setLocation(70 ,175);
                component1.setSize(component1.getPreferredSize());
                background.add(component1);
                background.repaint();
                background.revalidate();

            }else if(currentPlayer == 1)
            {
                player2name.setText(players[1].getName()+" Infantry numbers:" + players[1].getInfantryAmt());
                component1.setLocation(970,175);
                component1.setSize(component1.getPreferredSize());
                background.add(component1);
                background.repaint();
                background.revalidate();

            }else if(currentPlayer == 2)
            {
                player3name.setText(players[2].getName()+" Infantry numbers:" + players[2].getInfantryAmt());
                component1.setLocation(70,855);
                component1.setSize(component1.getPreferredSize());
                background.add(component1);
                background.repaint();
                background.revalidate();

            }else if(currentPlayer == 3)
            {
                player4name.setText(players[3].getName()+" Infantry numbers:" + players[3].getInfantryAmt());
                component1.setLocation(970,855);
                component1.setSize(component1.getPreferredSize());
                background.add(component1);
                background.repaint();
                background.revalidate();
            }
            JOptionPane.showMessageDialog(null, "Player "+ players[currentPlayer].getName()+" got 3 more soldiers");

        }
        repaint();
        //pack();
    }



}


