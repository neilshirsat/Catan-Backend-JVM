package io.neilshirsat.catan;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.neilshirsat.catan.NodeImpl.*;
import static io.neilshirsat.catan.VertexImpl.*;

public enum EdgeImpl implements Edge {

    EDGE_1(1, List.of(VERTEX_1, VERTEX_4), List.of(NODE_1), List.of(2, 7)),
    EDGE_2(2, List.of(VERTEX_1, VERTEX_5), List.of(NODE_1), List.of(1, 3, 8)),
    EDGE_3(3, List.of(VERTEX_2, VERTEX_5), List.of(NODE_2), List.of(2, 4, 8)),
    EDGE_4(4, List.of(VERTEX_2, VERTEX_6), List.of(NODE_2), List.of(3, 5, 9)),
    EDGE_5(5, List.of(VERTEX_3, VERTEX_6), List.of(NODE_3), List.of(4, 6, 9)),
    EDGE_6(6, List.of(VERTEX_3, VERTEX_7), List.of(NODE_3), List.of(5, 10)),
    EDGE_7(7, List.of(VERTEX_4, VERTEX_8), List.of(NODE_1), List.of(1, 11, 12)),
    EDGE_8(8, List.of(VERTEX_2, VERTEX_9), List.of(NODE_1, NODE_2), List.of(2, 3, 13, 14)),
    EDGE_9(9, List.of(VERTEX_6, VERTEX_10), List.of(NODE_2, NODE_3), List.of(4, 5, 15, 16)),
    EDGE_10(10, List.of(VERTEX_7, VERTEX_11), List.of(NODE_3), List.of(6, 7, 18)),
    EDGE_11(11, List.of(VERTEX_8, VERTEX_12), List.of(NODE_4), List.of(7, 12, 19)),
    EDGE_12(12, List.of(VERTEX_8, VERTEX_13), List.of(NODE_1, NODE_4), List.of(7, 11, 13, 20)),
    EDGE_13(13, List.of(VERTEX_9, VERTEX_13), List.of(NODE_1, NODE_5), List.of(8, 12, 14, 20)),
    EDGE_14(14, List.of(VERTEX_9, VERTEX_14), List.of(NODE_2, NODE_5), List.of(8, 13, 15, 21)),
    EDGE_15(15, List.of(VERTEX_10, VERTEX_14), List.of(NODE_2, NODE_6), List.of(9, 14, 16, 21)),
    EDGE_16(16, List.of(VERTEX_10, VERTEX_15), List.of(NODE_3, NODE_6), List.of(9, 15, 17, 22)),
    EDGE_17(17, List.of(VERTEX_11, VERTEX_15), List.of(NODE_3, NODE_7), List.of(10, 16, 18, 22)),
    EDGE_18(18, List.of(VERTEX_11, VERTEX_16), List.of(NODE_7), List.of(10, 17, 23)),
    EDGE_19(19, List.of(VERTEX_12, VERTEX_17), List.of(NODE_4), List.of(11, 24, 25)),
    EDGE_20(20, List.of(VERTEX_13, VERTEX_18), List.of(NODE_4, NODE_5), List.of(12, 13, 26, 27)),
    EDGE_21(21, List.of(VERTEX_14, VERTEX_19), List.of(NODE_5, NODE_6), List.of(14, 15, 28, 29)),
    EDGE_22(22, List.of(VERTEX_15, VERTEX_20), List.of(NODE_6, NODE_7), List.of(16, 17, 30, 31)),
    EDGE_23(23, List.of(VERTEX_16, VERTEX_21), List.of(NODE_7), List.of(18, 32, 33)),
    EDGE_24(24, List.of(VERTEX_17, VERTEX_22), List.of(NODE_8), List.of(19, 25, 34)),
    EDGE_25(25, List.of(VERTEX_17, VERTEX_23), List.of(NODE_4, NODE_8), List.of(19, 24, 26, 34)),
    EDGE_26(26, List.of(VERTEX_18, VERTEX_23), List.of(NODE_4, NODE_9), List.of(20, 25, 27, 34)),
    EDGE_27(27, List.of(VERTEX_18, VERTEX_24), List.of(NODE_5, NODE_9), List.of(20, 26, 28, 35)),
    EDGE_28(28, List.of(VERTEX_19, VERTEX_24), List.of(NODE_5, NODE_10), List.of(21, 27, 29, 36)),
    EDGE_29(29, List.of(VERTEX_19, VERTEX_25), List.of(NODE_6, NODE_10), List.of(21, 28, 30, 37)),
    EDGE_30(30, List.of(VERTEX_20, VERTEX_25), List.of(NODE_6, NODE_11), List.of(22, 29, 31, 37)),
    EDGE_31(31, List.of(VERTEX_20, VERTEX_26), List.of(NODE_7, NODE_11), List.of(22, 30, 32, 38)),
    EDGE_32(32, List.of(VERTEX_21, VERTEX_26), List.of(NODE_7, NODE_12), List.of(23, 31, 33, 38)),
    EDGE_33(33, List.of(VERTEX_21, VERTEX_27), List.of(NODE_12), List.of(23, 32, 39)),
    EDGE_34(34, List.of(VERTEX_22, VERTEX_28), List.of(NODE_8), List.of(24, 40)),
    EDGE_35(35, List.of(VERTEX_23, VERTEX_29), List.of(NODE_8, NODE_9), List.of(25, 26, 41, 42)),
    EDGE_36(36, List.of(VERTEX_24, VERTEX_30), List.of(NODE_9, NODE_10), List.of(27, 28, 43, 44)),
    EDGE_37(37, List.of(VERTEX_25, VERTEX_31), List.of(NODE_10, NODE_11), List.of(29, 30, 45, 46)),
    EDGE_38(38, List.of(VERTEX_26, VERTEX_32), List.of(NODE_11, NODE_12), List.of(31, 32, 47, 48)),
    EDGE_39(39, List.of(VERTEX_27, VERTEX_33), List.of(NODE_12), List.of(33, 49)),
    EDGE_40(40, List.of(VERTEX_28, VERTEX_34), List.of(NODE_8), List.of(34, 41, 50)),
    EDGE_41(41, List.of(VERTEX_29, VERTEX_34), List.of(NODE_8, NODE_13), List.of(35, 40, 42, 50)),
    EDGE_42(42, List.of(VERTEX_29, VERTEX_35), List.of(NODE_9, NODE_13), List.of(35, 41, 43, 51)),
    EDGE_43(43, List.of(VERTEX_30, VERTEX_35), List.of(NODE_9, NODE_14), List.of(36, 42, 44, 51)),
    EDGE_44(44, List.of(VERTEX_30, VERTEX_36), List.of(NODE_10, NODE_14), List.of(36, 43, 45, 52)),
    EDGE_45(45, List.of(VERTEX_31, VERTEX_36), List.of(NODE_10, NODE_15), List.of(37, 44, 46, 52)),
    EDGE_46(46, List.of(VERTEX_31, VERTEX_37), List.of(NODE_11, NODE_15), List.of(37, 45, 47, 53)),
    EDGE_47(47, List.of(VERTEX_32, VERTEX_37), List.of(NODE_11, NODE_16), List.of(38, 46, 48, 53)),
    EDGE_48(48, List.of(VERTEX_32, VERTEX_38), List.of(NODE_12, NODE_16), List.of(38, 47, 49, 54)),
    EDGE_49(49, List.of(VERTEX_33, VERTEX_38), List.of(NODE_12), List.of(39, 48, 54)),
    EDGE_50(50, List.of(VERTEX_34, VERTEX_39), List.of(NODE_13), List.of(40, 41, 55)),
    EDGE_51(51, List.of(VERTEX_35, VERTEX_40), List.of(NODE_13, NODE_14), List.of(42, 43, 56, 57)),
    EDGE_52(52, List.of(VERTEX_36, VERTEX_41), List.of(NODE_14, NODE_15), List.of(44, 45, 58, 59)),
    EDGE_53(53, List.of(VERTEX_37, VERTEX_42), List.of(NODE_15, NODE_16), List.of(46, 47, 60, 61)),
    EDGE_54(54, List.of(VERTEX_38, VERTEX_43), List.of(NODE_16), List.of(48, 49, 62)),
    EDGE_55(55, List.of(VERTEX_39, VERTEX_44), List.of(NODE_13), List.of(50, 56, 63)),
    EDGE_56(56, List.of(VERTEX_40, VERTEX_44), List.of(NODE_13, NODE_17), List.of(51, 55, 57, 63)),
    EDGE_57(57, List.of(VERTEX_40, VERTEX_45), List.of(NODE_14, NODE_17), List.of(51, 56, 58, 64)),
    EDGE_58(58, List.of(VERTEX_41, VERTEX_45), List.of(NODE_14, NODE_18), List.of(52, 57, 59, 64)),
    EDGE_59(59, List.of(VERTEX_41, VERTEX_46), List.of(NODE_15, NODE_18), List.of(52, 58, 60, 65)),
    EDGE_60(60, List.of(VERTEX_42, VERTEX_46), List.of(NODE_15, NODE_19), List.of(53, 59, 61, 65)),
    EDGE_61(61, List.of(VERTEX_42, VERTEX_47), List.of(NODE_16, NODE_19), List.of(53, 60, 62, 66)),
    EDGE_62(62, List.of(VERTEX_43, VERTEX_47), List.of(NODE_16), List.of(54, 61, 66)),
    EDGE_63(63, List.of(VERTEX_44, VERTEX_48), List.of(NODE_17), List.of(55, 56, 67)),
    EDGE_64(64, List.of(VERTEX_45, VERTEX_49), List.of(NODE_17, NODE_18), List.of(57, 58, 68, 69)),
    EDGE_65(65, List.of(VERTEX_46, VERTEX_50), List.of(NODE_18, NODE_19), List.of(59, 60, 70, 71)),
    EDGE_66(66, List.of(VERTEX_47, VERTEX_51), List.of(NODE_19), List.of(61, 62, 72)),
    EDGE_67(67, List.of(VERTEX_48, VERTEX_52), List.of(NODE_17), List.of(63, 68)),
    EDGE_68(68, List.of(VERTEX_49, VERTEX_52), List.of(NODE_17), List.of(64, 69)),
    EDGE_69(69, List.of(VERTEX_49, VERTEX_52), List.of(NODE_18), List.of(64, 70)),
    EDGE_70(70, List.of(VERTEX_50, VERTEX_53), List.of(NODE_18), List.of(65, 71)),
    EDGE_71(71, List.of(VERTEX_50, VERTEX_54), List.of(NODE_19), List.of(65, 72)),
    EDGE_72(72, List.of(VERTEX_51, VERTEX_54), List.of(NODE_19), List.of(66, 71));

