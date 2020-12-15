package backend;

public class TerritoryCard extends Card {
    private final Territory territory;


    TerritoryCard( String name, int value, Territory territory ) {
        super(name, value);
        this.territory = territory;
    }
}
