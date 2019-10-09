package ast;

public class FFQuestion extends Question {
    String question;

    @Override
    void parse() {
        question = tokenizer.getNext();
    }

    @Override
    void evaluate() {
        writer.println("\\question " + question);
        writer.println("\\vspace{1em}");
    }
}
