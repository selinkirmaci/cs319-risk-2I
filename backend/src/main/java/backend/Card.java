package backend;

import java.io.Serializable;

/**
 *
 * @author kaan
 */
public class Card implements Serializable {
    
    private final String name;
    private final int value;
    
    Card( String name, int value ) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }
    public int getValue() {
        return value;
    }
    
}
