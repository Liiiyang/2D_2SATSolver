import java.util.ArrayList;
import java.util.HashMap;

public class Literal {
    private int value;
    private boolean p;
    private boolean visited=false;
    public static HashMap<Integer,ArrayList<Literal>> litHistory=new HashMap<Integer, ArrayList<Literal>>(FileExtract.numVar+1, 1);

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
        for (Literal a:litHistory.get(value)) {
            if (!a.equals(this)) {
                return a;
            }
        }
        return this;
    }

    public HashMap<Integer, ArrayList<Literal>> getLitHistory(){
        return litHistory;
    }

    public void visited() {
        this.visited=true;
    }

    public void resetVisited() {
        this.visited = false;
    }

    public boolean isVisited() {
        return visited;
    }

    public void resetAllVisited(){
        for (int x:litHistory.keySet()){
            Literal pos=litHistory.get(x).get(0);
            Literal neg=litHistory.get(x).get(1);
            pos.resetVisited();
            neg.resetVisited();
        }
    }


}
