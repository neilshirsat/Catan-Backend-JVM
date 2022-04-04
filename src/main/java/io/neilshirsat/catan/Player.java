package io.neilshirsat.catan;

import java.util.UUID;

public class Player {
    UUID iD;
    Map resourceCounts;
    Vertex[] vertex;
    Edge[] roads;
    int victoryPoints;

    public Player(){

    }
    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void addVictoryPoints(){
        victoryPoints+=1;
    }
    public UUID getiD() {
        return iD;
    }

}
