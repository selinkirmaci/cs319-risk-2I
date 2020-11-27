package backend;

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

        //continue until defender has no units left
        while( defender.getTotalValue() != 0 ) {

            if( (attacker.getTotalValue() == 0) || (defender.getTotalValue() == 0) ) {
                return; //battle has ended
            }

            attRoll = dice.rollDice();
            defRoll = dice.rollDice();

            if( attRoll > defRoll ) { //defender loses
                defender.forfeit(1); //May change: consider how the loss will be calculated!
            } else if( attRoll == defRoll ) {
                continue; //roll again
            } else { //attacker loses
                attacker.forfeit(1); //May change: consider how the loss will be calculated!
            }
        }

    }
}