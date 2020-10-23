package backend;

/**
 *
 * @author kaan
 */
public class Territory {
    private final String name;
    Army army;
    
    
    Territory( String name ) {
        this.name = name;
    }
    
    public void setArmy( Army newArmy ) {
        this.army = newArmy;
    }
    
    public Army getArmy() {
        return army;
    }
}
