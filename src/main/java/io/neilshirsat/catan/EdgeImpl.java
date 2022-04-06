package io.neilshirsat.catan;

import java.util.List;

public enum EdgeImpl implements Edge {

    EDGE_1(1, List.of(1,4),List.of(1), List.of(2,7)),
    EDGE_2(2, List.of(1,5),List.of(1), List.of(1,3,8)),
    EDGE_3(3, List.of(2,5),List.of(2), List.of(2,4,8)),
    EDGE_4(4, List.of(2,6),List.of(2), List.of(3,5,9)),
    EDGE_5(5, List.of(3,6),List.of(3), List.of(4,6,9)),
    EDGE_6(6, List.of(3,7),List.of(3), List.of(5,10)),
    EDGE_7(7, List.of(4,8),List.of(1), List.of(1,11,12)),
    EDGE_8(8, List.of(2,9),List.of(1,2), List.of(2,3,13,14)),
    EDGE_9(9, List.of(6,10),List.of(2,3), List.of(4,5,15,16)),
    EDGE_10(10, List.of(7,11),List.of(3), List.of(6,7,18)),
    EDGE_11(11, List.of(8,12),List.of(4), List.of(7,12,19)),
    EDGE_12(12, List.of(8,13),List.of(1,4), List.of(7,11,13,20)),
    EDGE_13(13, List.of(9,13),List.of(1,5), List.of(8,12,14,20)),
    EDGE_14(14, List.of(9,14),List.of(2,5), List.of(8,13,15,21)),
    EDGE_15(15, List.of(10,14),List.of(2,6), List.of(9,14,16,21)),
    EDGE_16(16, List.of(10,15),List.of(3,6), List.of(9,15,17,22)),
    EDGE_17(17, List.of(11,15),List.of(3,7), List.of(10,16,18,22)),
    EDGE_18(18, List.of(11,16),List.of(7), List.of(10,17,23)),
    EDGE_19(19, List.of(12,17),List.of(4), List.of(11,24,25)),
    EDGE_20(20, List.of(13,18),List.of(4,5), List.of(12,13,26,27)),
    EDGE_21(21, List.of(14,19),List.of(5,6), List.of(14,15,28,29)),
    EDGE_22(22, List.of(15,20),List.of(6,7), List.of(16,17,30,31)),
    EDGE_23(23, List.of(16,21),List.of(7), List.of(18,32,33)),
    EDGE_24(24, List.of(17,22),List.of(8), List.of(19,25,34)),
    EDGE_25(25, List.of(17,23),List.of(4,8), List.of(19,24,26,34)),
    EDGE_26(26, List.of(18,23),List.of(4,9), List.of(20,25,27,34)),
    EDGE_27(27, List.of(18,24),List.of(5,9), List.of(20,26,28,35)),
    EDGE_28(28, List.of(19,24),List.of(5,10), List.of(21,27,29,36)),
    EDGE_29(29, List.of(19,25),List.of(6,10), List.of(21,28,30,37)),
    EDGE_30(30, List.of(20,25),List.of(6,11), List.of(22,29,31,37)),
    EDGE_31(31, List.of(20,26),List.of(7,11), List.of(22,30,32,38)),
    EDGE_32(32, List.of(21,26),List.of(7,12), List.of(23,31,33,38)),
    EDGE_33(33, List.of(21,27),List.of(12), List.of(23,32,39)),
    EDGE_34(34, List.of(22,28),List.of(8), List.of(24,40)),
    EDGE_35(35, List.of(23,29),List.of(8,9), List.of(25,26,41,42)),
    EDGE_36(36, List.of(24,30),List.of(9,10), List.of(27,28,43,44)),
    EDGE_37(37, List.of(25,31),List.of(10,11), List.of(29,30,45,46)),
    EDGE_38(38, List.of(26,32),List.of(11,12), List.of(31,32,47,48)),
    EDGE_39(39, List.of(27,33),List.of(12), List.of(33,49)),
    EDGE_40(40, List.of(28,34),List.of(8), List.of(34,41,50)),
    EDGE_41(41, List.of(29,34),List.of(8,13), List.of(35,40,42,50)),
    EDGE_42(42, List.of(29,35),List.of(9,13), List.of(35,41,43,51)),
    EDGE_43(43, List.of(30,35),List.of(9,14), List.of(36,42,44,51)),
    EDGE_44(44, List.of(30,36),List.of(10,14), List.of(36,43,45,52)),
    EDGE_45(45, List.of(31,36),List.of(10,15), List.of(37,44,46,52)),
    EDGE_46(46, List.of(31,37),List.of(11,15), List.of(37,45,47,53)),
    EDGE_47(47, List.of(32,37),List.of(11,16), List.of(38,46,48,53)),
    EDGE_48(48, List.of(32,38),List.of(12,16), List.of(38,47,49,54)),
    EDGE_49(49, List.of(33,38),List.of(12), List.of(39,48,54)),
    EDGE_50(50, List.of(34,39),List.of(13), List.of(40,41,55)),
    EDGE_51(51, List.of(35,40),List.of(13,14), List.of(42,43,56,57)),
    EDGE_52(52, List.of(36,41),List.of(14,15), List.of(44,45,58,59)),
    EDGE_53(53, List.of(37,42),List.of(15,16), List.of(46,47,60,61)),
    EDGE_54(54, List.of(38,43),List.of(16), List.of(48,49,62)),
    EDGE_55(55, List.of(39,44),List.of(13), List.of(50,56,63)),
    EDGE_56(56, List.of(40,44),List.of(13,17), List.of(51,55,57,63)),
    EDGE_57(57, List.of(40,45),List.of(14,17), List.of(51,56,58,64)),
    EDGE_58(58, List.of(41,45),List.of(14,18), List.of(52,57,59,64)),
    EDGE_59(59, List.of(41,46),List.of(15,18), List.of(52,58,60,65)),
    EDGE_60(60, List.of(42,46),List.of(15,19), List.of(53,59,61,65)),
    EDGE_61(61, List.of(42,47),List.of(16,19), List.of(53,60,62,66)),
    EDGE_62(62, List.of(43,47),List.of(16), List.of(54,61,66)),
    EDGE_63(63, List.of(44,48),List.of(17), List.of(55,56,67)),
    EDGE_64(64, List.of(45,49),List.of(17,18), List.of(57,58,68,69)),
    EDGE_65(65, List.of(46,50),List.of(18,19), List.of(59,60,70,71)),
    EDGE_66(66, List.of(47,51),List.of(19), List.of(61,62,72)),
    EDGE_67(67, List.of(48,52),List.of(17), List.of(63,68)),
    EDGE_68(68, List.of(49,52),List.of(17), List.of(64,69)),
    EDGE_69(69, List.of(49,52),List.of(18), List.of(64,70)),
    EDGE_70(70, List.of(50,53),List.of(18), List.of(65,71)),
    EDGE_71(71, List.of(50,54),List.of(19), List.of(65,72)),
    EDGE_72(72, List.of(51,54),List.of(19), List.of(66,71));

