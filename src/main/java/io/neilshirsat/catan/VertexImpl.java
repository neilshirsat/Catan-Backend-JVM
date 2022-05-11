package io.neilshirsat.catan;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public enum VertexImpl implements Vertex {

    VERTEX_1(1, List.of(1,2), List.of(1),null),
    VERTEX_2(2, List.of(3,4), List.of(2),null),
    VERTEX_3(3, List.of(5,6), List.of(3),null),
    VERTEX_4(4, List.of(1,7), List.of(1),null),
    VERTEX_5(5, List.of(2,3,8), List.of(1,2),null),
    VERTEX_6(6, List.of(4,5,9), List.of(2,3),null),
    VERTEX_7(7, List.of(6,10), List.of(3),null),
    VERTEX_8(8, List.of(1,11,13), List.of(1,4),null),
    VERTEX_9(9, List.of(8,13,14), List.of(1,2,5),null),
    VERTEX_10(10, List.of(9,15,16), List.of(2,3,6),null),
    VERTEX_11(11, List.of(10,17,18), List.of(3,7),null),
    VERTEX_12(12, List.of(11,19), List.of(4),null),
    VERTEX_13(13, List.of(12,13,20), List.of(1,4,5),null),
    VERTEX_14(14, List.of(14,15,21), List.of(2,5,6),null),
    VERTEX_15(15, List.of(16,17,22), List.of(3,6,7),null),
    VERTEX_16(16, List.of(18,22), List.of(7),null),
    VERTEX_17(17, List.of(19,24,25), List.of(4,8),null),
    VERTEX_18(18, List.of(20,26,27), List.of(4,5,9),null),
    VERTEX_19(19, List.of(21,28,29), List.of(5,6,10),null),
    VERTEX_20(20, List.of(22,30,31), List.of(6,7,11),null),
    VERTEX_21(21, List.of(23,32,33), List.of(7,12),null),
    VERTEX_22(22, List.of(24,34), List.of(8),null),
    VERTEX_23(23, List.of(25,26,35), List.of(4,8,9),null),
    VERTEX_24(24, List.of(27,28,36), List.of(5,9,10),null),
    VERTEX_25(25, List.of(29,30,37), List.of(6,10,11),null),
    VERTEX_26(26, List.of(31,32,38), List.of(7,11,12),null),
    VERTEX_27(27, List.of(33,39), List.of(12),null),
    VERTEX_28(28, List.of(34,40), List.of(8),null),
    VERTEX_29(29, List.of(35,41,42), List.of(8,9,13),null),
    VERTEX_30(30, List.of(36,43,44), List.of(9,10,14),null),
    VERTEX_31(31, List.of(37,45,46), List.of(10,11,15),null),
    VERTEX_32(32, List.of(38,47,48), List.of(11,12,16),null),
    VERTEX_33(33, List.of(39,49), List.of(12),null),
    VERTEX_34(34, List.of(40,41,50), List.of(8,13),null),
    VERTEX_35(35, List.of(42,43,51), List.of(9,13,14),null),
    VERTEX_36(36, List.of(44,45,52), List.of(10,14,15),null),
    VERTEX_37(37, List.of(46,47,53), List.of(11,15,16),null),
    VERTEX_38(38, List.of(48,49,54), List.of(11,12,16),null),
    VERTEX_39(39, List.of(50,55), List.of(13),null),
    VERTEX_40(40, List.of(51,56,57), List.of(13,14,17),null),
    VERTEX_41(41, List.of(52,58,59), List.of(14,15,18),null),
    VERTEX_42(42, List.of(53,60,61), List.of(15,16,19),null),
    VERTEX_43(43, List.of(54,62), List.of(16),null),
    VERTEX_44(44, List.of(55,56,63), List.of(13,17),null),
    VERTEX_45(45, List.of(57,58,64), List.of(14,17,18),null),
    VERTEX_46(46, List.of(59,60,65), List.of(15,18,19),null),
    VERTEX_47(47, List.of(61,62,66), List.of(16,19),null),
    VERTEX_48(48, List.of(63,69), List.of(17),null),
    VERTEX_49(49, List.of(64,68,69), List.of(17,18),null),
    VERTEX_50(50, List.of(65,70,71), List.of(18,19),null),
    VERTEX_51(51, List.of(66,72), List.of(19),null),
    VERTEX_52(52, List.of(67,68), List.of(17),null),
    VERTEX_53(53, List.of(69,70), List.of(18),null),
    VERTEX_54(54, List.of(71,72), List.of(19),null);

    public int vertxId;

    private List<Integer> connectedEdges;

    private List<Integer> connectedNodes;

    private Player controlledPlayer = Player.DEFAULT;

    private VertexType vertexType = VertexType.EMPTY;

    private Port port;

    VertexImpl(
            int vertxId,
            List<Integer> connectedEdges,
            List<Integer> connectedNodes,
            Port port
    ) {
        this.vertxId = vertxId;
        this.connectedEdges = connectedEdges;
        this.connectedNodes = connectedNodes;
        this.port = port;
    }

    public int getVertxId() {
        return vertxId;
    }

    public List<Integer> getConnectedEdges() {
        return connectedEdges;
    }

    public List<Integer> getConnectedNodes() {
        return connectedNodes;
    }

    public Player getControlledPlayer() {
        return controlledPlayer;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public Port getPort() {
        return port;
    }


    public enum VertexType {
        EMPTY,
        SETTLEMENT,
        CITY;
    }

    public boolean isEmpty() {
        return vertexType == VertexType.EMPTY;
    }

    public boolean hasSettlement() {
        return vertexType == VertexType.SETTLEMENT;
    }

    public boolean hasCity() {
        return vertexType == VertexType.CITY;
    }

    @Override
    public void buildSettlement(Player player) {
        this.controlledPlayer = player;
        this.vertexType = VertexType.SETTLEMENT;
    }

    @Override
    public void buildCity(Player player) {
        this.vertexType = VertexType.CITY;
    }

    public void setControlledPlayer(Player controlledPlayer) {
        this.controlledPlayer = controlledPlayer;
    }


    public enum Port {
        PORT_LUMBER(2, 1,ResourceType.LUMBER),
        PORT_BRICK(2,1,ResourceType.BRICK),
        PORT_WOOL(2,1,ResourceType.WOOL),
        PORT_WHEAT(2,1,ResourceType.WHEAT),
        PORT_ORE(2,1,ResourceType.ORE),
        PORT_RANDOM(3,1,null);

        private int giveResource;
        private int getResource;
        private ResourceType resourceType;


        public int getGiveResource() {
            return giveResource;
        }

        public int getGetResource() {
            return getResource;
        }

        public ResourceType getResourceType() {
            return resourceType;
        }

        Port (
                int giveResource,
                int getResource,
                ResourceType resourceType
        ) {
            this.giveResource = giveResource;
            this.getResource = getResource;
            this.resourceType = resourceType;
        }
    }

    @Override
    public boolean isPortVertex() {
        return List.of(1,2,4,6,11,12,16,17,27,33,34,39,43,47,48,50,52,53).contains(getVertxId());
    }

    public static List<Integer> PortVertices() {
        return List.of(1,2,4,6,11,12,16,17,27,33,34,39,43,47,48,50,52,53);
    }

    public static List<Port> getPortList() {
        List<Port> ports = new java.util.ArrayList<>(List.of(
                Port.PORT_LUMBER,
                Port.PORT_BRICK,
                Port.PORT_WOOL,
                Port.PORT_WHEAT,
                Port.PORT_ORE,
                Port.PORT_RANDOM,
                Port.PORT_RANDOM,
                Port.PORT_RANDOM,
                Port.PORT_RANDOM
        ));
        Collections.shuffle(ports);
        return ports;
    }

    public static void buildPorts() {
        List<Port> ports = getPortList();
        List<Integer> portVertices = PortVertices();
        for (int i = 0; i < 9; i++) {
            getVertex(portVertices.get(i*2)).setPort(ports.get(i));
            getVertex(portVertices.get(i*2+1)).setPort(ports.get(i));
        }
    }

    @Override
    public VertexType getVertexType() {
        return vertexType;
    }

    @Override
    public boolean canBuildCity(Player player) {
        return hasSettlement()&&this.getControlledPlayer() == player;
    }

    @Override
    public boolean canBuildSettlement(Player player) {
        if (this.vertexType == VertexType.EMPTY) {
            return false;
        }
        boolean checkEdges = false;

        for (int edgeId : getConnectedEdges()) {
            if (EdgeImpl.getEdge(edgeId).isRoad() && EdgeImpl.getEdge(edgeId).getControlledPlayer() == this.getControlledPlayer()) {
                checkEdges = true;
                for (Vertex v : EdgeImpl.getEdge(edgeId).getConnectedVertices()) {
                    if (v.getVertexType() != VertexType.EMPTY) {
                        return false;
                    }
                }
            }
        }
        return checkEdges;
    }

    public boolean canBuildSettlementFirstTurn(Player player) {
        for (int edgeId : connectedEdges) {
            for (Vertex v : EdgeImpl.getEdge(edgeId).getConnectedVertices()) {
                if (v.getVertexType() != VertexType.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static VertexImpl getVertex(int vertexId) {
        return switch (vertexId) {
            case 1 -> VERTEX_1;
            case 2 -> VERTEX_2;
            case 3 -> VERTEX_3;
            case 4 -> VERTEX_4;
            case 5 -> VERTEX_5;
            case 6 -> VERTEX_6;
            case 7 -> VERTEX_7;
            case 8 -> VERTEX_8;
            case 9 -> VERTEX_9;
            case 10 -> VERTEX_10;
            case 11 -> VERTEX_11;
            case 12 -> VERTEX_12;
            case 13 -> VERTEX_13;
            case 14 -> VERTEX_14;
            case 15 -> VERTEX_15;
            case 16 -> VERTEX_16;
            case 17 -> VERTEX_17;
            case 18 -> VERTEX_18;
            case 19 -> VERTEX_19;
            case 20 -> VERTEX_20;
            case 21 -> VERTEX_21;
            case 22 -> VERTEX_22;
            case 23 -> VERTEX_23;
            case 24 -> VERTEX_24;
            case 25 -> VERTEX_25;
            case 26 -> VERTEX_26;
            case 27 -> VERTEX_27;
            case 28 -> VERTEX_28;
            case 29 -> VERTEX_29;
            case 30 -> VERTEX_30;
            case 31 -> VERTEX_31;
            case 32 -> VERTEX_32;
            case 33 -> VERTEX_33;
            case 34 -> VERTEX_34;
            case 35 -> VERTEX_35;
            case 36 -> VERTEX_36;
            case 37 -> VERTEX_37;
            case 38 -> VERTEX_38;
            case 39 -> VERTEX_39;
            case 40 -> VERTEX_40;
            case 41 -> VERTEX_41;
            case 42 -> VERTEX_42;
            case 43 -> VERTEX_43;
            case 44 -> VERTEX_44;
            case 45 -> VERTEX_45;
            case 46 -> VERTEX_46;
            case 47 -> VERTEX_47;
            case 48 -> VERTEX_48;
            case 49 -> VERTEX_49;
            case 50 -> VERTEX_50;
            case 51 -> VERTEX_51;
            case 52 -> VERTEX_52;
            case 53 -> VERTEX_53;
            case 54 -> VERTEX_54;
            default -> null;
        };
    }
    
    public static List<VertexImpl> allVerticies() {
        return List.of(
                VERTEX_1,
                VERTEX_2,
                VERTEX_3,
                VERTEX_4,
                VERTEX_5,
                VERTEX_6,
                VERTEX_7,
                VERTEX_8,
                VERTEX_9,
                VERTEX_10,
                VERTEX_11,
                VERTEX_12,
                VERTEX_13,
                VERTEX_14,
                VERTEX_15,
                VERTEX_16,
                VERTEX_17,
                VERTEX_18,
                VERTEX_19,
                VERTEX_20,
                VERTEX_21,
                VERTEX_22,
                VERTEX_23,
                VERTEX_24,
                VERTEX_25,
                VERTEX_26,
                VERTEX_27,
                VERTEX_28,
                VERTEX_29,
                VERTEX_30,
                VERTEX_31,
                VERTEX_32,
                VERTEX_33,
                VERTEX_34,
                VERTEX_35,
                VERTEX_36,
                VERTEX_37,
                VERTEX_38,
                VERTEX_39,
                VERTEX_40,
                VERTEX_41,
                VERTEX_42,
                VERTEX_43,
                VERTEX_44,
                VERTEX_45,
                VERTEX_46,
                VERTEX_47,
                VERTEX_48,
                VERTEX_49,
                VERTEX_50,
                VERTEX_51,
                VERTEX_52,
                VERTEX_53,
                VERTEX_54
        );
    }
}