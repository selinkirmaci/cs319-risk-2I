// Author: Sukru

package backend;

import com.sun.deploy.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class CombatManager {

    private final Dice dice;
    private static CombatManager cmInstance = new CombatManager();

    private CombatManager() {
        dice = Dice.getInstance();
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
                defender.forfeit(); //May change: consider how the loss will be calculated!
                System.out.println( "Defender lost one infantry. Current army value for defender:" + defender.getTotalValue() );
            } else if( attRoll == defRoll ) {
                System.out.println( "Even." );
                continue; //roll again
            } else { //attacker loses
                attacker.forfeit(); //May change: consider how the loss will be calculated!
                System.out.println( "Attacker lost one infantry. Current army value for attacker:" + attacker.getTotalValue() );
            }

            attacker.getOwner().unBoost(); // boost ends
        }

    }

    // completes a single attack( a single roll ) and returns the results of these dice rolls
    public int[] singleAttack( Army attacker, Army defender,int attackerDiceNumber ,int defenderDiceNumber ) {
        int attRoll; //attacker's result from dice roll
        int defRoll; //defender's result from dice roll

        if( attacker.getTotalValue() == 1 || defender.getTotalValue() == 0 ) {
            return null;
        }
        int[]attRolls = new int[attackerDiceNumber];
        int[]defRolls = new int[defenderDiceNumber];

        for(int i = 0; i < attackerDiceNumber;i++)
        {
            if( attacker.getOwner().isBoosted() ) { // attacker is boosted!
                attRolls[i] = dice.rollBiasedDice();
            } else {
                attRolls[i] = dice.rollDice();
            }
        }
        for(int i = 0; i < defenderDiceNumber;i++)
        {
            defRolls[i] = dice.rollDice();
        }
        Arrays.sort(attRolls);
        Arrays.sort(defRolls);

        if(attackerDiceNumber == 1 || defenderDiceNumber == 1)
        {
            if(attRolls[attackerDiceNumber-1] > defRolls[defenderDiceNumber-1])
            {
                System.out.println( "Defender lost one infantry. Current army value for defender:" + defender.getTotalValue() );
                defender.forfeit();
            } else if(attRolls[attackerDiceNumber-1] == defRolls[defenderDiceNumber-1])
            {
                System.out.println( "Even." );
                System.out.println("Attacker lost one infantry. Current army value for attacker:" + attacker.getTotalValue());
                attacker.forfeit();
            }else {
                System.out.println("Attacker lost one infantry. Current army value for attacker:" + attacker.getTotalValue());
                attacker.forfeit();
            }
        }else{
            if(attRolls[attackerDiceNumber-1] > defRolls[defenderDiceNumber-1])
            {
                System.out.println( "Defender lost one infantry. Current army value for defender:" + defender.getTotalValue() );
                defender.forfeit();
            }else{
                System.out.println("Attacker lost one infantry. Current army value for attacker:" + attacker.getTotalValue());
                attacker.forfeit();
            }
            if(attRolls[attackerDiceNumber-2] <= defRolls[defenderDiceNumber-2])
            {
                System.out.println("Attacker lost one infantry. Current army value for attacker:" + attacker.getTotalValue());
                attacker.forfeit();
            }else
            {
                System.out.println( "Defender lost one infantry. Current army value for defender:" + defender.getTotalValue() );
                defender.forfeit();
            }
        }

/*
        if( attacker.getOwner().isBoosted() ) { // attacker is boosted!
            attRoll = dice.rollBiasedDice();
        } else {
            attRoll = dice.rollDice();
        }

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
 */

        int results[] = new int[attackerDiceNumber+defenderDiceNumber];
        System.arraycopy(attRolls,0,results,0,attackerDiceNumber);
        System.arraycopy(defRolls,0,results,attackerDiceNumber,defenderDiceNumber);
        //results[0] = attRoll;
        //results[1] = defRoll;

        return results;
    }

    // returns the winner if the war has ended, returns null otherwise
    public Army warEnded( Army attacker, Army defender ) {
        // battle ends when attacker has 1 infantry left
        if( (attacker.getTotalValue() == 1) || (defender.getTotalValue() == 0) ) {
            return getWinner( attacker, defender ); // battle has ended
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

    public static CombatManager getInstance(){
        return cmInstance;
    }

}