    public int getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(int edgeId) {
        this.edgeId = edgeId;
    }

    public void setSurroundingNodes(List<Integer> surroundingNodes) {
        this.surroundingNodes = surroundingNodes;
    }

    public void setConnectedEdges(List<Integer> connectedEdges) {
        this.connectedEdges = connectedEdges;
    }

    public List<Integer> getSurroundingVertex() {
        return surroundingVertex;
    }

    public void setSurroundingVertex(List<Integer> surroundingVertex) {
        this.surroundingVertex = surroundingVertex;
    }

    public int edgeId;

    private List<Integer> surroundingNodes;

    private List<Integer> connectedEdges;

    private List<Integer> surroundingVertex;

    @Override
    public List<Integer> getSurroundingNodes() {
        return surroundingNodes;
    }

    @Override
    public List<Integer> getConnectedEdges() {
        return connectedEdges;
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

    private boolean isRoad;

    private Player controlledPlayer;

    public int calculateRoadLength(int road) {

        return -1;
    }

    private boolean isLongestRoad() {
        //TODO CHECK IF TRUE
        return false;
    }

    EdgeImpl(
            int edgeId, List<Integer> surroundingVertex, List<Integer> surroundingNodes,List<Integer> connectedEdges
    ) {
        this.edgeId = edgeId;
        this.surroundingNodes = surroundingNodes;
        this.connectedEdges = connectedEdges;
        this.surroundingVertex = surroundingVertex;
    }

}

