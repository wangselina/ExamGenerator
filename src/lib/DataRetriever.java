package lib;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class DataRetriever {

    private static DataRetriever dataRetriever;
    private HashMap<String, HashMap<String, List<JSONObject>>> data = new HashMap<>();

    private DataRetriever() {

        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("src/questions.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray mathQuestions =  (JSONArray) jsonObject.get("math");
            JSONArray sceinceQuestions = (JSONArray)  jsonObject.get("science");

            data.put("math", parseHelper(mathQuestions));
            data.put("science",parseHelper(sceinceQuestions));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private  HashMap<String,  List<JSONObject>> parseHelper(JSONArray questions) {
        Iterator<JSONObject> iterator = questions.iterator();
        HashMap<String,  List<JSONObject>> map = new HashMap<>();

        map.put("MC", new ArrayList<JSONObject>());
        map.put("SA", new ArrayList<JSONObject>());
        map.put("LA", new ArrayList<JSONObject>());

        while (iterator.hasNext()) {
            JSONObject question = iterator.next();
            String type = (String) question.get("type");
            map.get(type).add(question);
        }

        return map;
    }

    public static DataRetriever getDataRetriever() {
        return dataRetriever;
    }

    public static void makeDataRetriever() {
        if (dataRetriever == null){
            dataRetriever = new DataRetriever();
        }
    }

    public  HashMap<String, HashMap<String, List<JSONObject>>> getData() {
        return data;
    }
}
