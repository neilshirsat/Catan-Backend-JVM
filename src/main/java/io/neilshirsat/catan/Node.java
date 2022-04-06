package io.neilshirsat.catan;

import java.util.List;

public interface Node {

    int getNodeId();

    List<Integer> getEdges();

    List<Integer> getVertices();

    int getNW();

    int getNE();

    int getSW();

    int getSE();

    int getW();

    int getE();

    Resource getResource();

    NumberPieces getNumberPieces();

    boolean isBorderNode();

    boolean isInnerNode();

}
