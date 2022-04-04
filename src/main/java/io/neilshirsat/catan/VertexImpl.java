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
    VERTEX_34(1, List.of(1,2), List.of(1)),
    VERTEX_35(1, List.of(1,2), List.of(1)),
    VERTEX_36(1, List.of(1,2), List.of(1)),
    VERTEX_37(1, List.of(1,2), List.of(1)),
    VERTEX_38(1, List.of(1,2), List.of(1)),
    VERTEX_39(1, List.of(1,2), List.of(1)),
    VERTEX_40(1, List.of(1,2), List.of(1)),
    VERTEX_41(1, List.of(1,2), List.of(1)),
    VERTEX_42(1, List.of(1,2), List.of(1)),
    VERTEX_43(1, List.of(1,2), List.of(1)),
    VERTEX_44(1, List.of(1,2), List.of(1)),
    VERTEX_45(1, List.of(1,2), List.of(1)),
    VERTEX_46(1, List.of(1,2), List.of(1)),
    VERTEX_47(1, List.of(1,2), List.of(1)),
    VERTEX_48(1, List.of(1,2), List.of(1)),
    VERTEX_49(1, List.of(1,2), List.of(1)),
    VERTEX_50(1, List.of(1,2), List.of(1)),
    VERTEX_51(1, List.of(1,2), List.of(1)),
    VERTEX_52(1, List.of(1,2), List.of(1)),
    VERTEX_53(1, List.of(1,2), List.of(1)),
    VERTEX_54(1, List.of(1,2), List.of(1));

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
}