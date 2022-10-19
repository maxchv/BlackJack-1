package org.example;

public enum Suite {
    SPADES("\u2664"), HEARTS("\u2665"), DIAMONDS("\u2666"), CLUBS("\u2667");

    private final String suite;

    Suite(String suite) {
        this.suite = suite;
    }

    public String getSuite() {
        return suite;
    }

    @Override
    public String toString() {
        return suite;
    }
}
