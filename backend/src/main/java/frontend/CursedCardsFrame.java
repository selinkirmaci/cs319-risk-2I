package frontend;

import backend.Card;
import backend.Hand;
import backend.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class CursedCardsFrame extends JFrame implements ActionListener
{
    private JButton backToGame,tradeCards;
    private JLabel cardNumberInfo;
    private JButton[] cards;
    private int cardNumber;
    private JPanel gridPanel;
    private ArrayList<Card> playersCards;
    private Player currPlayer;
    private backend.Game game;
    public CursedCardsFrame( backend.Game game)
    {
        setLayout(null);
        setPreferredSize(new Dimension(1200,900));
        setBounds(10,50,400,400);
        setBackground(Color.lightGray);

        this.currPlayer = game.getPlayers()[game.getCurrentPlayerTurn()];
        this.game = game;

        cardNumber = currPlayer.getHand().getSize();
        cards = new JButton[cardNumber];
        playersCards = currPlayer.getHand().getCards();

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3,3));
        gridPanel.setBounds(10,100,1190,750);

        backToGame = new JButton("BACK");
        backToGame.setBounds(0,850,1200,25);
        backToGame.addActionListener(this);

        tradeCards = new JButton("TRADE");
        tradeCards.setBounds(1000,10,100,40);
        tradeCards.addActionListener(this);

        if(cardNumber == 0)
            tradeCards.setVisible(false);

        for(int i = 0; i < cardNumber;i++)
        {
            Icon icon = new ImageIcon("./src/main/resources/images/cursedcards/"+(playersCards.get(i).getName()).toLowerCase().trim()+"_card.png");
            JButton tmp = new JButton();
            tmp.setName(playersCards.get(i).getName());
            tmp.setIcon(icon);
            tmp.addActionListener(this);
            tmp.setBackground(Color.lightGray);
            gridPanel.add(tmp);
        }
        cardNumberInfo = new JLabel();
        cardNumberInfo.setText("You have "+cardNumber+" cursed cards.");
        cardNumberInfo.setBounds(5,5,300,100);
        cardNumberInfo.setFont(new Font(Font.SERIF,Font.BOLD,30));
        cardNumberInfo.setForeground(Color.BLUE);
        add(cardNumberInfo);
        add(backToGame);
        add(tradeCards);
        add(gridPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
