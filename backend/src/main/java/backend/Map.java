package backend;

import java.util.ArrayList;

/**
 *
 * @author kaan
 */
public class Map {

    private final ArrayList<Continent> continents;
    
    Map( ArrayList<Continent> continents ) {
        this.continents = continents;
    }

    public ArrayList<Continent> getContinents() {
        return continents;
    }

    /* get all territories which has an army in it */
    public ArrayList<Territory> getAllNonEmptyTerritories() {
        Continent currC;
        Territory currT;
        ArrayList<Territory> nonEmpty = new ArrayList<>();
        for( int i = 0; i < continents.size(); i++ ) {
            currC = continents.get(i);
            for( int j = 0; j < currC.getTerritories().size(); j++ ) {
                currT = currC.getTerritories().get(j);
                if( currT.getArmy() != null ) {
                    nonEmpty.add(currT);
                }
            }
        }

        return nonEmpty;
    }


    /* returns the territory with the corresponding name, returns null if not found */
    public Territory getTerritoryFromName( String territory ) {
        Continent currC;
        Territory currT;
        ArrayList<Territory> nonEmpty = new ArrayList<>();
        for( int i = 0; i < continents.size(); i++ ) {
            currC = continents.get(i);
            for( int j = 0; j < currC.getTerritories().size(); j++ ) {
                currT = currC.getTerritories().get(j);
                if( currT.getName().equals(territory) ) {
                    return currT;
                }
            }
        }

        return null;
    }


    // returns true if both of the territories are the same player's
    public boolean sameOwners( Territory one, Territory two ) {
        if( one.getArmy().getOwner() == two.getArmy().getOwner() ) {
            return true;
        } else {
            return false;
        }
    }
    
}
