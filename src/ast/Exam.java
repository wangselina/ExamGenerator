package ast;

import java.util.ArrayList;
import java.util.List;

public class Exam extends Node {

    private List<Question> questions = new ArrayList<>();
    private int grade = 0;

    @Override
    public void parse() {
        tokenizer.getAndCheckNext("exam");
        while(!tokenizer.checkToken("done")) {
            if (tokenizer.checkToken("get")){
                tokenizer.getAndCheckNext("get");
                int numOfQuestions = Integer.parseInt(tokenizer.getNext());
                String typeOfQuestion = tokenizer.getNext();
                tokenizer.getAndCheckNext("questions");
                parseHelper(numOfQuestions, typeOfQuestion);

            } else if (tokenizer.checkToken("grade")) {
                tokenizer.getAndCheckNext("grade");
                grade = Integer.parseInt(tokenizer.getNext());
            }
        }
    }

    private void parseHelper(int numOfQuestion, String type) {
        for (int i = 0; i < numOfQuestion; i++) {
            Question q = null;
            if (type.equals("MC")) {
                q = new MCQuestion();
            } else if (type.equals("SA")){
                q = new SAQuestion();
            } else if (type.equals("LA")){
                q = new LAQuestion();
            }

            q.parse();
            questions.add(q);
        }

    }

    @Override
    public void evaluate() {
        writer.println("\\documentclass{exam}");
        writer.println("\\begin{document}");
        writer.println("\\begin{questions}");
        for (Question q : questions) {
            q.evaluate();
        }
        writer.println("end{questions}");
        writer.println("end{document}");
    }
}
