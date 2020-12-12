package backend;

/**
 *
 * @author kaan
 */
public class Dice {
    
    private static final int SIDES = 6;
    private static Dice diceInstance = new Dice();

    private Dice() {}
    
    public int rollDice() {
        return (int) (Math.random() * SIDES) + 1;
    }

    public static Dice getInstance(){
        return diceInstance;
    }
    
}

