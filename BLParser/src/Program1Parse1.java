
import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Mathew Sinadinos
 * @author Daniel Legesse
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to the block string that is the body of
     *          the instruction string at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        // Declare name of instruction
        String instructionName;

        String expectInstruction = tokens.dequeue();
        // Deal with beginning of instruction code
        Reporter.assertElseFatalError(expectInstruction.equals("INSTRUCTION"),
                "Expected INSTRUCTION but got " + expectInstruction);

        instructionName = tokens.dequeue();

        String expectIs = tokens.dequeue();
        Reporter.assertElseFatalError(expectIs.equals("IS"),
                "Expected IS but got " + expectIs);

        // Deal with the actual instructions
        body.parseBlock(tokens);

        // Deal with the end
        String expectEnd = tokens.dequeue();
        Reporter.assertElseFatalError(expectEnd.equals("END"),
                "Expected END but got " + expectEnd);

        String newName = tokens.dequeue();
        Reporter.assertElseFatalError(newName.equals(instructionName),
                "Expected name " + instructionName + " but got " + newName);

        return instructionName;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        // Create new body and context
        Statement body = this.newBody();
        Map<String, Statement> context = this.newContext();

        // Handle opening of the program.

        String expectProgram = tokens.dequeue();
        Reporter.assertElseFatalError(expectProgram.equals("PROGRAM"),
                "Expected PROGRAM but got " + expectProgram);

        String name = tokens.dequeue();

        // Check to see if the instruction is a keyword of the language
        if (name.equals("move") || name.equals("turnleft")
                || name.equals("turnright") || name.equals("infect")
                || name.equals("skip")) {
            Reporter.assertElseFatalError(false,
                    "That program name cannot be used as it is a reserved word");
        }

        this.setName(name);
        String expectIs = tokens.dequeue();
        Reporter.assertElseFatalError(expectIs.equals("IS"),
                "Expected IS but got " + expectIs);

        // Handle the parsing of any instructions
        while (tokens.front().equals("INSTRUCTION")) {
            // Create a new statement body for the instruction
            Statement instructionBody = this.newBody();
            // Store the instruction name and instruction body
            String instructionName = parseInstruction(tokens, instructionBody);

            // Check to see if the instruction already exists
            Reporter.assertElseFatalError(!context.hasKey(instructionName),
                    "That instruction name is already defined");

            // Check to see if the instruction is a keyword of the language
            Reporter.assertElseFatalError(!Tokenizer.isKeyword(instructionName),
                    "That instruction name cannot be used because it's a reserved word");

            // Add the instruction to the map
            context.add(instructionName, instructionBody);
        }

        // Parse main body

        String expectBegin = tokens.dequeue();
        Reporter.assertElseFatalError(expectBegin.equals("BEGIN"),
                "Expected BEGIN but got " + expectBegin);
        body.parseBlock(tokens);

        // Handle end tokens

        String expectEnd = tokens.dequeue();
        Reporter.assertElseFatalError(expectEnd.equals("END"),
                "Expected END but got " + expectEnd);

        String expectName = tokens.dequeue();
        Reporter.assertElseFatalError(expectName.equals(name),
                "Expected " + name + " but got " + expectName);

        Reporter.assertElseFatalError(
                tokens.dequeue().equals(Tokenizer.END_OF_INPUT),
                "Expected END OF INPUT");

        // Swap everything back into this
        this.swapBody(body);
        this.swapContext(context);

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
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
