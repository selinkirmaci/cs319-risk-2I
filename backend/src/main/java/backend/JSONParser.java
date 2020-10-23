package backend;

import java.util.ArrayList;

/**
 * Parses JSON file which contains all of the data(continent names,
 * territory names, etc.) and returns them as preferred type
 * @author kaan
 */
public class JSONParser {
    
    public ArrayList<Continent> getContinents( String path ) {
        ArrayList<Continent> continents = new ArrayList();
        
        //call getTerritories and use it to create continents
        //Parser logic here
        
        return continents;
    }
    
    public ArrayList<Territory> getTerritories( String path ) {
        ArrayList<Territory> territories = new ArrayList();
        
        //Parser logic here
        
        return territories;
    }
    
    public ArrayList<Card> getCards( String path ) {
        ArrayList<Card> cards = new ArrayList();
        
        //Parser logic here
        
        return cards;
    }
    
}
