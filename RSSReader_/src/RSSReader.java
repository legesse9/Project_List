import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to convert an XML RSS (version 2.0) feed from a given URL into the
 * corresponding HTML output file.
 *
 * @author Daniel Legesse
 *
 */
public final class RSSReader {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSReader() {
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
     * <table border="1">
     * <tr>
     * <th>Date</th>
     * <th>Source</th>
     * <th>News</th>
     * </tr>
     *
     * @param channel
     *            the channel element XMLTree
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputHeader(XMLTree channel, SimpleWriter out) {
        assert channel != null : "Violation of: channel is not null";
        assert out != null : "Violation of: out is not null";
        assert channel.isTag() && channel.label().equals("channel") : ""
                + "Violation of: the label root of channel is a <channel> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        String title = "";
        String description = "";
        String link = "";

        /*
         * Checking for description, title, and link within channel tag and
         * printing them out.
         */

        int titleIndex = getChildElement(channel, "title");
        if (channel.child(titleIndex).numberOfChildren() > 0) {
            title = channel.child(titleIndex).child(0).label();
        } else {
            title = "No title available";
        }
        int descIndex = getChildElement(channel, "description");
        if (channel.child(descIndex).numberOfChildren() > 0) {
            description = channel.child(descIndex).child(0).label();
        } else {
            description = "No description available";
        }
        int linkIndex = getChildElement(channel, "link");
        if (channel.child(linkIndex).numberOfChildren() > 0) {
            link = channel.child(linkIndex).child(0).label();
        } else {
            link = "No link available";
        }

        out.println("<html>");
        out.println("<head>");
        out.println("<tile><a href=\"" + link + "\">" + title + "</a></title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>" + description + "</p>");
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>Date</th>");
        out.println("<th>Source</th>");
        out.println("<th>News</th>");
        out.println("</tr>");

        // Calling processItem for every tag with "item" label

        for (int i = 0; i < channel.numberOfChildren(); i++) {
            if (channel.child(i).isTag()) {
                if (channel.child(i).label().equals("item")) {
                    processItem(channel.child(i), out);
                }
            }
        }
    }

    /**
     * Outputs the "closing" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * </table>
     * </body> </html>
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "closing" tags]
     */
    private static void outputFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of type tag of the {@code XMLTree}
     *         or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of type tag of the {@code XMLTree} or
     *   -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        int i = 0;
        boolean firstOccurence = false;
        if (xml.isTag()) {
            if (xml.numberOfChildren() > 0) {
                while (!firstOccurence) {
                    if ((xml.child(i).isTag())
                            && tag.equals(xml.child(i).label())) {
                        firstOccurence = true;
                    }
                    if (!firstOccurence) {
                        i++;
                    }
                }
            }
        }
        return i;
    }

    /**
     * Processes one news item and outputs one table row. The row contains three
     * elements: the publication date, the source, and the title (or
     * description) of the item.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures <pre>
     * out.content = #out.content *
     *   [an HTML table row with publication date, source, and title of news item]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        String pubDate = "";
        String source = "";
        String title = "";
        String link = "";
        String sourceURL = "";

        /*
         * Checking for pubDate, source, sourceURL, title, and link within each
         * index tag and printing them out in a table row
         */

        int pubDateIndex = getChildElement(item, "pubDate");
        if (pubDateIndex != -1
                && item.child(pubDateIndex).numberOfChildren() > 0) {
            pubDate = item.child(pubDateIndex).child(0).label();
        } else {
            pubDate = "No pubDate avaliabe";
        }
        out.println("<th>" + pubDate + "</>");

        int sourceIndex = getChildElement(item, "source");
        if (sourceIndex != -1
                && item.child(sourceIndex).numberOfChildren() > 0) {
            source = item.child(sourceIndex).child(0).label();
            sourceURL = item.child(sourceIndex).attributeValue("url");
        } else {
            source = "No source available";
            sourceURL = "No source URL available";
        }
        out.println("<th><a href=\"" + sourceURL + "\">" + source + "</>");

        int titleIndex = getChildElement(item, "title");
        if (titleIndex != -1 && item.child(titleIndex).numberOfChildren() > 0) {
            title = item.child(titleIndex).child(0).label();
        } else {
            title = "No title available";
        }

        int linkIndex = getChildElement(item, "link");
        if (linkIndex != -1 && item.child(linkIndex).numberOfChildren() > 0) {
            link = item.child(linkIndex).child(0).label();
        } else {
            link = "No link available";
        }
        out.println("<th><a href=\"" + link + "\">" + title + "</>");

        out.println("</tr>");

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * getting user input for URL for RSS feed and Output file name for html
         * page
         */
        out.println("URL: ");
        String url = in.nextLine();
        XMLTree xml = new XMLTree1(url);
        XMLTree channel = xml;
        if (xml.isTag() && xml.label().equals("rss")
                && xml.hasAttribute("version")
                && xml.attributeValue("version").equals("2.0")) {
            channel = xml.child(0);
        }
        out.println("Output file name: ");
        String outputfileName = in.nextLine();

        SimpleWriter outFile = new SimpleWriter1L(outputfileName);

        // Calling all methods to translate RSS feed to html page
        outputHeader(channel, outFile);
        int itemIndex = getChildElement(channel, "item");
        XMLTree item = channel.child(itemIndex);
        processItem(item, outFile);
        outputFooter(outFile);

        in.close();
        out.close();
    }

}