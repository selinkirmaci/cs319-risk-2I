package backend;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents Army inside each continent
 * @author kaan
 */
public class Army implements Serializable {
    
    private ArrayList<Troop> troops;
    private int totalValue; //total value of all of the troops in this army
    private Player owner; //owner of the army
    private int infantryAmt = 0;
    private int immuneTurnCount;
    
    public Army( ArrayList<Troop> troops, Player owner ) {
        immuneTurnCount = 0;
        this.owner = owner;
        this.troops = troops;
        computeTotalValue( troops );
        computeTroopAmts( troops );
    }

    // make immune for 3 turns
    public void makeImmune() {
        immuneTurnCount = 3;
    }

    public int getImmuneTurnCount() {
        return immuneTurnCount;
    }

    public void reduceImmuneTurnCount() {
        immuneTurnCount--;
    }

    public boolean isImmune() {
        return immuneTurnCount > 0;
    }
    
    //return total value of the army in terms of infantries
    private void computeTotalValue( ArrayList<Troop> troops ) {
        int val = 0;
        for ( Troop troop : troops ) {
            if( troop instanceof Infantry ) {
                val += 1;
            }
        }
        
        totalValue = val;
    }


    public ArrayList<Troop> getTroops() {
        return troops;
    }

    /* for testing purposes */
    public void printArmy() {
        System.out.println("\t\tOwner of the army: " + owner.getName() );
        System.out.println("\t\tInfantry: " + infantryAmt );
        System.out.println( "\t\tTotal army value: " + totalValue );

    }

    public Player getOwner() {
        return owner;
    }
    
    public int getTotalValue() {
        return totalValue;
    }

    //reduce ONE infantry from the army
    public void forfeit() {
        if( troops.size() == 0 ) {
            return;
        }

        troops.remove(0);
        infantryAmt--;
        computeTotalValue(troops);
    }

    public void forfeitMultiple( int loss ) {
        for( int i = 0; i < loss; i++ ) {
            troops.remove(0);
            infantryAmt--;
        }
        computeTotalValue(troops);
    }

    //increase the amount of soldiers
    public void fortify( ArrayList<Troop> newTroops ) {
        troops.addAll(newTroops);
        computeTotalValue(troops);
        computeTroopAmts( newTroops );
    }

    private void computeTroopAmts( ArrayList<Troop> troops ) {
        //update troop amounts
        for ( Troop troop : troops ) {
            if( troop instanceof Infantry ) {
                infantryAmt++;
            }
        }
    }

    public void changeOwner( Player p ) {
        owner = p;
    }
}
