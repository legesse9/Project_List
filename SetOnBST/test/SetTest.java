import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Mathew Sinadinos
 * @author Daniel Legesse
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Test creating an empty set.
     */
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Testing add to a set when it starts as empty.
     */
    @Test
    public final void testAddEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("red");
        /*
         * Call method under test
         */
        s.add("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test adding to a set when the set is not initially empty.
     */
    @Test
    public final void testAddNonEmptyOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red");
        Set<String> sExpected = this.createFromArgsRef("red", "blue");
        /*
         * Call method under test
         */
        s.add("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test adding to a set when the set has multiple items in it.
     */
    @Test
    public final void testAddNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red", "blue", "green");
        Set<String> sExpected = this.createFromArgsRef("red", "blue", "green",
                "yellow");
        /*
         * Call method under test
         */

        s.add("yellow");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test removing from a set and making it empty.
     */
    @Test
    public final void testRemoveLeavingEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red");
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        String x = s.remove("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("red", x);
    }

    /**
     * Testing remove from a set and leaving it not empty.
     */
    @Test
    public final void testRemoveLeavingNonEmptyOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red", "blue");
        Set<String> sExpected = this.createFromArgsRef("red");
        /*
         * Call method under test
         */
        String x = s.remove("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("blue", x);
    }

    /**
     * Testing remove from a set and leaving multiple items left.
     */
    @Test
    public final void testRemoveLeavingNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red", "blue", "green");
        Set<String> sExpected = this.createFromArgsRef("red", "blue");
        /*
         * Call method under test
         */
        String x = s.remove("green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("green", x);
    }

    /**
     * Test removeAny leaving nothing left in the set.
     */
    @Test
    public final void testRemoveAnyLeavingEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red");
        Set<String> sExpected = this.createFromArgsRef("red");
        /*
         * Call method under test
         */
        String x = s.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected.contains(x), true);
        sExpected.remove(x);
        assertEquals(sExpected, s);
    }

    /**
     * Test removeAny leaving an item left in the set.
     */
    @Test
    public final void testRemoveAnyLeavingNonEmptyOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red", "blue");
        Set<String> sExpected = this.createFromArgsRef("red", "blue");
        /*
         * Call method under test
         */
        String x = s.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected.contains(x), true);
        sExpected.remove(x);
        assertEquals(sExpected, s);
    }

    /**
     * Test removeAny with more than one item in the set.
     */
    @Test
    public final void testRemoveAnyLeavingNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red", "blue", "green");
        Set<String> sExpected = this.createFromArgsRef("red", "blue", "green");
        /*
         * Call method under test
         */
        String x = s.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected.contains(x), true);
        sExpected.remove(x);
        assertEquals(sExpected, s);
    }

    /**
     * Test contains method with an empty set.
     */
    @Test
    public final void testContainsZero() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        boolean doesContain = s.contains("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(doesContain, false);
    }

    /**
     * Test contains with only one item in the set and it being true.
     */
    @Test
    public final void testContainsOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red");
        Set<String> sExpected = this.createFromArgsRef("red");
        /*
         * Call method under test
         */
        boolean doesContain = s.contains("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(doesContain, true);
    }

    /**
     * Test contains with only one item in the set and it being false.
     */
    @Test
    public final void testContainsOneFalse() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red");
        Set<String> sExpected = this.createFromArgsRef("red");
        /*
         * Call method under test
         */
        boolean doesContain = s.contains("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(doesContain, false);
    }

    /**
     * Test contains with multiple items and it being true.
     */
    @Test
    public final void testContainsMoreThanOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red", "blue", "green");
        Set<String> sExpected = this.createFromArgsRef("red", "blue", "green");
        /*
         * Call method under test
         */
        boolean doesContain = s.contains("green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(doesContain, true);
    }

    /**
     * Test contains with multiple items and it being false.
     */
    @Test
    public final void testContainsMoreThanOneFalse() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red", "blue", "green");
        Set<String> sExpected = this.createFromArgsRef("red", "blue", "green");
        /*
         * Call method under test
         */
        boolean doesContain = s.contains("purple");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(doesContain, false);
    }

    /**
     * Test size when the set is empty.
     */
    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int i = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(0, i);
    }

    /**
     * Test size with one item.
     */
    @Test
    public final void testSizeNonEmptyOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red");
        Set<String> sExpected = this.createFromArgsRef("red");
        /*
         * Call method under test
         */
        int i = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(1, i);
    }

    /**
     * Test size with multiple items in the set.
     */
    @Test
    public final void testSizeNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red", "blue", "green");
        Set<String> sExpected = this.createFromArgsRef("red", "blue", "green");
        /*
         * Call method under test
         */
        int i = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(3, i);
    }

    /**
     * Test size after adding an extra item in the set.
     */
    @Test
    public final void testSizeAfterAdd() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red", "blue", "green");
        Set<String> sExpected = this.createFromArgsRef("red", "blue", "green",
                "purple");
        /*
         * Call method under test
         */

        s.add("purple");
        int i = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(4, i);
    }

    /**
     * Test size after removing an item from the set.
     */
    @Test
    public final void testSizeAfterRemove() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red", "blue", "green");
        Set<String> sExpected = this.createFromArgsRef("red", "blue");
        /*
         * Call method under test
         */

        s.remove("green");
        int i = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(2, i);
    }

    /**
     * Test size after removeAny.
     */
    @Test
    public final void testSizeAfterRemoveAny() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red", "blue", "green");
        Set<String> sExpected = this.createFromArgsRef("red", "blue", "green");
        /*
         * Call method under test
         */
        String x = s.removeAny();
        int i = s.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected.contains(x), true);
        sExpected.remove(x);
        assertEquals(sExpected, s);
        assertEquals(2, i);
    }

}
