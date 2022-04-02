package io.neilshirsat.catan;

public interface Node {

    Node getNE();

    Node getE();

    Node getSE();

    Node getNW();

    Node getW();

    Node getSW();

    boolean isBorderNode();

}
