import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Different methods to find the nth fibonacci number.
 */
public class Fibonacci {

  private long startTime = 0;
  private long endTime = 0;

  /**
   * Returns the time taken (in nanoseconds) for the most recent Fibonacci calculation
   * (0 if none performed).
   */
  public long getCalculationTime() {
    return endTime - startTime;
  }
  
  /**
   * Recursive approach.
   * O(n^2) time complexity.
   * O(n) space complexity.
   */
  public long fib1(int n) {
    if (n == 0) {
      return 0;
    } else if (n == 1) {
      return 1;
    }
    return fib1(n - 1) + fib1(n - 2);
  }

  /**
   * Iterative approach.
   * O(n) time complexity.
   * O(1) space complexity.
   */
  public BigInteger fib2(int n) {
    startTime = System.nanoTime();
    BigInteger minusTwo = new BigInteger("0");
    BigInteger minusOne = new BigInteger("1");
    BigInteger current = new BigInteger("1");

    if (n == 0) {
      return minusTwo;
    } else if (n == 1) {
      return minusOne;
    }

    for (int i = 3; i <= n; i++) {
      minusTwo = minusOne;
      minusOne = current;
      current = minusOne.add(minusTwo);
    }
    endTime = System.nanoTime();
    return current;
  }

  /**
   * Binet's formula (theoretically faster than fib2).
   * NB: not much practical use as it requires very high precision decimals.
   */
  public BigInteger fib3(int n) {
    startTime = System.nanoTime();
    double goldenRatio = (1 + Math.sqrt(5)) / 2;
    BigDecimal fib = new BigDecimal(
      (Math.pow(goldenRatio, n) - Math.pow(-goldenRatio, -n)) / Math.sqrt(5));
    BigInteger fibInt = fib.toBigInteger();
    endTime = System.nanoTime();
    return fibInt;
  }

  /**
   * ASCENSION.
   * The fast-doubling method.
   * O(logn) time complexity.
   * O(1) space complexity.
   * 
   * <p>
   * Code source: <a href=https://www.nayuki.io/page/fast-fibonacci-algorithms>
   * Project Nayuki - Fast Fibonacci algorithms
   * </a>
   * </p>
   */
  public BigInteger fib4(int n) {
    startTime = System.nanoTime();
    BigInteger a = BigInteger.ZERO;
    BigInteger b = BigInteger.ONE;

    for (int bit = Integer.highestOneBit(n); bit != 0; bit >>>= 1) {
      // loop invariant: a = F(m), b = F(m + 1)
      BigInteger c = a.multiply(b.shiftLeft(1).subtract(a));
      BigInteger d = a.multiply(a).add(b.multiply(b));
      a = c;
      b = d;

      // advance by one conditionally
      if ((n & bit) != 0) {
        BigInteger e = a.add(b);
        a = b;
        b = e;
      }
    }
    endTime = System.nanoTime();
    return a;
  }

  /**
   * Compute the nth Fibonacci number using a specific algorithm.
   * @param n
   * @param version [1-4]
   */
  public BigInteger compute(int n, int version) {
    switch (version) {
      case 1: {
        // gotta time fib1 here cause it's recursive
        startTime = System.nanoTime();
        BigInteger result = BigInteger.valueOf(fib1(n));
        endTime = System.nanoTime();
        return result;
      }
      case 2: return fib2(n);
      case 3: return fib3(n);
      case 4: return fib4(n);
      default: {
        System.out.println("Error: '" + version + "' is not a valid algorithm number");
        System.out.println("Valid numbers are [1, 2, 3, 4]");
        System.exit(1);
        break;
      }
    }
    return BigInteger.ZERO;
  }

  public static void main(String[] args) {
    int version = 0;
    int nth = 0;

    if (args.length != 2) {
      System.out.println("Error: invalid usage\nExpected: 'Fibonacci -v n'");
      System.exit(1);
    }

    try {
      version = Integer.parseInt(args[0].replace("-", ""));
    } catch (NumberFormatException e) {
      System.out.println("Error: '" + version + "' is not a valid algorithm number");
      System.out.println("Valid numbers are [1, 2, 3, 4]");
      System.exit(1);
    }

    try {
      nth = Integer.parseInt(args[1]);
      if (nth < 0) {
        throw new NumberFormatException();
      }
    } catch (NumberFormatException e) {
      System.out.println("Error: 'n' must be a non-negative integer");
      System.exit(1);
    }

    Fibonacci fib = new Fibonacci();
    BigInteger result = fib.compute(nth, version);

    // With larger values of n, the terminal cannot display the result in full
    long startWrite = 0;
    long endWrite = 0;
    File f = new File("out.txt");
    try {
      PrintWriter write = new PrintWriter(f);
      startWrite = System.nanoTime();
      write.println(result);
      endWrite = System.nanoTime();
      write.close();
    } catch (FileNotFoundException e) {
      System.out.println("Error: Output could not be written to file");
      System.exit(1);
    }

    BigDecimal calculationTime = new BigDecimal((fib.getCalculationTime()) / 1_000_000_000.0);
    System.out.printf("Calculation time: %.6f seconds\n", calculationTime);

    BigDecimal writeTime = new BigDecimal((endWrite - startWrite) / 1_000_000_000.0);
    System.out.printf("Write time: %.6f\n", writeTime);
  }
}
