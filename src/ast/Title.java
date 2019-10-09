package ast;

public class Title extends Node {
    String title;
    @Override
    public void parse() {
        tokenizer.getAndCheckNext("title:");
        title = tokenizer.getNext();
    }

    @Override
    public void evaluate() {
        writer.println("\\title{" + title + "}");
    }
}
