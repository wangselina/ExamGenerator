package lib;

public class QuestionsNotFoundException extends RuntimeException {
    public QuestionsNotFoundException(){ System.out.println("You forgot to add questions! Please add some questions.");
    }
}
