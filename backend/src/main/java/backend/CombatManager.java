// Author: Sukru

package backend;

import com.sun.deploy.util.ArrayUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class CombatManager implements Serializable {

    private final Dice dice;
    private static CombatManager cmInstance = new CombatManager();

    private CombatManager() {
        dice = Dice.getInstance();
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
                defender.forfeit();
                System.out.println( "Defender lost one infantry. Current army value for defender:" + defender.getTotalValue() );
            } else if(attRolls[attackerDiceNumber-1] == defRolls[defenderDiceNumber-1])
            {
                System.out.println( "Even." );
                attacker.forfeit();
                System.out.println("Attacker lost one infantry. Current army value for attacker:" + attacker.getTotalValue());
            }else {
                attacker.forfeit();
                System.out.println("Attacker lost one infantry. Current army value for attacker:" + attacker.getTotalValue());
            }
        } else {
            if( attRolls[attackerDiceNumber-1] > defRolls[defenderDiceNumber-1] )
            {
                defender.forfeit();
                System.out.println( "Defender lost one infantry. Current army value for defender:" + defender.getTotalValue() );
            }else{
                attacker.forfeit();
                System.out.println("Attacker lost one infantry. Current army value for attacker:" + attacker.getTotalValue());
            }
            if(attRolls[attackerDiceNumber-2] <= defRolls[defenderDiceNumber - 2] && (attacker.getTotalValue() > 0) )
            {
                attacker.forfeit();
                System.out.println("Attacker lost one infantry. Current army value for attacker:" + attacker.getTotalValue());
            }else
            {
                defender.forfeit();
                System.out.println( "Defender lost one infantry. Current army value for defender:" + defender.getTotalValue() );
            }
        }

        int results[] = new int[attackerDiceNumber+defenderDiceNumber];
        System.arraycopy(attRolls,0,results,0,attackerDiceNumber);
        System.arraycopy(defRolls,0,results,attackerDiceNumber,defenderDiceNumber);
        return results;
    }

    // returns the winner if the war has ended, returns null otherwise
    public Army warEnded( Army attacker, Army defender ) {
        // battle ends when attacker has 1 infantry left
        if( (attacker.getTotalValue() == 0) || (attacker.getTotalValue() == 1) || (defender.getTotalValue() == 0) ) {
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