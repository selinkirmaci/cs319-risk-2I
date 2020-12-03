package backend;

public class GUIController {
    private GameManager gameManager;
    private frontend.Map map;

    public GUIController( GameManager gameManager, frontend.Map map ) {
        this.gameManager = gameManager;
        this.map = map;
    }


}
