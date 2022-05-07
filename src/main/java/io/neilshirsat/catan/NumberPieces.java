package io.neilshirsat.catan;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.List;

public enum NumberPieces {

    A(5),
    B(2),
    C(6),
    D(3),
    E(8),
    F(10),
    G(9),
    H(12),
    I(11),
    J(4),
    K(8),
    L(10),
    M(9),
    N(4),
    O(5),
    P(6),
    Q(3),
    R(11),
    NONE(-1);

    private int value;

    NumberPieces(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static List<NumberPieces> getNumberPiecesOrdered() {
        return List.of(
                A,
                B,
                C,
                D,
                E,
                F,
                G,
                H,
                I,
                J,
                K,
                L,
                M,
                N,
                O,
                P,
                Q,
                R
        );
    }

    @JsonValue
    public int toJSON() {
        return this.value;
    }

}
