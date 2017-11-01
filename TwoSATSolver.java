/**
 * Created by Li Yang on 30/10/2017.
 */
import java.util.*;

public class TwoSATSolver {
    TwoSATSolver(){}

    public void addLine(int lita, int litb, Graph implictGraph, Graph revGraph) /* Remove1??? throws InvalidInputException*/ {
        //Boolean is for pos or negative

        /* Remove1???
        if(!fe.getFiletype().equals("cnf")){
            throw new InvalidInputException();
        }
        */

        Literal a0 = null, a1;
        Literal b0, b1;
        Boolean a_sign, b_sign;
        //neg or pos for a and b
        a_sign = (lita > 0);
        b_sign = (litb > 0);
        HashMap<Integer, ArrayList<Literal>> litHistory = a0.getLitHistory(); // Setting to the Hashmap in Literal Lit History

        if (litHistory.containsKey(lita)) {
            ArrayList<Literal> currentList = litHistory.get(lita);
            a0 = currentList.get(0);
            a1 = currentList.get(1);
        } else {
            //creating Literals for all 2
            a0 = new Literal(Math.abs(lita), true);
            a1 = new Literal(Math.abs(lita), false);

            //enter in hashMap litHistory
            ArrayList<Literal> a_ArrayList = new ArrayList<Literal>();
            Collections.addAll(a_ArrayList, a0, a1);
            litHistory.put(lita, a_ArrayList);
        }
        if (litHistory.containsKey((int) litb)) {
            ArrayList<Literal> currentList = litHistory.get(litb);
            b0 = currentList.get(0);
            b1 = currentList.get(1);
        } else {
            //creating Literals for all 2
            b0 = new Literal(Math.abs(litb), true);
            b1 = new Literal(Math.abs(litb), false);

            //enter in hashMap litHistory
            ArrayList<Literal> b_ArrayList = new ArrayList<Literal>();
            Collections.addAll(b_ArrayList, b0, b1);
            litHistory.put(lita, b_ArrayList);
        }

        //Selecting Correct Literal setting to a and b
        Literal a;
        Literal b;
        if (a_sign = true) {
            a = a0;
        } else {
            a = a1;
        }
        if (b_sign = true) {
            b = b0;
        } else {
            b = b1;
        }

        //Creates new Clause
        Clause c = new Clause(a, b);

        //add postive and negative nodes
        implictGraph.addNode(a0);
        implictGraph.addNode(a1);
        implictGraph.addNode(b0);
        implictGraph.addNode(b1);

        //add edges derived from clauses (-3->4) and (3<-4) for 3or4
        ArrayList<Literal> clauses_Literal = c.implies();  // returns edges - Start and end points in [Literal1_start,Literal1_end,Literal2_start,Literal2_end]
        implictGraph.addEdge(clauses_Literal.get(0), clauses_Literal.get(1));
        implictGraph.addEdge(clauses_Literal.get(2), clauses_Literal.get(3));


    }
    public boolean isSat(){
        return false;
    }
}
    /*
    class InvalidInputException extends Exception {
        InvalidInputException() {
            System.out.println("INVALID INPUT");
        }
    }*/
