import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Mathew Sinadinos
 * @author Daniel Legesse
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /*
     * Test cases for constructor
     */

    /**
     * No argument test.
     */
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> map = this.constructorTest();
        Map<String, String> mapExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(map, mapExpected);
    }

    /*
     * Test cases for add
     */

    /**
     * Add entry to an empty map.
     */
    @Test
    public final void testAddEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef("one", "1");
        /*
         * Call method under test
         */
        map.add("one", "1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapExpected, map);
    }

    /**
     * Add an entry to the map when it is not empty.
     */
    @Test
    public final void testAddNoneEmptyOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("one", "1");
        Map<String, String> mapExpected = this.createFromArgsRef("one", "1",
                "two", "2");
        /*
         * Call method under test
         */
        map.add("two", "2");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapExpected, map);
    }

    /**
     * Add a third entry to a map.
     */
    @Test
    public final void testAddNoneEmptyMoreOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("one", "1", "two",
                "2");
        Map<String, String> mapExpected = this.createFromArgsRef("one", "1",
                "two", "2", "three", "3");
        /*
         * Call method under test
         */
        map.add("three", "3");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapExpected, map);
    }

    /**
     * Add multiple entries to a map in the same method.
     */
    @Test
    public final void testAddMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("one", "1", "two",
                "2");
        Map<String, String> mapExpected = this.createFromArgsRef("one", "1",
                "two", "2", "three", "3", "four", "4");
        /*
         * Call method under test
         */
        map.add("three", "3");
        map.add("four", "4");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mapExpected, map);
    }

    /*
     * Test cases for remove
     */

    /**
     * Remove the only entry in a map, making it empty.
     */
    @Test
    public final void testRemoveLeavingEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("one", "1");
        Map<String, String> mapExpected = this.createFromArgsRef("one", "1");
        /*
         * Call method under test
         */
        Pair<String, String> p = map.remove("one");
        Pair<String, String> pExpected = mapExpected.remove("one");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(pExpected, p);
        assertEquals(mapExpected, map);
    }

    /**
     * Remove an entry from the map when the map has multiple entries.
     */
    @Test
    public final void testRemoveLeavingNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("one", "1", "two",
                "2", "three", "3");
        Map<String, String> mapExpected = this.createFromArgsRef("one", "1",
                "two", "2", "three", "3");
        /*
         * Call method under test
         */
        Pair<String, String> p = map.remove("one");
        Pair<String, String> pExpected = mapExpected.remove("one");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(pExpected, p);
        assertEquals(mapExpected, map);
    }

    /**
     * Remove multiple entries in the same method.
     */
    @Test
    public final void testRemoveMultiple() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("one", "1", "two",
                "2", "three", "3");
        Map<String, String> mapExpected = this.createFromArgsRef("one", "1",
                "two", "2", "three", "3");
        /*
         * Call method under test
         */
        Pair<String, String> p = map.remove("one");
        Pair<String, String> p2 = map.remove("two");
        Pair<String, String> pExpected = mapExpected.remove("one");
        Pair<String, String> p2Expected = mapExpected.remove("two");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(pExpected, p);
        assertEquals(p2Expected, p2);
        assertEquals(mapExpected, map);
    }

    /*
     * Test cases for removeAny
     */

    /**
     * removeAny on the only element in the map.
     */
    @Test
    public final void testRemoveAnyLeavingEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("one", "1");
        Map<String, String> mapExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        Pair<String, String> pair = map.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(!map.hasKey(pair.key()), true);
        assertEquals(mapExpected, map);
    }

    /**
     * removeAny when there are multiple elements in the map.
     */
    @Test
    public final void testRemoveAnyLeavingNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("one", "1", "two",
                "2", "three", "3");
        Map<String, String> mapExpected = this.createFromArgsRef("one", "1",
                "two", "2", "three", "3");
        /*
         * Call method under test
         */
        Pair<String, String> pair = map.removeAny();
        mapExpected.remove(pair.key());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(!map.hasKey(pair.key()), true);
        assertEquals(mapExpected, map);
    }

    /*
     * Test cases for value
     */

    /**
     * Check value of key when map only has one element.
     */
    @Test
    public final void testValueNonEmptyOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("one", "1");
        Map<String, String> mapExpected = this.createFromArgsRef("one", "1");
        /*
         * Call method under test
         */
        String value = map.value("one");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("1", value);
        assertEquals(mapExpected, map);
    }

    /**
     * Check value of key when map has multiple elements.
     */
    @Test
    public final void testValueNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("one", "1", "two",
                "2", "three", "3");
        Map<String, String> mapExpected = this.createFromArgsRef("one", "1",
                "two", "2", "three", "3");
        /*
         * Call method under test
         */
        String value = map.value("two");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("2", value);
        assertEquals(mapExpected, map);
    }

    /*
     * Test cases for hasKey
     */

    /**
     * hasKey when the map has no entries.
     */
    @Test
    public final void testHasKeyEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        boolean hasKey = map.hasKey("one");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(hasKey, false);
        assertEquals(mapExpected, map);
    }

    /**
     * hasKey when it is the only entry.
     */
    @Test
    public final void testHasKeyNonEmptyOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("one", "1");
        Map<String, String> mapExpected = this.createFromArgsRef("one", "1");
        /*
         * Call method under test
         */
        boolean hasKey = map.hasKey("one");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(hasKey, true);
        assertEquals(mapExpected, map);
    }

    /**
     * hasKey when there are multiple entries in the map.
     */
    @Test
    public final void testHasKeyNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("one", "1", "two",
                "2", "three", "3");
        Map<String, String> mapExpected = this.createFromArgsRef("one", "1",
                "two", "2", "three", "3");
        /*
         * Call method under test
         */
        boolean hasKey = map.hasKey("three");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(hasKey, true);
        assertEquals(mapExpected, map);
    }

    /*
     * Test cases for size
     */

    /**
     * Check size when map is empty.
     */
    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int size = map.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, size);
        assertEquals(mapExpected, map);
    }

    /**
     * Check size when map has 1 element.
     */
    @Test
    public final void testSizeNonEmptyOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("one", "1");
        Map<String, String> mapExpected = this.createFromArgsRef("one", "1");
        /*
         * Call method under test
         */
        int size = map.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(1, size);
        assertEquals(mapExpected, map);
    }

    /**
     * Check size when map has multiple elements.
     */
    @Test
    public final void testSizeNonEmptyMoreThanOne() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("one", "1", "two",
                "2", "three", "3");
        Map<String, String> mapExpected = this.createFromArgsRef("one", "1",
                "two", "2", "three", "3");
        /*
         * Call method under test
         */
        int size = map.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(3, size);
        assertEquals(mapExpected, map);
    }

    /**
     * Check size after using remove.
     */
    @Test
    public final void testSizeAfterUsingRemove() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("one", "1", "two",
                "2", "three", "3");
        Map<String, String> mapExpected = this.createFromArgsRef("one", "1",
                "two", "2");
        /*
         * Call method under test
         */
        map.remove("three");
        int size = map.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(2, size);
        assertEquals(mapExpected, map);
    }

    /**
     * Check size after removeAny.
     */
    @Test
    public final void testSizeAfterRemoveAny() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest("one", "1", "two",
                "2", "three", "3");
        Map<String, String> mapExpected = this.createFromArgsRef("one", "1",
                "two", "2", "three", "3");
        /*
         * Call method under test
         */
        Pair<String, String> pair = map.removeAny();
        mapExpected.remove(pair.key());
        int size = map.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(2, size);
        assertEquals(!map.hasKey(pair.key()), true);
        assertEquals(mapExpected, map);
    }

    /**
     * Check size after add.
     */
    @Test
    public final void testSizeAdd() {
        /*
         * Set up variables
         */
        Map<String, String> map = this.createFromArgsTest();
        Map<String, String> mapExpected = this.createFromArgsRef("one", "1");
        /*
         * Call method under test
         */
        map.add("one", "1");
        int size = map.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(1, size);
        assertEquals(mapExpected, map);
    }
}
