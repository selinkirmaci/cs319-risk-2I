package backend;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author kaan
 */
public class GameManager {
    private Game game;
    private boolean gameOver;
    private SoundManager soundManager;
    private final int INITIAL_TROOP_AMT = 100;
    private final String MAP_FILE_PATH = "./src/main/java/backend/jsonfiles/map.json";
    private final String CARDS_FILE_PATH = "./src/main/java/backend/jsonfiles/cards.json";

    public GameManager(int playerNumber,String[] playernames, int[]playerAvatarIndexs) {
        startGame(playerNumber, playernames,playerAvatarIndexs);
    }

    private void startGame(int playerNumber,String[] playernames, int[]playerAvatars) {
        //Scanner sc= new Scanner(System.in);

        //ask for user amount first
        /*
        System.out.println( "Enter player amount(3-5):" );
        int playerAmt = -1;
        while( playerAmt == -1 || playerAmt <= 2 || playerAmt > 5 ) {
            try {
                playerAmt = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println( "You should enter an integer value between 3 to 5!" );
                System.out.println( "Enter player amount again:" );
            }
        }
*/
        //create the players
        Player[] players = createPlayers(playerNumber,playernames,playerAvatars);

        //initialise the game
        game = new Game(playerNumber, players, MAP_FILE_PATH,
                CARDS_FILE_PATH );
    }

    /* ask for names and avatar pics of the users and create the players accordingly */
    private Player[] createPlayers( int playerAmt,String[] playernames, int[]playerAvatars ) {
        Player[] players = new Player[playerAmt];
        Scanner sc= new Scanner(System.in);
        for( int i = 0; i < playerAmt; i++ ) {
            /*
            System.out.println( "Enter username for user " + i );
            String username = sc.nextLine();

            System.out.println( "Select avatar for user " + i );

            int avatarChoice = sc.nextInt();
            sc.nextLine();

             */

            //set path of avatar image
            String avatarImgPath = setAvatarImage(playerAvatars[i]);

            //get infantry amount
            int infantryAmt = getInfantryAmt(playerAmt);

            //add each player
            players[i] = new Player( playernames[i], new Avatar(avatarImgPath), infantryAmt, i );

        }

        return players;
    }

    /* return infantry amt for each player based on player amt */
    private int getInfantryAmt( int playerAmt ) {
        int infAmt = 0;
        if( playerAmt == 3 ) {
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
            avatarImgPath = "./src/main/resources/images/alexander_p.png";
        } else if( choice == 2 ) {
            avatarImgPath = "./src/main/resources/images/hannibal_p.png";
        } else if( choice == 3 ) {
            avatarImgPath = "./src/main/resources/images/cengiz_p.png";
        } else if( choice == 4 ) {
            avatarImgPath = "./src/main/resources/images/caesar_p.png";
        }

        return avatarImgPath;
    }



    // TODO:
    //  endGame(), restartGame(), loadGame(), ...

}