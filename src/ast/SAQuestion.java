package ast;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;

public class SAQuestion extends Question {
    String question;

    @Override
    void parse() {
        HashMap<String, HashMap<String, List<JSONObject>>> data = dataRetriever.getData();

        List<JSONObject> MCQuestions = data.get(Node.subject).get("SA");

        int randomIndex = (int) (Math.random() * MCQuestions.size());
        JSONObject questionObject = MCQuestions.get(randomIndex);

        question = (String) questionObject.get("question");

        if (Node.graded) {
            Node.grade = Node.grade + 2;
        }

    }

    @Override
    public void evaluate() {
        String finalQuestion = Node.graded ? question + " (2 marks)" : question;
        writer.println("\\question " + finalQuestion);
        writer.println("\\vspace{1in}");
    }
}
