package com.davollo.regly;

// Java program to generate short url from integer id and
// integer id back from short url.
import java.lang.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Shortener
{
    // Map to store 62 possible characters
    static final char[] map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    static final int base = map.length;
    static final Map<Character,Integer> unmap= new HashMap<Character, Integer>();

    static {
        for( int i=0; i < base; i++) unmap.put(map[i], i);
    }

    // Encode in integer as a short character sequence
    String encode(int n)
    {
        StringBuilder shortstr = new StringBuilder();

        // Convert given integer to our base
        do {
            shortstr.append(map[n % base]);

            n = n / base;
        }  while (n > 0);

        // Reverse shortURL to complete base conversion
        return shortstr.reverse().toString();
    }

    // Decode a short character sequence to an integer
    int decode(String shortURL)
    {
        int id = 0; // initialize result

        // A simple base conversion logic
        for( char c : shortURL.toCharArray())
        {
            id = id * base + unmap.get( c);
        }

        return id;
    }

    // Store our customers long URLs against the integer key we generate for them
    // In a real implementation, we need to use a scalable persistent storage like DynamoDb
    static Map<Integer, String> idToLong= new ConcurrentHashMap<>();
    static Map<String, Integer> longToId= new ConcurrentHashMap<>();

    public String shorten( String string) {
        Integer token;

        // So, obviously this is going to be a performance no-no
        // synchronizing on 'this' will mean that there is only 1 thread executing
        // in a single vm, there are better options (maybe sync'ing on the String to be shortened)
        // but since the proper impl has to use a distributed lock, this is ok for now, as it will
        // let me test the semantics
        synchronized ( this) {
            token = longToId.get( string);

            if( token == null) {
                token = nextToken();

                idToLong.put(token, string);
                longToId.put( string, token);
            }
        }

        return encode(token);
    }

    public String restore( String string) {
        Integer token= decode( string);

        return idToLong.get(token);
    }

    private static Integer key= 13967;
    private Integer nextToken() {
        return key++;
    }
}

