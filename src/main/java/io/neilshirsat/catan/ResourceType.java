package io.neilshirsat.catan;

public enum ResourceType {
    ORE,
    WOOL,
    WHEAT,
    LUMBER,
    BRICK,
    NONE;

    private int amountLeft = 19;

    public void setAmountLeft(int count) {
        this.amountLeft+=count;
    }

    //TODO getAmountLeft has to be >=0
    private int getAmountLeft() {
        return amountLeft;
    }

}
