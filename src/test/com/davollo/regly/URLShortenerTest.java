package com.davollo.regly;

import java.util.HashMap;
import java.util.Map;

import static com.davollo.regly.URLShortener.decode;
import static com.davollo.regly.URLShortener.encode;
import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.*;

class URLShortenerTest {
    Map<Integer, String> exampleCodings = new HashMap<>(Map.ofEntries(
            entry(12345, "dnh")
    ));

    @org.junit.jupiter.api.Test
    void testEncode() {
        for (Map.Entry< Integer, String> entry : exampleCodings.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();

            assertEquals( value, encode(key));
        }
    }

    @org.junit.jupiter.api.Test
    void testDecode() {
        for (Map.Entry< Integer, String> entry : exampleCodings.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();

            assertEquals(key, decode(value));
        }
    }
}