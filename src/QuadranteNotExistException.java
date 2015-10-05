
public class QuadranteNotExistException extends Exception{

    public QuadranteNotExistException() {
        super("Quadrante não existe.");
    }

    public QuadranteNotExistException(String message) {
        super(message);
    }

}
