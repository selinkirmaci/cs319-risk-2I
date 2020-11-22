package backend;

import java.util.ArrayList;

/**
 *
 * @author kaan
 */
public class Player {
    private int infantryAmt; //3 players = 35 infantry, 4p = 30, 5p = 25
    private final String name;
    private final Avatar avatar;
    private Hand hand;
    private boolean underAttack;
    private ArrayList<Continent> gainedContinents;
    private final int playerId;
    
    public Player( String name, Avatar avatar, int infantryAmt, int playerId ) {
        this.name = name;
        this.avatar = avatar;
        gainedContinents = new ArrayList<Continent>();
        hand = new Hand();
        this.infantryAmt = infantryAmt;
        this.playerId = playerId;
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

    void attack(Player defender, Territory territory) {};

    void sendTroops( int troopAmt, Player player ) {}

    void swapCards( ArrayList<Card> cardsToExchange ) {} // exchange cards for infantries
}
