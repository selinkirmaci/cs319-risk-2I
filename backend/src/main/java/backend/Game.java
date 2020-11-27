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
    private int currentTroopAmt;
    private CardLibrary cardLib;
    private Map map;
    private final JSONParser parser;
    private final CombatManager cm;
    private int initialTroopAmt; //initial troop amount for each player
    private final int CONTINENT_AMT = 6;
    
    public Game( int playerAmt, Player[] players, String mapFilePath, String cardsFilePath ) {
        this.parser = new JSONParser();
        this.playerAmt = playerAmt;
        this.dice = new Dice();
        this.players = players;
        initialTroopAmt = players[0].getInfantryAmt();
        initCards( cardsFilePath );
        initMap( mapFilePath );

        cm = new CombatManager();
        manageTurns();
    }
    
    
    /* create map by parsing the data from a JSON file preferably 
       named map.json */
    private void initMap( String path ) {
        ArrayList<Continent> continents = parser.getContinents( path );
        map = new Map( continents );
    }

    public void printMap() {
        for(int i = 0; i < map.getContinents().size(); i++) {
            Continent c = map.getContinents().get(i);
            System.out.println("Continent " + c.getName() + ": ");
            for( int j = 0; j < c.getTerritories().size(); j++ ) {
                Territory t = c.getTerritories().get(j);
                System.out.println("\tTerritory " + t.getName() + ": ");
                Army a = t.getArmy();
                if( a != null ) {
                    a.printArmy();
                } else {
                    System.out.println("\t\t" + t.getName() + " has no army yet.");
                }


            }
            System.out.println("------");
        }
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
        System.out.println( "First Turn: Distributing armies into random continents" );
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
            int troopAmt = initialTroopAmt / 5;

            // distribute an army of 5 infantries into 4 continents (for now)
            for( int j = 0; j < 4; j++ ) {
                Territory currTerr = currTerrs.get(j); // current territory
                players[i].useInfantries(troopAmt); // reduce the amt of infantries that the player has
                players[i].addGainedTerritory(currTerr); // add the gained territory

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
    as much as (num. of territories the player has) / 3
    the player cannot get less than 3 units regardless of the owned territory amount
    players should also receive additional units if they own a whole continent */
    private void startTurn( Player p ) {
        int numOfTerr = p.getGainedTerritories().size();
        int addition = 3;
        if( (numOfTerr / 3) > 3 ) {
            addition = numOfTerr / 3;
        }
        p.addInfantries( addition );
        System.out.println( "Added " + addition + " infantries to player " + p.getName() );

        //add additional units for gained continents
        for( int i = 0; i < p.getGainedContinents().size(); i++ ) {
            int extra;
            extra = p.getGainedContinents().get(i).getExtraArmies();
            p.addInfantries( extra );
            System.out.println( "Added " + extra + " amount of infantries to player " +
                    p.getName() + " for continent " + p.getGainedContinents().get(i) );
        }

    }

    private void printInfAmt() {
        for( int i = 0; i < playerAmt; i++ ) {
            System.out.println( "Infantries in hand for player " + players[i].getName() + ": " +
                    players[i].getInfantryAmt() );
        }
    }


    /* TODO: turn logic for the game */
    private void manageTurns() {
        int turn = 0;
        Player current = players[turn];
        boolean firstTurn = true;
        while( !checkTermination() ) {

            /* execute the initial turn */
            if( firstTurn ) {
                initialTurn();
                printMap();
                for( int i = 0; i < playerAmt; i++ ) {
                    System.out.println( "Infantries in hand for player " + players[i].getName() + ": " +
                            players[i].getInfantryAmt() );
                }
            }

            while( players[turn].hasLost() ) { //if the current player has lost the game, skip the turn of that player
                turn++;
                turn = turn % playerAmt;
                current = players[turn];
            }
            System.out.println( "**** Turn of player: " + current.getName() + " ****" );

            startTurn(current);

            printInfAmt();

            draftTurn(current);

            printInfAmt();

            printMap();

            attackTurn(current);

            printMap();


            // TODO: call attackTurn here
            //       may call fortifyTurn here
            //       may call cardTurn here
            //



            firstTurn = false;

            //prepare for next turn
            turn++;
            turn = turn % playerAmt;
            current = players[turn];

        }
    }
    
    /* add troops to the already owned territories
    * this function currently supports only one transfer action to one of the owned territories for simplicity,
    * this may be changed to support several transfer actions */
    private void draftTurn( Player p ) {
        ArrayList<Territory> territories = p.getGainedTerritories();
        int numOfTerr = p.getGainedTerritories().size();

        System.out.println("Select which territory you want to strengthen: ");
        for( int i = 0; i < numOfTerr; i++ ) {
            System.out.println( i + ": " + territories.get(i).getName() );
        }

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        int troopAmt;
        do {
            System.out.println("Enter amount of troops you want to deploy: ");
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
    private void attackTurn( Player p ) {

        ArrayList<Territory> nonEmptyTerritories = map.getAllNonEmptyTerritories();
        ArrayList<Territory> myTerrs = p.getGainedTerritories();
        int myTerrsAmt = p.getGainedTerritories().size();
        int from, to;

        System.out.println("Select which territory you want to attack from: ");
        for( int i = 0; i < myTerrsAmt; i++ ) {
            System.out.println( i + ": " + myTerrs.get(i).getName() );
        }

        Scanner sc = new Scanner(System.in);
        from = sc.nextInt();

        // defender
        Territory fromTerritory = myTerrs.get(from);

        System.out.println("Select which territory you want to attack to: ");
        for( int i = 0; i < nonEmptyTerritories.size(); i++ ) {
            if( nonEmptyTerritories.get(i).getArmy().getOwner() != p ) { // if the territory is not player p's
                System.out.println( i + ": " + nonEmptyTerritories.get(i).getName() );
            }
        }

        sc = new Scanner(System.in);
        to = sc.nextInt();

        // attacker
        Territory toTerritory = nonEmptyTerritories.get(to);

        System.out.println( "Enter amount of troops you want to deploy for attacking: " );
        int troopAmt = sc.nextInt();

        Army def = fromTerritory.getArmy();
        Army att = toTerritory.getArmy();

        cm.executeWar( att, def );

        if( (att.getTotalValue() == 0) ) { // attacker loses
            toTerritory.getArmy().changeOwner(p);
        } else if( (def.getTotalValue() == 0) ) {
            // attacker wins. defender's territory is now attacker's
            def.changeOwner( att.getOwner() );
            ArrayList<Troop> toAdd = new ArrayList<>();
            toAdd.add( new Infantry() );
            def.fortify( toAdd ); // add a single infantry to the gained territory TODO: FOR NOW
        }
    }

    /* receive cards
    * TODO: check the conditions where a player receives a card. Also, add a turn method where the
    *  player may convert some combinations of these cards into troops */
    private void cardTurn() {

    }

    /* if a player does not have any territory, that player loses the game */
    private void checkIfLosed( Player p ) {
        if( p.getGainedTerritories().size() == 0 ) {
            p.loseGame();
        }
    }

    /* if the player has all of the continents, the player wins */
    private void checkIfWon( Player p ) {
        if( p.getGainedContinents().size() == 7 ) {
            p.winGame();
        }
    }



    /* returns true if the game is ended */
    private boolean checkTermination() {
        for( int i = 0; i < playerAmt; i++ ) {
            // TODO: write termination conditions i.e. total game duration has ended( may be 10 mins )
            // or a player won the game etc.
        }

        return false;
    }

    public Player [] getPlayers() {
        return players;
    }
    
    
}
