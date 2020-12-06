package frontend;

import backend.Card;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class CardPanel extends JFrame implements ActionListener
{
    private JButton backToGame;
    private JLabel cardNumberInfo;
    private JButton[] cards;
    private int cardNumber;
    private JPanel gridPanel;
    public CardPanel()
    {
        setLayout(null);
        setPreferredSize(new Dimension(1200,600));
        setBounds(105,105,400,400);
        setBackground(Color.lightGray);
        cardNumber = 4;
        cards = new JButton[cardNumber];

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(1,cardNumber));
        gridPanel.setBounds(15,100,800,400);
        backToGame = new JButton("BACK");
        backToGame.setBounds(5,550,1200,25);
        backToGame.addActionListener(this);

        for(int i = 0; i < cardNumber;i++)
        {
            Icon icon = new ImageIcon("./src/main/resources/images/card_photo.PNG");
            JButton tmp = new JButton(icon);
            gridPanel.add(tmp);
        }
        cardNumberInfo = new JLabel("You have 3 cards");
        cardNumberInfo.setBounds(5,10,300,100);
        cardNumberInfo.setFont(new Font(Font.SERIF,Font.BOLD,30));
        cardNumberInfo.setForeground(Color.BLUE);
        add(cardNumberInfo);
        add(backToGame);
        add(gridPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == backToGame)
        {
            dispose();
        }
    }
}
