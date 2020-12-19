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

    public static void main(String[] args) {
        JFrame f = new JFrame();
        MiniGamePanel miniGamePanel = new MiniGamePanel();
        f.add(miniGamePanel);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800,600);
    }
    public MiniGame(String winner, GameManager gameManager)
    {
        this.gameManager = gameManager;
        setLayout(null);
        setPreferredSize(new Dimension(1200,900));
        setBounds(10,50,400,400);
        setBackground(Color.lightGray);
        pack();

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //should give go back to menu where you chose colors vs. or can  change it to quit simply

    }
    private static class MiniGamePanel extends JPanel implements ActionListener, KeyListener
    {
        int[] randomxpositions;
        int[] randomypositions;
        Ellipse2D.Double[] balls;

        Timer t = new Timer(5,this);
        double playerx = 400,playery=520, velx = 0, vely = 0;
        double x1=40,y1=0;
        private MiniGamePanel()
        {
            t.start();
            addKeyListener(this);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
            randomxpositions = new int[100];
            randomypositions = new int[100];

            for(int i = 0 ; i<100; i++)
            {
                Random rnd = new Random();
                int randomx = (rnd.nextInt(110) + 1) * 10;
                randomxpositions[i] = randomx;

                Random rnd1 = new Random();
                int randomy = (rnd1.nextInt(50) + 1) * -20;
                randomypositions[i] = randomy;
            }
            balls = new Ellipse2D.Double[100];
            for(int i = 0; i<100; i++)
            {
                balls[i] = new Ellipse2D.Double(randomxpositions[i], randomypositions[i], 40, 40);
            }
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            g2.fill(new Ellipse2D.Double(playerx,playery,40,40));

            for(int i = 0; i<100; i++)
            {
                balls[i] = new Ellipse2D.Double(randomxpositions[i], randomypositions[i], 40, 40);
            }
            for(int i = 0; i<100; i++)
            {
                g2.fill(balls[i]);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
            playerx += velx;
            playery += vely;
            objectConstantlyGoDown();
            checkCollision();
        }


        public void checkCollision()
        {
            for(int i = 0; i<100; i++)
            {
                boolean entered = false;
                if(!entered) {
                    if (balls[i].getY() + 36 >= playery) {
                        if (balls[i].getX() <= playerx + 40 && balls[i].getX() >= playerx) {
                            System.out.println("collision");
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
            for(int i = 0 ; i<20; i++)
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
}

