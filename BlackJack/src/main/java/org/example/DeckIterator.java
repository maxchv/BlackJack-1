package org.example;

import java.util.Iterator;

public class DeckIterator implements Iterator<Deck.Card> {
    private final Deck.Card[] deck;
    private int index;

    public DeckIterator(Deck.Card[] deck) {
        this.deck = deck;
        this.index = -1;
    }

    @Override
    public boolean hasNext() {
        return ++index < deck.length;
    }

    @Override
    public Deck.Card next() {
        return deck[index];
    }
}
