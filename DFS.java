package com.example.victorlee.a2sat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;





public class SCC {
    //Map<Literal, Set<Literal>> getAdjList() {
      //  return AdjList;
//}
    private Stack<Literal> stack;


    public SCC(){
        this.stack = new Stack<>();
    }

    public void dfs(Graph implicitGraph , Graph revGraph){
        //run dfs and return a stack for first part
        ArrayList<Literal> e = new ArrayList<Literal>();
        ArrayList<Literal> eList = new ArrayList<Literal>();
        ArrayList<ArrayList<Literal>> newList = new ArrayList<ArrayList<Literal>>();
            for ( Literal v : implicitGraph.getAdjList().keySet()) {
                if (!v.isVisited()) {
                    v.visited();
                    dfsWithStack(v,implicitGraph,e);
                }
            }
        findingSCC(e, revGraph,eList ,newList);
        transversingArrays(newList);
     }

    private void dfsWithStack(Literal rootVertex, Graph implicitGraph, ArrayList e) {
        this.stack.add(rootVertex);
        while (!stack.isEmpty()) {
            Literal actualVertex = this.stack.pop();
            e.add(actualVertex);
            System.out.println();
            //System.out.println(actualVertex + " ");
            for (Literal v : implicitGraph.getAdjList().get(actualVertex)) {
                if (!v.isVisited()) {
                    v.visited();
                    this.stack.push(v);
                }
            }
        }
    }

    private void findingSCC(ArrayList<Literal> e, Graph revGraph,ArrayList<Literal> eList,ArrayList<ArrayList<Literal>> newList){
        revGraph.resetAllVisited();
        for (int i=0; i<e.size(); i++){
            Object secondVertex = e.get(i);
            if (!(e.get(i).isVisited())) {
                for (Literal v : revGraph.getAdjList().get(secondVertex)) {
                    if (!v.isVisited()) {
                        v.visited();
                    }
                    eList.add(v);
                    for(int b = 0; b < eList.size(); b++) {
                        System.out.print(eList.get(i));
                    }
                }
                newList.add(eList);
            }
        }
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
            for(Literal b:arrayList)
            { if (a.neg().equals(b))return false;}
        }
        return true;
    }
}
