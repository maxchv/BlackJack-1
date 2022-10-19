package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

    private String name;
    public final static int LEVEL_ACE = 14;
    public final static int LEVEL_ACE_MORE10 = 1;
    public final static int LIMIT_FOR_ACE = 9;
    public final static int LIMIT_FOR_DEALER = 17;
    public final static int WINNER_SCORE = 21;
    public final static int GAME_OVER = 22;
    protected int indexCount = 2;

    Deck deck = new Deck();
    Players player = new Players();
    Players dealer = new Players();
    Scanner scanner = new Scanner(System.in);

    public Game(String name) {
        this.name = name;
    }

    public void start() {
        System.out.println("Welcome to casino!");
        deck.shuffle();
        initialisation();

        while (player.getScore() < WINNER_SCORE) {
            System.out.println("If you want Hit - enter h, if you want Stand - enter s");
            String scn = scanner.next();
            if (scn.equals("s")) {
                stop();
            }
            if (scn.equals("h")) {
                hit();
                System.out.println(Arrays.toString(player.cards));
                System.out.println("You score: " + player.getScore());
                if (player.getScore() == WINNER_SCORE) {
                    System.out.println("You win!");
                }
                if (player.getScore() > WINNER_SCORE) {
                    System.out.println("You loose!");
                    indexCount++;
                }
            }
        }
    }

    private void initialisation() {
        player.getCard(deck.deck[0]);
        player.getCard(deck.deck[1]);
        if (deck.deck[0].getLEVEL() == LEVEL_ACE && deck.deck[1].getWeightCount() > LIMIT_FOR_ACE) {
            deck.deck[0].setWeightCount(LEVEL_ACE_MORE10);
        }
        if (deck.deck[1].getLEVEL() == LEVEL_ACE && deck.deck[0].getWeightCount() > LIMIT_FOR_ACE) {
            deck.deck[1].setWeightCount(LEVEL_ACE_MORE10);
        }
        if (deck.deck[0].getLEVEL() == LEVEL_ACE && deck.deck[1].getLEVEL() == LEVEL_ACE) {
            deck.deck[0].setWeightCount(LEVEL_ACE_MORE10);
            deck.deck[1].setWeightCount(LEVEL_ACE_MORE10);
        }
        player.setScore(deck.deck[0].getWeightCount() + deck.deck[1].getWeightCount());

        System.out.println(Arrays.toString(player.cards));
        System.out.println("You score: " + player.getScore());
    }

    private void hit() {
        player.getCard(deck.deck[indexCount]);
        indexCount++;
        player.checkAce();
    }

    private void stop() {
        while (dealer.getScore() < LIMIT_FOR_DEALER) {
            dealer.getCard(deck.deck[indexCount]);
            dealer.checkAce();
            indexCount++;
        }
        System.out.println(Arrays.toString(dealer.cards));
        System.out.println("Dealer score: " + dealer.getScore());
        if (dealer.getScore() < player.getScore() || dealer.getScore() > WINNER_SCORE) {
            System.out.println("You win!");
        } else {
            System.out.println("You loose!");
        }
        player.setScore(GAME_OVER);
    }
}
