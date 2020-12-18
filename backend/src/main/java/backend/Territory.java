package backend;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author kaan
 */
public class Territory implements Serializable {
    private final String name;
    private Army army;
    private ArrayList<Territory> neighbors;
    
    
    Territory( String name ) {
        this.name = name;
    }
    
    public void setArmy( Army newArmy ) {
        this.army = newArmy;
    }
    
    public Army getArmy() {
        if( army == null ) {
            return null;
        } else {
            return army;
        }
    }

    public String getName() { return name; }
    public void setNeighbors( ArrayList<Territory> neighbors ) {
        this.neighbors = neighbors;
    }

    public ArrayList<Territory> getNeighbors() {
        return neighbors;
    }

    public boolean isNeighbor( Territory neighbor ) {
        return neighbors.contains(neighbor);
    }
}
