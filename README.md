# reg-ly
2024 Assessment 1 - URL shortener

Based on https://stackoverflow.com/questions/742013/how-do-i-create-a-url-shortener
and then https://www.geeksforgeeks.org/how-to-design-a-tiny-url-or-url-shortener/
(originally contributed by shubham96301)
and then modernised in Intellij

https://systemdesign.one/url-shortening-system-design/
is a detailed coverage of many aspects of this question, and lead to a focus more on the key generation and locking required

I'd like to avoid the initial lookup to see if this URL has been shortened before - that is overhead, and
will introduce synchronisation issues
However, as the output URL is shorter, and so contains less information within it, 
any hashing system cannot detect collisions 

So doesn't seem possible to avoid initial check, and so distributed concurrency solution will be required

----

Original Brief
2024 DE Technical Exercise 1~

Exercise 1: URL shortening service

We would like you to tackle the system design and coding challenge of creating a URL shortening service like bit.ly.

Part 1 highlights key requirements and Part 2 is the coding exercise.
- Requirements
- Short URL creation:
  - Long URL must be valid.
  - No duplicates (long or short) allowed. 
  - Length of the shortened URL needs to be as short as possible. 
  - Short URL allowed characters (0-9) and (a-z, A-Z). 
- Client redirection:
  - The short URL will redirect the user to original URL. 
  - A redirection request can be either via a web browser or RESTful API (Application Programming Interface) request. 
- Analytics 
  - Track click events on the short URL. 
  - Track API redirection requests. 
- NFR's 
  - A 100 million URL's can be generated per day. 
  - 1160 write operations per second. 
  - 11600 read operations per second. 
  - 10-year data retention (365 billion 100 bytes 10 years=365 TB.

1. Coding
   2. Create a working URL shortening service in a programming language of your choice based upon the system design/requirements above.
3. Things to consider (you may be asked about):
   4. Unit & integration testing.
   5. Mocks/Stubs.
   6. Defensive coding-Security & resiliency.
   7. How have you considered CAP Theorem.
   8. How would you containerise your solution.
   9. Explore several types of URL encoding algorithms.
