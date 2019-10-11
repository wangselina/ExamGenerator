package ast;

public class Program extends Node {

    Node document;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("create");
        document = new Exam();
        document.parse();
        tokenizer.getAndCheckNext("done");
    }

    @Override
    public void evaluate() {
        document.evaluate();
    }
}
