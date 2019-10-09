package ast;

public class FFQuestion extends Question {
    String question;

    @Override
    void parse() {
        question = tokenizer.getNext();
    }

    @Override
    void evaluate() {

    }
}
