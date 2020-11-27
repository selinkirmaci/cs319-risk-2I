package backend;

/**
 *
 * @author kaan
 */
public class Territory {
    private final String name;
    private Army army;
    
    
    Territory( String name ) {
        this.name = name;
    }
    
    public void setArmy( Army newArmy ) {
        this.army = newArmy;
    }
    
    public Army getArmy() {
        if( army == null ) {
            return null;
        } else {
            return army;
        }
    }

    public String getName() { return name; }
}
