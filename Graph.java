/**
 * Created by Li Yang on 31/10/2017.
 */

import java.util.*;

/******************************************************************************
 * File: Graph.java
 *
 *
 * Creating a implication graph, to do this,
 * a directed graph is implemented, using Adjacency List
 *
 */
public class Graph {
    /* Maps the Nodes in the graph to sets of outgoing edges */
    private HashMap<Literal, ArrayList<Literal>> AdjList = new HashMap<>();

    /* Adds a new node to the graph, returns false if the node already exists */
    public boolean addNode(Literal node) {
        //Return false if the node already exists
        if (AdjList.containsKey(node)) {
            return false;
        }
        //adds a new node otherwise
        AdjList.put(node, new ArrayList<>());
        return true;
    }

    /* With a first node and a second node, creates an edge to link
       it up together, returns an error if one of the node is missing from the graph
    */
    public void addEdge(Literal node1, Literal node2) {
        //Checks to ensure both nodes exists in the graph
        if (!AdjList.containsKey(node1) || !AdjList.containsKey(node2)) {
            throw new NoSuchElementException("Missing Node!");
        }
        //If both nodes exists, add the edge
        AdjList.get(node1).add(node2);
    }
    // reset visited for revGraph

    public HashMap<Literal, ArrayList<Literal>> getAdjList() {
        return AdjList;
    }
    public void resetAllVisited(){
        for (Literal x:AdjList.keySet()){
            x.resetAllVisited();
            break;
        }
    }
}