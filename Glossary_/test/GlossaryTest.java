import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import components.map.Map;
import components.map.Map1L;

public class GlossaryTest {

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetString1() {
        //Boundary Case as none of the terms are in the description
        Map<String, String> terms = new Map1L<String, String>();
        terms.add("justice", "just behavior or treatment");
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
        String str = Glossary.getString(terms, linkArray);
        String strExp = "<p><font color = 'black'>"
                + "just behavior or treatment" + "</font></p>";
        assertEquals(strExp, str);

    }

    @Test
    public void testGetString2() {
        //Normal Case as one of the terms are in the description
        Map<String, String> terms = new Map1L<String, String>();
        terms.add("language",
                "a set of strings of characters, each of which has meaning");
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
        String str = Glossary.getString(terms, linkArray);
        String strExp = "<p><font color = 'black'>"
                + "a set of strings of characters, each of which has "
                + "<a href=\""
                + "file:///C:/Users/Daniel%20Legesse/Documents/workspace/OsuCseWs"
                + "Template/workspace/Glossary_/meaning.html" + "\">"
                + "meaning" + "</a>" + "</font></p>";
        assertEquals(strExp, str);

    }

    @Test
    public void testGetString3() {
        //Special Case as "terms" as term is still in terms so the link should be there
        Map<String, String> terms = new Map1L<String, String>();
        terms.add("glossary", "a list of difficult or specialized terms");
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
        String str = Glossary.getString(terms, linkArray);
        String strExp = "<p><font color = 'black'>"
                + "a list of difficult or specialized " + "<a href=\""
                + "file:///C:/Users/Daniel%20Legesse/Documents/workspace/OsuCseWs"
                + "Template/workspace/Glossary_/term.html" + "\">" + "term"
                + "</a>" + "s" + "</font></p>";
        assertEquals(strExp, str);

    }

    @Test
    public void testGetTerms() {
        //Normal Case as a text with 3 terms and definitions
        Map<String, String> glossary = new Map1L<String, String>();
        Map<String, String> glossaryTemp = new Map1L<String, String>();
        glossary.add("justice", "just behavior or treatment");
        glossary.add("truth", "the quality or state of being true");
        glossary.add("lie",
                "to make an untrue statement with intent to deceive");
        Glossary.getTerms("data/terms2.txt", glossaryTemp);
        assertEquals(glossary, glossaryTemp);

    }

    @Test
    public void testGetTerms2() {
        /*
         * Special Case as a text with 3 terms and definitions but definitions
         * is more than one line
         */
        Map<String, String> glossary = new Map1L<String, String>();
        Map<String, String> glossaryTemp = new Map1L<String, String>();
        glossary.add("justice", "just behavior or treatment");
        glossary.add("truth", "the quality or state of being true");
        glossary.add("glossary",
                "a list of difficult or specialized terms, with their definitions, "
                        + "usually near the end of a book");
        Glossary.getTerms("data/terms3.txt", glossaryTemp);
        assertEquals(glossary, glossaryTemp);

    }

}
