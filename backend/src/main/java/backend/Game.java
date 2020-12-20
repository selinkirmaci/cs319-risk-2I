package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
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
    private final Dice dice;
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
                curr.setContinent(map.getContinentOfTerritory(curr));
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
    // each player will get (1-2-3-4) randomly chosen continent based on player amount
    public void initialTurn() {
        System.out.println( "First Turn: Distributing armies into random continents" );
        ArrayList<Continent> conts = map.getContinents();
        ArrayList<Integer> playerContinents = new ArrayList<Integer>();
        int amtOfConts = conts.size() / playerAmt; // amount of continents to distribute
        int remanining = conts.size() - amtOfConts * playerAmt;
        int[] amtsToDistribute = new int[playerAmt];
        HashMap<Player, ArrayList<Continent>> distribution = new HashMap<>();

        for( int i = 0; i < playerAmt; i++ ) {
            if( remanining > 0 ) {
                amtsToDistribute[i] = amtOfConts + 1;
                remanining--;
            } else {
                amtsToDistribute[i] = amtOfConts;
            }
        }

        for( int i = 0; i < playerAmt; i++ ) {
            ArrayList<Continent> continentsToPut = new ArrayList<>();;
            for( int j = 0; j < amtsToDistribute[i]; j++ ) {

                if(playerContinents.size() == 7) {
                    return;
                }

                int randomCont = getRandomNumberInRange(0,6);

                // get random continent until an unused continent appears
                // and this unused continent has > 3 territories
                while( playerContinents.contains(randomCont) ) {
                    randomCont = getRandomNumberInRange(0,6);
                }

                playerContinents.add(randomCont);

                Continent currCont = conts.get(randomCont);
                ArrayList<Territory> currTerrs = currCont.getTerritories();
                int terrAmt = currTerrs.size();
                int troopAmt = 1;
                players[i].addGainedContinent(currCont);

                continentsToPut.add(currCont);
            }
            distribution.put(players[i], continentsToPut);
        }

        distributeSoldiers(distribution);
        distributeRemainings();
    }

    public void distributeSoldiers(HashMap<Player, ArrayList<Continent>> map) {

        int terrAmt[] = new int[playerAmt]; // distribute based on these territory amounts

        for( int i = 0; i < playerAmt; i++ ) {
            terrAmt[i] = 0;
        }

        // get territory amounts of each player
        for( int i = 0; i < map.size(); i++ ) {
            for( int j = 0; j < map.get(players[i]).size(); j++ ) {
                terrAmt[i] += map.get(players[i]).get(j).getTerritories().size();
            }
        }

        int troopAmt = players[0].getInfantryAmt();
        int distributeAmt[] = new int[playerAmt];


        for( int i = 0; i < playerAmt; i++ ) {
            distributeAmt[i] = troopAmt / terrAmt[i];
        }


        for( int i = 0; i < playerAmt; i++ ) { // each player
            for( int j = 0; j < players[i].getGainedContinents().size(); j++ ) { // each continent
                for( int k = 0; k < players[i].getGainedContinents().get(j).getTerritories().size(); k++ ) { // each territory
                    int distAmt = distributeAmt[i];
                    Territory currTerr = players[i].getGainedContinents().get(j).getTerritories().get(k);
                    currTerr.setContinent(players[i].getGainedContinents().get(j));
                    players[i].useInfantries(distAmt);
                    players[i].addGainedTerritory(currTerr); // add the gained territory

                    //create troops:
                    ArrayList<Troop> troops = new ArrayList<Troop>();
                    for (int p = 0; p < distAmt; p++) {
                        troops.add(new Infantry());
                    }
                    Army army = new Army(troops, players[i]);
                    currTerr.setArmy(army);
                }
            }
        }
    }

    private void distributeRemainings() {

        for( int i = 0; i < playerAmt; i++ ) {
            Player c = players[i];
            int left = c.getInfantryAmt();
            if( left == 0 ) {
                continue;
            }
            for( int j = 0; j < c.getGainedTerritories().size(); j++ ) {
                if( left == 0 ) {
                    break;
                }
                ArrayList<Troop> troops = new ArrayList();
                troops.add(new Infantry());
                c.getGainedTerritories().get(j).getArmy().fortify(troops);
                c.useInfantries(1);
                left = c.getInfantryAmt();

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
    private int startTurn( Player p ) {
        int numOfTerr = p.getGainedTerritories().size();
        int addition = 3;
        if( (numOfTerr / 3) > 3 ) {
            addition = numOfTerr / 3;
        }
        p.addInfantries( addition );
        System.out.println( "Added " + addition + " infantries to player " + p.getName() );

        int totalAmt = addition;

        //add additional units for gained continents
        for( int i = 0; i < p.getGainedContinents().size(); i++ ) {
            int extra;
            extra = p.getGainedContinents().get(i).getExtraArmies();
            totalAmt += extra;
            p.addInfantries( extra );
            System.out.println( "Added " + extra + " amount of infantries to player " +
                    p.getName() + " for continent " + p.getGainedContinents().get(i) );
        }

        return totalAmt;

    }

    // passes the turn to the other player
    // checks if any territory is immune and if there is an immune territory,
    // decrease its round count
    // if a player has rebellion, pass that player's turn too
    public int passTurn() {
        currentPlayerTurn++;
        currentPlayerTurn = currentPlayerTurn % playerAmt;
        Player currPlayer = players[currentPlayerTurn];

        checkIfLosed(currPlayer);
        boolean won = checkIfWon(currPlayer);

        // Pass lost players
        if( currPlayer.hasLost() ) {
            passTurn();
            return -1;
        } else if( currPlayer.isHasRebellion() ) {
            currPlayer.endRebellion();
            passTurn();
            return -1;
        }

        System.out.println( "**** Turn of player: " + currPlayer.getName() + " ****" );
        int gainedAmt = startTurn(currPlayer); //start the turn of current player
        currPlayer.getHand().tryGettingCurseCard(); // try to get immunity card

        decreaseImmunityCounts();
        System.out.println( "Currently immune territories: " + currentlyImmuneTerritories.toString() );
        return gainedAmt;

    }
    
    /* add troops to owned or empty territories */
    public void draftTurn( Territory draftTo, int troopAmt ) {
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
            p.addGainedTerritory(draftTo);
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

            System.out.println("defenders ters" + def.getOwner().hasNoTerritory());
            def.getOwner().checkIfLost();

            att.getOwner().addGainedTerritory(toTerritory);

            att.getOwner().checkIfWon();

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

            //add created infantries to toTerr
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

    /* if a player has all of the continents or all other players have lost, that player wins */
    public boolean checkIfWon( Player p ) {
        if(!secretMissionModOn) {
            if (p.getGainedContinents().size() == 7) {
                p.winGame();
                return true;
            } else  {
                int lossCount = 0;
                for( int i = 1; i < playerAmt + 1; i++ ) {
                    Player currPlayer = players[i - 1];
                    if( currPlayer == p ) {
                        continue;
                    }
                    if( currPlayer.hasLost() ) {
                        lossCount++;
                    }
                }

                if( lossCount == playerAmt - 1 ) { // other players lost
                    return true;
                }
            }
        } else {
            System.out.println("On secretMissionConditions");
            System.out.println("************************************************************************");
            int secretMissionNumber = p.getSecretMission();
            if(secretMissionNumber == 1) //conquer 24 territories
            {
                System.out.println("Secret mission 1");
                if( p.getGainedTerritories().size()==30 )
                {
                    p.winGame();
                    return true;
                }
            }else if(secretMissionNumber == p.getGainedContinentsNumber() + 1) // conquer 2 continents
            {
                System.out.println("Secret mission 2");
                System.out.println("Number of continents is: " + p.getGainedContinentsNumber());
                if(p.getGainedContinentsNumber() == 2)
                {
                    p.winGame();
                    return true;
                }
            }else if(secretMissionNumber == 3) // conquer at least 2 territories in each continent
            {
                System.out.println("Secret mission 3");
                ArrayList<Territory> gainedTerritories = p.getGainedTerritories();
                int[] continentArray = p.getContinentNumbersArray();
                boolean secretMissionComplete = true;
                if(gainedTerritories.size() >=14)
                {
                    for(int c: continentArray)
                    {
                        System.out.println("territory in continent number is: "+c);
                        if(c < 2)
                        {
                            secretMissionComplete = false;
                        }
                    }
                    if(secretMissionComplete) {
                        p.winGame();
                        return true;
                    }
                }


            }else if(secretMissionNumber == 4) //attack and win against all players at least 2 times
            {
                System.out.println("Secret mission 4");
                ArrayList<Integer> attackNumbers = p.getAttackNumbers();
                boolean secretMissionComplete = false;
                for(int a:attackNumbers)
                {
                    System.out.println("attack number is: " + a);
                    if( a >= 2)
                    {
                        secretMissionComplete = true;
                    }
                }
                if(secretMissionComplete) {
                    p.winGame();
                    return true;
                }

            }
            return false;
        }
        return false;
    }

    // returns winner if anyone won, null otherwise
    public Player checkIfAnyoneWon() {
        System.out.println("In checking if anyone won");
        if(!secretMissionModOn) {
            for (int i = 0; i < playerAmt; i++) {
                Player curr = players[currentPlayerTurn];
                if (curr.getGainedContinentsNumber() == 7) {
                    curr.hasWon();
                    return curr;
                }
                return null;
            }
        } else {
            for (int i = 0; i < playerAmt; i++) {
                Player curr = players[i];
                if(checkIfWon(curr))
                    return curr;
            }
            return null;
        }
        return null;
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
