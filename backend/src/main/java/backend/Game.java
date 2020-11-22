package backend;

import java.util.ArrayList;

/**
 *
 * @author kaan
 */
public class Game {
    private final int playerAmt;
    private ArrayList<Player> players;
    private int playerTurn;
    private final Dice dice;
    private final int INITIAL_TROOP_AMT;
    private int currentTroopAmt;
    private CardLibrary cardLib;
    private Map map;
    private final JSONParser parser;
    
    public Game( int playerAmt, ArrayList<Player> players,
            int initialTroopAmt, String mapFilePath, String cardsFilePath ) {
        this.parser = new JSONParser();
        this.playerAmt = playerAmt;
        this.dice = new Dice();
        this.INITIAL_TROOP_AMT = initialTroopAmt;
        this.players = players;
        initCards( cardsFilePath );
        initMap( mapFilePath );
    }
    
    
    /* create map by parsing the data from a JSON file preferably 
       named map.json */
    private void initMap( String path ) {
        ArrayList<Continent> continents = parser.getContinents( path );
        map = new Map( continents );
    }
    
    /* create cards by parsing the data from a JSON file preferably 
       named cards.json */
    private void initCards( String path ) {
        ArrayList<Card> cards = parser.getCards( path );
        this.cardLib = new CardLibrary(cards);
    }
    
    /*  */
    public void fortifyTurn() {
        
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    
}
