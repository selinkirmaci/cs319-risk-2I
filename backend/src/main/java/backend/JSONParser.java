package backend;

import java.util.ArrayList;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

/**
 * Parses JSON file which contains all of the data (continent names,
 * territory names and card properties) and returns them as preferred type
 * @author Selcen
 */
public class JSONParser {

    private static JSONParser parserInstance = new JSONParser();

    private JSONParser() {}

    // creates and returns a continents array by parsing the data from a JSON file named path
    public ArrayList<Continent> getContinents( String path ) {
        ArrayList<Continent> continents = new ArrayList<>();

        try {
            JSONArray continentList = (JSONArray) new org.json.simple.parser.JSONParser().parse(new FileReader(path));

            for (int i = 0; i < continentList.size(); i++) {
                JSONObject cont = (JSONObject) continentList.get(i);
                String name = (String) cont.get("name");
                int extraArmies = ((Long) cont.get("extraArmies")).intValue();
                JSONArray territoryList = (JSONArray) cont.get("territories");

                ArrayList<Territory> territories = new ArrayList<>();
                for ( Object o : territoryList ) {
                    String territoryName = ( String ) o;
                    Territory territory = new Territory(territoryName);
                    territories.add(territory);
                }

                Continent continent = new Continent(extraArmies, name, territories, i + 1);
                continents.add(continent);
            }
        }
        catch ( IOException | ParseException e ) {
            e.printStackTrace();
        }

        return continents;
    }

    // creates and returns a cards array by parsing the data from a JSON file named path
    public ArrayList<Card> getCards( String path ) {
        ArrayList<Card> cards = new ArrayList<>();

        try {
            JSONArray cardList = (JSONArray) new org.json.simple.parser.JSONParser().parse(new FileReader(path));

            for (Object o : cardList) {
                JSONObject card = (JSONObject) o;
                String name = (String) card.get("name");
                String troopType = (String) card.get("troopType");
                String territory = (String) card.get("territory");
                int damage = ((Long) card.get("damage")).intValue();

                // determining troop type
                Troop troop;
                if (troopType.equalsIgnoreCase("Artillery")) {
                    troop = new Artillery();
                } else if (troopType.equalsIgnoreCase("Calvary")) {
                    troop = new Calvary();
                } else if (troopType.equalsIgnoreCase("Infantry")) {
                    troop = new Infantry();
                } else {
                    troop = new Troop();
                }

                // determining card type
                Card newCard;
                if (damage != 0) {     // curse card
                    newCard = new CurseCard(name, troop, damage);
                } else if (!territory.equals("")) {    // territory card
                    newCard = new TerritoryCard(name, territory, troop);
                } else {  // normal card
                    newCard = new Card(name, troop);
                }

                cards.add(newCard);
            }
        }
        catch ( IOException | ParseException e ) {
            e.printStackTrace();
        }

        return cards;
    }

    public static JSONParser getInstance() {
        return parserInstance;
    }
    
}
