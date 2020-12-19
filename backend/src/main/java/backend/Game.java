package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author kaan
 */
public class Game implements Serializable {
    private boolean secretMissionModOn;
    private final int playerAmt;
    private Player[] players;
    private int currentPlayerTurn;
    private String turnType;
    private final Dice dice;
    private int currentTroopAmt;
    private CardLibrary cardLib;
    private Map map;
    private final JSONParser parser;
    private final CombatManager cm;
    private int initialTroopAmt; //initial troop amount for each player
    private final int CONTINENT_AMT = 7;
    private int tradeCount;
    private ArrayList<Territory> currentlyImmuneTerritories;
    private String neighborsPath;
    private ArrayList<ArrayList<Territory>> neighbors;
    
    public Game( int playerAmt, Player[] players, String mapFilePath, String neighborsPath ) {
        this.secretMissionModOn = false;
        this.parser = JSONParser.getInstance();
        this.playerAmt = playerAmt;
        this.dice = Dice.getInstance();
        this.players = players;
        this.neighborsPath = neighborsPath;
        initialTroopAmt = players[0].getInfantryAmt();
        initMap( mapFilePath );
        initCards();
        currentlyImmuneTerritories = new ArrayList<>();

        initialTurn();
        startTurn(players[0]); // start the turn of first player( add infantries )
        printMap();
        cm = CombatManager.getInstance();
        currentPlayerTurn = 0;
        tradeCount = 0;
        turnType = "draft";
    }

    public void addImmuneTerritory( Territory t ) {
        currentlyImmuneTerritories.add(t);
    }

    public void removeImmuneTerritory( Territory t ) {
        currentlyImmuneTerritories.remove(t);
    }

    // this gets called beginning of every turn and decreases the immunity count of
    // the territories that are currently immune and removes the territories whose immunities are ended
    public void decreaseImmunityCounts() {
        for( int i = 0; i < currentlyImmuneTerritories.size(); i++ ) {
            Territory curr = currentlyImmuneTerritories.get(i);
            if( curr.getArmy().getImmuneTurnCount() == 0 ) {
                removeImmuneTerritory(curr);
            }
            curr.getArmy().reduceImmuneTurnCount();
        }
    }
    
    
    /* create map by parsing the data from a JSON file preferably 
       named map.json */
    private void initMap( String path ) {
        ArrayList<Continent> continents = parser.getContinents( path );
        map = new Map( continents );
        this.neighbors = parser.getNeighbors( neighborsPath, map );
        printNeighbors();
    }

