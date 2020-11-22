package backend;

import java.util.ArrayList;

/**
 *
 * @author kaan
 */
public class Continent {
    private final int extraArmies;
    private final String name;
    private final ArrayList<Territory> territories;
    
    public Continent( int extraArmies, String name, ArrayList<Territory> territories, int continentNum ) {
        this.extraArmies = extraArmies;
        this.name = name;
        this.territories = territories;
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }
}
