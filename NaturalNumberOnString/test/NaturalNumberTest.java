import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Daniel Legesse
 * @author Mathew Sinadinos
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /*
     * Constructor
     */

    /**
     * No argument test.
     */
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test with generic integer.
     */
    @Test
    public final void testIntArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(1);
        NaturalNumber nExpected = this.constructorRef(1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test at integer max value.
     */
    @Test
    public final void testIntMaxArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber nExpected = this.constructorRef(Integer.MAX_VALUE);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test with zero value for integer.
     */
    @Test
    public final void testIntZeroArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test with a generic integer value.
     */
    @Test
    public final void testStringArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest("1");
        NaturalNumber nExpected = this.constructorRef("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test with String as zero.
     */
    @Test
    public final void testStringZeroArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest("0");
        NaturalNumber nExpected = this.constructorRef("0");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test with a value greater than Integer's max value.
     */
    @Test
    public final void tesStringLargeArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = this.constructorTest("2147483648");
        NaturalNumber nExpected = this.constructorRef("2147483648");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test with a generic Natural Number.
     */
    @Test
    public final void testNNArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nArgument = this.constructorRef(5);

        NaturalNumber n = this.constructorTest(nArgument);
        NaturalNumber nExpected = this.constructorRef(nArgument);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test with a zero Natural Number.
     */
    @Test
    public final void testNNZeroArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nArgument = this.constructorRef(0);

        NaturalNumber n = this.constructorTest(nArgument);
        NaturalNumber nExpected = this.constructorRef(nArgument);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Test with a Natural Number larger than Integer limit.
     */
    @Test
    public final void testNNLargeArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber nArgument = this.constructorRef("2147483648");

        NaturalNumber n = this.constructorTest(nArgument);
        NaturalNumber nExpected = this.constructorRef(nArgument);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * multiplyBy10
     */

    /**
     * When the value starts as zero.
     */
    @Test
    public final void testMultiplyBy10Empty() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(3);
        /*
         * Call method under test
         */
        n.multiplyBy10(3);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * When the value starts at a number other than zero.
     */
    @Test
    public final void testMultiplyBy10Three() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(12);
        NaturalNumber nExpected = this.constructorRef(123);
        /*
         * Call method under test
         */
        n.multiplyBy10(3);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Multiplying by zero when the value is not zero.
     */
    @Test
    public final void testMultiplyBy10Zero() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(12);
        NaturalNumber nExpected = this.constructorRef(120);
        /*
         * Call method under test
         */
        n.multiplyBy10(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /**
     * Multiplying by zero when the value is zero.
     */
    @Test
    public final void testMultiplyBy10ZeroWhenZero() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);
        /*
         * Call method under test
         */
        n.multiplyBy10(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, n);
    }

    /*
     * divideBy10
     */

    /**
     * Divide by 10 when value is zero.
     */
    @Test
    public final void testDivideBy10Zero() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef(0);
        /*
         * Call method under test
         */
        int val = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(val, 0);
        assertEquals(nExpected, n);
    }

    /**
     * Divide by 10 when value is single digit.
     */
    @Test
    public final void testDivideBy10Single() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(3);
        NaturalNumber nExpected = this.constructorRef(0);
        /*
         * Call method under test
         */
        int val = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(val, 3);
        assertEquals(nExpected, n);
    }

    /**
     * Divide by 10 when value is not a single digit.
     */
    @Test
    public final void testDivideBy10Large() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(3345);
        NaturalNumber nExpected = this.constructorRef(334);
        /*
         * Call method under test
         */
        int val = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(val, 5);
        assertEquals(nExpected, n);
    }

    /**
     * Divide by 10 when remainder zero.
     */
    @Test
    public final void testDivideBy10RemainderZero() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(3340);
        NaturalNumber nExpected = this.constructorRef(334);
        /*
         * Call method under test
         */
        int val = n.divideBy10();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(val, 0);
        assertEquals(nExpected, n);
    }

    /*
     * isZero
     */

    /**
     * isZero is true.
     */
    @Test
    public final void isZeroTrue() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n.isZero(), nExpected.isZero());
        assertEquals(nExpected, n);
    }

    /**
     * isZero is true.
     */
    @Test
    public final void isZeroFalse() {
        /*
         * Set up variables
         */
        NaturalNumber n = this.constructorTest(1);
        NaturalNumber nExpected = this.constructorRef(1);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(n.isZero(), nExpected.isZero());
        assertEquals(nExpected, n);
    }

}
