package trail;

public class TrailException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public TrailException(){
        super("Trail error");
    }
    
    public TrailException(String msg){
        super(msg);
    }

}
