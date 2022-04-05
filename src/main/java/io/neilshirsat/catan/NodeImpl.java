package io.neilshirsat.catan;

import java.util.List;

public enum NodeImpl implements Node {

    NODE_1(1,-1, 2, 5,-1,-1,4,true),

    NODE_2(2,-1, 3,6, -1,1, 5,true),

    NODE_3(3,-1, -1, 7, -1, 2, 6,true),

    NODE_4(4,1,5,9,-1,-1,8,true),

    NODE_5(5,2,6,10,1,4,9,false),

    NODE_6(6,3,7,11,2,5,10,false),

    NODE_7(7,-1,-1,12,3,6,11,true),

    NODE_8(8,4,9,13,-1,-1,-1,true),

    NODE_9(9,5,10,14,4,8,13,false),

    NODE_10(10,6,11,15,5,9,14,false),

    NODE_11(11,7,12,16,6,10,15,false),

    NODE_12(12,-1,-1,-1,7,11,16,true),

    NODE_13(13,9,14,17,8,-1,-1,true),

    NODE_14(14,10,15,18,9,13,17,false),

    NODE_15(15,11,16,19,10,14,18,false),

    NODE_16(16,12,-1,-1,11,15,19,true),

    NODE_17(17,14,18,-1,13,-1,-1,true),

    NODE_18(18,15,19,-1,14,17,-1,true),

    NODE_19(19,16,-1,-1,15,18,-1,true);


    public int id;

    private int NE, E, SE, NW, W, SW;

    private boolean isBorderNode, hasRobber;

    NodeImpl(
            int id, int NE,int E, int SE, int NW, int W, int SW, boolean isBorderNode
    ) {
        this.id = id;
        this.NE = NE;
        this.E = E;
        this.SE = SE;
        this.NW = NW;
        this.W = W;
        this.SW = SW;
        this.isBorderNode = isBorderNode;
        hasRobber = false;
    }

    @Override
    public int getNE() {
        return NE;
    }

    @Override
    public int getE() {
        return E;
    }

    @Override
    public int getSE() {
        return SE;
    }

    @Override
    public int getNW() {
        return NW;
    }

    @Override
    public int getW() {
        return W;
    }

    @Override
    public int getSW() {
        return SW;
    }

    @Override
    public boolean isBorderNode() {
        return isBorderNode;
    }
    @Override
    public boolean hasRobber() {
        return hasRobber;
    }

    @Override
    public List<Node> spiralTraversal() {
        return List.of(NODE_1,NODE_4,NODE_8,NODE_13,NODE_17,NODE_18,NODE_19,NODE_16,NODE_12,NODE_7,NODE_3,NODE_2,NODE_5,NODE_9,NODE_14,NODE_15,NODE_11,NODE_6,NODE_10);
    }
}
