package ast;

public class Program extends Node {

    Node document;

    @Override
    public void parse() {
        if (tokenizer.checkToken("create exam")) {
            document = new Exam();
        } else if (tokenizer.checkToken("create worksheet")) {
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
