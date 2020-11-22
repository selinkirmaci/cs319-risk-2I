package backend;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author kaan
 */
public class Game {
    private final int playerAmt;
    private Player[] players;
    private int playerTurn;
    private final Dice dice;
    private final int INITIAL_TROOP_AMT;
    private int currentTroopAmt;
    private CardLibrary cardLib;
    private Map map;
    private final JSONParser parser;
    private final CombatManager cm;
    
    public Game( int playerAmt, Player[] players,
            int initialTroopAmt, String mapFilePath, String cardsFilePath ) {
        this.parser = new JSONParser();
        this.playerAmt = playerAmt;
        this.dice = new Dice();
        this.INITIAL_TROOP_AMT = initialTroopAmt;
        this.players = players;
        initCards( cardsFilePath );
        initMap( mapFilePath );
        cm = new CombatManager();
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

    // this is the first turn when the game starts
    // each player will get a part of a randomly chosen continent
    public void initialTurn() {
        ArrayList<Continent> conts = map.getContinents();
        ArrayList<Integer> playerContinents = new ArrayList<Integer>();

        for( int i = 0; i < playerAmt; i++ ) {
            ArrayList<Integer> randomTerrits = new ArrayList<Integer>();
            int randomCont = getRandomNumberInRange(0,5);

            // get random continent until an unused continent appears
            // and this unused continent has > 3 territories
            while( playerContinents.contains(randomCont) &&
                    conts.get(randomCont).getTerritories().size() > 3 ) {
                randomCont = getRandomNumberInRange(0,5);
            }

            playerContinents.add(randomCont);

            Continent currCont = conts.get(randomCont);
            ArrayList<Territory> currTerrs = currCont.getTerritories();
            int terrAmt = currTerrs.size();
            int troopAmt = INITIAL_TROOP_AMT / 5;

            // distribute an army of 5 infantries into 4 continents (for now)
            for( int j = 0; j < 4; j++ ) {
                Territory currTerr = currTerrs.get(j); // current territory
                players[i].useInfantries(troopAmt); // reduce the amt of infantries that the player has

                //create troops:
                ArrayList<Troop> troops = new ArrayList<Troop>();
                for( int k = 0; k < troopAmt; k++ ) {
                    troops.add( new Infantry() );
                }

                Army army = new Army( troops, players[i] );
                //set the army of that territory
                currTerr.setArmy(army);
            }
        }
    }

    /* return random number between min-max both inclusive */
    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /* at the start a player's turn, the player receives infantries
    as much as (num. of territories the player has)/3
    the player cannot get less than 3 units regardless of the owned territory amount
    players should also receive additional units if they own a whole continent */
    private void startTurn( Player p ) {
        int numOfTerr = p.getGainedTerritories().size();
        int addition = 3;
        if( (numOfTerr / 3) > 3 ) {
            addition = numOfTerr / 3;
        }
        p.addInfantries( addition );

        //add additional units for gained continents
        for( int i = 0; i < p.getGainedContinents().size(); i++ ) {
            int extra;
            extra = p.getGainedContinents().get(i).getExtraArmies();
            p.addInfantries( extra );
        }

    }


    /* TODO: turn logic for the game */
    private void manageTurns() {

    }
    
    /* add troops to the already owned territories
    * this function currently supports only one transfer action to one of the owned territories for simplicity,
    * this may be changed to support several transfer actions */
    public void fortifyTurn( Player p ) {
        ArrayList<Territory> territories = p.getGainedTerritories();
        int numOfTerr = p.getGainedTerritories().size();

        System.out.println("Select which territory you want to fortify: ");
        for( int i = 0; i < numOfTerr; i++ ) {
            System.out.println( i + ": " + territories.get(i) );
        }

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        //CHECK HERE UPON TESTING TO AVOID SCANNER ERRORS

        int troopAmt;
        do {
            System.out.println("Enter amount of troops you want to fortify: ");
            troopAmt = sc.nextInt();
        } while( troopAmt > p.getInfantryAmt() && troopAmt < 1 );


        //put those troops into selected territory
        p.useInfantries(troopAmt);
        Territory territory = territories.get(choice);
        Army army = territory.getArmy();

        //create infantries
        ArrayList<Troop> troopsToFortify = new ArrayList<Troop>();
        for( int i = 0; i < troopAmt; i++ ) {
            troopsToFortify.add( new Infantry() );
        }

        //fortify troops
        army.fortify(troopsToFortify);
    }

    /* might attack to bordering territories
    *  might choose not to attack
    *  can attack as many times as the player wants */
    public void attackTurn( Player p ) {
        // TODO: first select the territory you want to attack from,
        //  and then select a bordering territory to attack to

        //cm.executeWar( attacker, defender );
    }

    /* receive cards */
    public void draftTurn() {

    }

    /* returns true if the game is ended */
    private boolean checkTermination() {
        for( int i = 0; i < playerAmt; i++ ) {
            // TODO: write termination conditions i.e. total game duration has ended( may be 10 mins )
        }

        return true;
    }

    public Player [] getPlayers() {
        return players;
    }
    
    
}
