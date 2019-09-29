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

    }
}