    private static final List<Integer> allEdges = List.of(
            1,
            2,
            3,
            4,
            5,
            6,
            7,
            8,
            9,
            10,
            11,
            12,
            13,
            14,
            15,
            16,
            17,
            18,
            19,
            20,
            21,
            22,
            23,
            24,
            25,
            26,
            27,
            28,
            29,
            30,
            31,
            32,
            33,
            34,
            35,
            36,
            37,
            38,
            39,
            40,
            41,
            42,
            43,
            44,
            45,
            46,
            47,
            48,
            49,
            50,
            51,
            52,
            53,
            54,
            55,
            56,
            57,
            58,
            59,
            60,
            61,
            62,
            63,
            64,
            65,
            66,
            67,
            68,
            69,
            70,
            71,
            72
    );

    public int edgeId;

    private List<Node> surroundingNodes;

    private List<Integer> connectedEdges;

    private List<Vertex> connectedVertices;

    private boolean isRoad = false;

    private Player controlledPlayer = Player.DEFAULT;

    private int roadLengthCache = 0;

    private boolean[] visited;

    EdgeImpl(
            int edgeId, List<Vertex> connectedVertices, List<Node> surroundingNodes, List<Integer> connectedEdges
    ) {
        this.edgeId = edgeId;
        this.surroundingNodes = surroundingNodes;
        this.connectedEdges = connectedEdges;
        this.connectedVertices = connectedVertices;
    }

