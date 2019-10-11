package ast;

import lib.DataRetriever;
import lib.Tokenizer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public abstract class Node {

    protected static boolean graded = false;
    protected static String subject;
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
