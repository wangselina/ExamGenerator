package lib;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException(){ System.out.println("You forgot to specify the subject! Please set the subject.");
    }
}
