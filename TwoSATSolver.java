/**
 * Created by Li Yang on 30/10/2017.
 */
import java.util.*;

public class TwoSATSolver {
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
        boolean test1 = false;
        boolean test2=false;
        for( int x:Literal.litHistory.keySet()){
            if(x== Math.abs(lita)){
                test1=true;
            }
            if(x==Math.abs(litb)){
                test2=true;
            }
        }
        if (test1 == true) {
            //System.out.println("duplicate key lita");
            ArrayList<Literal> currentList = Literal.litHistory.get(Math.abs(lita));
            a0 = currentList.get(0);
            a1 = currentList.get(1);
        } else {
            //creating Literals for all 2
            a0 = new Literal(Math.abs(lita), true);
            a1 = new Literal(Math.abs(lita), false);

            //enter in hashMap litHistory
            ArrayList<Literal> a_ArrayList = new ArrayList<Literal>();
            Collections.addAll(a_ArrayList, a0, a1);
            Literal.litHistory.put(Math.abs(lita), a_ArrayList);
        }

        if (test2==true) {
            //System.out.println("duplicate litb");
            ArrayList<Literal> currentList = Literal.litHistory.get(Math.abs(litb));
            b0 = currentList.get(0);
            b1 = currentList.get(1);
        } else {
            //creating Literals for all 2
            b0 = new Literal(Math.abs(litb), true);
            b1 = new Literal(Math.abs(litb), false);

            //enter in hashMap litHistory
            ArrayList<Literal> b_ArrayList = new ArrayList<Literal>();
            Collections.addAll(b_ArrayList, b0, b1);
            Literal.litHistory.put(Math.abs(litb), b_ArrayList);
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
        ArrayList<Literal> clauses_Literal = c.implies();  // returns edges - Start and end points in [Literal1_start,Literal1_end,
        implicitGraph.addNode(a0);
        implicitGraph.addNode(a1);
        implicitGraph.addNode(b0);
        implicitGraph.addNode(b1);

        //add edges derived from clauses (-3->4) and (3<-4) for 3or4 neg.a,
        implicitGraph.addEdge(clauses_Literal.get(0), clauses_Literal.get(1));
        implicitGraph.addEdge(clauses_Literal.get(2), clauses_Literal.get(3));

        //****trouble shooting printing implicitGraph inputs
        System.out.println(" adding edge :"+clauses_Literal.get(0).getValue()+""+clauses_Literal.get(0).getP());
        System.out.println(" to :"+clauses_Literal.get(1).getValue()+""+clauses_Literal.get(1).getP());
        System.out.println(" adding edge :"+clauses_Literal.get(2).getValue()+""+clauses_Literal.get(2).getP());
        System.out.println(" to :"+clauses_Literal.get(3).getValue()+""+clauses_Literal.get(3).getP());
        //////

        //add postive and negative nodes and edges for reverse graph used in SCC
        revGraph.addNode(a0);
        revGraph.addNode(a1);
        revGraph.addNode(b0);
        revGraph.addNode(b1);
        revGraph.addEdge(clauses_Literal.get(1), clauses_Literal.get(0));
        revGraph.addEdge(clauses_Literal.get(3), clauses_Literal.get(2));

        //****trouble shooting printing revGraph inputs

        //revGraph.addEdge(clauses_Literal.get(2), clauses_Literal.get(3));
        /*
        System.out.println(" adding edge :"+clauses_Literal.get(1).getValue()+""+clauses_Literal.get(1).getP());
        System.out.println(" to :"+clauses_Literal.get(0).getValue()+""+clauses_Literal.get(0).getP());
        System.out.println(" adding edge :"+clauses_Literal.get(3).getValue()+""+clauses_Literal.get(3).getP());
        System.out.println(" to :"+clauses_Literal.get(2).getValue()+""+clauses_Literal.get(2).getP());
        */

    }
    public void makeSCC(Graph implicitGraph , Graph revGraph) {
        SCC sccTest=new SCC();
        sccTest.runSCC(implicitGraph,revGraph);
    }
}

