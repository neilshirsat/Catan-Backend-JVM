package io.neilshirsat.catan;

import java.util.List;

public enum EdgeImpl implements Edge {

    EDGE_1(),
    EDGE_2,
    EDGE_3,
    EDGE_4,
    EDGE_5,
    EDGE_6,
    EDGE_7,
    EDGE_8,
    EDGE_9,
    EDGE_10,
    EDGE_11,
    EDGE_12,
    EDGE_13,
    EDGE_14,
    EDGE_15,
    EDGE_16,
    EDGE_17,
    EDGE_18,
    EDGE_19,
    EDGE_20,
    EDGE_21,
    EDGE_22,
    EDGE_23,
    EDGE_24,
    EDGE_25,
    EDGE_26,
    EDGE_27,
    EDGE_28,
    EDGE_29,
    EDGE_30,
    EDGE_31,
    EDGE_32,
    EDGE_33,
    EDGE_34,
    EDGE_35,
    EDGE_36,
    EDGE_37,
    EDGE_38,
    EDGE_39,
    EDGE_40,
    EDGE_41,
    EDGE_42,
    EDGE_43,
    EDGE_44,
    EDGE_45,
    EDGE_46,
    EDGE_47,
    EDGE_48,
    EDGE_49,
    EDGE_50,
    EDGE_51,
    EDGE_52,
    EDGE_53,
    EDGE_54,
    EDGE_55,
    EDGE_56,
    EDGE_57,
    EDGE_58,
    EDGE_59,
    EDGE_60,
    EDGE_61,
    EDGE_62,
    EDGE_63,
    EDGE_64,
    EDGE_65,
    EDGE_66,
    EDGE_67,
    EDGE_68,
    EDGE_69,
    EDGE_70,
    EDGE_71,
    EDGE_72,


    public int edgeId;

    private Vertex VertexA;

    private Vertex VertexB;

    private List<Node> surroundingNodes;

    private List<Edge> connectedEdges;

    EdgeImpl(
            int edgeId,Vertex VertexA,Vertex VertexB, List<Node> surroundingNodes,List<Edge> connectedEdges
    ) {
        this.edgeId = edgeId;
        this.VertexA = VertexA;
        this.VertexB = VertexB;
        this.surroundingNodes = surroundingNodes;
        this.connectedEdges = connectedEdges;
    }
    @Override
    public Vertex getVertexA() {
        return null;
    }

    @Override
    public Vertex getVertexB() {
        return null;
    }

    @Override
    public List<Node> getSurroundingNodes() {
        return null;
    }

    @Override
    public List<Edge> getConnectedEdges() {
        return null;
    }
}
