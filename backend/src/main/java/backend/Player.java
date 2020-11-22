package backend;

/**
 *
 * @author kaan
 */
public class Player {
    private int infantryAmt;
    private final String name;
    private final Avatar avatar;
    
    public Player( String name, Avatar avatar ) {
        this.name = name;
        this.avatar = avatar;
    }
}
