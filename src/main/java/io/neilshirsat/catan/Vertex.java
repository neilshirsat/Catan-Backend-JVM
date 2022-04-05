package io.neilshirsat.catan;

import java.util.List;

public interface Vertex {

    List<Integer> getAdjacentNodes();

    List<Integer> getAdjacentEdges();

    boolean isPortVertex();

    boolean canBuildPort();

    boolean canBuiltSettlement();

    boolean canBuildCity();

    boolean canAddSettlement();



}