    private void printNeighbors() {
        for( int i = 0; i < neighbors.size(); i++ ) {
            Territory curr = neighbors.get(i).get(0);
            System.out.println("neighbors of " + curr.getName());
            for( int j = 0; j < neighbors.get(i).size() - 1; j++ ) {
                System.out.println(neighbors.get(i).get(j + 1).getName());
                curr.setNeighbors(neighbors.get(i));
            }
        }
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

    public void printHands() {
        for( int i = 0; i < playerAmt; i++ ) {
            Hand curr = players[i].getHand();
            System.out.println("Hand of player " + players[i].getName() + ": ");
            curr.printHand();
        }
    }
    
    // create cards
    private void initCards() {
        this.cardLib = new CardLibrary();
        cardLib.createTerritoryCards(map);
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

    // passes the turn to the other player
    // checks if any territory is immune and if there is an immune territory,
    // decrease its round count
    // if a player has rebellion, pass that player's turn too
    public void passTurn() {
        currentPlayerTurn++;
        currentPlayerTurn = currentPlayerTurn % playerAmt;
        Player currPlayer = players[currentPlayerTurn];

        // Pass lost players
        if( currPlayer.hasLost() ) {
            passTurn();
            return;
        } else if( currPlayer.isHasRebellion() ) {
            currPlayer.endRebellion();
            passTurn();
            return;
        }

        System.out.println( "**** Turn of player: " + currPlayer.getName() + " ****" );
        startTurn(currPlayer); //start the turn of current player
        currPlayer.getHand().tryGettingCurseCard(); // try to get immunity card

        decreaseImmunityCounts();
        System.out.println( "Currently immune territories: " + currentlyImmuneTerritories.toString() );

    }
    
    /* add troops to the already owned territories
    * this function currently supports only one transfer action to one of the owned territories for simplicity,
    * this may be changed to support several transfer actions */
    public void draftTurn( Territory draftTo, int troopAmt ) {
        turnType = "draft";
        Player p = players[currentPlayerTurn];
        ArrayList<Territory> territories = p.getGainedTerritories();

        //put those troops into selected territory
        p.useInfantries(troopAmt);
        Territory territory = draftTo;


        //create infantries
        ArrayList<Troop> troopsToFortify = new ArrayList<Troop>();
        for( int i = 0; i < troopAmt; i++ ) {
            troopsToFortify.add( new Infantry() );
        }

        if( territory.getArmy() == null ) { // if there is no army in the draftTo location
            territory.setArmy( new Army( troopsToFortify, p ) );
            printMap();
            return;
        }

        Army army = territory.getArmy();

        //fortify troops
        army.fortify(troopsToFortify);
        printMap();
    }

    // gets called only at the end of the attack turn
    public boolean endAttackTurn( Territory attackFrom, Territory attackTo ) {
        Player p = players[currentPlayerTurn];
        turnType = "attack";

        // attacker
        Territory fromTerritory = attackFrom;

        // defender
        Territory toTerritory = attackTo;

        Army att = fromTerritory.getArmy();
        Army def = toTerritory.getArmy();

        if( (att.getTotalValue() == 1) ) { // attacker loses
            System.out.println( "Attacker loses." );
            printMap();
            return false;
        } else if( (def.getTotalValue() == 0) ) {
            // attacker wins. defender's territory is now attacker's
            System.out.println( "Attacker wins. Defender's territory is now attacker's" );

            def.getOwner().removeTerritory(toTerritory);
            att.getOwner().addGainedTerritory(toTerritory);

            ArrayList<Troop> toAdd = new ArrayList<>();
            toAdd.add( new Infantry() );

            def.changeOwner( att.getOwner() );
            def.fortify( toAdd ); // add a single infantry to the gained territory

            att.getOwner().getTerritoryCard(toTerritory, cardLib); // get territory card
            printMap();
            printHands();
            return true;
        }

        System.out.println( "There is an error in attackTurn!" );
        return false; // should not happen
    }

    // TODO: This is only for territory cards, make it viable for curse cards too.
    public boolean cardTurn( ArrayList<Card> cards ) {
        Player p = players[currentPlayerTurn];
        int gained = p.tradeTerritoryCards(cards, tradeCount);
        updateTradeCount();
        if( gained == -1 ) {
            return false;
        }

        System.out.println("Player " + p.getName() + " gained " + gained + " infantries from trading.");
        p.addInfantries(gained); // add gained infantries to player
        return true;
    }

    public boolean curseCardTurn( CurseCard curseCard, Territory chosenTerritory, Player chosenPlayer ) {
        Player p = players[currentPlayerTurn];
        return p.tradeCurseCard(curseCard, this, chosenTerritory, chosenPlayer );
    }

    // Gets called during a war. Defender player requests alliance from a player other than the attacker.
    // Player getAllianceFrom transfers requested amount of soldiers to defender's territory.
    public boolean getAlliance( Player getAllianceFrom, Territory defender, int infantryAmt ) {

        if( infantryAmt > getAllianceFrom.getInfantryAmt() ) {
            System.out.println("Player " + getAllianceFrom.getName() + " does not have enough infantries.");
            return false;
        }

        getAllianceFrom.useInfantries(infantryAmt);

        ArrayList<Troop> toFortify = new ArrayList<>();
        for( int i = 0; i < infantryAmt; i++ ) {
            toFortify.add( new Infantry() );
        }

        // add these infantries to the defender's army
        defender.getArmy().fortify(toFortify);
        return true;
    }
    public boolean retreat( Territory fromTerr, Territory toTerr)
    {
        Player currPlayer = players[currentPlayerTurn];
        if(fromTerr.getArmy().getOwner() == currPlayer && toTerr.getArmy().getOwner() == currPlayer)
        {
            //cretae infantries to add later
            ArrayList<Troop> troopsToFortify = new ArrayList<Troop>();
            for( int i = 0; i < fromTerr.getArmy().getTotalValue(); i++ ) {
                troopsToFortify.add( new Infantry() );
            }

            //add created infantreis to toTerr
            toTerr.getArmy().fortify(troopsToFortify);

            //remove all infantries from the fromTerr
            fromTerr.removeArmy();
            return true;
        }
        return false;
    }

    // carry soldierAmount of infantries from "from" to "to"
    public boolean fortifyTurn( Territory from, Territory to, int soldierAmount ) {

        if( map.remoteNeighbor(from, to) ) {
            from.getArmy().forfeitMultiple(soldierAmount);
            ArrayList<Troop> toAdd = new ArrayList<>();
            for( int i = 0; i < soldierAmount; i++ ) {
                toAdd.add(new Infantry());
            }

            to.getArmy().fortify(toAdd);
            return true;
        } else {
            return false;
        }

    }

    /* if a player does not have any territory, that player loses the game */
    private void checkIfLosed( Player p ) {
        if( p.getGainedTerritories().size() == 0 ) {
            p.loseGame();
        }
    }

    /* if a player has all of the continents, that player wins */
    private void checkIfWon( Player p ) {
        if( p.getGainedContinents().size() == 7 ) {
            p.winGame();
        }
    }

    // returns winner if anyone won, null otherwise
    public Player checkIfAnyoneWon() {
        for( int i = 0; i < playerAmt; i++ ) {
            Player curr = players[currentPlayerTurn];
            if( curr.getGainedContinents().size() == 7 ) {
                curr.hasWon();
                return curr;
            }
        }
        return null;
    }



    /* returns true if the game is ended */
    private boolean checkTermination() {
        for( int i = 0; i < playerAmt; i++ ) {
            if( players[i].hasWon() ) { // end the game if a player won the game
                return true;
            }
            // TODO: may write other termination conditions i.e. total game duration has ended( may be 10 mins )
        }

        return false;
    }

    public Player [] getPlayers() {
        return players;
    }

    public void printInfAmt() {
        for( int i = 0; i < playerAmt; i++ ) {
            System.out.println( "Infantries in hand for player " + players[i].getName() + ": " +
                    players[i].getInfantryAmt() );
        }
    }

    public int getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }

    public Map getMap() {
        return map;
    }

    public CombatManager getCombatManager() {
        return cm;
    }

    public void setSecretMissionMod(boolean onOff)
    {
        this.secretMissionModOn = onOff;
    }
    public boolean getSecretMissionMod()
    {
        return secretMissionModOn;
    }
    public void updateTradeCount()
    {
        for(int i = 0; i < players.length;i++)
        {
            tradeCount += players[i].getTradeCount();
        }
    }

    public int getTradeCount() {
        return tradeCount;
    }
    
}
