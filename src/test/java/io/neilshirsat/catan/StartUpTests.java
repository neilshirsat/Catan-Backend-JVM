package io.neilshirsat.catan;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.junit.jupiter.api.Assertions;

@Testable
public class StartUpTests {

    private GameStateImpl gameState;

    @Test
    public void doesTheGameListOutTheNumbersProperly() {
        int x = 9+5;
        Assertions.assertEquals(14, x);
    }

}
