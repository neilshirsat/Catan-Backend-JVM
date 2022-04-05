package io.neilshirsat.catan;

import java.util.List;

public interface Edge {

    List<Integer> getSurroundingNodes();

    List<Integer> getConnectedEdges();

}
