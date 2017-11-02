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
        /*
        revGraph.addEdge(clauses_Literal.get(2), clauses_Literal.get(3));

        System.out.println(" adding edge :"+clauses_Literal.get(1).getValue()+""+clauses_Literal.get(1).getP());
        System.out.println(" to :"+clauses_Literal.get(0).getValue()+""+clauses_Literal.get(0).getP());
        System.out.println(" adding edge :"+clauses_Literal.get(3).getValue()+""+clauses_Literal.get(3).getP());
        System.out.println(" to :"+clauses_Literal.get(2).getValue()+""+clauses_Literal.get(2).getP());
        */

    }
    public void makeSCC(Graph revGraph , Graph implicitGraph){
        // SCC.
        SCC sccTest=new SCC();
        sccTest.dfs(revGraph,implicitGraph);

        //reset rev graph visited for the literals
        ArrayList<ArrayList<Literal>> newList=sccTest.findingSCC(revGraph);

        //System.out.println("Printing New List SCCs");
        for(ArrayList<Literal> a:newList){
            for(Literal b:a){
                System.out.println(b.getValue());
                System.out.println("?mm");
            }
        }


        System.out.println(sccTest.transversingArrays(newList));


        // SCC.getConnectedArray    [1,4],[1,2,3,4,5],[1,2,3,45,6]
        // isSat loop through all literal and make sure no repeated Literal,,,   Make sure neg and normal are not int he same SCC.

        /* Test Case for SCC to make sure Test Case transverse down the literals ,Literal 1->4, 4->1 , 3->2
        Literal a1=new Literal(21,true);
        Literal a4=new Literal(1,true);

        //Literal a4=new Literal(1,true);
        Literal a_1=new Literal(21,false);

        Literal a3=new Literal(1,true);
        Literal a2=new Literal(1,true);
        //Literal a1=new Literal(1,true);
        Literal a9=new Literal(1,true);

        ArrayList<Literal> test1 = new ArrayList<>();
        Collections.addAll(test1,a_1,a1);

        ArrayList<Literal> litHis= new ArrayList<>();
        Collections.addAll(litHis,a1,a_1);
        litHistory.put(21,litHis);

        ArrayList<Literal> test2 = new ArrayList<>();
        Collections.addAll(test2,a4,a_1);
        ArrayList<Literal> test3 = new ArrayList<>();
        Collections.addAll(test3,a3,a2,a1,a9);
        ArrayList<ArrayList<Literal>> test =new ArrayList<ArrayList<Literal>>();
        Collections.addAll(test,test1,test2,test3);

        System.out.println("sat:"+ SCC.transversingArrays(test));
        */

        // / Then Tranverse down the list. send in individual and check that its not
    }
    public static boolean isSat(){
        return false;
    }

}

