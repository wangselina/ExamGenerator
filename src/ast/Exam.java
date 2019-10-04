package ast;

import java.util.ArrayList;
import java.util.List;

public class Exam extends Node {

    private List<Question> questions = new ArrayList<>();
    private int grade = 0;

    @Override
    void parse() {

    }

    @Override
    void evaluate() {
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
