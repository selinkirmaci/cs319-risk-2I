package backend;

public class CurseCard extends Card {

    private static String curseType;
    public CurseCard( String name, int value, String curseType ) {
        super( name, value );
        this.curseType = curseType;
    }
}
