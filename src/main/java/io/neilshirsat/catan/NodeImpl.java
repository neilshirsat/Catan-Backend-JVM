package io.neilshirsat.catan;

import java.util.*;

public enum NodeImpl implements Node {

    NODE_1(
            1,
            -1,
            -1,
            -1,
            2,
            4,
            5,
            List.of(1,2,7,8,12,13),
            List.of(1,4,5,8,9,13)
    ),

    NODE_2(
            2,
            -1,
            -1,
            1,
            3,
            5,
            6,
            List.of(3,4,8,9,14,15),
            List.of(1,4,5,8,9,13)
    ),

    NODE_3(
            3,
            -1,
            -1,
            2,
            -1,
            6,
            7,
            List.of(5,6,9,10,16,17),
            List.of(1,4,5,8,9,13)
    ),

    NODE_4(
            4,
            -1,
            1,
            -1,
            5,
            8,
            9,
            List.of(11,12,19,20,25,26),
            List.of(1,4,5,8,9,13)
    ),

    NODE_5(
            5,
            1,
            2,
            4,
            6,
            9,
            10,
            List.of(13,14,20,21,27,28),
            List.of(1,4,5,8,9,13)
    ),

    NODE_6(
            6,
            2,
            3,
            5,
            7,
            10,
            11,
            List.of(15,16,21,22,29,30),
            List.of(1,4,5,8,9,13)
    ),

    NODE_7(
            7,
            3,
            -1,
            6,
            -1,
            11,
            12,
            List.of(17,18,22,23,31,32),
            List.of(1,4,5,8,9,13)
    ),

    NODE_8(
            8,
            -1,
            4,
            -1,
            9,
            -1,
            13,
            List.of(24,25,34,35,40,41),
            List.of(1,4,5,8,9,13)
    ),

    NODE_9(
            9,
            4,
            5,
            8,
            10,
            13,
            14,
            List.of(26,27,35,36,42,43),
            List.of(1,4,5,8,9,13)
    ),

    NODE_10(
            10,
            5,
            6,
            9,
            11,
            14,
            15,
            List.of(28,29,36,37,44,45),
            List.of(1,4,5,8,9,13)
    ),

    NODE_11(
            11,
            6,
            7,
            10,
            12,
            15,
            16,
            List.of(30,31,37,38,46,47),
            List.of(1,4,5,8,9,13)
    ),

    NODE_12(
            12,
            7,
            -1,
            11,
            -1,
            16,
            -1,
            List.of(32,33,38,39,48,49),
            List.of(1,4,5,8,9,13)
    ),

    NODE_13(
            13,
            8,
            9,
            -1,
            14,
            -1,
            17,
            List.of(41,42,50,51,55,56),
            List.of(1,4,5,8,9,13)),

    NODE_14(
            14,
            9,
            10,
            13,
            15,
            17,
            18,
            List.of(43,44,51,52,57,58),
            List.of(1,4,5,8,9,13)
    ),

    NODE_15(
            15,
            10,
            11,
            14,
            16,
            18,
            19,
            List.of(45,46,52,53,59,60),
            List.of(1,4,5,8,9,13)
    ),

    NODE_16(
            16,
            11,
            12,
            15,
            -1,
            19,
            -1,
            List.of(47,48,52,53,61,62),
            List.of(1,4,5,8,9,13)
    ),

    NODE_17(
            17,
            13,
            14,
            -1,
            18,
            -1,
            -1,
            List.of(56,57,63,64,67,68),
            List.of(1,4,5,8,9,13)
    ),

    NODE_18(
            18,
            14,
            15,
            17,
            19,
            -1,
            -1,
            List.of(58,59,64,65,69,70),
            List.of(1,4,5,8,9,13)
    ),

    NODE_19(
            19,
            15,
            16,
            18,
            -1,
            -1,
            -1,
            List.of(60,61,65,66,71,72),
            List.of(1,4,5,8,9,13)
    );

    private int nodeId;

    private List<Integer> edges;

    private List<Integer> vertices;

    private int NW;

    private int NE;

    private int SW;

    private int SE;

    private int W;

    private int E;

    private boolean hasRobber = false;

    NodeImpl(
            int nodeId,
            int NW,
            int NE,
            int W,
            int E,
            int SW,
            int SE,
            List<Integer> edges,
            List<Integer> vertices
    ) {
        this.nodeId = nodeId;
        this.edges = edges;
        this.vertices = vertices;
        this.NW = NW;
        this.NE = NE;
        this.W = W;
        this.E = E;
        this.SW = SW;
        this.SE = SE;
    }

    private Resource resource;

    private NumberPieces numberPieces;

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public List<Integer> getEdges() {
        return edges;
    }

    public void setEdges(List<Integer> edges) {
        this.edges = edges;
    }

    public List<Integer> getVertices() {
        return vertices;
    }

    public void setVertices(List<Integer> vertices) {
        this.vertices = vertices;
    }

    public int getNW() {
        return NW;
    }

    public void setNW(int NW) {
        this.NW = NW;
    }

    public int getNE() {
        return NE;
    }

    public void setNE(int NE) {
        this.NE = NE;
    }

    public int getSW() {
        return SW;
    }

    public void setSW(int SW) {
        this.SW = SW;
    }

    public int getSE() {
        return SE;
    }

    public void setSE(int SE) {
        this.SE = SE;
    }

    public int getW() {
        return W;
    }

