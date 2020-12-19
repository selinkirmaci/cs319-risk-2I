package backend;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author kaan
 */
public class Player implements Serializable {

    private int infantryAmt; //3 players = 35 infantry, 4p = 30, 5p = 25
    private final String name;
    private Avatar avatar;
    private Hand hand;
    private ArrayList<Continent> gainedContinents;
    private ArrayList<Territory> gainedTerritories;
    private Continent[] allContinants;
    private final int playerId;
    private boolean hasLost;
    private boolean won;
    private Color colorOfPlayer;
    private int tradeCount;
    private int secretMission;
    private boolean boosted;
    private boolean hasRebellion;
    private HashMap<String,Integer> attacksAgainstOthers;
    private int[] continentNumbersArray;
    private int gainedContinentsNumber;
    
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
        boosted = false;
        hasRebellion = false;
        continentNumbersArray = new int[7];
        allContinants = new Continent[7];
        gainedContinentsNumber = 0;
        for (int i:continentNumbersArray)
        {
            i = 0;
        }
        attacksAgainstOthers = new HashMap<String, Integer>();
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

    public void addAttack(String enemy)
    {
        System.out.println("In add attack method==========================");
        if(!attacksAgainstOthers.containsKey(enemy))
        {
            attacksAgainstOthers.put(enemy,1);
        }else
        {
            System.out.println("In add attack method2222==========================");
            System.out.println(attacksAgainstOthers.get(enemy));
            attacksAgainstOthers.put(enemy,attacksAgainstOthers.get(enemy)+1);
            System.out.println(attacksAgainstOthers.get(enemy));
        }
    }
    public ArrayList<Integer> getAttackNumbers()
    {
       ArrayList<Integer> attackNumbers = new ArrayList<Integer>();
       for(int i: attacksAgainstOthers.values())
       {
           attackNumbers.add(i);
       }
       return attackNumbers;
    }

    public ArrayList<Continent> getGainedContinents() {
        return gainedContinents;
    }

    public int getInfantryAmt() {
        return infantryAmt;
    }

    public Territory getTerritory(String terr)
    {
        for(Territory t: gainedTerritories)
        {
            if(t.getName().equals(terr))
                return t;
        }
        return null;
    }

    public ArrayList<Territory> getGainedTerritories() {
        return gainedTerritories;
    }

    public boolean hasTerritory( Territory ter ) {
        return gainedTerritories.contains(ter);
    }
    public void addGainedTerritory( Territory t ) {
        if( !gainedTerritories.contains(t) ) {
            gainedTerritories.add(t);
            System.out.println(name);
            System.out.println("Continent name is: "+t.getContinent().getName()+" ===============================================");
            if(t.getContinent().getName().equals("Klinosra"))
            {
                continentNumbersArray[0] += 1;
            }else if(t.getContinent().getName().equals("Zamuria"))
            {
                continentNumbersArray[1] += 1;
            }else if(t.getContinent().getName().equals("Teutonia"))
            {
                continentNumbersArray[2] += 1;
            }else if(t.getContinent().getName().equals("Grinomika"))
            {
                continentNumbersArray[3] += 1;
            }else if(t.getContinent().getName().equals("Mimiken"))
            {
                continentNumbersArray[4] += 1;
            }else if(t.getContinent().getName().equals("Kultanien"))
            {
                continentNumbersArray[5] += 1;
            }else if(t.getContinent().getName().equals("Donner"))
            {
                continentNumbersArray[6] += 1;
            }
            for(int i : continentNumbersArray)
            {
                System.out.println(i);
            }
        }

        checkGainedContinents();
    }
    public int[] getContinentNumbersArray()
    {
        return continentNumbersArray;
    }
    public int getGainedContinentsNumber()
    {
        return gainedContinentsNumber;
    }

    public void setAllContinants(Continent[] continants)
    {
        this.allContinants = continants;
    }
    public void removeTerritory( Territory t ) {
        if(t.getContinent().getName().equals("Klinosra"))
        {
            continentNumbersArray[0] -= 1;
        }else if(t.getContinent().getName().equals("Zamuria"))
        {
            continentNumbersArray[1] -= 1;
        }else if(t.getContinent().getName().equals("Teutonia"))
        {
            continentNumbersArray[2] -= 1;
        }else if(t.getContinent().getName().equals("Grinomika"))
        {
            continentNumbersArray[3] -= 1;
        }else if(t.getContinent().getName().equals("Mimiken"))
        {
            continentNumbersArray[4] -= 1;
        }else if(t.getContinent().getName().equals("Kultanien"))
        {
            continentNumbersArray[5] -= 1;
        }else if(t.getContinent().getName().equals("Donner"))
        {
            continentNumbersArray[6] -= 1;
        }
        gainedTerritories.remove(t);
        checkGainedContinents();
    }

