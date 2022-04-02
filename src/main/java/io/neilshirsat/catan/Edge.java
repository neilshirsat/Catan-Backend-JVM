package io.neilshirsat.catan;

public interface Edge {

    Vertex getVertexA();

    Vertex getVertexB();

    List<Node> getSurroundingEdges();

    List<Edge> getConnectedEdges();

}
