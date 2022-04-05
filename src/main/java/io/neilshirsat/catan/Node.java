package io.neilshirsat.catan;

public interface Node {

    int getNE();

    int getE();

    int getSE();

    int getNW();

    int getW();

    int getSW();

    boolean isBorderNode();

}
