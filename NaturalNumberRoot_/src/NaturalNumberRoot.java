import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author Daniel Legesse
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";

        // initializing variables and copies used to find correct roots
        NaturalNumber nR = new NaturalNumber2(r);
        NaturalNumber nCopy = new NaturalNumber2(n);
        // initializing upperBound as 'n' + 1
        nCopy.increment();
        NaturalNumber upperBound = new NaturalNumber2(nCopy);
        // initializing lowerBound as 0
        NaturalNumber lowerBound = new NaturalNumber2(0);
        NaturalNumber upperBoundCopy = new NaturalNumber2(upperBound);
        NaturalNumber lowerBoundCopy = new NaturalNumber2(lowerBound);
        NaturalNumber average = new NaturalNumber2(0);
        NaturalNumber zero = new NaturalNumber2(0);
        NaturalNumber one = new NaturalNumber2(1);
        NaturalNumber two = new NaturalNumber2(2);
        boolean isTrue = true;

        if (n.compareTo(zero) > 0 && r > 0) {
            while (isTrue) {
                // finding average of upper and lower bound
                upperBoundCopy.add(lowerBoundCopy);
                upperBoundCopy.divide(two);
                average.copyFrom(upperBoundCopy);
                NaturalNumber result = new NaturalNumber2(1);
                NaturalNumber count = new NaturalNumber2(0);
                // Calculating the average to the power of the root
                while (count.compareTo(nR) < 0) {
                    result.multiply(average);
                    count.increment();
                }
                // If that result is greater than 'n, it is the new upperBound
                if (result.compareTo(n) > 0) {
                    upperBound.copyFrom(average);
                    // else if result is smaller than 'n', it is the new lowerBound
                } else if (result.compareTo(n) <= 0) {
                    lowerBound.copyFrom(average);
                }
                NaturalNumber upperBoundCopy1 = new NaturalNumber2(upperBound);
                NaturalNumber lowerBoundCopy1 = new NaturalNumber2(lowerBound);
                upperBoundCopy.copyFrom(upperBound);
                lowerBoundCopy.copyFrom(lowerBound);
                upperBoundCopy1.subtract(lowerBoundCopy1);
                // once upper and lower bound are within 1, the root is the lowerBound
                if (upperBoundCopy1.compareTo(one) == 0) {
                    average.copyFrom(lowerBound);
                    isTrue = false;
                }

            }

        }
        n.copyFrom(average);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }

}