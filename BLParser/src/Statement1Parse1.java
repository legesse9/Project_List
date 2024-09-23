import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary methods {@code parse} and
 * {@code parseBlock} for {@code Statement}.
 *
 * @author Mathew Sinadinos
 * @author Daniel Legesse
 *
 */
public final class Statement1Parse1 extends Statement1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Converts {@code c} into the corresponding {@code Condition}.
     *
     * @param c
     *            the condition to convert
     * @return the {@code Condition} corresponding to {@code c}
     * @requires [c is a condition string]
     * @ensures parseCondition = [Condition corresponding to c]
     */
    private static Condition parseCondition(String c) {
        assert c != null : "Violation of: c is not null";
        assert Tokenizer
                .isCondition(c) : "Violation of: c is a condition string";
        return Condition.valueOf(c.replace('-', '_').toUpperCase());
    }

    /**
     * Parses an IF or IF_ELSE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"IF"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an if string is a proper prefix of #tokens] then
     *  s = [IF or IF_ELSE Statement corresponding to if string at start of #tokens]  and
     *  #tokens = [if string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseIf(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("IF") : ""
                + "Violation of: <\"IF\"> is proper prefix of tokens";

        // Ensure prefix is if
        String isIf = tokens.dequeue();
        Reporter.assertElseFatalError(isIf.equals("IF"),
                "Expected IF but found " + isIf);

        // Grab condition of if
        String strCond = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isCondition(strCond),
                strCond + " is not a valid condition.");
        Condition cond = parseCondition(strCond);

        // Ensure "then" is there
        String isThen = tokens.dequeue();
        Reporter.assertElseFatalError(isThen.equals("THEN"),
                "Expected THEN but found " + isThen);

        // Parse the block
        Statement ifBody = s.newInstance();
        ifBody.parseBlock(tokens);

        // Check to see if it is an if-else statement
        String isElse = tokens.front();
        if (isElse.equals("ELSE")) {
            tokens.dequeue();
            Statement elseBody = s.newInstance();
            elseBody.parseBlock(tokens);
            // Assemble "if else" if else exists
            s.assembleIfElse(cond, ifBody, elseBody);
        } else {
            // Otherwise assemble if statement
            s.assembleIf(cond, ifBody);
        }

        // Deal with the end
        String isEnd = tokens.dequeue();
        Reporter.assertElseFatalError(isEnd.equals("END"),
                "Expected END but found " + isEnd);

        isIf = tokens.dequeue();
        Reporter.assertElseFatalError(isIf.equals("IF"),
                "Expected IF but found " + isIf);
    }

    /**
     * Parses a WHILE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"WHILE"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [a while string is a proper prefix of #tokens] then
     *  s = [WHILE Statement corresponding to while string at start of #tokens]  and
     *  #tokens = [while string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseWhile(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("WHILE") : ""
                + "Violation of: <\"WHILE\"> is proper prefix of tokens";

        // Ensure prefix is while
        String isWhile = tokens.dequeue();
        Reporter.assertElseFatalError(isWhile.equals("WHILE"),
                "Expected WHILE but found " + isWhile);

        // Grab condition of while
        String strCond = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isCondition(strCond),
                strCond + " is not a valid condition.");
        Condition cond = parseCondition(strCond);

        // Ensure "do" is there
        String isDo = tokens.dequeue();
        Reporter.assertElseFatalError(isDo.equals("DO"),
                "Expected DO but found " + isDo);

        // Parse the block
        Statement whileBody = s.newInstance();
        whileBody.parseBlock(tokens);

        // Assemble while statement
        s.assembleWhile(cond, whileBody);

        // Deal with the end
        String isEnd = tokens.dequeue();
        Reporter.assertElseFatalError(isEnd.equals("END"),
                "Expected END but found " + isEnd);

        isWhile = tokens.dequeue();
        Reporter.assertElseFatalError(isWhile.equals("WHILE"),
                "Expected WHILE but found " + isWhile);
    }

    /**
     * Parses a CALL statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires [identifier string is a proper prefix of tokens]
     * @ensures <pre>
     * s =
     *   [CALL Statement corresponding to identifier string at start of #tokens]  and
     *  #tokens = [identifier string at start of #tokens] * tokens
     * </pre>
     */
    private static void parseCall(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0
                && Tokenizer.isIdentifier(tokens.front()) : ""
                        + "Violation of: identifier string is proper prefix of tokens";

        // Grab name of call
        String name = tokens.dequeue();

        // Ensure that call exists
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(name),
                name + "is not a valid call");

        // Assemble call
        s.assembleCall(name);
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Statement1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        // Grab next token
        String next = tokens.front();

        // Check to see if it is an if or while statement
        if (Tokenizer.isKeyword(next)) {
            if (next.equals("WHILE")) {
                parseWhile(tokens, this);
            } else if (next.equals("IF")) {
                parseIf(tokens, this);
            }
            // Check to see if it is a call
        } else if (Tokenizer.isIdentifier(next)) {
            parseCall(tokens, this);
        } else {
            Reporter.assertElseFatalError(false,
                    "Token is neither a keyword or identifier.");
        }
    }

    @Override
    public void parseBlock(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        Statement s = this.newInstance();
        while (!tokens.front().equals("END")
                && !tokens.front().equals(Tokenizer.END_OF_INPUT)
                && !tokens.front().equals("ELSE")) {
            this.parse(tokens);
            s.addToBlock(s.lengthOfBlock(), this);
        }

        this.transferFrom(s);
    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL statement(s) file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Statement s = new Statement1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        s.parse(tokens); // replace with parseBlock to test other method
        /*
         * Pretty print the statement(s)
         */
        out.println("*** Pretty print of parsed statement(s) ***");
        s.prettyPrint(out, 0);

        in.close();
        out.close();
    }

}
