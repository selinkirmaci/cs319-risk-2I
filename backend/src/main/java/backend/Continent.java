package backend;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author kaan
 */
public class Continent implements Serializable {
    private final int extraArmies;
    private final String name;
    private final ArrayList<Territory> territories;
    
    public Continent( int extraArmies, String name, ArrayList<Territory> territories, int continentNum ) {
        this.extraArmies = extraArmies;
        this.name = name;
        this.territories = territories;
    }

    public int getExtraArmies() {
        return extraArmies;
    }

    public ArrayList<Territory> getTerritories() {
        return territories;
    }

    public String getName() { return name; }

    public Continent returnSelf(String continentName)
    {
        if(name.equals(continentName))
        {
            return this;
        }
        else
            return null;
    }
}
