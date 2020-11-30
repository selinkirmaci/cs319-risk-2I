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
    private ArrayList<Territory> gainedTerritories;
    private final int playerId;
    private boolean hasLost;
    private boolean won;
    
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
}
