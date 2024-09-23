import java.util.Arrays;

import components.map.Map;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Outputs given text page into html page with links.
 *
 * @author Daniel Legesse
 *
 */
public final class Glossary {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Glossary() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static void myMethod() {
        /*
         * Put your code for myMethod here
         */
    }

    /**
     * Uses map to check if descriptions of terms have one of the other terms
     * inside it and adds the link to that term if it does.
     *
     * @param terms
     *            A map with terms and their definitions
     * @param linkArray
     *            a string array of all the links to the HTML Pages of terms
     *
     * @return the html format text that will be printed
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    static String getString(Map<String, String> terms, String[] linkArray) {
        String title = "";
        String description = "";
        Map<String, String> termsCopy = new Map1L<String, String>();
        Map.Pair<String, String> x = terms.removeAny();
        title = x.key();
        description = x.value();
        termsCopy.add(title, description);
        if (description.contains("glossary") || description.contains("word")
                || description.contains("definition")
                || description.contains("meaning")
                || description.contains("term")
                || description.contains("language")
                || description.contains("book")) {
            if (description.contains("meaning")) {
                int y = description.indexOf("meaning");
                String str2 = "meaning";
                String descriptionCut = description.substring(0, y);
                descriptionCut = descriptionCut + "<a href=\"" + linkArray[1]
                        + "\">" + "meaning" + "</a>";
                description = "<p><font color = 'black'>" + descriptionCut
                        + description.substring(y + str2.length(),
                                description.length())
                        + "</font></p>";

            }
            if (description.contains("glossary")) {
                int y = description.indexOf("glossary");
                String str2 = "glossary";
                String descriptionCut = description.substring(0, y);
                descriptionCut = descriptionCut + "<a href=\"" + linkArray[0]
                        + "\">" + "glossary" + "</a>";
                description = "<p><font color = 'black'>" + descriptionCut
                        + description.substring(y + str2.length(),
                                description.length())
                        + "</font></p>";

            }
            if (description.contains("book")) {
                int y = description.indexOf("book");
                String str2 = "book";
                String descriptionCut = description.substring(0, y);
                descriptionCut = descriptionCut + "<a href=\"" + linkArray[2]
                        + "\">" + "book" + "</a>";
                description = "<p><font color = 'black'>" + descriptionCut
                        + description.substring(y + str2.length(),
                                description.length())
                        + "</font></p>";

            }
            if (description.contains("term")) {
                int y = description.indexOf("term");
                String str2 = "term";
                String descriptionCut = description.substring(0, y);
                final int three = 3;
                descriptionCut = descriptionCut + "<a href=\""
                        + linkArray[three] + "\">" + "term" + "</a>";
                description = "<p><font color = 'black'>" + descriptionCut
                        + description.substring(y + str2.length(),
                                description.length())
                        + "</font></p>";

            }
            if (description.contains("definition")) {
                int y = description.indexOf("definition");
                String str2 = "definition";
                String descriptionCut = description.substring(0, y);
                final int four = 4;
                descriptionCut = descriptionCut + "<a href=\"" + linkArray[four]
                        + "\">" + "definition" + "</a>";
                description = "<p><font color = 'black'>" + descriptionCut
                        + description.substring(y + str2.length(),
                                description.length())
                        + "</font></p>";

            }
            if (description.contains("language")) {
                int y = description.indexOf("language");
                String str2 = "language";
                String descriptionCut = description.substring(0, y);
                final int five = 5;
                descriptionCut = descriptionCut + "<a href=\"" + linkArray[five]
                        + "\">" + "language" + "</a>";
                description = "<p><font color = 'black'>" + descriptionCut
                        + description.substring(y + str2.length(),
                                description.length())
                        + "</font></p>";

            }
            if (description.contains("word")) {
                int y = description.indexOf("word");
                String str2 = "word";
                String descriptionCut = description.substring(0, y);
                String word = "word";
                final int six = 6;
                descriptionCut = descriptionCut + "<a href=\"" + linkArray[six]
                        + "\">" + "word" + "</a>";
                description = "<p><font color = 'black'>" + descriptionCut
                        + description.substring(y + str2.length(),
                                description.length())
                        + "</font></p>";

            }
        } else {
            description = "<p><font color = 'black'>" + description
                    + "</font></p>";
        }

        return description;

    }

    /**
     * Outputs the "opening" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * <html> <head> <title>the channel tag title as the page title</title>
     * </head> <body>
     * <h1>the page title inside a link to the <channel> link</h1>
     * <p>
     * the channel description
     * </p>
     *
     * @param terms
     *            A map with terms and their definitions
     * @param linkArray
     *            a string array of all the links to the HTML Pages of terms
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputHeader(Map<String, String> terms,
            String[] linkArray, SimpleWriter out) {

        String title = "";
        String description = "";
        Map<String, String> termsCopy = new Map1L<String, String>();
        // Extracts key and values from Map with terms and definitions
        Map.Pair<String, String> x = terms.removeAny();
        title = x.key();
        description = x.value();
        termsCopy.add(title, description);
        String link = "file:///C:/Users/Daniel%20Legesse/Documents/workspace/Osu"
                + "CseWsTemplate/workspace/Glossary_/Test.html";
        // outputs individual page for terms
        out.println("<html>");
        out.println("<head>");
        out.println("<tile><em><strong><font color = 'red'><font size = '5'>"
                + title + "</font></em></strong></title>");
        out.println("</head>");
        out.println("<body>");
        String str = getString(termsCopy, linkArray);
        out.println(str);
        out.println("<p><hr>" + "</hr></p>");
        out.println("<p><font color = 'black'>" + "Return to " + "<a href=\""
                + link + "\">" + "index" + "</a>" + "</font></p>");
    }

    /**
     * Inputs a "menu" of terms (items) and their defintions from the given file
     * and stores them in the given {@code Map}.
     *
     * @param fileName
     *            the name of the input file
     * @param terms
     *            A map of terms and their definitions
     * @replaces priceMap
     * @requires <pre>
     * [file named fileName exists but is not open, and has the
     *  format of one "term" (unique in the file) and one definition
     *  per line, with word and price separated by lines]
     * </pre>
     * @ensures [priceMap contains word -> price mapping from file fileName]
     */
    static void getTerms(String fileName, Map<String, String> terms) {
        assert fileName != null : "Violation of: fileName is not null";
        assert terms != null : "Violation of: priceMap is not null";

        SimpleReader input = new SimpleReader1L(fileName);
        SimpleWriter out = new SimpleWriter1L();
        // Extracts terms and definitions line by line from text file
        while (!input.atEOS()) {
            String term = input.nextLine();
            String definition = input.nextLine();
            String empty = input.nextLine();
            if (!empty.isEmpty()) {
                definition = definition + empty;
                empty = input.nextLine();
            }
            terms.add(term, definition);
        }

        input.close();

    }

