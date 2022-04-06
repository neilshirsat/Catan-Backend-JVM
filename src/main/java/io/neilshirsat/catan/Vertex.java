package io.neilshirsat.catan;

import java.util.List;

public interface Vertex {

    int getVertxId();

    List<Integer> getConnectedEdges();

    List<Integer> getConnectedNodes();

    Player getControlledPlayer();

    void setControlledPlayer(Player controlledPlayer);

    void buildSettlement();

    void buildCity();

    boolean isPortVertex();

    boolean canBuildRoad();

    boolean canBuildSettlement();

}
