package io.neilshirsat.catan;

import java.util.ArrayList;
import java.util.List;

public enum VertexImpl implements Vertex {

    VERTEX_1(1, List.of(1,2), List.of(1)),
    VERTEX_2(2, List.of(3,4), List.of(2)),
    VERTEX_3(3, List.of(5,6), List.of(3)),
    VERTEX_4(4, List.of(1,7), List.of(1)),
    VERTEX_5(5, List.of(2,3,8), List.of(1,2)),
    VERTEX_6(6, List.of(4,5,9), List.of(2,3)),
    VERTEX_7(7, List.of(6,10), List.of(3)),
    VERTEX_8(8, List.of(1,11,13), List.of(1,4)),
    VERTEX_9(9, List.of(8,13,14), List.of(1,2,5)),
    VERTEX_10(10, List.of(9,15,16), List.of(2,3,6)),
    VERTEX_11(11, List.of(10,17,18), List.of(3,7)),
    VERTEX_12(12, List.of(11,19), List.of(4)),
    VERTEX_13(13, List.of(12,13,20), List.of(1,4,5)),
    VERTEX_14(14, List.of(14,15,21), List.of(2,5,6)),
    VERTEX_15(15, List.of(16,17,22), List.of(3,6,7)),
    VERTEX_16(16, List.of(18,22), List.of(7)),
    VERTEX_17(17, List.of(19,24,25), List.of(4,8)),
    VERTEX_18(18, List.of(20,26,27), List.of(4,5,9)),
    VERTEX_19(19, List.of(21,28,29), List.of(5,6,10)),
    VERTEX_20(20, List.of(22,30,31), List.of(6,7,11)),
    VERTEX_21(21, List.of(23,32,33), List.of(7,12)),
    VERTEX_22(22, List.of(24,34), List.of(8)),
    VERTEX_23(23, List.of(25,26,35), List.of(4,8,9)),
    VERTEX_24(24, List.of(27,28,36), List.of(5,9,10)),
    VERTEX_25(25, List.of(29,30,37), List.of(6,10,11)),
    VERTEX_26(26, List.of(31,32,38), List.of(7,11,12)),
    VERTEX_27(27, List.of(33,39), List.of(12)),
    VERTEX_28(28, List.of(34,40), List.of(8)),
    VERTEX_29(29, List.of(35,41,42), List.of(8,9,13)),
    VERTEX_30(30, List.of(36,43,44), List.of(9,10,14)),
    VERTEX_31(31, List.of(37,45,46), List.of(10,11,15)),
    VERTEX_32(32, List.of(38,47,48), List.of(11,12,16)),
    VERTEX_33(33, List.of(39,49), List.of(12)),
    VERTEX_34(34, List.of(40,41,50), List.of(8,13)),
    VERTEX_35(35, List.of(42,43,51), List.of(9,13,14)),
    VERTEX_36(36, List.of(44,45,52), List.of(10,14,15)),
    VERTEX_37(37, List.of(46,47,53), List.of(11,15,16)),
    VERTEX_38(38, List.of(48,49,54), List.of(11,12,16)),
    VERTEX_39(39, List.of(50,55), List.of(13)),
    VERTEX_40(40, List.of(51,56,57), List.of(13,14,17)),
    VERTEX_41(41, List.of(52,58,59), List.of(14,15,18)),
    VERTEX_42(42, List.of(53,60,61), List.of(15,16,19)),
    VERTEX_43(43, List.of(54,62), List.of(16)),
    VERTEX_44(44, List.of(55,56,63), List.of(13,17)),
    VERTEX_45(45, List.of(57,58,64), List.of(14,17,18)),
    VERTEX_46(46, List.of(59,60,65), List.of(15,18,19)),
    VERTEX_47(47, List.of(61,62,66), List.of(16,19)),
    VERTEX_48(48, List.of(63,69), List.of(17)),
    VERTEX_49(49, List.of(64,68,69), List.of(17,18)),
    VERTEX_50(50, List.of(65,70,71), List.of(18,19)),
    VERTEX_51(51, List.of(66,72), List.of(19)),
    VERTEX_52(52, List.of(67,68), List.of(17)),
    VERTEX_53(53, List.of(69,70), List.of(18)),
    VERTEX_54(54, List.of(71,72), List.of(19));

    public int vertxId;

    private List<Integer> connectedEdges;

    private List<Integer> connectedNodes;

    VertexImpl(
            int vertxId,
            List<Integer> connectedEdges,
            List<Integer> connectedNodes
    ) {
        this.vertxId = vertxId;
        this.connectedEdges = connectedEdges;
        this.connectedNodes = connectedNodes;
    }

    @Override
    public List<Integer> getAdjacentEdges() {
        return connectedEdges;
    }

    @Override
    public List<Integer> getAdjacentNodes() {
        return connectedNodes;
    }

    @Override
    public boolean isPortVertex() {
        return false;
    }

    @Override
    public boolean canBuildPort() {
        return false;
    }

    @Override
    public boolean canBuiltSettlement() {
        return false;
    }

    @Override
    public boolean canBuildCity() {
        return false;
    }

    @Override
    public boolean canAddSettlement() {
        return false;
    }

    public boolean hasSettlement() {
        return false;
    }
}