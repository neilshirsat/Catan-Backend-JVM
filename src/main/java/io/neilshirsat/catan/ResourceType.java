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
        if (this.amountLeft+count>=0)
        this.amountLeft+=count;
    }


    public int getAmountLeft() {
        return amountLeft;
    }

    public boolean isEmpty() {
        return !(amountLeft > 0);
    }

}
