package backend;

import java.util.ArrayList;

/**
 * Represents Army inside each continent
 * @author kaan
 */
public class Army {
    
    private ArrayList<Troop> troops;
    private int totalValue; //total value of all of the troops in this army
    private Player owner; //owner of the army
    private Territory territory; //territory where the army is in( we may not use this! )
    
    public Army( ArrayList<Troop> troops, Player owner ) {
        this.owner = owner;
        this.troops = troops;
        totalValue = computeTotalValue( troops );
    }
    
    //return total value of the army in terms of infantries
    private int computeTotalValue( ArrayList<Troop> troops ) {
        int val = 0;
        for ( Troop troop : troops ) {
            if( troop instanceof Infantry ) {
                val += 1;
            } else if( troop instanceof Calvary ) {
                val += 5;
            } else if( troop instanceof Artillery ) {
                val += 10;
            }
        }
        
        return val;
    }

    public Player getOwner() {
        return owner;
    }
    
    public int getTotalValue() {
        return totalValue;
    }

    //reduce the amount of soldiers
    public void forfeit( int loss ) {

    }
    
}