    public static Edge getEdge(int edgeId) {
        return switch (edgeId) {
            case 1 -> EDGE_1;
            case 2 -> EDGE_2;
            case 3 -> EDGE_3;
            case 4 -> EDGE_4;
            case 5 -> EDGE_5;
            case 6 -> EDGE_6;
            case 7 -> EDGE_7;
            case 8 -> EDGE_8;
            case 9 -> EDGE_9;
            case 10 -> EDGE_10;
            case 11 -> EDGE_11;
            case 12 -> EDGE_12;
            case 13 -> EDGE_13;
            case 14 -> EDGE_14;
            case 15 -> EDGE_15;
            case 16 -> EDGE_16;
            case 17 -> EDGE_17;
            case 18 -> EDGE_18;
            case 19 -> EDGE_19;
            case 20 -> EDGE_20;
            case 21 -> EDGE_21;
            case 22 -> EDGE_22;
            case 23 -> EDGE_23;
            case 24 -> EDGE_24;
            case 25 -> EDGE_25;
            case 26 -> EDGE_26;
            case 27 -> EDGE_27;
            case 28 -> EDGE_28;
            case 29 -> EDGE_29;
            case 30 -> EDGE_30;
            case 31 -> EDGE_31;
            case 32 -> EDGE_32;
            case 33 -> EDGE_33;
            case 34 -> EDGE_34;
            case 35 -> EDGE_35;
            case 36 -> EDGE_36;
            case 37 -> EDGE_37;
            case 38 -> EDGE_38;
            case 39 -> EDGE_39;
            case 40 -> EDGE_40;
            case 41 -> EDGE_41;
            case 42 -> EDGE_42;
            case 43 -> EDGE_43;
            case 44 -> EDGE_44;
            case 45 -> EDGE_45;
            case 46 -> EDGE_46;
            case 47 -> EDGE_47;
            case 48 -> EDGE_48;
            case 49 -> EDGE_49;
            case 50 -> EDGE_50;
            case 51 -> EDGE_51;
            case 52 -> EDGE_52;
            case 53 -> EDGE_53;
            case 54 -> EDGE_54;
            case 55 -> EDGE_55;
            case 56 -> EDGE_56;
            case 57 -> EDGE_57;
            case 58 -> EDGE_58;
            case 59 -> EDGE_59;
            case 60 -> EDGE_60;
            case 61 -> EDGE_61;
            case 62 -> EDGE_62;
            case 63 -> EDGE_63;
            case 64 -> EDGE_64;
            case 65 -> EDGE_65;
            case 66 -> EDGE_66;
            case 67 -> EDGE_67;
            case 68 -> EDGE_68;
            case 69 -> EDGE_69;
            case 70 -> EDGE_70;
            case 71 -> EDGE_71;
            case 72 -> EDGE_72;
            default -> null;
        };
    }

