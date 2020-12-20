package backend;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author kaan
 */
public class CardLibrary implements Serializable {
    
    private HashMap<String, Card> territoryCards; // Key: Card name, Value: Card object
    CardLibrary() {
        territoryCards = new HashMap<String, Card>();
    }


    // creates and adds territory cards for each territory
    public void createTerritoryCards( Map map ) {
        for( int i = 0; i < map.getContinents().size(); i++ ) { // each continent
            for( int j = 0; j < map.getContinents().get(i).getTerritories().size(); j++ ) { // each territory
                Territory curr = map.getContinents().get(i).getTerritories().get(j);
                Card territoryCardInf = new TerritoryCard(curr.getName() + "_infantry", 1, curr); // infantry card
                Card territoryCardCalv = new TerritoryCard(curr.getName() + "_calvary", 5, curr); // calvary card
                Card territoryCardArty = new TerritoryCard(curr.getName() + "_artillery", 10, curr); // artillery card
                territoryCards.put(territoryCardInf.getName(), territoryCardInf);
                territoryCards.put(territoryCardCalv.getName(), territoryCardCalv);
                territoryCards.put(territoryCardArty.getName(), territoryCardArty);
            }
        }
    }

    // checks if the card exists in the library
    public boolean territoryCardExists( String cardName ) {
        return territoryCards.containsKey(cardName);
    }


    public boolean useTerritoryCard( String cardName ) {
        if( territoryCardExists(cardName) ) {
            territoryCards.remove(cardName);
        }
        return false;
    }

    public Card getCard( String cardName ) {
        return territoryCards.get(cardName);
    }
}
