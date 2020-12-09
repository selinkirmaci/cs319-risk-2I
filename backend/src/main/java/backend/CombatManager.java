// Author: Sukru

package backend;

import javax.swing.JButton;

public class CombatManager {

    private final Dice dice;

    public CombatManager() {
        dice = new Dice();
    }

    /* Execute war between two armies.
       Update the armies and continent owner according to winner.  */
    public void executeWar( Army attacker, Army defender ) {

        System.out.println( "Executing war between attacker: " + attacker.getOwner().getName()
                + " and defender: " + defender.getOwner().getName() );

        int attRoll; //attacker's result from dice roll
        int defRoll; //defender's result from dice roll

        //continue rolling until the battle ends
        while( defender.getTotalValue() != 0 && attacker.getTotalValue() != 0 ) {

            if( (attacker.getTotalValue() == 0) || (defender.getTotalValue() == 0) ) {
                return; //battle has ended
            }

            attRoll = dice.rollDice();
            defRoll = dice.rollDice();

            System.out.println( "Attacker rolled:" + attRoll );
            System.out.println( "Defender rolled:" + defRoll );

            if( attRoll > defRoll ) { //defender loses
                System.out.println( "Defender lost one infantry. Current army value for defender:" + defender.getTotalValue() );
                defender.forfeit(); //May change: consider how the loss will be calculated!
            } else if( attRoll == defRoll ) {
                System.out.println( "Even." );
                continue; //roll again
            } else { //attacker loses
                System.out.println( "Attacker lost one infantry. Current army value for attacker:" + attacker.getTotalValue() );
                attacker.forfeit(); //May change: consider how the loss will be calculated!
            }
        }

    }

    // completes a single attack( a single roll ) and returns the results of these dice rolls
    public int[] singleAttack( Army attacker, Army defender ) {
        int attRoll; //attacker's result from dice roll
        int defRoll; //defender's result from dice roll

        attRoll = dice.rollDice();
        defRoll = dice.rollDice();

        System.out.println( "Attacker rolled:" + attRoll );
        System.out.println( "Defender rolled:" + defRoll );

        if( attRoll > defRoll ) { //defender loses
            System.out.println( "Defender lost one infantry. Current army value for defender:" + defender.getTotalValue() );
            defender.forfeit(); //May change: consider how the loss will be calculated!
        } else if( attRoll == defRoll ) {
            System.out.println( "Even." );
        } else { //attacker loses
            System.out.println( "Attacker lost one infantry. Current army value for attacker:" + attacker.getTotalValue() );
            attacker.forfeit(); //May change: consider how the loss will be calculated!
        }

        int results[] = new int[2];
        results[0] = attRoll;
        results[1] = defRoll;

        return results;
    }

    // returns the winner if the war has ended, returns null otherwise
    public Army warEnded( Army attacker, Army defender ) {
        if( (attacker.getTotalValue() == 0) || (defender.getTotalValue() == 0) ) {
            return getWinner( attacker, defender ); //battle has ended
        } else {
            return null;
        }
    }

    public Army getWinner( Army attacker, Army defender ) {
        if( attacker.getTotalValue() == 0 ) {
            return attacker;
        } else {
            return defender;
        }
    }

}