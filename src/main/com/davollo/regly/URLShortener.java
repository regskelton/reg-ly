package com.davollo.regly;

// Tech assessment #1
// Based on https://stackoverflow.com/questions/742013/how-do-i-create-a-url-shortener
// and then https://www.geeksforgeeks.org/how-to-design-a-tiny-url-or-url-shortener/
// (originally contributed by shubham96301)
// and then modernised in Intellij

// Java program to generate short url from integer id and
// integer id back from short url.
import java.lang.*;
import java.io.*;

class URLShortener
{
    // Encode ain integer as a short character sequence
    static String encode(int n)
    {
        // Map to store 62 possible characters
        char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        StringBuffer shorturl = new StringBuffer();

        // Convert given integer id to a base 62 number
        while (n > 0)
        {
            // use above map to store actual character
            // in short url
            shorturl.append(map[n % 62]);
            n = n / 62;
        }

        // Reverse shortURL to complete base conversion
        return shorturl.reverse().toString();
    }

    // Decode a short character sequence to an integer
    static int decode(String shortURL)
    {
        int id = 0; // initialize result

        // A simple base conversion logic
        for (int i = 0; i < shortURL.length(); i++)
        {
            if ('a' <= shortURL.charAt(i) &&
                    shortURL.charAt(i) <= 'z')
                id = id * 62 + shortURL.charAt(i) - 'a';
            if ('A' <= shortURL.charAt(i) &&
                    shortURL.charAt(i) <= 'Z')
                id = id * 62 + shortURL.charAt(i) - 'A' + 26;
            if ('0' <= shortURL.charAt(i) &&
                    shortURL.charAt(i) <= '9')
                id = id * 62 + shortURL.charAt(i) - '0' + 52;
        }
        return id;
    }

    // Driver Code
    public static void main (String[] args) throws IOException
    {
        int n = 12345;
        String shorturl = encode(n);
        System.out.println("Generated short url is " + shorturl);
        System.out.println("Id from url is " +
                decode(shorturl));
    }
}

