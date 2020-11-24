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
    private int infantryAmt = 0;
    private int calvaryAmt = 0;
    private int artilleryAmt = 0;
    
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
                infantryAmt++;
                val += 1;
            } else if( troop instanceof Calvary ) {
                calvaryAmt++;
                val += 5;
            } else if( troop instanceof Artillery ) {
                artilleryAmt++;
                val += 10;
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
        System.out.println("\t\tTroops: ");
        System.out.println("\t\tInfantry: " + infantryAmt + ", Calvary: " + calvaryAmt +
                ", Artillery: " + artilleryAmt );
        System.out.println( "\t\tTotal army value: " + totalValue );

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

    public int getInfantryAmt() {
        return infantryAmt;
    }

    public int getArtilleryAmt() {
        return artilleryAmt;
    }

    public int getCalvaryAmt() {
        return calvaryAmt;
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
