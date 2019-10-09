import ast.Node;
import ast.Program;
import lib.DataRetriever;
import lib.Tokenizer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        List<String> literals = Arrays.asList("create", "get", "done", "questions", "grade", "subject:", "custom:", "title:");
        Tokenizer.makeTokenizer("src/input.txt", literals);
        DataRetriever.makeDataRetriever();
        Program document = new Program();
        document.parse();
        document.setWriter("output.tex");
        document.evaluate();
        Node.closeWriter();
    }
}
