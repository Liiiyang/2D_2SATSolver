import java.util.ArrayList;
import java.util.Stack;

public class SCC {

    //Map<Literal, Set<Literal>> getAdjList() {
      //  return AdjList;
//}
    private Stack<Literal> stack;
    ArrayList<Literal> e;
    ArrayList<Literal> eList;
    ArrayList<ArrayList<Literal>> newList;

    public SCC(){
        this.stack = new Stack<>();
        e = new ArrayList<Literal>();
        eList = new ArrayList<Literal>();
        newList = new ArrayList<ArrayList<Literal>>();
    }

    public void dfs(Graph implicitGraph , Graph revGraph){
        //run dfs and return a stack for first part

            for ( Literal v : implicitGraph.getAdjList().keySet()) {
                if (!v.isVisited()) {
                    v.visited();
                    dfsWithStack(v,implicitGraph,e);
                }
            }
     }

    private void dfsWithStack(Literal rootVertex, Graph implicitGraph, ArrayList e) {
        this.stack.add(rootVertex);
        while (!stack.isEmpty()) {
            Literal actualVertex = this.stack.pop();
            e.add(actualVertex);
            System.out.println(actualVertex.getValue()+" "+actualVertex.getP()+"");
            //System.out.println(actualVertex + " ");
            for (Literal v : implicitGraph.getAdjList().get(actualVertex)) {
                if (!v.isVisited()) {
                    v.visited();
                    this.stack.push(v);
                }
            }
        }
        System.out.println("________________________________________________________________");
    }

    public ArrayList<ArrayList<Literal> > findingSCC(Graph revGraph){
        revGraph.resetAllVisited();
        ArrayList<Literal> eList1 = new ArrayList<>();
        for (int i=0; i<e.size(); i++){
            Object secondVertex = e.get(i);
            if (!(e.get(i).isVisited())) {
                for (Literal v : revGraph.getAdjList().get(secondVertex)) {
                    if (!v.isVisited()) {
                        eList1.add(v);
                        v.visited();
                    }
                }
                //revGraph.resetAllVisited();
            }
        }
        newList.add((ArrayList<Literal>) eList1.clone());
        return newList;
    }

    public static boolean transversingArrays(ArrayList<ArrayList<Literal>> sccArrays){
        for( ArrayList<Literal> i: sccArrays){
            if (SCC.checkSat(i)==false){
                return false;
            }
        }
        return true;
    }
    public static boolean checkSat(ArrayList<Literal> arrayList){
        for (Literal a:arrayList){
            //add the true false solution
            System.out.println(a.getValue());
            for(Literal b:arrayList)
            { if (a.neg().equals(b))return false;}
        }
        return true;
    }
}