    //TODO: check if there are gained continents
    public void checkGainedContinents() {
        int gainedContinentNumber = 0;
        if(continentNumbersArray[0] == 10)
        {
            gainedContinentNumber += 1;
        }
        if(continentNumbersArray[1] == 6)
        {
            gainedContinentNumber += 1;
        }
        if(continentNumbersArray[2] == 9)
        {
            gainedContinentNumber += 1;
        }
        if(continentNumbersArray[3] == 4)
        {
            gainedContinentNumber += 1;
        }
        if(continentNumbersArray[4] == 7)
        {
            gainedContinentNumber += 1;
        }
        if(continentNumbersArray[5] == 7)
        {
            gainedContinentNumber += 1;
        }
        if(continentNumbersArray[6] == 2)
        {
            gainedContinentNumber += 1;
        }
        System.out.println("GainedContinentNumber is: " + gainedContinentNumber);
        gainedContinentsNumber = gainedContinentNumber;
    }

    public boolean hasNoTerritory() {
        return gainedTerritories.size() == 0;
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
        if( cardLib.territoryCardExists(t.getName() + "_infantry") ) { // infantry card exists
            cards.add(cardLib.getCard(t.getName() + "_infantry"));
        }
        if( cardLib.territoryCardExists(t.getName() + "_calvary") ) { // calvary card exists
            cards.add(cardLib.getCard(t.getName() + "_calvary"));
        }
        if( cardLib.territoryCardExists(t.getName() + "_artillery") ) { // artillery card exists
            cards.add(cardLib.getCard(t.getName() + "_artillery"));
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
            hand.removeUsedTerritoryCards(toTrade);

            this.tradeCount++;
        }

        return infantryAmt;
    }

    // Trade given curse card
    // 1: celebration
    // 2: epidemic
    // 3: immunity
    // 4: powerboost
    // 5: rebellio
    // Returns true if the curse card is traded succesfully.
    // You might pass null into chosenTerritory parameter if the card type is celebration or powerboost
    public boolean tradeCurseCard( CurseCard curseCard, Game game, Territory chosenTerritory, Player chosenPlayer ) {
        int cardType = curseCard.getValue();

        if( cardType == 1 ) { // celebration
            tradeCelebrationCard(game);
        } else if( cardType == 2 ) { // epidemic
            tradeEpidemicCard(chosenTerritory);
        } else if( cardType == 3 ) { // immunity
            tradeImmunityCard(chosenTerritory, game);
        } else if( cardType == 4 ) { // powerboost
            tradePowerboostCard();
        } else if( cardType == 5 ) { // rebellio
            tradeRebellionCard(chosenPlayer);
        } else {
            return false;
        }

        hand.removeUsedCurseCard(curseCard);
        return true;
    }


    // Collect celebration card
    // Returns true if the celebration card is traded succesfully.
    // Collect 3 soldiers from other players.
    private void tradeCelebrationCard( Game game ) {
        // player that used the card
        Player currentPlayer = game.getPlayers()[game.getCurrentPlayerTurn()];

        for( int i = 0; i < game.getPlayers().length; i++ ) {
            Player curr = game.getPlayers()[i];
            // pass if current player has lost or does not have 3 infantries
            if( (curr.hasLost()) || (curr.getInfantryAmt() < 3) ) {
                continue;
            }

            curr.useInfantries(3); // remove 3 infantries from curr
            currentPlayer.addInfantries(3); // add 3 infantries to the player that used the card
        }
    }

    // Destroy 4 soldiers from chosen enemy territory
    // If enemy territory has LTE 4 soldiers, make damage until 1 enemy is left.
    private void tradeEpidemicCard( Territory chosenTerritory ) {
        if( chosenTerritory.getArmy().getTotalValue() <= 4 ) {
            chosenTerritory.getArmy().forfeitMultiple( chosenTerritory.getArmy().getTotalValue() - 1 );
        } else {
            chosenTerritory.getArmy().forfeitMultiple( 4 );
        }
    }

    // enemies cant attack to your chosen territory for 3 rounds
    private void tradeImmunityCard( Territory chosenTerritory, Game game ) {
        chosenTerritory.getArmy().makeImmune();
        game.addImmuneTerritory(chosenTerritory);
    }

    // increases your chance of winning during an attack
    private void tradePowerboostCard() {
        boosted = true;
    }

    // chosen player cannot play for one round
    private void tradeRebellionCard( Player p ) {
        p.hasRebellion = true;
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

    public boolean isBoosted() {
        return boosted;
    }

    public void unBoost() {
        boosted = false;
    }

    public boolean isHasRebellion() {
        return hasRebellion;
    }

    public void endRebellion() {
        hasRebellion = false;
    }

    public void checkIfLost() {
        if( hasNoTerritory() ) {
            loseGame();
        }
    }
    public void checkIfWon() {
        if( gainedContinents.size() == 7 ) {
            winGame();
        }
    }

}
