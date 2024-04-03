package com.davollo.regly;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShortenerTest {
    Map<Integer, String> exampleCodings = new HashMap<>( Map.ofEntries(
            entry(12345, "dnh"),
            entry(54321, "oij")));
    List<TestData> exampleShortenings = new ArrayList<>( List.of(
            new TestData("dNr", "https://mydomain.com/really/long/url/contents"),
            new TestData("dNr", "https://mydomain.com/really/long/url/contents"),
            new TestData("dNs", "https://mydomain.com/really/long/url/contents/3"),
            new TestData("dNt", "https://mydomain.com/really/long/url/contents/2")));

    @Test
    void testEncode() {
        Shortener shortener = new Shortener();

        for (Map.Entry<Integer, String> entry : exampleCodings.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();

            assertEquals(value, shortener.encode(key));
        }
    }

    @Test
    void testDecode() {
        Shortener shortener = new Shortener();

        for (Map.Entry<Integer, String> entry : exampleCodings.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();

            assertEquals(key, shortener.decode(value));
        }
    }

    @Test
    void shorten() {
        Shortener shortener = new Shortener();

        for (TestData td : exampleShortenings) {
            assertEquals(td.shortString, shortener.shorten(td.longString), td.longString);
        }
    }

    @Test
    void restore() {
        Shortener shortener = new Shortener();

        // pre-load the data store (and why not assert while we're doing it)
        for (TestData td : exampleShortenings) {
            assertEquals(td.shortString, shortener.shorten(td.longString), td.longString);
        }

        for (TestData td : exampleShortenings) {
            assertEquals(td.longString, shortener.restore(td.shortString), td.shortString);
        }
    }

    static class TestData {
        String shortString;
        String longString;

        public TestData(String shortString, String longString) {
            this.shortString = shortString;
            this.longString = longString;
        }

        @Override
        public String toString() {
            return "TestData{" + "shortString='" + shortString + '\'' + ", longString='" + longString + '\'' + '}';
        }
    }
}