package ast;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.List;

public class LAQuestion extends Question {

    String question;

    @Override
    void parse() {
        HashMap<String, HashMap<String, List<JSONObject>>> data = dataRetriever.getData();
        // TODO: ADD grade
        List<JSONObject> LAQuestions = data.get(subject).get("LA");

        int randomIndex = (int) (Math.random() % LAQuestions.size());
        JSONObject questionObject = LAQuestions.get(randomIndex);

        this.question = (String) questionObject.get("question");
    }

    @Override
    void evaluate() {
        writer.println("\\question " + question);
        writer.println("\\vspace{3in}");
    }
}
