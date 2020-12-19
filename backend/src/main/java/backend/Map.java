package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author kaan
 */
public class Map implements Serializable {

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


    public Continent getContinentOfTerritory(Territory t)
    {
        for(Continent c: continents)
        {
            if(c.getTerritories().contains(t))
            {
                return c;
            }
        }
        return null;
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

    // returns owned and unvisited neighbors of territory t
    public ArrayList<Territory> getOwnedNeighbors( Territory t, ArrayList<Territory> visited ) {
        ArrayList<Territory> neighbors = t.getNeighbors();
        Army army = t.getArmy();
        if( (army == null) || (neighbors.size() == 0) ) {
            return neighbors;
        }
        Player owner = army.getOwner();
        ArrayList<Territory> ownedNeighbors = new ArrayList<>();
        for( Territory neighbor: neighbors ) {
            if( (neighbor.getArmy() != null) && (neighbor.getArmy().getOwner() == owner)
                    && (neighbor != t) && !visited.contains(neighbor) ) {
                ownedNeighbors.add(neighbor);
            }
        }

        return ownedNeighbors;
    }

    public boolean foundTargetTerritory( ArrayList<Territory> candidates, Territory target ) {
        if( candidates.contains(target) ) {
            return true;
        } else {
            return false;
        }
    }

    public void pushListToStack( Stack<Territory> s, ArrayList<Territory> l ) {
        for( Territory item: l ) {
            s.push(item);
        }
    }

    public void printTerrStack( Stack<Territory> s ) {
        for( Territory t: s ) {
            System.out.println(t.getName() + ", ");
        }
    }


    // find if two territories are connected with each other
    public boolean remoteNeighbor( Territory from, Territory to ) {
        ArrayList<Territory> visitedNeighbors = new ArrayList<>();
        Stack<Territory> searchStack = new Stack<>();
        searchStack.push(from);

        Territory curr;
        do {
            curr = searchStack.pop(); // visit neighbor

            System.out.println("curr(visiting): " + curr.getName());
            if( !visitedNeighbors.contains(curr) ) {
                visitedNeighbors.add(curr);
            }

            pushListToStack( searchStack, getOwnedNeighbors(curr, visitedNeighbors) );

            printTerrStack(searchStack);

            if( curr == to ) {
                return true;
            }

            if( (searchStack.size() == 0) || (visitedNeighbors.size() > 45) ) {
                return false;
            }
        } while( curr != to );

        return true;

    }
    
}
