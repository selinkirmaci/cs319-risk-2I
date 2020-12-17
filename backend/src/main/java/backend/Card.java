package backend;

/**
 *
 * @author kaan
 */
public class Card {
    
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
