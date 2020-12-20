package frontend;

import backend.Card;
import backend.GameManager;
import backend.SoundManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;


public class MiniGame extends JFrame implements ActionListener
{
    public JButton restartGameButton,backToMenu;
    public GameManager gameManager;

    /*
    public static void main(String[] args) {
        JFrame f = new JFrame("Mini Game");
        MiniGamePanel miniGamePanel = new MiniGamePanel();
        f.add(miniGamePanel);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800,600);
    }
     */

    public MiniGame()
    {
        setLayout(null);
        setPreferredSize(new Dimension(1200,900));
        setBounds(10,50,400,400);
        setBackground(Color.lightGray);
        //MiniGamePanel miniGamePanel = new MiniGamePanel();
        //add(miniGamePanel);
        pack();
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //should give go back to menu where you chose colors vs. or can  change it to quit simply

    }

}

