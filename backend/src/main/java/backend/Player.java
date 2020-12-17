package backend;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author kaan
 */
public class Player {

    private int infantryAmt; //3 players = 35 infantry, 4p = 30, 5p = 25
    private final String name;
    private Avatar avatar;
    private Hand hand;
    private boolean underAttack;
    private ArrayList<Continent> gainedContinents;
    private ArrayList<Territory> gainedTerritories;
    private final int playerId;
    private boolean hasLost;
    private boolean won;
    private Color colorOfPlayer;
    private int tradeCount;
    private int secretMission;
    
    public Player( String name, Avatar avatar, int infantryAmt, int playerId ) {
        this.name = name;
        this.avatar = avatar;
        gainedContinents = new ArrayList<Continent>();
        gainedTerritories = new ArrayList<Territory>();
        hand = new Hand();
        this.infantryAmt = infantryAmt;
        this.playerId = playerId;
        hasLost = false;
        won = false;
        tradeCount = 0;
    }

    public void winGame() {
        won = true;
    }

    public void loseGame() {
        hasLost = true;
    }

    public boolean hasLost() {
        return hasLost;
    }

    public boolean hasWon() {
        return won;
    }

    public ArrayList<Continent> getGainedContinents() {
        return gainedContinents;
    }

    public int getInfantryAmt() {
        return infantryAmt;
    }

    public ArrayList<Territory> getGainedTerritories() {
        return gainedTerritories;
    }

    public boolean hasTerritory(Territory ter)
    {
        for(int i = 0; i<gainedTerritories.size();i++)
        {
            if(gainedTerritories.get(i).getName().equals(ter.getName()))
                return true;
        }
        return false;
    }
    public void addGainedTerritory( Territory t ) {
        gainedTerritories.add(t);
        checkGainedContinents();
    }

    public void removeTerritory( Territory t ) {
        gainedTerritories.remove(t);
        checkGainedContinents();
    }

    //TODO: check if there are gained continents
    public void checkGainedContinents() {

    }

    public boolean hasNoTerritory() {
        return gainedContinents.size() == 0;
    }

    public boolean hasAllTerritories() {
        return gainedContinents.size() == 6; // change later
    }

    public void useInfantries( int usage ) {
        infantryAmt -= usage;
    }

    public void addInfantries( int addition ) {
        infantryAmt += addition;
    }

    public String getName() {
        return name;
    }

    void attack(Player defender, Territory territory) {};

    void sendTroops( int troopAmt, Player player ) {}

    void swapCards( ArrayList<Card> cardsToExchange ) {} // exchange cards for infantries

    public Color getColor() {return colorOfPlayer;}

    void setColor(Color color)
    {
        this.colorOfPlayer = color;
    }

    public Avatar getAvatar() { return avatar; }

    public Hand getHand() { return hand; }

    /* return random number between min-max both inclusive */
    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    // gets called after gaining a territory from an attack
    // can either get infantry, calvary or artillery card types of the gained territory.
    // If 3 of these exist, there is 1/3 chance of getting artillery, calvary, infantry card types
    // If 2 of these exist, as example: there is 1/2 chance of getting artillery, calvary or artillery, infantry etc.
    public void getTerritoryCard( Territory t, CardLibrary cardLib ) {
        ArrayList<Card> cards = new ArrayList<>();
        int typeCount = 0;
        if( cardLib.territoryCardExists(t.getName() + "_inf") ) { // infantry card exists
            cards.add(cardLib.getCard(t.getName() + "_inf"));
        }
        if( cardLib.territoryCardExists(t.getName() + "_calv") ) { // calvary card exists
            cards.add(cardLib.getCard(t.getName() + "_calv"));
        }
        if( cardLib.territoryCardExists(t.getName() + "_arty") ) { // artillery card exists
            cards.add(cardLib.getCard(t.getName() + "_arty"));
        }

        if( cards.size() == 0 ) { // no card left
            return;
        } else if( cards.size() == 1 ) { // single card left
            hand.addCard( cards.get(0) );
            cardLib.useTerritoryCard(cards.get(0).getName());
            return;
        }

        int random = getRandomNumberInRange(0, cards.size() - 1);
        System.out.println("Player " + name + " got " + cards.get(random).getName() + " card.");
        hand.addCard( cards.get(random) );
        cardLib.useTerritoryCard(cards.get(random).getName());

    }

    // Trades territory cards
    // * 3 cards of the same troop design (a,a,a), (i,i,i), (c,c,c)
    // * 1 each of the 3 troop designs (a,i,c)
    // if tradecount is x, get 5*x extra infantries
    // returns gained infantry amount
    public int tradeTerritoryCards( ArrayList<Card> toTrade, int tradeCount ) {
        int infantryAmt = -1;
        if( toTrade.size() < 3 ) {
            System.out.println("Not enough territory cards to trade.");
            return infantryAmt;
        }

        if( hand.isViable(toTrade) ) {
            // add gained infantries to player
            infantryAmt = (tradeCount + 1) * 5;

            // remove the used cards
            hand.removeUsedCards(toTrade);

            this.tradeCount++;
        }

        return infantryAmt;
    }

    public int getSecretMission() {
        return secretMission;
    }

    public void setSecretMission(int secretMission) {
        this.secretMission = secretMission;
    }
    public int getTradeCount()
    {
        return tradeCount;
    }
}
