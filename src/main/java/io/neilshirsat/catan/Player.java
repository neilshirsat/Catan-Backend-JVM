package io.neilshirsat.catan;

import java.util.Map;

//TODO ADD STATE AND SETUP FOR PLAYERS
public class Player {

    public static Player DEFAULT = null;

    private String playerName;

    private Map<ResourceType, Integer> deck;

    private Map<DevelopmentCards, Integer> developmentCards;

    private Map<SpecialCards, Integer> specialCards;

    private int victoryPoints;

}
