package ast;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;

public class SAQuestion extends Question {
    String question;

    @Override
    void parse() {
        HashMap<String, HashMap<String, List<JSONObject>>> data = dataRetriever.getData();
        // TODO: ADD grade
        List<JSONObject> MCQuestions = data.get(subject).get("SA");

        int randomIndex = (int) (Math.random() * MCQuestions.size());
        JSONObject questionObject = MCQuestions.get(randomIndex);

        question = (String) questionObject.get("question");

    }

    @Override
    public void evaluate() {
        writer.println("\\question " + question);
        writer.println("\\vspace{1in}");
    }
}
