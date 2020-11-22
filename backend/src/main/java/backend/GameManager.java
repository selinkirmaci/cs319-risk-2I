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
    private final String MAP_FILE_PATH = "./jsonfiles/map.json";
    private final String CARDS_FILE_PATH = "./jsonfiles/cards.json";

    public GameManager() {
        startGame();
    }

    private void startGame() {
        Scanner sc= new Scanner(System.in);

        //ask for user amount first
        System.out.println( "Enter player amount(3-5):" );
        int playerAmt = -1;
        while( playerAmt == -1 && playerAmt <= 2 && playerAmt > 5 ) {
            try {
                playerAmt = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println( "You should enter an integer value between 3 to 5!" );
                System.out.println( "Enter player amount again:" );
            }
        }

        //create the players
        Player[] players = createPlayers(playerAmt);

        //initialise the game
        game = new Game(playerAmt, players, INITIAL_TROOP_AMT, MAP_FILE_PATH,
                CARDS_FILE_PATH );
    }

    /* ask for names and avatar pics of the users and create the players accordingly */
    private Player[] createPlayers( int playerAmt ) {
        Player[] players = new Player[playerAmt];
        Scanner sc= new Scanner(System.in);
        for( int i = 0; i < playerAmt; i++ ) {
            System.out.println( "Enter username for user " + i );
            String username = sc.nextLine();

            System.out.println( "Select avatar for user " + i );

            int avatarChoice = sc.nextInt();
            sc.nextLine();

            //set path of avatar image
            String avatarImgPath = setAvatarImage(avatarChoice);

            //get infantry amount
            int infantryAmt = getInfantryAmt(playerAmt);

            //add each player
            players[i] = new Player( username, new Avatar(avatarImgPath), infantryAmt, i );

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
        if( choice == 1 ) {
            avatarImgPath = "/avatarImgs/one.png";
        } else if( choice == 2 ) {
            avatarImgPath = "/avatarImgs/two.png";
        } else if( choice == 3 ) {
            avatarImgPath = "/avatarImgs/three.png";
        } else if( choice == 4 ) {
            avatarImgPath = "/avatarImgs/four.png";
        }

        return avatarImgPath;
    }



    // TODO:
    //  endGame(), restartGame(), loadGame(), ...

}