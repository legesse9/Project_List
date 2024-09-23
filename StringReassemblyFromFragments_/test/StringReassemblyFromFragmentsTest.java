import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

public class StringReassemblyFromFragmentsTest {

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test_overlap1() {
        /*
         * Set up variables and call method under test
         */
        String str1 = "CGAGGTAGT";
        String str2 = "AGTAGAACG";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(3, overlap);
    }

    @Test
    public void test_overlap2() {
        /*
         * Set up variables and call method under test
         */
        String str1 = "ABCDEFG";
        String str2 = "HIJKLMN";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(0, overlap);
    }

    @Test
    public void test_overlap3() {
        /*
         * Set up variables and call method under test
         */
        String str1 = "ABCDEFG";
        String str2 = "GHIJKLM";
        int overlap = StringReassembly.overlap(str1, str2);
        assertEquals(1, overlap);
    }

    @Test
    public void test_combination1() {
        /*
         * Set up variables and call method under test
         */
        String str1 = "CGAGGTAGT";
        String str2 = "AGTAGAACG";
        int overlap = StringReassembly.overlap(str1, str2);
        String combination = StringReassembly.combination(str1, str2, overlap);
        assertEquals("CGAGGTAGTAGAACG", combination);
    }

    @Test
    public void test_combination2() {
        /*
         * Set up variables and call method under test
         */
        String str1 = "ABCDEFG";
        String str2 = "HIJKLMN";
        int overlap = StringReassembly.overlap(str1, str2);
        String combination = StringReassembly.combination(str1, str2, overlap);
        assertEquals("ABCDEFGHIJKLMN", combination);
    }

    @Test
    public void test_combination3() {
        /*
         * Set up variables and call method under test
         */
        String str1 = "ABCDEFG";
        String str2 = "GHIJKLM";
        int overlap = StringReassembly.overlap(str1, str2);
        String combination = StringReassembly.combination(str1, str2, overlap);
        assertEquals("ABCDEFGHIJKLM", combination);
    }

    @Test
    public void test_addToSet1() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = new Set1L<>();
        Set<String> setTemp = new Set1L<>();
        set.add("Hello");
        set.add("GoodBye");
        set.add("Hi");
        setTemp.add("Hello");
        setTemp.add("GoodBye");
        setTemp.add("Hi");
        String str = "Bye";
        StringReassembly.addToSetAvoidingSubstrings(set, str);
        assertEquals(setTemp, set);
    }

    @Test
    public void test_addToSet2() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = new Set1L<>();
        Set<String> setTemp = new Set1L<>();
        set.add("Hello");
        set.add("GoodBye");
        set.add("Hi");
        setTemp.add("Hello");
        setTemp.add("GoodBye");
        setTemp.add("Hi");
        setTemp.add("Daniel");
        String str = "Daniel";
        StringReassembly.addToSetAvoidingSubstrings(set, str);
        assertEquals(setTemp, set);
    }

    @Test
    public void test_addToSet3() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = new Set1L<>();
        Set<String> setTemp = new Set1L<>();
        set.add("Hello");
        set.add("GoodBye");
        set.add("Hi");
        setTemp.add("Hello");
        setTemp.add("GoodBye");
        setTemp.add("Hi");
        String str = "GoodBye";
        StringReassembly.addToSetAvoidingSubstrings(set, str);
        assertEquals(setTemp, set);
    }

    @Test
    public void test_input1() {
        /*
         * Set up variables and call method under test
         */
        SimpleReader in = new SimpleReader1L("data/Bye.txt");
        Set<String> set = new Set1L<>();
        Set<String> setTemp = new Set1L<>();
        set.add("Hello");
        set.add("GoodBye");
        set.add("Hi");
        setTemp.add("Hello");
        setTemp.add("GoodBye");
        setTemp.add("Hi");
        Set<String> setCopy = StringReassembly.linesFromInput(in);
        String str = setCopy.removeAny();
        StringReassembly.addToSetAvoidingSubstrings(set, str);
        assertEquals(setTemp, set);
    }

    @Test
    public void test_input2() {
        /*
         * Set up variables and call method under test
         */
        SimpleReader in = new SimpleReader1L("data/Daniel.txt");
        Set<String> set = new Set1L<>();
        Set<String> setTemp = new Set1L<>();
        set.add("Hello");
        set.add("GoodBye");
        set.add("Hi");
        setTemp.add("Hello");
        setTemp.add("GoodBye");
        setTemp.add("Hi");
        setTemp.add("Daniel");
        Set<String> setCopy = StringReassembly.linesFromInput(in);
        if (setCopy.size() > 0) {
            while (setCopy.size() > 0) {
                String str1 = setCopy.removeAny();
                set.add(str1);
            }
        }
        assertEquals(setTemp, set);
    }

    @Test
    public void test_input3() {
        /*
         * Set up variables and call method under test
         */
        SimpleReader in = new SimpleReader1L("data/GoodBye.txt");
        Set<String> set = new Set1L<>();
        Set<String> setTemp = new Set1L<>();
        set.add("Hello");
        set.add("GoodBye");
        set.add("Hi");
        setTemp.add("Hello");
        setTemp.add("GoodBye");
        setTemp.add("Hi");
        Set<String> setCopy = StringReassembly.linesFromInput(in);
        String str = setCopy.removeAny();
        StringReassembly.addToSetAvoidingSubstrings(set, str);
        assertEquals(setTemp, set);
    }

}
