package backend;

import java.util.ArrayList;

/**
 *
 * @author kaan
 */
public class Player {
    private int infantryAmt;
    private final String name;
    private final Avatar avatar;
    private Hand hand;
    private boolean underAttack;
    private ArrayList<Continent> gainedContinents;
    
    public Player( String name, Avatar avatar ) {
        this.name = name;
        this.avatar = avatar;
        gainedContinents = new ArrayList<Continent>();
        hand = new Hand();
    }

    public boolean hasNoTerritory() {
        return gainedContinents.size() == 0;
    }

    public boolean hasAllTerritories() {
        return gainedContinents.size() == 6; // change later
    }

    void attack(Player defender, Territory territory) {};

    void sendTroops( int troopAmt, Player player ) {}

    void swapCards( ArrayList<Card> cardsToExchange ) {} // exchange cards for infantries
}
