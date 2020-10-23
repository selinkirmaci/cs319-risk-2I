package backend;

/**
 *
 * @author kaan
 */
public class Dice {
    
    private static final int SIDES = 6;
    
    public int rollDice() {
        return (int) (Math.random() * SIDES) + 1;
    }
    
}

