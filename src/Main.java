import ast.Exam;
import ast.Node;
import ast.Program;
import lib.Tokenizer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        List<String> literals = Arrays.asList("create", "get", "done", "questions");
        Tokenizer.makeTokenizer("src/input.txt", literals);
        Program document = new Program();
        document.parse();
        Exam.setWriter("output.tex");
        document.evaluate();
        Node.closeWriter();
    }
}
