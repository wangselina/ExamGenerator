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

    }

    @Override
    void evaluate() {
        String questionCommand = Node.graded ? "\\question[4] " : "\\question ";
        writer.println(questionCommand + question);
        writer.println("\\vspace{3in}");
    }
}
