package backend;

public class CurseCard extends Card {

    private final int damage;

    public CurseCard( String name, Troop troopType, int damage ) {
        super( name, troopType );
        this.damage = damage;
    }
}
