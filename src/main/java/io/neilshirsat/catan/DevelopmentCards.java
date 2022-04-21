package io.neilshirsat.catan;

public enum DevelopmentCards {
    VICTORY_POINT,
    KNIGHT,
    MONOPOLY,
    YEAR_OF_PLENTY,
    ROAD_BUILDING;

    private int amountLeft() {
        return switch(this) {
            case VICTORY_POINT -> 5;
            case KNIGHT -> 14;
            case MONOPOLY, ROAD_BUILDING, YEAR_OF_PLENTY -> 2;
        };
    }
}
