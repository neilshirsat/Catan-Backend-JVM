package io.neilshirsat.catan;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface Vertex {

    int getVertxId();

    List<Integer> getConnectedEdges();

    List<Integer> getConnectedNodes();

    Player getControlledPlayer();

    void setControlledPlayer(Player controlledPlayer);

    void buildSettlement(Player player);

    void buildCity(Player player);

    boolean isPortVertex();

    VertexImpl.VertexType getVertexType();

    boolean canBuildCity(Player player);

    boolean canBuildSettlement(Player player);

}
