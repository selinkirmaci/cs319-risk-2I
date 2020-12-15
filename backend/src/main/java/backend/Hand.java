package backend;

import java.util.ArrayList;
import java.util.Random;

public class Hand {

    private ArrayList<Card> cards;

    Hand() {
        cards = new ArrayList<Card>();
    }

    public void addCard( Card card ) {
        cards.add(card);
    }

    /* return random number between min-max both inclusive */
    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    // try to get immunity card (1/10 probability of getting it)
    public void tryGettingImmunityCard() {
        int random = getRandomNumberInRange(0, 9);
        if( random == 0 ) {
            System.out.println("Gained immunity card!");
            Card c = new ImmunityCard("Immunity Card", 1);
            cards.add(c); // add immunity card to hand
        }
    }

    public void printHand() {
        for( int i = 0; i < cards.size(); i++ ) {
            System.out.println(cards.get(i).getName());
        }
    }


}
