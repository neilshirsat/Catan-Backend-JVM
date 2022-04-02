package io.neilshirsat.catan;

public interface Vertex {

    List<Node> getAdjacentNodes();

    List<Edge> getAdjacentEdges();

    List<Node> getAdjacentNodes();

    boolean isPortVertex();

    boolean canBuildPort();

    boolean canBuiltSettlement();

    boolean canBuildCity();

    boolean canAddSettlement();



}