    /**
     * Outputs title and an unordered list of links to individual HTML pages.
     *
     * @param termArray
     *            a string array of all the terms
     * @param linkArray
     *            a string array of all the links to the HTML Pages of terms
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "closing" tags]
     */
    private static void outputList(String[] termArray, String[] linkArray,
            SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        // Outputs opening page with links to term pages
        int size = termArray.length;
        String title = "Glossary";
        out.println("<html>");
        out.println("<head>");
        out.println("<h1>" + title + "</h1>");
        out.println("<p><hr>" + "</hr></p>");
        out.println("<p>" + "Index" + "</p>");
        out.println("</head>");
        out.println("<ul>");
        for (int i = 0; i < size; i++) {
            out.println("<li>");
            out.println("<p>");
            out.println("<a href=\"" + linkArray[i] + "\">" + termArray[i]
                    + "</a>");
            out.println("</p>");
            out.println("</li>");
        }
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments12
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        Map<String, String> terms = new Map1L<String, String>();
        Map<String, String> termsCopy = new Map1L<String, String>();
        Map<String, String> termsTemp = new Map1L<String, String>();
        final int seven = 7;
        // Creates Map for terms and definitions from text page
        String[] termArray = new String[seven];
        String[] definitionArray = new String[seven];
        getTerms("data/terms.txt", termsTemp);
        out.println("Map: " + terms);
        // Creates copies of the map to call other methods with
        for (int i = 0; i < seven; i++) {
            Map.Pair<String, String> x = termsTemp.removeAny();
            String title = x.key();
            String description = x.value();
            terms.add(title, description);
            termsCopy.add(title, description);
        }
        out.println("MapCopy: " + termsCopy);
        int size = termsCopy.size();

        for (int i = 0; i < size; i++) {
            Map.Pair<String, String> x = termsCopy.removeAny();
            String title = x.key();
            String description = x.value();
            termArray[i] = title;
            definitionArray[i] = description;
        }
        out.println("Terms: " + Arrays.toString(termArray));
        out.println("Definitions: " + Arrays.toString(definitionArray));
        // Array of links to individual pages
        String[] linkArray = {
                "file:///C:/Users/Daniel%20Legesse/Documents/workspace/OsuCseWs"
                        + "Template/workspace/Glossary_/glossary.html",
                "file:///C:/Users/Daniel%20Legesse/Documents/workspace/OsuCseWs"
                        + "Template/workspace/Glossary_/meaning.html",
                "file:///C:/Users/Daniel%20Legesse/Documents/workspace/OsuCseWs"
                        + "Template/workspace/Glossary_/book.html",
                "file:///C:/Users/Daniel%20Legesse/Documents/workspace/OsuCseWs"
                        + "Template/workspace/Glossary_/term.html",
                "file:///C:/Users/Daniel%20Legesse/Documents/workspace/OsuCseWs"
                        + "Template/workspace/Glossary_/definition.html",
                "file:///C:/Users/Daniel%20Legesse/Documents/workspace/OsuCseWs"
                        + "Template/workspace/Glossary_/language.html",
                "file:///C:/Users/Daniel%20Legesse/Documents/workspace/OsuCseWs"
                        + "Template/workspace/Glossary_/word.html" };

        for (int i = 0; i < size; i++) {
            String outputfileName = termArray[i] + ".html";
            SimpleWriter outFile = new SimpleWriter1L(outputfileName);
            outputHeader(terms, linkArray, outFile);
        }

        out.println("Output file name: ");
        String outputfileName = in.nextLine();
        SimpleWriter outFile = new SimpleWriter1L(outputfileName);
        outputList(termArray, linkArray, outFile);

        out.close();
        in.close();
    }

}
