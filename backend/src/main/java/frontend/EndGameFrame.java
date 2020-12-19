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
    public GameManager gameManager;
    private SoundManager soundManager;
    public EndGameFrame(String winner, GameManager gameManager, SoundManager soundManager)
    {
        this.gameManager = gameManager;
        this.soundManager = soundManager;

        setLayout(null);
        setPreferredSize(new Dimension(1200,900));
        setBounds(10,50,400,400);
        setBackground(Color.lightGray);

        restartGameButton = new JButton();
        restartGameButton.setText("PLAY AGAIN?");
        restartGameButton.setBounds(10,800,100,50);
        restartGameButton.setFont(new Font(Font.SERIF,Font.BOLD,40));
        restartGameButton.addActionListener(this);

        backToMenu = new JButton();
        backToMenu.setText("MAIN MENU");
        backToMenu.setBounds(1000,800,200,50);
        backToMenu.setFont(new Font(Font.SERIF,Font.BOLD,40));
        backToMenu.addActionListener(this);

        add(restartGameButton);
        add(backToMenu);
        pack();

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        //should give go back to menu where you chose colors vs. or can  change it to quit simply
        if(e.getSource() == restartGameButton)
        {
            Map m = new Map(gameManager,soundManager);
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
