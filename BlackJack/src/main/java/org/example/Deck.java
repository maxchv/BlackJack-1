package org.example;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Deck implements Iterable<Deck.Card> {

    private final static int DECK52 = 52;
    private final static int INDEX52FINISH = 12;
    private final static int INDEX52START = 0;
    Card[] deck; // private??

    public Deck() {
        deck = new Card[0];
        // перевірка роботи метода checkAce:
        //deck = new Card[]{new Card("2", 2, 2, Suite.CLUBS), new Card("8", 8, 8, Suite.SPADES), new Card("6", 6, 6, Suite.DIAMONDS),new Card("Ace", 14, 11, Suite.SPADES),};
        addCard();
    }

    private final String[] dignityArr = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private final int[] levelArr = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    private final int[] weightlArr = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
    private final Suite[] suites = Suite.values();

    static class Card implements Comparable<Card> {
        private final String DIGNITY;
        private final int LEVEL;
        private int weightCount;
        Suite suite; // private ??

        public Card(String dignity, int level, int weightCount, Suite suite) {
            this.DIGNITY = dignity;
            this.LEVEL = level;
            this.weightCount = weightCount;
            this.suite = suite;
        }

        public String getDIGNITY() {
            return DIGNITY;
        }

        public int getLEVEL() {
            return LEVEL;
        }

        public Suite getSuite() {
            return suite;
        }

        public int getWeightCount() {
            return weightCount;
        }

        public void setWeightCount(int weightCount) {
            this.weightCount = weightCount;
        }

       /* @Override
        public String toString() {
            return String.format("\n Dignity: %5s, Suite: %s, Level: %2d,  Weight: %2d", DIGNITY, suite, LEVEL, weightCount);
        }*/

        @Override
        public String toString() {
            return DIGNITY + suite;
        }

        @Override
        public int compareTo(Card o) {
            int result = this.suite.compareTo(o.suite);
            if (result == 0) {
                result = LEVEL - o.getLEVEL();
            }
            return result;
        }
    }

    //переробив метод addCard з попереднього завдання з урахуванням зауваження, використовуючи вкладений цикл
    public void addCard() {
        for (int j = 0; j < suites.length; j++) {
            for (int i = 0; i < dignityArr.length; i++) {
                deck = Arrays.copyOf(deck, deck.length + 1);
                deck[deck.length - 1] = new Card(dignityArr[i], levelArr[i], weightlArr[i], suites[j]);
            }
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(deck);
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < deck.length; i++) {
            Card tmp = deck[i];
            int rnd = random.nextInt(0, deck.length);
            deck[i] = deck[rnd];
            deck[rnd] = tmp;
        }
    }

    public void sort() {
        Arrays.sort(deck);
    }

    @Override
    public Iterator<Card> iterator() {
        return new DeckIterator(deck);
    }
}
