package ast;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MCQuestion extends Question {

    String question;
    List<String> options = new ArrayList<>();

    @Override
    void parse() {
        HashMap<String, HashMap<String, List<JSONObject>>> data = dataRetriever.getData();
        // TODO: ADD grade
        List<JSONObject> MCQuestions = data.get(Node.subject).get("MC");

        int randomIndex = (int) (Math.random() * MCQuestions.size());
        JSONObject questionObject = MCQuestions.get(randomIndex);

        question = (String) questionObject.get("question");

        JSONArray arrayOfOptions = (JSONArray) questionObject.get("options");
        parseOptions(arrayOfOptions);
    }

    private void parseOptions(JSONArray list) {
        Iterator<String> iterator = list.iterator();

        while(iterator.hasNext()) {
            options.add(iterator.next());
        }
    }

    @Override
    void evaluate() {
        writer.println("\\question " + question);
        writer.println("\\begin{enumerate}");
        for (String option: options) {
            writer.println("\\item " + option);
        }
        writer.println("\\end{enumerate}");
        writer.println("\\vspace{1em}");
    }
}
