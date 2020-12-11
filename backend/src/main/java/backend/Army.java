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
    private int infantryAmt = 0;
    private int calvaryAmt = 0;
    private int artilleryAmt = 0;
    
    public Army( ArrayList<Troop> troops, Player owner ) {
        this.owner = owner;
        this.troops = troops;
        computeTotalValue( troops );
        computeTroopAmts( troops );
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

    public ArrayList<Troop> getTroops() {
        return troops;
    }

    /* for testing purposes */
    public void printArmy() {
        System.out.println("\t\tOwner of the army: " + owner.getName() );
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

    //reduce ONE infantry from the army
    public void forfeit() {
        // TODO this is very inefficient for now. We might improve this eger firsat kalirsa
        for( int i = 0; i < troops.size(); i++ ) {
            Troop t = troops.get(i);

            if( t instanceof Infantry ) {
                troops.remove(i); //delete the infantry
                break;
            }
        }

        infantryAmt--;
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
            } else if( troop instanceof Calvary ) {
                calvaryAmt++;
            } else if( troop instanceof Artillery ) {
                artilleryAmt++;
            }
        }
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
            infantryAmt -= 5;
            calvaryAmt += 1;
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
            infantryAmt -= 10;
            artilleryAmt += 1;
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

    public void changeOwner( Player p ) {
        owner = p;
    }
}
