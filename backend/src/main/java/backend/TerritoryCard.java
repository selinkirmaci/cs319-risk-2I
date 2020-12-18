package backend;

import java.io.Serializable;

public class TerritoryCard extends Card implements Serializable {
    private final Territory territory;


    TerritoryCard( String name, int value, Territory territory ) {
        super(name, value);
        this.territory = territory;
    }
}
