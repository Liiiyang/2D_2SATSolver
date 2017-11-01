import java.util.ArrayList;
import java.util.HashMap;

public class Literal {
    private int value;
    private boolean p;
    private static HashMap<Integer,ArrayList<Literal>> litHistory;

    Literal(int value, boolean p) {
        this.p=p;
        this.value=value;
    }

    public int getValue() {
        return value;
    }

    public boolean getP() {
        return p;
    }
    public Literal neg(){
        // returning the negation from Hashmap: litHistory
        if(p==true){return litHistory.get(value).get(1);}
        else {return litHistory.get(value).get(0);}
    }
    public HashMap<Integer, ArrayList<Literal>> getLitHistory(){
        return litHistory;
    }



}
