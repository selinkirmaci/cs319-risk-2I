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
        computeTotalValue( troops );
    }
    
    //return total value of the army in terms of infantries
    private void computeTotalValue( ArrayList<Troop> troops ) {
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
        
        totalValue = val;
    }

    public Player getOwner() {
        return owner;
    }
    
    public int getTotalValue() {
        return totalValue;
    }

    //reduce the amount of soldiers
    public void forfeit( int loss ) {

        computeTotalValue(troops);
    }

    //increase the amount of soldiers
    public void fortify( ArrayList<Troop> newTroops ) {

        for( int i = 0; i < troops.size(); i++ ) {
            troops.add( newTroops.get(i) );
        }

        computeTotalValue(troops);
    }

    private int getInfantryAmt() {
        int count = 0;
        for( int i = 0; i < troops.size(); i++ ) {
            if( troops.get(i) instanceof Infantry ) {
                count++;
            }
        }

        return count;
    }

    /* player might trade 5 infantries for one calvary
    or 10 infantries for one artillery */
    public void tradeTroops( int num ) {

        int infAmt = getInfantryAmt();
        if( (num == 5 && infAmt >= 5) ) { //convert to calvary
            int i = 0;
            while( i < num ) {
                if( troops.get(i) instanceof Infantry ) {
                    i++;
                    Troop toDelete = troops.remove(i);
                    toDelete = null; // to let garbage collector delete this troop
                }
            }
            troops.add( new Calvary() );
        } else if( (num == 10 && infAmt >= 10) ) { //convert to artillery
            int i = 0;
            while( i < num ) {
                if( troops.get(i) instanceof Infantry ) {
                    i++;
                    Troop toDelete = troops.remove(i);
                    toDelete = null; // to let garbage collector delete this troop
                }
            }
            troops.add( new Artillery() );
        }
    }
}
