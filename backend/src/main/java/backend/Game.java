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
    private final int initialTroopAmt;
    private int currentTroopAmt;
    private CardLibrary cardLib;
    private Map map;
    private final JSONParser parser;
    
    public Game( int playerAmt, ArrayList<String> playerNames,
            int initialTroopAmt, String mapFilePath, String cardsFilePath ) {
        this.parser = new JSONParser();
        this.playerAmt = playerAmt;
        this.dice = new Dice();
        this.initialTroopAmt = initialTroopAmt;
        initCards( cardsFilePath );
        initMap( mapFilePath );
        initPlayers( playerAmt, playerNames );
        
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
    
    
    /* initialize players according to player amount and given names */
    private void initPlayers( int playerAmt, ArrayList<String> names ) {
        players = new ArrayList();
        for( int i = 0; i < playerAmt; i++ ) {
            players.add( new Player( names.get(i) ) );
        }
    }
    
    
    /*  */
    public void fortifyTurn() {
        
    }
    
    
    
}
