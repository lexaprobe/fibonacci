# Fibonacci Number Generator

Use one of four different algorithms to calculate the nth Fibonacci number.

The [Fibonacci sequence](https://en.wikipedia.org/wiki/Fibonacci_sequence) is defined by the following recursive relation for an integer $n>2$

$$
F_n=F_{n-1}+F_{n-2}\space :\space F_0=0,\space F_1=1
$$


By calculating larger and larger Fibonacci numbers, this project is intended to portray just how unbelievably fast modern day computers are at doing math. Assuming, of course, the right method is used!

## Algorithms

1. **The recursive approach**
    - Excruciatingly slow, but simple to implement
    - $O(n^2)$ time complexity
    - $O(n)$ space complexity

2. **The iterative approach**
    - Still simple (using just a basic for loop), but much faster!
    - $O(n)$ time complexity
    - $O(1)$ space complexity

3. **Binet's formula**
    - Fast, but not very practical. Requires very high precision decimal values to actually work for a large $n$
    - $O(1)$ time complexity
    - $O(1)$ space complexity

4. **ASCENSION: the fast doubling method**
    - Harness the power of matrix multiplication and bit-shifting to achieve nigh-unbeatable speed
    - $O(\log n)$ time complexity
    - $O(1)$ space complexity

## How To Run

Make sure Java is installed on your computer and then run the following:
```bash
javac Fibonacci.java && java Fibonacci -v n
```
`v` is the algorithm [1-4] you want to use.

`n` is the nth Fibonacci number that will be calculated.

The program will then write the generated Fibonacci number to the file "out.txt" and display both a "calculation time" and a "write time" in the terminal.

## Benchmarks

Some arbitrary points of reference for the speed of each algorithm:

- Recursive approach $\to F_{50}$ in 64 seconds
- Binet's formula $\to$ capped at $F_{1476}$ due to decimal precision requirements
- Iterative approach $\to F_{100,000}$ in ~0.15 seconds
- Fast-doubling method $\to F_{100,000}$ in ~0.02 seconds

Times will vary with different computers etc.

## Resources

- [Binet's Formula](https://artofproblemsolving.com/wiki/index.php/Binet%27s_Formula)
- [Project Nayuki - Fast Fibonacci algorithms](https://www.nayuki.io/page/fast-fibonacci-algorithms)
