package io.neilshirsat.catan;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.*;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Player {

    PLAYER_1(1),
    PLAYER_2(2),
    PLAYER_3(3),
    PLAYER_4(4);

    Player(int id) {
        this.id = id;
    }

    public static Player DEFAULT = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    private String playerName;

    private String passcode;

    private Map<ResourceType, Integer> deck;

    private Map<DevelopmentCards, Integer> developmentCards;

    private Map<SpecialCards, Integer> specialCards;

    private TreeMap<Shop, Boolean> canBuyFromShop;

    private int victoryPoints;

    private int amountResourceCards;

    private int secretVictoryPoints;

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

    private int armySize;

    public int getArmySize() {
        return armySize;
    }

    public void setArmySize(int size) {
        this.armySize+=size;
    }


    public int getSecretVictoryPoints() {
        return secretVictoryPoints;
    }

    public void setSecretVictoryPoints(int secretVictoryPoints) {
        this.secretVictoryPoints = secretVictoryPoints;
    }

    private int amountRoads=19;

    private int amountSettlements=5;

    private int amountCities=4;

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
            if (p.getArmySize() >= largestArmyCount) {
                largestArmyCount = p.getArmySize();
                largestArmy = p;
            }
        }
        for (Player p : getAllPlayers()) {
            if (p.equals(largestArmy)) {
                p.setSpecialCards(Map.of(SpecialCards.LARGEST_ARMY, 1));
            } else p.setSpecialCards(Map.of(SpecialCards.LARGEST_ARMY, 0));
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

    public void purchase(Shop shop) {
        switch (shop) {
            case ROAD -> {
                if (this.getAmountRoads()>0) {
                    getDeck().put(ResourceType.LUMBER, getDeck().get(ResourceType.LUMBER) - 1);
                    getDeck().put(ResourceType.BRICK, getDeck().get(ResourceType.BRICK) - 1);
                    ResourceType.LUMBER.setAmountLeft(1);
                    ResourceType.BRICK.setAmountLeft(1);
                    setAmountRoads(-1);

                }
            }
            case SETTLEMENT -> {
                if (this.getAmountSettlements()>0) {
                    getDeck().put(ResourceType.LUMBER, getDeck().get(ResourceType.LUMBER) - 1);
                    getDeck().put(ResourceType.BRICK, getDeck().get(ResourceType.BRICK) - 1);
                    getDeck().put(ResourceType.WOOL, getDeck().get(ResourceType.WOOL) - 1);
                    getDeck().put(ResourceType.WHEAT, getDeck().get(ResourceType.WHEAT) - 1);
                    ResourceType.LUMBER.setAmountLeft(1);
                    ResourceType.BRICK.setAmountLeft(1);
                    ResourceType.WOOL.setAmountLeft(1);
                    ResourceType.WHEAT.setAmountLeft(1);
                    setAmountSettlements(-1);
                    setVictoryPoints(1);
                }
            }
            case CITY -> {
                if (this.getAmountCities()>0) {
                    getDeck().put(ResourceType.WHEAT, getDeck().get(ResourceType.WHEAT) - 2);
                    getDeck().put(ResourceType.ORE, getDeck().get(ResourceType.ORE) - 3);
                    ResourceType.WHEAT.setAmountLeft(2);
                    ResourceType.ORE.setAmountLeft(3);
                    setAmountCities(-1);
                    setVictoryPoints(1);
                }
            }
            case DEVELOPMENT_CARD -> {
                if (!DevelopmentCards.getDeck().isEmpty()) {
                    getDeck().put(ResourceType.WHEAT, getDeck().get(ResourceType.WHEAT) - 1);
                    getDeck().put(ResourceType.ORE, getDeck().get(ResourceType.ORE) - 1);
                    getDeck().put(ResourceType.WOOL, getDeck().get(ResourceType.WOOL) - 1);
                    ResourceType.WHEAT.setAmountLeft(1);
                    ResourceType.ORE.setAmountLeft(1);
                    ResourceType.WOOL.setAmountLeft(1);
                    DevelopmentCards developmentCards = DevelopmentCards.deck.pop();
                    if (developmentCards.equals(DevelopmentCards.VICTORY_POINT)) {
                        this.setSecretVictoryPoints(this.getSecretVictoryPoints()+1);
                    }
                    setDevelopmentCards(Map.of(developmentCards, 1));
                }
            }
        }
    }

    @JsonIgnore
    private VertexImpl previousBuiltNode;

    @JsonIgnore
    public VertexImpl getPreviousBuiltNode() {
        return previousBuiltNode;
    }

    @JsonIgnore
    public void setPreviousBuiltNode(VertexImpl previousBuiltNode) {
        this.previousBuiltNode = previousBuiltNode;
    }

    public enum COLOR {
        BLACK,
        WHITE,
        YELLOW,
        ORANGE,
        GREEN,
        BLUE,
        PURPLE
    }

    public COLOR getColor() {
        return color;
    }

    public void setColor(COLOR color) {
        this.color = color;
    }

    private COLOR color;

    public static ResourceType robAnotherPlayer(Player robbed, Player robbing) {
        List<ResourceType> keysAsArray = new ArrayList<ResourceType>(robbed.getDeck().keySet());
        List<ResourceType> keys = new ArrayList<>();
        for (ResourceType e: keysAsArray) {
            if (robbed.getDeck().get(e) > 0) {
                keys.add(e);
            }
        }
        if (keys.size() > 0) {
            ResourceType key = keys.get((int)(Math.random()*keys.size()));
            robbed.getDeck().put(key,robbed.getDeck().get(key)-1);
            robbing.getDeck().put(key,robbing.getDeck().get(key)+1);
            return key;
        }
        return ResourceType.NONE;
    }
}
