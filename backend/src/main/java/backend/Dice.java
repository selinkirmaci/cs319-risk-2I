package backend;

import java.io.Serializable;

/**
 *
 * @author kaan
 */
public class Dice implements Serializable {
    
    private static final int SIDES = 6;
    private static Dice diceInstance = new Dice();

    private Dice() {}
    
    public int rollDice() {
        return (int) (Math.random() * SIDES) + 1;
    }

    // gets called if a boosted player makes an attack
    public int rollBiasedDice() {
        return (int) ((Math.random() * 4) + 2);
    }

    public static Dice getInstance(){
        return diceInstance;
    }
    
}

