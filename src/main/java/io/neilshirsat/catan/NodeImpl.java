package io.neilshirsat.catan;

import java.util.List;

//TODO: Finish Nodes
public enum NodeImpl implements Node {

    NODE_1(1, List.of(1,2,7,8,12,13), List.of(1,4,5,8,9,13)),

    NODE_2(2, List.of(3,4,8,9,14,15), List.of(1,4,5,8,9,13)),

    NODE_3(3, List.of(5,6,9,10,16,17), List.of(1,4,5,8,9,13)),

    NODE_4(4, List.of(11,12,19,20,25,26), List.of(1,4,5,8,9,13)),

    NODE_5(5, List.of(13,14,20,21,27,28), List.of(1,4,5,8,9,13)),

    NODE_6(6, List.of(15,16,21,22,29,30), List.of(1,4,5,8,9,13)),

    NODE_7(7, List.of(17,18,22,23,31,32), List.of(1,4,5,8,9,13)),

    NODE_8(8, List.of(24,25,34,35,40,41), List.of(1,4,5,8,9,13)),

    NODE_9(9, List.of(26,27,35,36,42,43), List.of(1,4,5,8,9,13)),

    NODE_10(10, List.of(28,29,36,37,44,45), List.of(1,4,5,8,9,13)),

    NODE_11(11, List.of(30,31,37,38,46,47), List.of(1,4,5,8,9,13)),

    NODE_12(12, List.of(32,33,38,39,48,49), List.of(1,4,5,8,9,13)),

    NODE_13(13, List.of(41,42,50,51,55,56), List.of(1,4,5,8,9,13)),

    NODE_14(14, List.of(), List.of(1,4,5,8,9,13)),

    NODE_15(15, List.of(), List.of(1,4,5,8,9,13)),

    NODE_16(16, List.of(), List.of(1,4,5,8,9,13)),

    NODE_17(17, List.of(), List.of(1,4,5,8,9,13)),

    NODE_18(18, List.of(), List.of(1,4,5,8,9,13)),

    NODE_19(19, List.of(), List.of(1,4,5,8,9,13));

    int nodeId;

    List<Integer> edges;

    List<Integer> verticies;

    NodeImpl(
            int nodeId,
            List<Integer> edges,
            List<Integer> verticies
    ) {
        this.nodeId = nodeId;
        this.edges = edges;
        this.verticies = verticies;
    }

    ResourceType resourceType;

    public enum ResourceType {
        WOOD,
        WOOL,
        GRAIN,
        ORE,
        BRICK;
    }

}
