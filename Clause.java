import java.util.ArrayList;
import java.util.Collections;

public class Clause {
    Literal a;
    Literal b;
    Clause(Literal a, Literal b){
        this.a=a;
        this.b=b;
    }
    public ArrayList<Literal>implies(){
        ArrayList<Literal> clauses_Literal = new ArrayList<Literal>();
        Collections.addAll(clauses_Literal,a.neg(),b,b.neg(),a);
        return clauses_Literal;
    }

}
