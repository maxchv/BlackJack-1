package org.example;

import java.util.Arrays;

import static org.example.Game.*;

public class Players {
    private int score;
    Deck.Card[] cards;

    public Players() {
        this.score = 0;
        cards = new Deck.Card[0];
    }

    public void getCard(Deck.Card card) {
        cards = Arrays.copyOf(cards, cards.length + 1);
        cards[cards.length - 1] = card;
        //score += card.getWeightCount();
    }

    public void checkAce() {
        int aceCount = 0;
        int[] aceCountIndex = new int[0];
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].getLEVEL() == LEVEL_ACE) {
                aceCount++;
                aceCountIndex = Arrays.copyOf(aceCountIndex, aceCountIndex.length + 1);
                aceCountIndex[aceCountIndex.length - 1] = i;
            }
        }
        if (aceCount > 0 && score > LIMIT_FOR_ACE) {
            for (int i = 0; i < aceCountIndex.length; i++) {
                cards[aceCountIndex[i]].setWeightCount(LEVEL_ACE_MORE10);
            }

        }
        score = 0;
        for (int i = 0; i < cards.length; i++) {
            score += cards[i].getWeightCount();
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
