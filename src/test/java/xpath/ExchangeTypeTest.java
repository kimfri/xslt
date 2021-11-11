package xpath;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeTypeTest {

    @Test
    void givenAttributeSign_thenGetAttribute() {
        assertEquals(ExchangeType.ATTRIBUTE, getType(" ${apa.bepa}"));
        assertEquals(ExchangeType.XPATH, getType("/"));
        assertEquals(ExchangeType.FIX, getType("00"));
    }

    ExchangeType getType(String expression) {
        //@formatter:off
        if(expression.trim().startsWith("$")) return ExchangeType.ATTRIBUTE;
        else if (expression.startsWith("/")) return ExchangeType.XPATH;
        else return ExchangeType.FIX;
        //formatter:on
    }
}