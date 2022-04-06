package io.neilshirsat.catan;

import java.util.Arrays;
import java.util.List;

public interface Edge {

    int getEdgeId();

    List<Node> getSurroundingNodes();

    List<Integer> getConnectedEdges();

    List<Vertex> getConnectedVertices();

    boolean isRoad();

    void setRoad(boolean road);

    Player getControlledPlayer();

    int getRoadLengthCache();

    void setRoadLengthCache(int roadLengthCache);

    boolean containsValidConnectedEdges();

    void resetRoadLength();

}
