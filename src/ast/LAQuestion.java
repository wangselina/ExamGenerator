package ast;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;

public class LAQuestion extends Question {

    String question;

    @Override
    void parse() {
        HashMap<String, HashMap<String, List<JSONObject>>> data = dataRetriever.getData();

        List<JSONObject> LAQuestions = data.get(Node.subject).get("LA");

        int randomIndex = (int) (Math.random() * LAQuestions.size());
        JSONObject questionObject = LAQuestions.get(randomIndex);

        this.question = (String) questionObject.get("question");

        if (Node.graded) {
            Node.grade = Node.grade + 4;
        }
    }

    @Override
    void evaluate() {
        String finalQuestion = Node.graded ? question + " (2 marks)" : question;
        writer.println("\\question " + finalQuestion);
        writer.println("\\vspace{3in}");
    }
}
