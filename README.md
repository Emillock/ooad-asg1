Project: Random Number Generator and Statistical Analysis \
Author: Emil Mirzazada \
Course: Object Oriented Analysis & Design \
Date: 2026-02-08
--------------------------------------------------------

OVERVIEW
--------
This project implements a single Java class, Generator, that:

- Generates random double values in the range [0, 1) using three built-in Java
  random number generators:
  - java.util.Random
  - Math.random()
  - java.util.concurrent.ThreadLocalRandom
- Computes descriptive statistics (n, mean, sample standard deviation, min, max)
  using Welford’s online algorithm.
- Displays the results in a formatted table in the console.

--------------------------------------------------------

FILES
-----
Generator.java
- Main program file (single-class implementation).

README.txt
- This file.

--------------------------------------------------------

REQUIREMENTS
------------
- Java Development Kit (JDK) 8 or later

--------------------------------------------------------

HOW TO COMPILE AND RUN
---------------------
Open a terminal or command prompt, navigate to the directory containing
Generator.java, then run:

```bash
javac Generator.java
java Generator
```


--------------------------------------------------------

SAMPLE OUTPUT (values will vary)
--------------------------------
```text
Generator                   n            mean          stddev             min             max
-----------------------------------------------------------------------------------------------
java.util.Random           10        0,491267        0,241411        0,084240        0,726233
java.util.Random         1000        0,499281        0,290386        0,000290        0,998545
java.util.Random       100000        0,501048        0,288165        0,000019        0,999939
-----------------------------------------------------------------------------------------------
Math.random()              10        0,460982        0,190252        0,200159        0,813701
Math.random()            1000        0,509592        0,284069        0,000143        0,999504
Math.random()          100000        0,499844        0,288271        0,000015        0,999998
-----------------------------------------------------------------------------------------------
ThreadLocalRandom          10        0,619932        0,194910        0,426061        0,869527
ThreadLocalRandom        1000        0,494528        0,290754        0,000023        0,999699
ThreadLocalRandom      100000        0,499153        0,289167        0,000000        0,999979
-----------------------------------------------------------------------------------------------
```


--------------------------------------------------------

--------------------------------------------------------

RANDOM NUMBER GENERATORS USED
----------------------------
java.util.Random
- Classic pseudo-random generator
- Can be seeded for reproducible results

Math.random()
- Convenience method
- Uses a single shared Random instance internally (so the quality of randomness is the sam as for java.util.Random)

ThreadLocalRandom
- Provides one random generator per thread
- More efficient in multi-threaded environments

Note: None of these are cryptographically secure.
For security-sensitive randomness, use java.security.SecureRandom.

--------------------------------------------------------

REFERENCES
----------
Wikipedia – Algorithms for calculating variance
https://en.wikipedia.org/wiki/Algorithms_for_calculating_variance

Tom Morris – Java Welford implementation (IntAccumulator)
https://gist.github.com/tfmorris/0f3878a6fa1c91dc6787aee06f55cac8

--------------------------------------------------------
