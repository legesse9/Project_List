import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Mathew Sinadinos
 * @author Daniel Legesse
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Constructor
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        assertEquals(mExpected, m);
    }

    /*
     * Test Add
     */

    /**
     * Add to sorting machine when it is initially empty.
     */
    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");

        m.add("green");

        assertEquals(mExpected, m);
    }

    /**
     * Add to sorting machine when it has one item in it.
     */
    @Test
    public final void testAddWithOneItem() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "red");

        m.add("red");

        assertEquals(mExpected, m);
    }

    /**
     * Add to sorting machine when there are multiple items in it.
     */
    @Test
    public final void testAddWithTwoItems() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "red", "orange");
        m.add("orange");
        assertEquals(mExpected, m);
    }

    /*
     * Testing Extraction Mode
     */

    /**
     * Test changing to extraction mode with an empty sorting machine.
     */
    @Test
    public final void testExtractionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        m.changeToExtractionMode();

        assertEquals(m.isInInsertionMode(), mExpected.isInInsertionMode());
        assertEquals(m, mExpected);
    }

    /**
     * Test changing to extraction mode with a sorting machine with items.
     */
    @Test
    public final void testExtractionMode2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "red", "orange");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "red", "orange");

        m.changeToExtractionMode();

        assertEquals(m.isInInsertionMode(), mExpected.isInInsertionMode());
        assertEquals(m, mExpected);
    }

    /*
     * Testing removeFirst
     */

    /**
     * Testing removeFirst with one item in sorting machine.
     */
    @Test
    public final void testRemoveWithOneItem() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        String object = m.removeFirst();

        assertEquals(mExpected, m);
        assertEquals(object, "green");
    }

    /**
     * Testing removeFirst with multiple items in sorting machine.
     */
    @Test
    public final void testRemoveWithTwoItems() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "red");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "red");

        String object = m.removeFirst();

        assertEquals(mExpected, m);
        assertEquals(object, "green");
    }

    /*
     * Testing isInInsertionMode
     */

    /**
     * Test isInInsertionMode when false.
     */
    @Test
    public final void testIfInInsertionMode() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);

        assertEquals(m.isInInsertionMode(), false);
    }

    /**
     * Test isInInsertionMode when true.
     */
    @Test
    public final void testIfInInsertionMode2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);

        assertEquals(m.isInInsertionMode(), true);
    }

    /*
     * Testing size
     */

    /**
     * Test size when are no items in sorting machine and is in insertion mode.
     */
    @Test
    public final void testSizeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        assertEquals(0, m.size());
        assertEquals(mExpected, m);
    }

    /**
     * Test size when are no items in sorting machine and is not insertion mode.
     */
    @Test
    public final void testSizeEmpty2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        assertEquals(0, m.size());
        assertEquals(mExpected, m);
    }

    /**
     * Test size when there is one item and sorting machine is in insertion
     * mode.
     */
    @Test
    public final void testSizeOne() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");

        assertEquals(1, m.size());
        assertEquals(mExpected, m);
    }

    /**
     * Test size when there is one item and sorting machine is not in insertion
     * mode.
     */
    @Test
    public final void testSizeOne2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green");

        assertEquals(1, m.size());
        assertEquals(mExpected, m);
    }

    /**
     * Test size when there is multiple items and sorting machine is in
     * insertion mode.
     */
    @Test
    public final void testSizeMultiple() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "pink");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue", "pink");

        assertEquals(3, m.size());
        assertEquals(mExpected, m);
    }

    /**
     * Test size when there is multiple items and sorting machine is not in
     * insertion mode.
     */
    @Test
    public final void testSizeMultiple2() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue", "pink");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "green", "blue", "pink");

        assertEquals(3, m.size());
        assertEquals(mExpected, m);
    }

    /*
     * Order
     */

    /**
     * Test order with three elements.
     */
    @Test
    public final void testOrderThree() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "orange", "red", "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "red", "orange");
        m.order();
        assertEquals(mExpected, m);
    }

    /**
     * Test order with four elements.
     */
    @Test
    public final void testOrderFour() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "orange", "red", "green", "beige");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "beige", "green", "red", "orange");
        m.order();
        assertEquals(mExpected, m);
    }
}
