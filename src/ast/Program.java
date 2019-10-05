package ast;

public class Program extends Node {

    Node document;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("create");

        if (tokenizer.checkToken("exam")) {
            document = new Exam();
        } else if (tokenizer.checkToken("worksheet")) {
            document = new Worksheet();
        }

        document.parse();
        tokenizer.getAndCheckNext("done");
    }

    @Override
    public void evaluate() {
        document.evaluate();
    }
}
