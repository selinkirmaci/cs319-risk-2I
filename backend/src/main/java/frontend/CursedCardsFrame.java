package frontend;

import backend.*;
import jdk.nashorn.internal.scripts.JO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CursedCardsFrame extends JFrame implements ActionListener
{
    private JButton backToGame,tradeCards;
    private JLabel cardNumberInfo;
    private JButton[] cards;
    private int cardNumber;
    private JPanel gridPanel;
    private ArrayList<CurseCard> playersCards;
    private Player currPlayer;
    private Player[] players;
    private String chosenCardString;
    private CurseCard chosenCursedCard;
    private JButton chosenCardButton;
    private backend.Game game;
    private boolean tradeSuccess;
    private JFrame frame;
    private frontend.Map gamef;
    public CursedCardsFrame( backend.Game game, frontend.Map gamef )
    {
        setLayout(null);
        setPreferredSize(new Dimension(1200,900));
        setBounds(10,50,400,400);
        setBackground(Color.lightGray);

        this.currPlayer = game.getPlayers()[game.getCurrentPlayerTurn()];
        this.game = game;
        this.gamef = gamef;

        this.frame = new JFrame();

        players = game.getPlayers();
        chosenCardString = "";
        chosenCardButton = new JButton();

        cardNumber = currPlayer.getHand().getCurseCards().size();
        cards = new JButton[cardNumber];
        playersCards = currPlayer.getHand().getCurseCards();

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
            tmp.setBackground(Color.white);
            gridPanel.add(tmp);
        }
        cardNumberInfo = new JLabel();
        cardNumberInfo.setText("You have " + cardNumber + " cursed cards.");
        cardNumberInfo.setBounds(5,5,500,100);
        cardNumberInfo.setFont(new Font(Font.SERIF,Font.BOLD,30));
        cardNumberInfo.setForeground(Color.BLUE);
        add(cardNumberInfo);
        //add(backToGame);
        add(tradeCards);
        add(gridPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() != tradeCards)
        {
            chosenCardButton.setBackground(Color.white);
            chosenCardButton = (JButton) e.getSource();
            chosenCardButton.setBackground(Color.ORANGE);
            chosenCardString = chosenCardButton.getName();
        }
        if(e.getSource() == tradeCards)
        {
            // should call this method based on the chosen card's type( boost, immunity etc. )
            // you can pass null to unused parameters( e.g. chosenTerritory and chosenPlayer should be null in boost )
            // when epidemic card is attempted to trade, game should ask the player to choose a territory to attack, and then
            // call this function with the chosenTerritory.
            // when rebellion card is attempted to trade, game should ask the player to choose an enemy player,
            // and pass chosen player as parameter in the function below
            //bool tradeSuccess = game.curseCardTurn( /*CurseCard curseCard, Territory chosenTerritory, Player chosenPlayer*/ );

            chosenCursedCard = currPlayer.getHand().getCursedCard(chosenCardString);

            if(chosenCardString.equals("celebration"))
            {
                tradeSuccess = currPlayer.tradeCurseCard(chosenCursedCard,game,null,null);
            }else if(chosenCardString.equals("epidemic"))
            {
                //chose player to send epidemic
                ArrayList<Player> otherPlayers = new ArrayList<>();
                for(Player p: players)
                {
                    if(!p.getName().equals(currPlayer.getName()))
                    {
                        otherPlayers.add(p);
                    }
                }
                Object[] options = new Object[otherPlayers.size()];
                for(int i = 0; i<otherPlayers.size();i++)
                {
                    options[i] = otherPlayers.get(i).getName();
                }

                int selectedPlayer = JOptionPane.showOptionDialog(null, "Who do you want to send epidemic to?", "REBELLION",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, options, options[0]);

                ArrayList<Territory> enemyTerritories = otherPlayers.get(selectedPlayer).getGainedTerritories();

                //get territories of selected enemy
                String[] territories = new String[enemyTerritories.size()];
                for(int i = 0; i<enemyTerritories.size();i++)
                {
                    territories[i] = enemyTerritories.get(i).getName();
                }

                //player choses which territory to attack
                Object obj = JOptionPane.showInputDialog(null,"choose","choose",JOptionPane.PLAIN_MESSAGE,null,territories,territories);
                System.out.println(obj);

                String chosenTerr = obj.toString();
                Territory finalTerr = otherPlayers.get(selectedPlayer).getTerritory(chosenTerr);
                //trade send
                tradeSuccess = currPlayer.tradeCurseCard(chosenCursedCard,game,finalTerr,otherPlayers.get(selectedPlayer));


            }else if(chosenCardString.equals("immunity"))
            {
                ArrayList<Territory> playersTerritoties = currPlayer.getGainedTerritories();
                String[] territories = new String[playersTerritoties.size()];
                for(int i = 0; i<playersTerritoties.size();i++)
                {
                    territories[i] = playersTerritoties.get(i).getName();
                }
                //player choses which territory to make immune
                Object obj = JOptionPane.showInputDialog(null,"choose","choose",JOptionPane.PLAIN_MESSAGE,null,territories,territories);
                System.out.println(obj);

                String chosenTerr = obj.toString();
                Territory finalTerr = currPlayer.getTerritory(chosenTerr);
                //trade send
                tradeSuccess = currPlayer.tradeCurseCard(chosenCursedCard,game,finalTerr,null);
            }else if(chosenCardString.equals("powerboost"))
            {
                tradeSuccess = currPlayer.tradeCurseCard(chosenCursedCard,game,null,null);
            }else if(chosenCardString.equals("rebellio"))
            {
                ArrayList<Player> otherPlayers = new ArrayList<>();
                for(Player p: players)
                {
                    if(!p.getName().equals(currPlayer.getName()))
                    {
                        otherPlayers.add(p);
                    }
                }
                Object[] options = new Object[otherPlayers.size()];
                for(int i = 0; i<otherPlayers.size();i++)
                {
                    options[i] = otherPlayers.get(i).getName();
                }
                int selectedPlayer = JOptionPane.showOptionDialog(null, "Who do you want to send rebellion to?", "REBELLION",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, options, options[0]);
                tradeSuccess = currPlayer.tradeCurseCard(chosenCursedCard,game,null,otherPlayers.get(selectedPlayer));

            }

            if( tradeSuccess ) {
                JOptionPane.showMessageDialog(null, "Trade successful.");
                revalidate();
                dispose();
                gamef.updateTerritories();
            } else {
                JOptionPane.showMessageDialog(null, "Trade request invalid!");
            }
        }
    }
    public void setFrame(JFrame frame)
    {
        this.frame = frame;
    }
}
