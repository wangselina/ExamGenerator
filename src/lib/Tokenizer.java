package lib;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {


    private static String program;
    private static List<String> literals;
    private String[] tokens;
    private int currentToken;
    private static Tokenizer theTokenizer;

    private Tokenizer(String filename, List<String> literalsList){
        literals = literalsList;
        try {
            program = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Didn't find file");
            System.exit(0);
        }
        spaceKillingTokenize();
    }

    //modifies: this.tokens
    //effects: will result in a list of tokens (sitting at this.tokens) that has spaces within tokens.
    //          this might mean you need to strip off spaces around things during parsing (ick)
    private void spaceHappyTokenize (){
        String tokenizedProgram = program;
        tokenizedProgram = tokenizedProgram.replace("\n","");
        System.out.println(program);
        for (String s : literals){
            tokenizedProgram = tokenizedProgram.replace(s,"_"+s+"_");
            System.out.println(tokenizedProgram);
        }
        tokenizedProgram = tokenizedProgram.replaceAll("__","_");
        System.out.println(tokenizedProgram);
        String [] temparray=tokenizedProgram.split("_");
        tokens = new String[temparray.length-1];
        System.arraycopy(temparray,1,tokens,0,temparray.length-1);
        System.out.println(Arrays.asList(tokens));
    }

    //modifies: this.tokens
    //effects: will result in a list of tokens (sitting at this.tokens) that has no spaces within tokens.
    //          so if you want spaces within tokens, use the spaceHappyTokenize method (above) instead
    private void spaceKillingTokenize(){
        String tokenizedProgram = program;
        tokenizedProgram = tokenizedProgram.replace("\n","");

        // only replace numbers outside of quotation marks
        tokenizedProgram = tokenizedProgram.replaceAll("([0-9](?=(\"[^\"]*\"|[^\"])*$))","_$1_");
        System.out.println(program);

        for (String s : literals){
            tokenizedProgram = tokenizedProgram.replace(s,"_"+s+"_");
            System.out.println(tokenizedProgram);
        }

        // regex for all spaces not inside quotations
        String regex = "\\s(?=(\"[^\"]*\"|[^\"])*$)";
        tokenizedProgram = tokenizedProgram.replaceAll(regex,"");

        // remove quotation in tokens
        tokenizedProgram = tokenizedProgram.replaceAll("\"","");
        System.out.println(tokenizedProgram);
        String [] temparray=tokenizedProgram.split("[_]+");
        tokens = new String[temparray.length-1];

        System.arraycopy(temparray,1,tokens,0,temparray.length-1);
        System.out.println(Arrays.asList(tokens));
    }


    private String checkNext(){
        String token="";
        if (currentToken<tokens.length){
            token = tokens[currentToken];
        }
        else
            token="NO_MORE_TOKENS";
        return token;
    }

    public String getNext(){
        String token="";
        if (currentToken<tokens.length){
            token = tokens[currentToken];
            currentToken++;
        }
        else
            token="NULLTOKEN";
        return token;
    }


    public boolean checkToken(String regexp){
        String s = checkNext();
        System.out.println("comparing: "+s+"  to  "+regexp);
        return (s.matches(regexp));
    }


    public String getAndCheckNext(String regexp){
        String s = getNext();
        if (!s.matches(regexp)) System.exit(0);
        System.out.println("matched: "+s+"  to  "+regexp);
        return s;
    }

    public boolean moreTokens(){
        return currentToken<tokens.length;
    }

    public static void makeTokenizer(String filename, List<String> literals){
        if (theTokenizer==null){
            theTokenizer = new Tokenizer(filename,literals);
        }
    }

    public static Tokenizer getTokenizer(){
        return theTokenizer;
    }

}
