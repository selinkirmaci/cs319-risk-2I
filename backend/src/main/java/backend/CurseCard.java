package backend;

import java.io.Serializable;

public class CurseCard extends Card implements Serializable {


    // value = curse card type
    // 1: celebration
    // 2: epidemic
    // 3: immunity
    // 4: powerboost
    // 5: rebellio

    public CurseCard( String name, int value ) {
        super( name, value );
    }
}
