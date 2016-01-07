package maze;

public class MazeReadingException extends Exception {

    private static final long serialVersionUID = 1L;

    public MazeReadingException(String fileName, int lineNumber, String errorMessage) {
        super(errorMessage + " in " + fileName + " at line " + Integer.toString(lineNumber));
    }
}
