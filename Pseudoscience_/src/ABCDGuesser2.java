import java.util.Random;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * This program uses De Jager formula from inputed values for u,w,x,y, and z and
 * uses the 17 numbers in De Jagers' list to find a sum using the formula within
 * 1% of u.
 *
 * @author Daniel Legesse
 *
 */
public final class ABCDGuesser2 {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        double num = -1.0;
        while (num < 0) {
            String numString = in.nextLine();
            boolean isDouble = FormatChecker.canParseDouble(numString);
            if (isDouble) {
                num = Double.parseDouble(numString);
            }
            while (!isDouble) {
                out.println("Not a number");
                out.println("u-value: ");
                numString = in.nextLine();
                isDouble = FormatChecker.canParseDouble(numString);
                if (FormatChecker.canParseDouble(numString)) {
                    num = Double.parseDouble(numString);
                    if (num < 0) {
                        isDouble = false;
                    }
                }

            }
        }
        return num;

    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        double num = 1.0;
        while (num < 0 || num == 1) {
            String numString = in.nextLine();
            boolean isDouble = FormatChecker.canParseDouble(numString);
            if (isDouble) {
                num = Double.parseDouble(numString);
            }
            while (!isDouble) {
                out.println("Not a number");
                out.println("value: ");
                numString = in.nextLine();
                isDouble = FormatChecker.canParseDouble(numString);
                if (FormatChecker.canParseDouble(numString)) {
                    num = Double.parseDouble(numString);
                    if (num < 0 || num == 1) {
                        isDouble = false;
                    }
                }
            }
        }
        return num;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        // initializing values used in De Jager formula
        out.println("u value: ");
        double u = getPositiveDouble(in, out);
        out.println("w value: ");
        double w = getPositiveDoubleNotOne(in, out);
        out.println("x value: ");
        double x = getPositiveDoubleNotOne(in, out);
        out.println("y value: ");
        double y = getPositiveDoubleNotOne(in, out);
        out.println("z value: ");
        double z = getPositiveDoubleNotOne(in, out);
        double sum = 0;
        double a = 0;
        double b = 0;
        double c = 0;
        double d = 0;
        double relativeError = w;
        Random rand = new Random();
        final int hundred = 100;
        // calculates relative error until it is within 1% of inputed 'u' value
        for (relativeError = w; relativeError > 1; relativeError = Math
                .abs(((sum / u) * hundred) - hundred)) {
            /*
             * creating array consisting of 17 numbers potentially used in De
             * Jager formula
             */
            final double[] options = { -5, -4, -3, -2, -1, -1 / 2, -1 / 3,
                    -1 / 4, 0, 1 / 4, 1 / 3, 1 / 2, 1, 2, 3, 4, 5 };
            // randomly selecting numbers from array
            int length = options.length;
            int aRand = rand.nextInt(length);
            int bRand = rand.nextInt(length);
            int cRand = rand.nextInt(length);
            int dRand = rand.nextInt(length);
            // randomly selected numbers are assigned to values used in formula
            a = options[aRand];
            b = options[bRand];
            c = options[cRand];
            d = options[dRand];
            // formula used to find a sum
            sum = (Math.pow(w, a)) * (Math.pow(x, b)) * (Math.pow(y, c))
                    * (Math.pow(z, d));
        }
        /*
         * once relative error percentage is within 1%, print values used in
         * formula, sum, and relative error
         */
        out.println("A: " + a);
        out.println("B: " + b);
        out.println("C: " + c);
        out.println("D: " + d);
        out.println("Formula: " + "(" + w + "^" + a + ")" + "*" + "(" + x + "^"
                + b + ")" + "*" + "(" + y + "^" + c + ")" + "*" + "(" + z + "^"
                + d + ")");
        out.println("Sum: " + sum);
        out.println("RE: " + relativeError + "%");

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
