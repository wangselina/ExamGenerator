package ast;

public class FFQuestion extends Question {
    String question;
    int marks = 0;

    @Override
    void parse() {
        question = tokenizer.getNext();
        if (Node.graded) {
            marks = Integer.parseInt(tokenizer.getNext());
            tokenizer.getAndCheckNext("marks");
        }
    }

    @Override
    void evaluate() {
        String finalQuestion = Node.graded ? question + " ("+ marks + " marks)" : question;
        writer.println("\\question " + finalQuestion);
        writer.println("\\vspace{1em}");
    }
}
