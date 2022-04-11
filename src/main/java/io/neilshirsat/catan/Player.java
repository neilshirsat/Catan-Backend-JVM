package io.neilshirsat.catan;


import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public enum Player {

    PLAYER_1,
    PLAYER_2,
    PLAYER_3,
    PLAYER_4;

    public static Player DEFAULT = null;

    private String playerName;

    private String passcode;

    private Map<ResourceType, Integer> deck;

    private Map<DevelopmentCards, Integer> developmentCards;

    private Map<SpecialCards, Integer> specialCards;

    private int victoryPoints;

    public static int amountPlayers;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public Map<ResourceType, Integer> getDeck() {
        return deck;
    }

    public void setDeck(Map<ResourceType, Integer> deck) {
        this.deck = deck;
    }

    public Map<DevelopmentCards, Integer> getDevelopmentCards() {
        return developmentCards;
    }

    public void setDevelopmentCards(Map<DevelopmentCards, Integer> developmentCards) {
        this.developmentCards = developmentCards;
    }

    public Map<SpecialCards, Integer> getSpecialCards() {
        return specialCards;
    }

    public void setSpecialCards(Map<SpecialCards, Integer> specialCards) {
        this.specialCards = specialCards;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public static List<Player> getAllPlayers() {
        if (amountPlayers == 2)
            return List.of(PLAYER_1, PLAYER_2);
        if (amountPlayers == 3)
            return List.of(PLAYER_1, PLAYER_2, PLAYER_3);
        if (amountPlayers == 4)
            return List.of(PLAYER_1, PLAYER_2, PLAYER_3, PLAYER_4);
        throw new RuntimeException("AMOUNT PLAYERS NOT DEFINED");
    };

    public static void initializeAllPlayers() {

        //Set All Deck Cards, Development Cards, and Special Cards
        for (Player e: getAllPlayers()) {

            e.deck = new TreeMap<>();
            e.developmentCards = new TreeMap<>();
            e.specialCards = new TreeMap<>();

            e.deck.put(ResourceType.BRICK, 0);
            e.deck.put(ResourceType.LUMBER, 0);
            e.deck.put(ResourceType.ORE, 0);
            e.deck.put(ResourceType.WHEAT, 0);
            e.deck.put(ResourceType.WOOL, 0);

            e.developmentCards.put(DevelopmentCards.VICTORY_POINT, 0);
            e.developmentCards.put(DevelopmentCards.KNIGHT, 0);
            e.developmentCards.put(DevelopmentCards.MONOPOLY, 0);
            e.developmentCards.put(DevelopmentCards.YEAR_OF_PLENTY, 0);
            e.developmentCards.put(DevelopmentCards.ROAD_BUILDING, 0);

            e.specialCards.put(SpecialCards.LARGEST_ARMY, 0);
            e.specialCards.put(SpecialCards.LONGEST_ROAD, 0);
        }

    }

    public static void tradeCards(
            Map<ResourceType, Integer> player1Outgoing,
            Player player1,
            Map<ResourceType, Integer> player2Outgoing,
            Player player2
    ) {

        //Handel Player 1
        for (Map.Entry<ResourceType, Integer> k: player1Outgoing.entrySet()) {
            player1.deck.put(k.getKey(), player1.deck.get(k.getKey()) - k.getValue());
        }
        for (Map.Entry<ResourceType, Integer> k: player1Outgoing.entrySet()) {
            player2.deck.put(k.getKey(), player2.deck.get(k.getKey()) + k.getValue());
        }

        //Handel Player 2
        for (Map.Entry<ResourceType, Integer> k: player2Outgoing.entrySet()) {
            player2.deck.put(k.getKey(), player2.deck.get(k.getKey()) - k.getValue());
        }
        for (Map.Entry<ResourceType, Integer> k: player2Outgoing.entrySet()) {
            player1.deck.put(k.getKey(), player1.deck.get(k.getKey()) + k.getValue());
        }
    }

}
