package backend;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> cards;

    Hand() {
        cards = new ArrayList<Card>();
    }

    public void addCard( Card card ) {
        cards.add(card);
    }


}
