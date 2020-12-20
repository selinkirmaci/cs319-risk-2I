package frontend;

import backend.Card;
import backend.GameManager;
import backend.SoundManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;



public class EndGameFrame extends JFrame implements ActionListener
{
    public JButton restartGameButton,backToMenu;
    public JLabel background;
    public JLabel messageLabel;
    public JLabel winnerLabel;
    public GameManager gameManager;
    private SoundManager soundManager;
    public EndGameFrame(String winner,GameManager gameManager,SoundManager soundManager)
    {
        this.gameManager = gameManager;
        this.soundManager = soundManager;

        setLayout(null);
        setPreferredSize(new Dimension(1000,800));
        setBounds(10,50,400,400);
        setBackground(Color.lightGray);

        restartGameButton = new JButton();
        restartGameButton.setText("QUIT");
        restartGameButton.setBounds(20,680,300,50);
        restartGameButton.setFont(new Font("Algerian",Font.BOLD,30));
        restartGameButton.addActionListener(this);

        backToMenu = new JButton();
        backToMenu.setText("MAIN MENU");
        backToMenu.setBounds(880,680,300,50);
        backToMenu.setFont(new Font("Algerian",Font.BOLD,30));
        backToMenu.addActionListener(this);

        Font font = new Font("Algerian",Font.BOLD,50);
        messageLabel = new JLabel("CONGRATULATIONS");
        messageLabel.setBounds(380,200,600,50);
        messageLabel.setFont(font);

        winnerLabel = new JLabel(winner);
        winnerLabel.setBounds(570,350,200,50);
        winnerLabel.setFont(new Font("Algerian",Font.BOLD,30));


        ImageIcon backgroundImage = new ImageIcon("./src/main/resources/images/endgame.jpg");
        background = new JLabel(backgroundImage);
        background.setBounds(0,0,1200,760);

        background.add(messageLabel);
        background.add(winnerLabel);
        background.add(restartGameButton);
        background.add(backToMenu);
        add(background);
        pack();

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        //should give go back to menu where you chose colors vs. or can  change it to quit simply
        if(e.getSource() == restartGameButton)
        {
            System.exit(0);
        }
        if(e.getSource() == backToMenu)
        {
            GUI frame = new GUI();
            frame.setVisible(true);
            frame.setTitle("Risk");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(1570,800)); //1570,800
            frame.setResizable(false);
            frame.pack();
            dispose();
        }
    }
}
