package io.neilshirsat.catan;

import java.util.List;

public interface Vertex {

    int getVertxId();

    List<Integer> getAdjacentNodes();

    List<Integer> getAdjacentEdges();

    boolean isPortVertex();

    boolean canBuildRoad();

    boolean canBuildSettlement();

    boolean canBuildCity();

    boolean canAddSettlement();

    boolean hasSettlement();

    boolean hasCity();



}
