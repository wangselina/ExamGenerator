package ast;

public class SAQuestion extends Question {
    String question;

    @Override
    void parse() {

    }

    @Override
    public void evaluate() {
        writer.println("\\question " + question);
        writer.println("\\vspace{1in}");
    }
}
