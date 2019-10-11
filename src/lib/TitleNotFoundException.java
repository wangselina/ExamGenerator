package lib;

public class TitleNotFoundException extends RuntimeException {
    public TitleNotFoundException(){ System.out.println("You forgot to specify the title! Please set the title.");
    }
}
