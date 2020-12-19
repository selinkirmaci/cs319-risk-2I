package backend;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author kaan
 */
public class GameManager implements Serializable {
    private Game game;
    private boolean gameOver;
    private SoundManager soundManager;
    private final int INITIAL_TROOP_AMT = 100;
    private final String MAP_FILE_PATH = "./src/main/java/backend/jsonfiles/map.json";
    private final String NEIGHBORS_FILE_PATH = "./src/main/java/backend/jsonfiles/neighbors.json";
    private final String SAVE_FILE_PATH = "./src/main/resources/saved_games/";
    private String slot1 = "", slot2 = "", slot3 = "", slot4 = "";
    private String newSave = "";

    public GameManager( int playerNumber, String[] playernames, int[]playerAvatars,
                       Color[] playerColors, boolean secretMission ) {
        Player[] players = createPlayers(playerNumber,playernames,playerAvatars,playerColors);
        for(int i = 0; i<playerNumber;i++)
        {
            int random = (int)(Math.random()*4)+1;
            players[i].setSecretMission(random);
        }

        try {
            FileReader reader1 = new FileReader("./src/main/resources/saved_games/slot1.txt");
            BufferedReader bufferedReader1 = new BufferedReader(reader1);

            FileReader reader2 = new FileReader("./src/main/resources/saved_games/slot2.txt");
            BufferedReader bufferedReader2 = new BufferedReader(reader2);

            FileReader reader3 = new FileReader("./src/main/resources/saved_games/slot3.txt");
            BufferedReader bufferedReader3 = new BufferedReader(reader3);

            FileReader reader4 = new FileReader("./src/main/resources/saved_games/slot3.txt");
            BufferedReader bufferedReader4 = new BufferedReader(reader3);

            String line1, line2, line3, line4;

            while ((line1 = bufferedReader1.readLine()) != null) {
                slot1=line1;
            }

            while ((line2 = bufferedReader2.readLine()) != null) {
                slot2=line2;
            }

            while ((line3 = bufferedReader3.readLine()) != null) {
                slot3=line3;
            }

            while ((line4 = bufferedReader4.readLine()) != null) {
                slot4=line4;
            }

            if( !slot1.equals("") )
            {
                System.out.println(slot1);
            }

            if( !slot2.equals("") )
            {
                System.out.println(slot2);
            }

            if( !slot3.equals("") )
            {
                System.out.println(slot3);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        //initialise the game
        game = new Game(playerNumber, players, MAP_FILE_PATH,
                NEIGHBORS_FILE_PATH );
        game.setSecretMissionMod(secretMission);
        this.game = game;
    }

    public void startGame(int playerNumber,String[] playernames, int[]playerAvatars, Color[]playerColors) {

        //create the players
        Player[] players = createPlayers(playerNumber,playernames,playerAvatars, playerColors);

        //initialise the game
        game = new Game(playerNumber, players, MAP_FILE_PATH,
                NEIGHBORS_FILE_PATH );
        this.game = game;
    }

    /* get the names and avatar pics of the users and create the players accordingly */
    private Player[] createPlayers( int playerAmt,String[] playernames, int[]playerAvatars, Color[] playerColors ) {
        Player[] players = new Player[playerAmt];
        for( int i = 0; i < playerAmt; i++ ) {

            //set path of avatar image
            String avatarImgPath = setAvatarImage(playerAvatars[i]);

            //get infantry amount
            int infantryAmt = getInfantryAmt(playerAmt);

            //add each player
            players[i] = new Player( playernames[i], new Avatar(avatarImgPath), infantryAmt, i );
            players[i].setColor(playerColors[i]);

        }

        return players;
    }

    /* return infantry amt for each player based on player amt */
    private int getInfantryAmt( int playerAmt ) {
        int infAmt = 0;
        if( playerAmt == 2 ) {
            infAmt = 40;
        } else if( playerAmt == 3 ) {
            infAmt = 35;
        } else if( playerAmt == 4 ) {
            infAmt = 30;
        } else if( playerAmt == 5 ) {
            infAmt = 25;
        }

        return infAmt;
    }

    // returns the path of the avatar image with the given choice
    private String setAvatarImage( int choice ) {
        String avatarImgPath = "";
        //TODO: add avatar images and change the paths accordingly
        if( choice == 1 ) {
            avatarImgPath = "./src/main/resources/images/napoleon_p.png";
        } else if( choice == 2 ) {
            avatarImgPath = "./src/main/resources/images/alexander_p.png";
        } else if( choice == 3 ) {
            avatarImgPath = "./src/main/resources/images/hannibal_p.png";
        } else if( choice == 4 ) {
            avatarImgPath = "./src/main/resources/images/caesar_p.png";
        }else if( choice == 5 ) {
            avatarImgPath = "./src/main/resources/images/cengiz_p.png";
        }else if( choice == 6 ) {
            avatarImgPath = "./src/main/resources/images/suleiman_p.png";
        }

        return avatarImgPath;
    }

    public Game getGame() {
        return game;
    }

    public void saveGame() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
        Date date = new Date();
        String sdate = formatter.format(date);

        try(ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(new File(SAVE_FILE_PATH + sdate + "_saved_game.data")))) {
            oos.writeObject(this);
            newSave = sdate + "_saved_game.data";
            System.out.println(newSave);
        } catch( IOException e ) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter("./src/main/resources/saved_games/slot1.txt", true);
            FileWriter writer2 = new FileWriter("./src/main/resources/saved_games/slot2.txt", true);
            FileWriter writer3 = new FileWriter("./src/main/resources/saved_games/slot3.txt", true);
            FileWriter writer4 = new FileWriter("./src/main/resources/saved_games/slot4.txt", true);

            if( slot1.equals("") )
            {
                writer.write(newSave);
            }

            else if( slot2.equals("") )
            {
                writer2.write(newSave);
            }

            else if( slot3.equals("") )
            {
                writer3.write(newSave);
            }

            else if( slot4.equals("") )
            {
                writer4.write(newSave);
            }

            writer.close();
            writer2.close();
            writer3.close();
            writer4.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // TODO:
    //  endGame(), restartGame(), loadGame(), ...

}