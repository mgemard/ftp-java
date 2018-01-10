package command;

public class UnknownCommandException extends Exception {

    public UnknownCommandException(String line) {
        super(line);
    }

}
