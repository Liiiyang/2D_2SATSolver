/**
 * Created by Li Yang on 30/10/2017.
 */
import java.util.*;

public class TwoSATSolver {
    private static HashMap<Integer, ArrayList<Literal>> litHistory=Literal.litHistory;; // Setting to the Hashmap in Literal Lit History
    public TwoSATSolver(){
    }

    public static void addLine(int lita, int litb, Graph implicitGraph, Graph revGraph){
        //Boolean is for pos or negative
        Literal a0 , a1;
        Literal b0, b1;
        Boolean a_sign, b_sign;
        //neg or pos for a and b
        a_sign = (lita > 0);
        b_sign = (litb > 0);
        //System.out.println(litHistory=a0.getLitHistory());
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
            litHistory.put(Math.abs(lita), a_ArrayList);
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
            litHistory.put(Math.abs(litb), b_ArrayList);
        }

        //Selecting Correct Literal setting to a and b
        Literal a;
        Literal b;
        if (a_sign == true) {
            a = a0;
        } else {
            a = a1;
        }
        if (b_sign == true) {
            b = b0;
        } else {
            b = b1;
        }

        //Creates new Clause
        Clause c = new Clause(a, b);

        //add postive and negative nodes
        implicitGraph.addNode(a0);
        implicitGraph.addNode(a1);
        implicitGraph.addNode(b0);
        implicitGraph.addNode(b1);

        //add edges derived from clauses (-3->4) and (3<-4) for 3or4
        ArrayList<Literal> clauses_Literal = c.implies();  // returns edges - Start and end points in [Literal1_start,Literal1_end,Literal2_start,Literal2_end]
        implicitGraph.addEdge(clauses_Literal.get(0), clauses_Literal.get(1));
        implicitGraph.addEdge(clauses_Literal.get(2), clauses_Literal.get(3));

        //add postive and negative nodes and edges for reverse graph used in SCC
        revGraph.addNode(a0);
        revGraph.addNode(a1);
        revGraph.addNode(b0);
        revGraph.addNode(b1);
        revGraph.addEdge(clauses_Literal.get(1), clauses_Literal.get(0));
        revGraph.addEdge(clauses_Literal.get(3), clauses_Literal.get(2));



    }
    public void SCC(Graph revGraph , Graph implicitGraph){
        // SCC.
        // SCC.dfs()

        //reset rev graph visited for the literals
        revGraph.resetAllVisited();


        // SCC.getConnectedArray    [1,4],[1,2,3,4,5],[1,2,3,45,6]
        // isSat loop through all literal and make sure no repeated Literal,,,   Make sure neg and normal are not int he same SCC.
        // Then Tranverse down the list.
    }
    public static boolean isSat(){
        return false;
    }

}

