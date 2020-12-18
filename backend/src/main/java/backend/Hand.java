package backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Hand implements Serializable {

    private ArrayList<TerritoryCard> territoryCards;
    private ArrayList<CurseCard> curseCards;

    Hand() {
        territoryCards = new ArrayList<TerritoryCard>();
        curseCards = new ArrayList<CurseCard>();
    }

    public void addCard( Card card ) {
        if( card instanceof TerritoryCard ) {
            territoryCards.add((TerritoryCard) card);
        } else {
            curseCards.add((CurseCard) card);
        }
    }

    public ArrayList<TerritoryCard> getTerritoryCards(){ return territoryCards; }
    public ArrayList<CurseCard> getCurseCards(){ return curseCards; }

    public CurseCard getCursedCard(String cardName)
    {
        for(CurseCard c: curseCards)
        {
            if(c.getName().equals(cardName))
                return c;
        }
        return null;
    }

    /* return random number between min-max both inclusive */
    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    // 1: celebration
    // 2: epidemic
    // 3: immunity
    // 4: powerboost
    // 5: rebellio
    // try to get curse card at the start of each turn
    // 1/10 probability of getting each one of the curse cards
    public void tryGettingCurseCard() {

        // choose one curse card randomly
        int randomType = getRandomNumberInRange(1, 5);
        CurseCard chosenCard = null;

        switch(randomType) {
            case 1:
                chosenCard = new CurseCard("celebration", 1);
                break;
            case 2:
                chosenCard = new CurseCard("epidemic", 2);
                break;
            case 3:
                chosenCard = new CurseCard("immunity", 3);
                break;
            case 4:
                chosenCard = new CurseCard("powerboost", 4);
                break;
            case 5:
                chosenCard = new CurseCard("rebellio", 5);
                break;
        }

        // there is a 1/10 chance of getting one of these cards
        int random = 0;//getRandomNumberInRange(0, 9); FOR TESTING. TODO: dont forget to correct random after testing
        if( random == 0 ) {
            System.out.println( "Gained " + chosenCard.getName() );
            curseCards.add(chosenCard); // add immunity card to hand
        }
    }

    public Card getCard(String cardName)
    {
        for( int i = 0; i < territoryCards.size(); i++ ) {
            if( territoryCards.get(i).getName() == cardName ) {
                return territoryCards.get(i);
            }
        }
        return null;
    }

    public int getSize() {
        return territoryCards.size();
    }

    public void printHand() {
        for( int i = 0; i < territoryCards.size(); i++ ) {
            System.out.println(territoryCards.get(i).getName());
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
                if( cardName.substring(cardName.indexOf("_") + 1).equals("infantry") ) {
                    inf++;
                } else if( cardName.substring(cardName.indexOf("_") + 1).equals("calvary") ) {
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

    public void removeUsedTerritoryCards( ArrayList<Card> cards ) {
        for( int i = 0; i < cards.size(); i++ ) {
            this.territoryCards.remove(cards.get(i));
        }
    }

    public void removeUsedCurseCard( CurseCard card ) {
        this.curseCards.remove(card);
    }


}
