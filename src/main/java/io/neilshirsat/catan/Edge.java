package io.neilshirsat.catan;

import java.util.List;

public interface Edge {

    Vertex getVertexA();

    Vertex getVertexB();

    List<Node> getSurroundingNodes();

    List<Edge> getConnectedEdges();

}
