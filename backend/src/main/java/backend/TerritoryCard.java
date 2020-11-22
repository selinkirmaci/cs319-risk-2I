package backend;

public class TerritoryCard extends Card {
    private final String territory;


    TerritoryCard( String name, String territory, Troop troopType ) {
        super(name, troopType);
        this.territory = territory;
    }
}
