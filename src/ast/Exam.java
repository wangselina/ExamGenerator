package ast;

import lib.QuestionsNotFoundException;
import lib.SubjectNotFoundException;
import lib.TitleNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Exam extends Node {

    private Title title;
    private List<Question> questions = new ArrayList<>();

    @Override
    public void parse() {
        if(tokenizer.checkToken("title:")) {
            title = new Title();
            title.parse();
        } else {
            throw new TitleNotFoundException();
        }

        while(!tokenizer.checkToken("done")) {
            if (tokenizer.checkToken("get")){
                // ensure subject exists
                if(Node.subject == null) {
                    throw new SubjectNotFoundException();
                }
                tokenizer.getAndCheckNext("get");
                int numOfQuestions = Integer.parseInt(tokenizer.getNext());
                String typeOfQuestion = tokenizer.getNext();
                parseHelper(numOfQuestions, typeOfQuestion);
                tokenizer.getAndCheckNext("questions");
            } else if (tokenizer.checkToken("graded:")) {
                tokenizer.getAndCheckNext("graded:");
                String gradedValue = tokenizer.getNext();
                if (gradedValue.equals("T")) {
                    Node.graded = true;
                }
            } else if(tokenizer.checkToken("subject:")) {
                tokenizer.getAndCheckNext("subject:");
                Node.subject = tokenizer.getNext();
            } else if(tokenizer.checkToken("custom:")) {
                tokenizer.getAndCheckNext("custom:");
                parseHelper(1, "FF");
            }
        }
        if(questions.isEmpty()) {
            throw new QuestionsNotFoundException();
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
            } else if (type.equals("FF")) {
                q = new FFQuestion();
            }

            q.parse();
            questions.add(q);
        }

    }

    @Override
    public void evaluate() {
        writer.println("\\documentclass{exam}");
        title.evaluate();
        writer.println("\\begin{document}");
        writer.println("\\maketitle");
        if (Node.graded){
            writer.println("\\begin{center}");
            writer.println("\\addpoints");
            writer.println("\\gradetable");
            writer.println("\\end{center}");
            writer.println("\\pagebreak");
        }
        writer.println("\\begin{questions}");
        for (Question q : questions) {
            q.evaluate();
        }
        writer.println("\\end{questions}");
        writer.println("\\end{document}");
    }
}
