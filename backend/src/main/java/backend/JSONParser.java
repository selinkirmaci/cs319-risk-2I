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
public class JSONParser implements Serializable {

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

    // initializes the neighbors of each territory
    // outer arraylist contains each territory
    // inner arraylist contains neighbors of these territories
    public ArrayList<ArrayList<Territory>> getNeighbors( String path, Map map ) {
        ArrayList<ArrayList<Territory>> allNeighbors = new ArrayList<>();
        try {
            JSONArray territoryList = (JSONArray) new org.json.simple.parser.JSONParser().parse(new FileReader(path));
            for (int i = 0; i < territoryList.size(); i++) {
                ArrayList<Territory> neighbors = new ArrayList<>();
                JSONObject territory = (JSONObject) territoryList.get(i);
                neighbors.add(map.getTerritoryFromName((String)territory.get("name")));
                JSONArray neighborList = (JSONArray) territory.get("neighbors");
                for( Object o : neighborList ) {
                    String territoryName = ( String ) o;
                    Territory t = map.getTerritoryFromName(territoryName);
                    neighbors.add(t);
                }
                allNeighbors.add(neighbors);
            }
        }
        catch ( IOException | ParseException e ) {
            e.printStackTrace();
        }

        return allNeighbors;
    }


    public static JSONParser getInstance() {
        return parserInstance;
    }
    
}
