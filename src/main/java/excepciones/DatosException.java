package excepciones;

public class DatosException extends Exception{

    public DatosException(){

    }
    public DatosException(String email){
        super(email);
    }
}
