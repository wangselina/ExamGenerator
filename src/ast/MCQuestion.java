package ast;

import lib.DataRetriever;

import java.util.ArrayList;
import java.util.List;

public class MCQuestion extends Question {

    String question;
    List<String> options = new ArrayList<>();

    @Override
    void parse() {
        DataRetriever.getDataRetriever().getData();
    }

    @Override
    void evaluate() {

    }
}
