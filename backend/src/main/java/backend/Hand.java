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

    public ArrayList<Card> getCards(){return cards;}

    /* return random number between min-max both inclusive */
    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    // try to get immunity card at the start of each turn (1/10 probability of getting it)
    public void tryGettingImmunityCard() {
        int random = getRandomNumberInRange(0, 9);
        if( random == 0 ) {
            System.out.println("Gained immunity card!");
            Card c = new ImmunityCard("Immunity Card", 1);
            cards.add(c); // add immunity card to hand
        }
    }

    public Card getCard(String cardName)
    {
        for(int i = 0; i<cards.size();i++)
        {
            if(cards.get(i).getName() == cardName)
            {
                return cards.get(i);
            }
        }
        return null;
    }
    public int getSize()
    {
        return cards.size();
    }

    public void printHand() {
        for( int i = 0; i < cards.size(); i++ ) {
            System.out.println(cards.get(i).getName());
        }
    }

    // checks if the selected cards are viable for exchange ( for territory cards )
    public boolean isViable(ArrayList<Card> cards) {
        int inf = 0; // infantry card count
        int c = 0; // calvary card count
        int a = 0; // artillery card count


        if( cards.size() < 3 ) {
            return false;
        }

        // first check if each card is territory cards
        for( int i = 0; i < cards.size(); i++ ) {
            if( cards.get(i) instanceof TerritoryCard ) {
                String cardName = cards.get(i).getName();
                if( cardName.substring(cardName.indexOf("_") + 1).equals("inf") ) {
                    inf++;
                } else if( cardName.substring(cardName.indexOf("_") + 1).equals("calv") ) {
                    c++;
                } else {
                    a++;
                }
            } else { // each card should be territory cards
                return false;
            }
        }

        // * 3 cards of the same troop design (a,a,a), (i,i,i), (c,c,c)
        // * 1 each of the 3 troop designs (a,i,c)
        if( ((inf == 1) && (c == 1) && (a == 1)) || (inf == 3) || (c == 3) || (a == 3) ) {
            return true;
        }

        return false;
    }

    public void removeUsedCards( ArrayList<Card> cards ) {
        for( int i = 0; i < cards.size(); i++ ) {
            this.cards.remove(cards.get(i));
        }
    }


}
