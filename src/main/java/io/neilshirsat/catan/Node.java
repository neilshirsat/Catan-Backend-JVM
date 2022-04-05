package io.neilshirsat.catan;

import java.util.List;

public interface Node {

    int getNE();

    int getE();

    int getSE();

    int getNW();

    int getW();

    int getSW();

    boolean isBorderNode();

    boolean hasRobber();

    List<Node> spiralTraversal();
}
