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
    private Scanner sc;
    private final int INITIAL_TROOP_AMT = 100;
    private final String MAP_FILE_PATH = "./jsonfiles/map.json";
    private final String CARDS_FILE_PATH = "./jsonfiles/cards.json";

    public GameManager() {
        startGame();
    }

    private void startGame() {
        Scanner sc= new Scanner(System.in);

        //ask for user amount first
        System.out.println( "Enter player amount:" );
        int playerAmt = sc.nextInt();
        ArrayList<Player> players = createPlayers(playerAmt);
        game = new Game(playerAmt, players, INITIAL_TROOP_AMT, MAP_FILE_PATH,
                CARDS_FILE_PATH );
    }

    /* ask for names and avatar pics of the users and create the players accordingly */
    private ArrayList<Player> createPlayers(int playerAmt ) {
        ArrayList<Player> players = new ArrayList<Player>();
        Scanner sc= new Scanner(System.in);
        for( int i = 0; i < playerAmt; i++ ) {
            System.out.println( "Enter username for user " + i );
            String username = sc.nextLine();

            System.out.println( "Select avatar for user " + i );
            int avatarChoice = sc.nextInt();
            String avatarImgPath = setAvatarImage(avatarChoice);
            players.add( new Player( username, new Avatar(avatarImgPath) ) );

        }

        return players;
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
    //  startGame(): Ask the user amount and usernames of each user.
    //  endGame(), restartGame(), loadGame()

}