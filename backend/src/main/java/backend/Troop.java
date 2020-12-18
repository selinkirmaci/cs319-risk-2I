package backend;

import java.io.Serializable;

/**
 *
 * @author kaan
 */
public class Troop implements Serializable {

    protected int value;
    protected boolean cursed = false;
    
    public int getValue() {
        return value;
    }

    public void curse() {
        cursed = true;
    }

    public boolean isCursed() {
        return cursed;
    }
}