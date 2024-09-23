import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map2;
import components.queue.Queue;
import components.queue.Queue2;
import components.set.Set;
import components.set.Set2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine2;

/**
 * Counts word occurrences in a given input file and outputs an HTML document
 * with words in a tag cloud with their font size depending on how many
 * occurrences of a given word there is.
 *
 * @author Mathew Sinadinos
 * @author Daniel Legesse
 *
 */
public final class TagCloudGenerator {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private TagCloudGenerator() {
    }

    /*
     * Code from CSE 2221: Glossary Project
     */
    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        int end = position;
        boolean isSeperator = separators.contains(text.charAt(position));
        while (end < text.length()
                && isSeperator == separators.contains(text.charAt(end))) {
            end++;
        }
        return text.substring(position, end);
    }

    /*
     * Code from CSE 2221: Glossary Project
     */
    /**
     * Compare {@code String}s alphabetically.
     */
    private static class AlpahbeticalSort
            implements Comparator<Map.Pair<String, Integer>> {

        @Override
        /**
         * Takes in two strings and compares them to be in alphabetical order
         *
         * @param s1
         *            the first map pair
         * @param s2
         *            the second map pair
         * @returns an integer, positive if alphabetically s1 > s2, negative if
         *          s2 > s1 and zero if s1 = s2
         * @ensures an integer to be returned which will be positive if
         *          alphabetically s1 > s2, negative if s2 > s1 and zero if s1 >
         *          s2
         */
        public int compare(Map.Pair<String, Integer> s1,
                Map.Pair<String, Integer> s2) {
            return s1.key().compareTo(s2.key());
        }
    }

    /**
     * Compare {@code String}s by decreasing order.
     */
    private static class CountSort
            implements Comparator<Map.Pair<String, Integer>> {

        @Override
        /**
         * Takes in two map pairs and compares their values to be in greatest to
         * least order
         *
         * @param s1
         *            the first map pair
         * @param s2
         *            the second map pair
         * @returns an integer, positive if numerically s1 > s2, negative if s2
         *          > s1 and zero if s1 = s2
         * @ensures an integer to be returned which will be positive if
         *          alphabetically s1 > s2, negative if s2 > s1 and zero if s1 >
         *          s2
         */
        public int compare(Map.Pair<String, Integer> s1,
                Map.Pair<String, Integer> s2) {
            return s2.value().compareTo(s1.value());
        }
    }

    /**
     * Inputs a list of a words and their number of occurrences from a given
     * file and stores them in a given {@code Map}.
     *
     * @param wordMap
     *            the term -> number of occurrences
     * @param inputFile
     *            the input stream
     * @updates inputFile
     * @requires <pre>
     * inputFile.is_open
     * </pre>
     * @ensures [wordMap contains words -> number of occurrences from file
     *          inputFile]
     */
    private static void getMapFromFile(Map<String, Integer> wordMap,
            SimpleReader inputFile) {
        Set<Character> separators = new Set2<>();

        // Define possible separators
        separators.add(' ');
        separators.add(',');
        separators.add('.');
        separators.add('-');
        separators.add(';');
        separators.add(':');
        separators.add('!');
        separators.add('?');
        separators.add('\'');
        separators.add('"');
        separators.add('(');
        separators.add(')');
        separators.add('[');
        separators.add(']');
        separators.add('_');
        separators.add('*');
        separators.add('\t');
        separators.add('\n');
        separators.add('\r');

        // Check if bottom of file is reached
        while (!inputFile.atEOS()) {
            int position = 0;
            String getLine = inputFile.nextLine().toLowerCase();
            // Check to see if the end of the line is reached
            while (position < getLine.length()) {
                String word = nextWordOrSeparator(getLine, position,
                        separators);
                position = position + word.length();
                // Check to make sure the word is actually a word
                if (!separators.contains(word.charAt(0))) {
                    if (wordMap.hasKey(word)) {
                        int occurrences = wordMap.value(word);
                        wordMap.remove(word);
                        wordMap.add(word, occurrences + 1);
                    } else {
                        wordMap.add(word, 1);
                    }
                }
            }
        }
    }

    /**
     * Generates HTML header for a given file.
     *
     * @param outputFile
     *            the output stream
     * @param inputName
     *            the name of the file
     * @param numberOfWords
     *            number of words to display in cloud
     * @updates outputFile
     * @requires outputFile.is_open = true
     * @ensures <pre>
     * outputFile.is_open and
     * outputFile.content = #output.content *
     *  [HTML, head, title tags, style sheets, and beginning of body]
     * </pre>
     */
    private static void generateHeader(SimpleWriter outputFile,
            String inputName, int numberOfWords) {
        outputFile.println("<html><head><title>Top " + numberOfWords
                + " words in " + inputName + "</title>");
        outputFile.println(
                "<link href=\"http://web.cse.ohio-state.edu/software/2231/web-sw2/assignments/projects/tag-cloud-generator/data/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        outputFile.println(
                "<link href=\"tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        outputFile.println("</head>");
        outputFile.println("<body><h2>Top " + numberOfWords + " words in "
                + inputName + "</h2>");
        outputFile.println("<hr>");
        outputFile.println("<div class=\"cdiv\">");
        outputFile.println("<p class=\"cbox\">");
    }

    /**
     * Generates HTML footer for a given file.
     *
     * @param outputFile
     *            the output stream
     * @updates outputFile
     * @requires outputFile.is_open = true
     * @ensures <pre>
     * outputFile.is_open and
     * outputFile.content = #output.content *
     *  [End of paragraph, div, body, and html tag]
     * </pre>
     */
    private static void generateFooter(SimpleWriter outputFile) {
        outputFile.println("</p></div></body></html>");
    }

    /**
     * Processes a word and it's number of occurrences into a divider in the
     * HTML output file.
     *
     * @param wordMap
     *            word -> number of occurrences map
     * @param words
     *            list of words in alphabetical order
     * @param numberOfWords
     *            of words in word cloud
     * @param outputFile
     *            the output stream
     * @param maxAndMin
     *            max and min number of occurrences in list of words
     * @updates outputFile.content
     * @requires outputFile.is_open
     * @ensures <pre>
     * outputFile.content = [an addition word in the world cloud inside the div]
     * </pre>
     */
    private static void processWords(Map<String, Integer> wordMap,
            Queue<String> words, SimpleWriter outputFile, int numberOfWords,
            Queue<Integer> maxAndMin) {

        Queue<String> tempQueue = words.newInstance();
        tempQueue.transferFrom(words);

        // First we need to get a scale.
        int maxNumber = maxAndMin.dequeue();
        int minNumber = maxAndMin.dequeue();

        int oldRange = maxNumber - minNumber;
        final int newRange = 37;
        final int newMin = 11;

        for (int i = 0; i < numberOfWords; i++) {
            String term = tempQueue.dequeue();
            int newValue = (((wordMap.value(term) - minNumber) * (newRange))
                    / (oldRange)) + newMin;
            // Print word to table
            outputFile.print("<span style=\"cursor:default\" class=\"f"
                    + newValue + "\"");
            outputFile.println(" title=\"count: " + wordMap.value(term) + "\">"
                    + term + "</span>");
            // Restore words
            words.enqueue(term);
        }
    }

    /**
     * Adds all words from wordMap into {@code Queue} called words and sorts the
     * Queue in alphabetical order after first sorting by number of occurrences.
     *
     * @param wordMap
     *            a map of word -> number of occurrences
     * @param words
     *            an empty {@code Queue}
     * @param numberOfWords
     *            number of words in tag cloud
     * @param maxAndMin
     *            an empty queue containing the max occurrences of words and min
     *
     * @requires |words| = 0 and |maxAndMin| = 0
     * @ensures words = [all keys from wordMap sorted in alphabetical order]
     */
    private static void sortQueueFromMap(Map<String, Integer> wordMap,
            Queue<String> words, int numberOfWords, Queue<Integer> maxAndMin) {
        // Make new sorting machine
        Comparator<Pair<String, Integer>> wordCountOrder = new CountSort();
        // Define order
        SortingMachine<Map.Pair<String, Integer>> wordCounts = new SortingMachine2<>(
                wordCountOrder);

        // Sort all the strings by count first
        for (Pair<String, Integer> mapPairs : wordMap) {
            wordCounts.add(mapPairs);
        }

        wordCounts.changeToExtractionMode();

        // Define new sorting machine now for alphabetical
        Comparator<Map.Pair<String, Integer>> alphabeticalSort = new AlpahbeticalSort();
        SortingMachine<Map.Pair<String, Integer>> alphabeticalMachine = new SortingMachine2<>(
                alphabeticalSort);

        int maxValue = -1;

        // Get largest number for fontSizes
        if (wordCounts.size() > 0 && numberOfWords > 0) {
            Pair<String, Integer> largestPair = wordCounts.removeFirst();
            maxValue = largestPair.value();
            alphabeticalMachine.add(largestPair);
        }

        int counter = 2;
        while (wordCounts.size() > 0 && counter < numberOfWords) {
            Pair<String, Integer> pair = wordCounts.removeFirst();
            alphabeticalMachine.add(pair);
            counter++;
        }

        // Get smallest number for fontSizes
        int minValue = -1;
        if (wordCounts.size() > 0) {
            Pair<String, Integer> smallestPair = wordCounts.removeFirst();
            minValue = smallestPair.value();
            alphabeticalMachine.add(smallestPair);
        }

        alphabeticalMachine.changeToExtractionMode();

        maxAndMin.enqueue(maxValue);
        maxAndMin.enqueue(minValue);

        // Add all words to Queue from Sorting Machine
        while (alphabeticalMachine.size() > 0) {
            Pair<String, Integer> removeNext = alphabeticalMachine
                    .removeFirst();
            if (!Character.isDigit(removeNext.key().charAt(0))) {
                words.enqueue(removeNext.key());
            }
        }
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

        // Asks user for the name of the input file
        out.println("Please enter the name of the input file.");
        String inputName = in.nextLine();
        SimpleReader inputFile = new SimpleReader1L(inputName);

        // Ask the user for the name of the output file
        out.println("Please enter the name of the output file.");
        SimpleWriter outputFile = new SimpleWriter1L(in.nextLine());

        // Ask the user for the number of words to be included in the word cloud
        out.println(
                "How many words would you like to include in the word cloud?");
        int numberOfWords = in.nextInteger();

        // Generate header
        generateHeader(outputFile, inputName, numberOfWords);

        // Generate map containing words and their respective counts
        Map<String, Integer> wordMap = new Map2<>();
        getMapFromFile(wordMap, inputFile);

        // Sort the words in alphabetical order using a Queue
        Queue<String> words = new Queue2<>();
        Queue<Integer> maxAndMin = new Queue2<>();
        sortQueueFromMap(wordMap, words, numberOfWords, maxAndMin);

        // Process words to write to HTML
        processWords(wordMap, words, outputFile, numberOfWords, maxAndMin);

        // Output footer
        generateFooter(outputFile);

        /*
         * Close input and output streams
         */
        inputFile.close();
        outputFile.close();
        in.close();
        out.close();
    }

}
