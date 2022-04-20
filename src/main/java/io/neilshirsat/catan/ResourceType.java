package io.neilshirsat.catan;

public enum ResourceType {
    ORE,
    WOOL,
    WHEAT,
    LUMBER,
    BRICK,
    NONE;

    //TODO NEED TO MAKE SURE THAT THERE IS A UNIDIRECTIONAL DATA FLOW TO AMOUNT LEFT
    //TODO IF THERE IS AN ERROR WITH THE AMOUNT OF CARDS LEFT YOU NEED TO CHECK THE MUTATIONS TO THIS THROUGHOUT THE CODEBASE
    private int amountLeft = 19;

    public void setAmountLeft(int count) {
        if (this.amountLeft+count>=0)
        this.amountLeft+=count;
    }

    //TODO getAmountLeft has to be >=0
    private int getAmountLeft() {
        return amountLeft;
    }

    public boolean isEmpty() {
        return !(amountLeft > 0);
    }

}
