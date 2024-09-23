
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Daniel Legesse
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_47() {
        NaturalNumber n = new NaturalNumber2(47);
        NaturalNumber nExpected = new NaturalNumber2(47);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testIsWitness() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber nExpected = new NaturalNumber2(9);
        NaturalNumber p = new NaturalNumber2(17);
        NaturalNumber pExpected = new NaturalNumber2(17);
        boolean resultExpected = false;
        boolean result = CryptoUtilities.isWitnessToCompositeness(n, p);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(resultExpected, result);
    }

    @Test
    public void testIsWitness2() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber nExpected = new NaturalNumber2(9);
        NaturalNumber p = new NaturalNumber2(19);
        NaturalNumber pExpected = new NaturalNumber2(19);
        boolean resultExpected = false;
        boolean result = CryptoUtilities.isWitnessToCompositeness(n, p);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(resultExpected, result);
    }

    @Test
    public void testIsWitness3() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(4);
        NaturalNumber p = new NaturalNumber2(47);
        NaturalNumber pExpected = new NaturalNumber2(47);
        boolean resultExpected = false;
        boolean result = CryptoUtilities.isWitnessToCompositeness(n, p);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(resultExpected, result);
    }

    @Test
    public void testIsPrime1_47() {
        NaturalNumber p = new NaturalNumber2(47);
        NaturalNumber pExpected = new NaturalNumber2(47);
        boolean resultExpected = true;
        boolean result = CryptoUtilities.isPrime1(p);
        assertEquals(pExpected, p);
        assertEquals(resultExpected, result);
    }

    @Test
    public void testIsPrime1_107() {
        NaturalNumber p = new NaturalNumber2(107);
        NaturalNumber pExpected = new NaturalNumber2(107);
        boolean resultExpected = true;
        boolean result = CryptoUtilities.isPrime1(p);
        assertEquals(pExpected, p);
        assertEquals(resultExpected, result);
    }

    @Test
    public void testIsPrime1_29() {
        NaturalNumber p = new NaturalNumber2(29);
        NaturalNumber pExpected = new NaturalNumber2(29);
        boolean resultExpected = true;
        boolean result = CryptoUtilities.isPrime1(p);
        assertEquals(pExpected, p);
        assertEquals(resultExpected, result);
    }

    @Test
    public void testIsPrime2_47() {
        NaturalNumber p = new NaturalNumber2(47);
        NaturalNumber pExpected = new NaturalNumber2(47);
        boolean resultExpected = true;
        boolean result = CryptoUtilities.isPrime2(p);
        assertEquals(pExpected, p);
        assertEquals(resultExpected, result);
    }

    @Test
    public void testIsPrime2_107() {
        NaturalNumber p = new NaturalNumber2(107);
        NaturalNumber pExpected = new NaturalNumber2(107);
        boolean resultExpected = true;
        boolean result = CryptoUtilities.isPrime2(p);
        assertEquals(pExpected, p);
        assertEquals(resultExpected, result);
    }

    @Test
    public void testIsPrime2_29() {
        NaturalNumber p = new NaturalNumber2(29);
        NaturalNumber pExpected = new NaturalNumber2(29);
        boolean resultExpected = true;
        boolean result = CryptoUtilities.isPrime2(p);
        assertEquals(pExpected, p);
        assertEquals(resultExpected, result);
    }

    @Test
    public void generateNextPrime_25() {
        NaturalNumber p = new NaturalNumber2(25);
        NaturalNumber pExpected = new NaturalNumber2(29);
        CryptoUtilities.generateNextLikelyPrime(p);
        assertEquals(pExpected, p);
    }

    @Test
    public void generateNextPrime_57() {
        NaturalNumber p = new NaturalNumber2(57);
        NaturalNumber pExpected = new NaturalNumber2(59);
        CryptoUtilities.generateNextLikelyPrime(p);
        assertEquals(pExpected, p);
    }

}