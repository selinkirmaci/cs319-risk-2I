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

    public GameManager( int playerNumber, String[] playernames, int[]playerAvatars,
                       Color[] playerColors, boolean secretMission ) {
        Player[] players = createPlayers(playerNumber,playernames,playerAvatars,playerColors);
        for(int i = 0; i<playerNumber;i++)
        {
            int random = (int)(Math.random()*4)+1;
            players[i].setSecretMission(random);
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
        Date date = new Date();
        String sdate = formatter.format(date);

        try(ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(new File( System.getProperty("user.dir") + File.pathSeparator/*if does not work, try this: File.separator*/ + sdate + "_saved_game.data" )))) {
            oos.writeObject(this);
        } catch( IOException e ) {
            e.printStackTrace();
        }
    }

    // TODO:
    //  endGame(), restartGame(), loadGame(), ...

}