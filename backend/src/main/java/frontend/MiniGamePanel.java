package frontend;

import backend.GameManager;
import backend.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class MiniGamePanel extends JPanel implements ActionListener, KeyListener
{
    int[] randomxpositions;
    int[] randomypositions;

    int count;
    JLabel countLabel;

    Ellipse2D.Double[] balls;

    boolean gameOver;

    int ballNumber;

    GameManager gameManager;

    JFrame parentFrame;

    Timer t = new Timer(5,this);
    double playerx = 400,playery=520, velx = 0, vely = 0;
    double y1=0;
    public MiniGamePanel(JFrame f, GameManager gameManager)
    {
        t.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        this.gameManager = gameManager;
        this.parentFrame = f;

        ballNumber = 50;

        gameOver = false;

        randomxpositions = new int[ballNumber];
        randomypositions = new int[ballNumber];

        count = 0;
        countLabel = new JLabel();
        countLabel.setText("Collect all you can "+count);
        countLabel.setBounds(0,0,40,40);
        countLabel.setFont(new Font(Font.SERIF,Font.BOLD,40));
        countLabel.setForeground(Color.red);

        for(int i = 0 ; i<ballNumber; i++)
        {
            Random rnd = new Random();
            int randomx = (rnd.nextInt(110) + 1) * 10;
            randomxpositions[i] = randomx;

            Random rnd1 = new Random();
            int randomy = (rnd1.nextInt(50) + 1) * -20;
            randomypositions[i] = randomy;
        }
        balls = new Ellipse2D.Double[ballNumber];
        for(int i = 0; i<ballNumber; i++)
        {
            balls[i] = new Ellipse2D.Double(randomxpositions[i], randomypositions[i], 40, 40);
        }
        add(countLabel);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.fill(new Ellipse2D.Double(playerx,playery,40,40));

        for(int i = 0; i<ballNumber; i++)
        {
            balls[i] = new Ellipse2D.Double(randomxpositions[i], randomypositions[i], 40, 40);
        }
        for(int i = 0; i<ballNumber; i++)
        {
            g2.fill(balls[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!gameOver) {
            repaint();
            playerx += velx;
            playery += vely;
            objectConstantlyGoDown();
            checkCollision();
            gameOver = checkIfOver();
            if(gameOver){
                System.out.println("gameover");
            }
        }else
        {
            t.stop();
            Player[] players = gameManager.getGame().getPlayers();
            int currPlayerIndex = gameManager.getGame().getCurrentPlayerTurn();
            players[currPlayerIndex].addInfantries(count);
            removeAll();
            parentFrame.dispatchEvent(new WindowEvent(parentFrame, WindowEvent.WINDOW_CLOSING));
            gameOver = false;
        }

    }

    public boolean checkIfOver()
    {
        boolean gameOver = true;
        for(int i = 0; i<ballNumber; i++)
        {
            if(balls[i].getY()<800)
            {
                gameOver = false;
            }
        }
        return gameOver;
    }


    public void checkCollision()
    {
        for(int i = 0; i<ballNumber; i++)
        {
            boolean entered = false;
            if(!entered) {
                if (balls[i].getY() + 45 >= playery) {
                    if (balls[i].getX() <= playerx + 40 && balls[i].getX() >= playerx) {
                        count+=1;
                        countLabel.setText("Collect all you can "+count);
                        randomypositions[i] += 10000;
                        randomxpositions[i] -= 10000;
                        entered = true;
                    }
                }
            }
        }
    }

    public void objectConstantlyGoDown()
    {
        y1 += 2;
        for(int i = 0 ; i<ballNumber; i++)
        {
            randomypositions[i] +=2;
        }
    }

    public void left()
    {
        velx = -5;
        vely = 0;
    }
    public void right()
    {
        velx = 5;
        vely = 0;
    }
    public void stop()
    {
        velx = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_RIGHT)
        {
            right();
        }
        else if(code == KeyEvent.VK_LEFT)
        {
            left();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_RIGHT)
        {
            stop();
        }
        else if(code == KeyEvent.VK_LEFT)
        {
            stop();
        }
    }
}