    public int getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(int edgeId) {
        this.edgeId = edgeId;
    }

    @Override
    public List<Node> getSurroundingNodes() {
        return surroundingNodes;
    }

    public void setSurroundingNodes(List<Node> surroundingNodes) {
        this.surroundingNodes = surroundingNodes;
    }

    @Override
    public List<Integer> getConnectedEdges() {
        return connectedEdges;
    }

    public void setConnectedEdges(List<Integer> connectedEdges) {
        this.connectedEdges = connectedEdges;
    }

    @Override
    public List<Vertex> getConnectedVertices() {
        return connectedVertices;
    }

    public void setConnectedVertices(List<Vertex> connectedVertices) {
        this.connectedVertices = connectedVertices;
    }

    public boolean isRoad() {
        return isRoad;
    }

    public void setRoad(boolean road) {
        isRoad = road;
    }

    public Player getControlledPlayer() {
        return controlledPlayer;
    }

    public void setControlledPlayer(Player controlledPlayer) {
        this.controlledPlayer = controlledPlayer;
    }

    public int getRoadLengthCache() {
        return roadLengthCache;
    }

    public void setRoadLengthCache(int roadLengthCache) {
        this.roadLengthCache = roadLengthCache;
    }

    public boolean[] getVisited() {
        return visited;
    }

    public void setVisited(boolean[] visited) {
        this.visited = visited;
    }

    /**
     * Checks whether the Connected Edges Are Valid Based on whether
     * the Player Controls the Adjacent Edges
     *
     * @return Whether the Edge Contains Valid Connected Edges
     */
    public boolean containsValidConnectedEdges() {
        for (int edgeId : this.connectedEdges) {
            EdgeImpl edge = (EdgeImpl) EdgeImpl.getEdge(edgeId);
            if (edge.controlledPlayer == this.controlledPlayer && edge.isRoad && this.isRoad)
                return true;
        }
        return false;
    }

    /**
     * Reset the Length of the Road
     */
    public void resetRoadLength() {
        if (!this.isRoad) {
            roadLengthCache = 0;
            return;
        }
        if (!this.containsValidConnectedEdges()) {
            roadLengthCache = 0;
            return;
        }
        visited = new boolean[72];
        Arrays.fill(visited, false);
        DFS(this, 1);
        visited = null;
    }

    /**
     * DFS The Graph of Edges in order to find and update the Longest Road
     *
     * @param edge
     * @param roadLength
     */
    private void DFS(EdgeImpl edge, int roadLength) {
        visited[edge.edgeId] = true;

        if (!edge.isRoad) {
            return;
        }

        if (!edge.containsValidConnectedEdges()) {
            this.roadLengthCache = Math.max(this.roadLengthCache, roadLength);
            return;
        }

        connectedEdges = edge.connectedEdges;
        for (int connectedEdgeId : connectedEdges) {
            EdgeImpl connectedEdge = (EdgeImpl) EdgeImpl.getEdge(connectedEdgeId);
            if (connectedEdge.controlledPlayer == this.controlledPlayer) {
                if (!visited[connectedEdgeId]) {
                    DFS(connectedEdge, roadLength + 1);
                }
            }
        }

    }

    public static Edge LongestRoad;

    public static void resetLongestRoad() {
        int longestRoad = 4;
        Edge longestEdge = null;
        for (int edgeId: EdgeImpl.allEdges) {
            EdgeImpl edge = (EdgeImpl) EdgeImpl.getEdge(edgeId);
            if (edge.roadLengthCache > longestRoad) {
                longestRoad = edge.roadLengthCache;
                longestEdge = edge;
            }
        }
        LongestRoad.getControlledPlayer().setSpecialCards(Map.of(SpecialCards.LONGEST_ROAD,0));
        LongestRoad = longestEdge;
        LongestRoad.getControlledPlayer().setSpecialCards(Map.of(SpecialCards.LONGEST_ROAD,1));

    }

}