    public void setW(int w) {
        W = w;
    }

    public int getE() {
        return E;
    }

    public void setE(int e) {
        E = e;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public NumberPieces getNumberPieces() {
        return numberPieces;
    }

    public void setNumberPieces(NumberPieces numberPieces) {
        this.numberPieces = numberPieces;
    }

    public boolean isHasRobber() {
        return hasRobber;
    }

    public void setHasRobber(boolean hasRobber) {
        this.hasRobber = hasRobber;
    }

    public boolean isBorderNode() {
        return NW == -1 || NE == -1 || W == -1 || E == -1 || SW == -1 || SE == -1;
    }

    public boolean isInnerNode() {
        return !isBorderNode();
    }

    public static Node getNode(int nodeId) {
        return switch (nodeId) {
            case 1 -> NODE_1;
            case 2 -> NODE_2;
            case 3 -> NODE_3;
            case 4 -> NODE_4;
            case 5 -> NODE_5;
            case 6 -> NODE_6;
            case 7 -> NODE_7;
            case 8 -> NODE_8;
            case 9 -> NODE_9;
            case 10 -> NODE_10;
            case 11 -> NODE_11;
            case 12 -> NODE_12;
            case 13 -> NODE_13;
            case 14 -> NODE_14;
            case 15 -> NODE_15;
            case 16 -> NODE_16;
            case 17 -> NODE_17;
            case 18 -> NODE_18;
            case 19 -> NODE_19;
            default -> null;
        };
    }

    private static List<NodeImpl> getTransversal() {
        return List.of(
                NODE_1,
                NODE_4,
                NODE_8,
                NODE_13,
                NODE_17,
                NODE_18,
                NODE_19,
                NODE_16,
                NODE_17,
                NODE_12,
                NODE_7,
                NODE_3,
                NODE_2,
                NODE_5,
                NODE_9,
                NODE_14,
                NODE_15,
                NODE_6,
                NODE_10
        );
    }

    private static List<Resource> getResourceTypes() {
        List<Resource> resources = new ArrayList<>(List.of(
                Resource.HILLS,
                Resource.HILLS,
                Resource.HILLS,
                Resource.FOREST,
                Resource.FOREST,
                Resource.FOREST,
                Resource.MOUNTAINS,
                Resource.MOUNTAINS,
                Resource.MOUNTAINS,
                Resource.FIELDS,
                Resource.FIELDS,
                Resource.FIELDS,
                Resource.PASTURE,
                Resource.PASTURE,
                Resource.PASTURE,
                Resource.DESERT
        ));
        Collections.shuffle(resources);
        return resources;
    }

    public static NodeImpl robber = null;

    public static void changeRobber(int nodeId, Player robbed, Player robbing) {
        NodeImpl node = (NodeImpl) NodeImpl.getNode(nodeId);
        node.hasRobber = true;

        robber.hasRobber = false;
        robber = node;

        int random = (int) (Math.random() * robbed.getDeck().size());

        //Needs selected Player from User Interface
        for (Map.Entry<ResourceType,Integer> list : robbed.getDeck().entrySet()) {

        }
    }

    /**
     * Prepare the Core Board Setup of the Game
     *
     * Should Update all the Nodes to Reflect the Resources
     * and Number Pieces that they contain
     */
    public static void prepareNodeSetup() {
        List<NodeImpl> nodes = NodeImpl.getTransversal();
        List<Resource> resources = NodeImpl.getResourceTypes();
        List<NumberPieces> numberPieces = NumberPieces.getNumberPiecesOrdered();

        for (int i = 0; i < 19; i++) {
            NodeImpl node = nodes.get(i);
            node.setResource(resources.get(i));
            if (node.getResource() != Resource.DESERT) {
                node.setNumberPieces(numberPieces.get(i));
            }
            else {
                robber = node;
                node.hasRobber = true;
                node.setNumberPieces(NumberPieces.NONE);
            }
        }

        EdgeImpl.resetLongestRoad();
    }

    /**
     * Gets the Nodes with the speicified number on the
     * Tile Card
     *
     * @param number
     * @return
     */
    public static List<NodeImpl> getNodesWithDice(int number) {
        List<NodeImpl> nodes = new ArrayList<>();
        for (NodeImpl e: NodeImpl.getTransversal()) {
            if (e.numberPieces.getValue() == number) {
                nodes.add(e);
            }
        }
        return nodes;
    }

    /**
     * Increments the Amount of a Particular Type of Resource
     * Card that the Player Has
     */
    public static void receiveCards(List<NodeImpl> deck) {
        for (NodeImpl node: deck) {

            if (node.hasRobber) {
                continue;
            }

            List<Integer> vertices = node.vertices;
            for (int vertexId: vertices) {

                VertexImpl vertex = VertexImpl.getVertex(vertexId);

                if (vertex.hasSettlement()) {
                    vertex.getControlledPlayer().getDeck().put(
                            node.resource.getResourceType(),
                            vertex.getControlledPlayer().getDeck().get(node.resource.getResourceType()) + 1
                    );
                    node.resource.getResourceType().setAmountLeft(-1);
                }

                if (vertex.hasCity()) {
                    vertex.getControlledPlayer().getDeck().put(
                            node.resource.getResourceType(),
                            vertex.getControlledPlayer().getDeck().get(node.resource.getResourceType()) + 2
                    );
                    node.resource.getResourceType().setAmountLeft(-2);
                }
            }

        }

    }

}
