import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class SCC {
    Stack<Literal> stack1;
    public SCC(){
        stack1=new Stack<>();
    }

    public void dfs(Graph implicitGraph) {
        //run dfs and return a stack for first part
        System.out.println("-------------------------DFS----------------------------------");
        System.out.print("Total Vertices in implicitGraph: ");
        for (Literal v : implicitGraph.getAdjList().keySet()) {
            System.out.print(v.getValue() + " " + v.getP() + "  ");
        }
        System.out.print("\n\nPath Taken:  ");
        for (Literal v : implicitGraph.getAdjList().keySet()) {
            if (!v.isVisited()) {
                v.visited();
                dfsWithStack(v, implicitGraph);
            }
        }

        System.out.print("\n");

    }

    private void dfsWithStack(Literal rootVertex, Graph implicitGraph) {
        //rootVertex.//check neighbours
        //implicitGraph.getAdjList().get(rootVertex) neighbour adjlist>> Arralist<literals>
        for (Literal neighbourVertex:implicitGraph.getAdjList().get(rootVertex)){

            if(neighbourVertex!= null && !neighbourVertex.isVisited()){
                neighbourVertex.visited();
                dfsWithStack(neighbourVertex,implicitGraph);
            }
        }

        //only exits loop when all neighbours have been explored thus back track to parent. add into stack
        System.out.print(" "+rootVertex.getValue()+" "+ rootVertex.getP());
        stack1.add(rootVertex);
        //System.out.print(" ->>");
    }
    public ArrayList<Literal> getSCCList(Graph revGraph,Literal rootVertex,ArrayList<Literal> scc){
        //System.out.println(rootVertex.getValue()+" " + rootVertex.getP()+"  root");
        //for(Literal x:revGraph.getAdjList().keySet()){
         //   System.out.print(x.isVisited());}
        //System.out.print("\n");

        rootVertex.visited();
        scc.add(rootVertex);
        for (Literal neighbourVertex:revGraph.getAdjList().get(rootVertex)){

            if(!neighbourVertex.isVisited()){
                //Added to see which neighbours SCCList visit entering the nodes of reversed graph
                System.out.println(neighbourVertex.getValue()+" "+ neighbourVertex.getP()+"   Visit");
                getSCCList(revGraph,neighbourVertex,scc);
            }
            else{
                System.out.println(neighbourVertex.isVisited()+"end Visited ");
            }

        }
        return scc;

    }

    public ArrayList<ArrayList<Literal> > findingSCC(Graph implicitGraph,Graph revGraph){
        revGraph.resetAllVisited();
        ArrayList<Literal> scc=new ArrayList<>();
        ArrayList<ArrayList<Literal>> totalSccArrays=new ArrayList<ArrayList<Literal>>();
        System.out.println("__\n\n________finding SCC________\n\n____");
        while(!stack1.empty()) {
            Literal first= stack1.pop();
            //TROUBLE SHOOTING CASES printing SCC arrays
            System.out.println(" Literal from sccArrays\nFirst : "+first.getValue()+" "+first.getP());
            ArrayList<Literal> sccArrays=getSCCList(revGraph,first,scc);
            System.out.println("\nA Strongly Connected Components of nodes");
            for(Literal lit:sccArrays){
                System.out.print(lit.getValue()+" "+lit.getP()+" ,");
            }
            System.out.println("\n");
            totalSccArrays.add(sccArrays);
            for(Literal n:sccArrays) {
                System.out.println("Removed:"+ n.getValue()+" "+n.getP());
                stack1.remove(n);
            }
            scc.clear();
        }
        return totalSccArrays;
    }

    public boolean transversingArrays(ArrayList<ArrayList<Literal>> sccArrays){
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
            for(Literal b:arrayList)
            {
                System.out.println("Checking :"+a.getValue()+" "+a.getP()+" to "+b.getValue() +" "+b.getP());
                if (a.neg().equals(b))return false;}
        }
        return true;
    }

    public void runSCC(Graph implicitGraph, Graph revGraph) {
        //follow  stack and -> pop accordingly
        dfs(implicitGraph);
        // follow stack and pop

        //reset rev graph visited for the literals
        ArrayList<ArrayList<Literal>> newList= findingSCC(implicitGraph,revGraph);

        //________________Test__ArrayList<Literal> for Scc______________________
        System.out.println("________________Looping Through All SCC:Components______________________");
        for (ArrayList<Literal> x:newList){
            System.out.print("Scc: ");
            for(Literal y:x){
                System.out.print(y.getValue()+" "+y.getP());
                System.out.print(",");
            }
            System.out.println("\n__________________________");
        }

        if(transversingArrays(newList)==true){
            System.out.println("\n The Problem is :");
            System.out.println("          Satisfiable");
            generateSolutions(newList);}
            else{
            System.out.println("\nThe Problem is:");
            System.out.println("        Not Satisfiable");

            }

    }
    public void generateSolutions(ArrayList<ArrayList<Literal>> newList){
        /* Through Kosaraju's algorithm it has already been arranged in topological order, from 0->N
           We have to generate solutions through reverse topological order ensuring all literal has been accounted for. Setting to true in default case.
         */
        System.out.println("\nSolutions will be X1,X2...Xn as follows:");
        int[] answer= new int[FileExtract.numVar+1];
        Collections.reverse(newList);
        for (int a_1=1;a_1<answer.length;a_1++)
        {
            answer[a_1]=3; //setting to 2 so we can ensure there multiple setting of values
        }
        for(ArrayList<Literal> x:newList){
                for (Literal y : x) {
                    if(answer[1]==3) {//can change only when unvisited: denoted by 2
                        System.out.println("hifewhife");
                        if (y.getP()) {
                            answer[y.getValue()] = 1;
                        } else {
                            answer[y.getValue()] = 0;
                        }
                    }
                }

        }
        for (int a_1=1;a_1<answer.length;a_1++)
        {
//            if (answer[a_1]==2)
  //          {
    //            answer[a_1]=1; //changing all unvisited to default true
      //      }
            System.out.print(answer[a_1]+" ");

        }

    }
}
