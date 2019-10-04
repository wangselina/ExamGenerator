package ast;

public class LAQuestion extends Question {

    String question;

    @Override
    void parse() {

    }

    @Override
    void evaluate() {
        writer.println("\\question " + question);
        writer.println("\\vspace{3in}");
    }
}
