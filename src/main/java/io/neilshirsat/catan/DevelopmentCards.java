package io.neilshirsat.catan;

import java.util.Collections;
import java.util.Stack;

public enum DevelopmentCards {
    VICTORY_POINT,
    KNIGHT,
    MONOPOLY,
    YEAR_OF_PLENTY,
    ROAD_BUILDING;

    public static Stack<DevelopmentCards> deck = new Stack<>();

    static {
        deck.push(DevelopmentCards.KNIGHT);
        deck.push(DevelopmentCards.KNIGHT);
        deck.push(DevelopmentCards.KNIGHT);
        deck.push(DevelopmentCards.KNIGHT);
        deck.push(DevelopmentCards.KNIGHT);
        deck.push(DevelopmentCards.KNIGHT);
        deck.push(DevelopmentCards.KNIGHT);
        deck.push(DevelopmentCards.KNIGHT);
        deck.push(DevelopmentCards.KNIGHT);
        deck.push(DevelopmentCards.KNIGHT);
        deck.push(DevelopmentCards.KNIGHT);
        deck.push(DevelopmentCards.KNIGHT);
        deck.push(DevelopmentCards.KNIGHT);
        deck.push(DevelopmentCards.KNIGHT);
        deck.push(DevelopmentCards.VICTORY_POINT);
        deck.push(DevelopmentCards.VICTORY_POINT);
        deck.push(DevelopmentCards.VICTORY_POINT);
        deck.push(DevelopmentCards.VICTORY_POINT);
        deck.push(DevelopmentCards.VICTORY_POINT);
        deck.push(DevelopmentCards.MONOPOLY);
        deck.push(DevelopmentCards.MONOPOLY);
        deck.push(DevelopmentCards.YEAR_OF_PLENTY);
        deck.push(DevelopmentCards.YEAR_OF_PLENTY);
        deck.push(DevelopmentCards.ROAD_BUILDING);
        deck.push(DevelopmentCards.ROAD_BUILDING);
        Collections.shuffle(deck);
    }

    public static Stack<DevelopmentCards> getDeck() {
        return deck;
    }



}
