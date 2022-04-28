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

    private TreeMap<Shop, Boolean> canBuyFromShop;

    private int victoryPoints;

    private int amountResourceCards;

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

    private int amountRoads;

    private int amountSettlements;

    private int amountCities;


    public int getAmountRoads() {
        return amountRoads;
    }

    public void setAmountRoads(int amountRoads) {
        this.amountRoads += amountRoads;
    }

    public int getAmountSettlements() {
        return amountSettlements;
    }

    public void setAmountSettlements(int amountSettlements) {
        this.amountSettlements += amountSettlements;
    }

    public int getAmountCities() {
        return amountCities;
    }

    public void setAmountCities(int amountCities) {
        this.amountCities += amountCities;
    }

    public int getAmountResourceCards() {
        amountResourceCards = 0;
        for (Map.Entry<ResourceType, Integer> k : getDeck().entrySet()) {
            amountResourceCards+=k.getValue();
        }
        return amountResourceCards;
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

    public static void resetLargestArmy() {
        Player largestArmy = null;
        int largestArmyCount = 3;
        for (Player p : getAllPlayers()) {
            if (p.getDevelopmentCards().get(DevelopmentCards.KNIGHT)>=largestArmyCount) {
                largestArmyCount = p.getDevelopmentCards().get(DevelopmentCards.KNIGHT);
                largestArmy = p;
            }
        }
        for (Player p : getAllPlayers()) {
            if (p.equals(largestArmy)) {
                p.setSpecialCards(Map.of(SpecialCards.LARGEST_ARMY,1));
            }
            else p.setSpecialCards(Map.of(SpecialCards.LARGEST_ARMY,0));
        }
    }

    public static Player getPlayer(int playerId) {
        return switch (playerId) {
            case 1 -> PLAYER_1;
            case 2 -> PLAYER_2;
            case 3 -> PLAYER_3;
            case 4 -> PLAYER_4;
            default -> throw new IllegalStateException("Unexpected value: " + playerId);
        };
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

    public static boolean verifyTrade(String passcode, Map<ResourceType, Integer> player2Outgoing) {
        boolean b = false;
        for (Player player : getAllPlayers()) {
            if (player.getPasscode().equals(passcode)) {
                for (Map.Entry<ResourceType, Integer> k : player2Outgoing.entrySet()) {
                    if (player.getDeck().get(k.getKey()) > k.getValue()) {
                        b = true;
                    } else return false;
                }
            }
        }
        return b;
    }

    public enum Shop {
        ROAD,
        SETTLEMENT,
        CITY,
        DEVELOPMENT_CARD,
    }
    public TreeMap<Shop, Boolean> canBuyFromShop() {
        TreeMap<Shop, Boolean> map = new TreeMap<>();
        map.put(Shop.ROAD,false);
        map.put(Shop.SETTLEMENT,false);
        map.put(Shop.CITY,false);
        map.put(Shop.DEVELOPMENT_CARD,false);

        if (this.getDeck().get(ResourceType.LUMBER)>=1&&this.getDeck().get(ResourceType.BRICK)>=1) {
            map.put(Shop.ROAD,true);
        }
        if (this.getDeck().get(ResourceType.LUMBER)>=1&&this.getDeck().get(ResourceType.BRICK)>=1&&this.getDeck().get(ResourceType.WHEAT)>=1&&this.getDeck().get(ResourceType.WOOL)>=1) {
            map.put(Shop.SETTLEMENT,true);
        }
        if (this.getDeck().get(ResourceType.ORE)>=3&&this.getDeck().get(ResourceType.WHEAT)>=2) {
            map.put(Shop.CITY, true);
        }
        if (this.getDeck().get(ResourceType.ORE)>=1&&this.getDeck().get(ResourceType.WHEAT)>=1&&this.getDeck().get(ResourceType.WOOL)>=1) {
            map.put(Shop.DEVELOPMENT_CARD, true);
        }
        return map;
    }
    public boolean canBuyFromShop(Shop shop) {
        for (Map.Entry<Shop, Boolean> k : canBuyFromShop().entrySet()) {
            if (k.getKey().equals(shop)) {
                return k.getValue();
            }
        }
        return false;
    }

    //MAKE SURE TO CHECK IF CAN BUY FROM SHOP
    public void purchase(Shop shop) {
        switch (shop) {
            case ROAD -> {
                getDeck().put(ResourceType.LUMBER,getDeck().get(ResourceType.LUMBER)-1);
                getDeck().put(ResourceType.BRICK,getDeck().get(ResourceType.BRICK)-1);
                ResourceType.LUMBER.setAmountLeft(1);
                ResourceType.BRICK.setAmountLeft(1);
                setAmountRoads(1);
                setVictoryPoints(1);
            }
            case SETTLEMENT -> {
                getDeck().put(ResourceType.LUMBER,getDeck().get(ResourceType.LUMBER)-1);
                getDeck().put(ResourceType.BRICK,getDeck().get(ResourceType.BRICK)-1);
                getDeck().put(ResourceType.WOOL,getDeck().get(ResourceType.WOOL)-1);
                getDeck().put(ResourceType.WHEAT,getDeck().get(ResourceType.WHEAT)-1);
                ResourceType.LUMBER.setAmountLeft(1);
                ResourceType.BRICK.setAmountLeft(1);
                ResourceType.WOOL.setAmountLeft(1);
                ResourceType.WHEAT.setAmountLeft(1);
                setAmountSettlements(1);
            }
            case CITY -> {
                getDeck().put(ResourceType.WHEAT,getDeck().get(ResourceType.WHEAT)-2);
                getDeck().put(ResourceType.ORE,getDeck().get(ResourceType.ORE)-3);
                ResourceType.WHEAT.setAmountLeft(2);
                ResourceType.ORE.setAmountLeft(3);
                setAmountCities(1);
            }
            case DEVELOPMENT_CARD -> {
                if (!DevelopmentCards.getDeck().isEmpty()) {
                    getDeck().put(ResourceType.WHEAT, getDeck().get(ResourceType.WHEAT) - 1);
                    getDeck().put(ResourceType.ORE, getDeck().get(ResourceType.ORE) - 1);
                    getDeck().put(ResourceType.WOOL, getDeck().get(ResourceType.WOOL) - 1);
                    ResourceType.WHEAT.setAmountLeft(1);
                    ResourceType.ORE.setAmountLeft(1);
                    ResourceType.WOOL.setAmountLeft(1);
                    setDevelopmentCards(Map.of(DevelopmentCards.deck.pop(), 1));
                }
            }
        }
    }





}
