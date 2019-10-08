package ast;

import lib.DataRetriever;
import lib.Tokenizer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public abstract class Node {

    // TODO: instead of using default grade, subject, throw error when user hasn't provided grade, subject before getting questions
    protected static int grade = 1;
    protected static String subject = "math";
    protected Tokenizer tokenizer = Tokenizer.getTokenizer();
    static protected PrintWriter writer;
    protected DataRetriever dataRetriever = DataRetriever.getDataRetriever();

    public static void setWriter(String name) throws FileNotFoundException, UnsupportedEncodingException {
        writer = new PrintWriter(name, "UTF-8");
    }
    public static void closeWriter(){
        writer.close();
    }
    abstract void parse();
    abstract void evaluate();
}
