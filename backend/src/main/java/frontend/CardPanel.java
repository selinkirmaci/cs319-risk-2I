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

public class CardPanel extends JFrame implements ActionListener
{
    private JButton backToGame,tradeCards;
    private JLabel cardNumberInfo;
    private JButton[] cards;
    private int cardNumber;
    private JPanel gridPanel;
    private ArrayList<Card> playersCards;
    private JLabel tradeCountLabel;
    private String firstCard,secondCard,thirdCard;
    private JButton firstCardButton,secondCardButton,thirdCardButton;
    private int numberOfCardsChosen;
    private Player currPlayer;
    public CardPanel(Player currPlayer)
    {
        setLayout(null);
        setPreferredSize(new Dimension(1200,600));
        setBounds(105,105,400,400);
        setBackground(Color.lightGray);

        this.currPlayer = currPlayer;

        firstCard = "";
        secondCard = "";
        thirdCard = "";
        numberOfCardsChosen = 0;

        firstCardButton = new JButton();
        secondCardButton = new JButton();
        thirdCardButton = new JButton();

        cardNumber = currPlayer.getHand().getSize();
        cards = new JButton[cardNumber];
        playersCards = currPlayer.getHand().getCards();
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(2,10));
        gridPanel.setBounds(15,100,800,400);
        backToGame = new JButton("BACK");
        backToGame.setBounds(0,550,1200,25);
        backToGame.addActionListener(this);

        tradeCards = new JButton("TRADE");
        tradeCards.setBounds(1000,500,100,40);
        tradeCards.addActionListener(this);

        tradeCountLabel = new JLabel();
        tradeCountLabel.setText("There has been "+ currPlayer.getTradeCount() +" trades before.");
        tradeCountLabel.setBounds(810,20,400,40);
        tradeCountLabel.setFont(new Font(Font.SERIF,Font.BOLD,30));
        tradeCountLabel.setForeground(Color.BLUE);

        if(cardNumber == 0)
            tradeCards.setVisible(false);

        for(int i = 0; i < cardNumber;i++)
        {
            Icon icon = new ImageIcon("./src/main/resources/images/card_photo.PNG");
            JButton tmp = new JButton(playersCards.get(i).getName());
            tmp.setName(playersCards.get(i).getName());
            tmp.addActionListener(this);
            tmp.setBackground(Color.lightGray);
            gridPanel.add(tmp);
        }
        cardNumberInfo = new JLabel();
        cardNumberInfo.setText("You have "+cardNumber+" cards.");
        cardNumberInfo.setBounds(5,10,300,100);
        cardNumberInfo.setFont(new Font(Font.SERIF,Font.BOLD,30));
        cardNumberInfo.setForeground(Color.BLUE);
        add(cardNumberInfo);
        add(backToGame);
        add(tradeCards);
        add(tradeCountLabel);
        add(gridPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() != backToGame && e.getSource()!=tradeCards)
        {
            JButton cardButton = (JButton) e.getSource();
            if(numberOfCardsChosen == 0)
            {
                firstCardButton.setBackground(Color.lightGray);
                firstCardButton = (JButton)e.getSource();
                firstCardButton.setBackground(Color.BLUE);
                firstCard = cardButton.getName();
                numberOfCardsChosen++;
            }else if(numberOfCardsChosen == 1)
            {
                secondCardButton.setBackground(Color.lightGray);
                secondCardButton = (JButton)e.getSource();
                secondCardButton.setBackground(Color.BLUE);
                secondCard = cardButton.getName();
                numberOfCardsChosen++;
            }else if(numberOfCardsChosen == 2)
            {
                thirdCardButton.setBackground(Color.lightGray);
                thirdCardButton = (JButton)e.getSource();
                thirdCardButton.setBackground(Color.BLUE);
                thirdCard = cardButton.getName();
                numberOfCardsChosen = 0;
            }
        }
        if(e.getSource() == backToGame)
        {
            dispose();
        }
        if(e.getSource() == tradeCards)
        {
            //shit is going downnn
            Card card1 = currPlayer.getHand().getCard(firstCard);
            Card card2 = currPlayer.getHand().getCard(secondCard);
            Card card3 = currPlayer.getHand().getCard(thirdCard);
            ArrayList<Card> tmpList = new ArrayList<Card>();
            tmpList.add(card1);
            tmpList.add(card2);
            tmpList.add(card3);
            int tradeSucceess = currPlayer.tradeTerritoryCards(tmpList);
            if(tradeSucceess == -1)
            {
                //problem because of the null frame
                JOptionPane.showMessageDialog(null, "Trade request invalid!");
            }else
                JOptionPane.showMessageDialog(null, "Trade successfull.");
        }
    }
}
