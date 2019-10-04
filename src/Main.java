import ast.Exam;
import ast.Node;
import lib.Tokenizer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        List<String> literals = Arrays.asList("");
        Tokenizer.makeTokenizer("input.txt",literals);
        Exam exam = new Exam();
        exam.parse();
        Exam.setWriter("output.tex");
        exam.evaluate();
        Node.closeWriter();
    }
}
