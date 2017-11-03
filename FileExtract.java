import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Li Yang on 1/11/2017.
 */
/******************************************************************************
 * File: FileExtract.java
 *
 *
 * To extract out all the necessary information from the CNF File
 * that is to be used in the TwoSATSolver
 */

class InvalidInputException extends Exception {
    InvalidInputException() {
        System.out.print("INVALID INPUT");
    }
}

public class FileExtract {
    public static int numVar, numClauses;;

    public static void main(String[] args) throws IOException,InvalidInputException {
        String filetype;
        String line=null;
        String path="C:\\Users\\Kenny\\Downloads\\test.cnf";

        try {
            FileReader file = new FileReader(path);
            BufferedReader bfile = new BufferedReader(file);
            line = bfile.readLine();

            //varibles for Sat
            int lita, litb;
            Graph implicitGraph = new Graph();
            Graph revGraph = new Graph();
            TwoSATSolver twoSat = new TwoSATSolver();
            while (line != null) {
                // Comment Line
                if (line.charAt(0) == 'c') {
                    System.out.println("Comment: " + line);
                }
                // Problem Line
                else if (line.charAt(0) == 'p') {
                    String[] problemline = line.split(" ");
                    filetype = problemline[1];
                    numVar = Integer.parseInt(problemline[2]);
                    numClauses = Integer.parseInt(problemline[3]);
                    if (!filetype.equals("cnf")) {
                        throw new InvalidInputException();
                    }
                }
                //Literals and Clauses
                else {
                    String[] current = line.split(" ");
                    lita = Integer.parseInt(current[0]);
                    litb = Integer.parseInt(current[1]);
                    TwoSATSolver.addLine(lita, litb, implicitGraph, revGraph);
                }
                line = bfile.readLine();
            }
            //***trouble Shooting
            // print graph to check

            System.out.println("__________Graph Test_________");
            for (Literal a1 : implicitGraph.getAdjList().keySet()) {
                System.out.println("\nStart:" + a1.getValue()+a1.getP());
                for (Literal xtest : implicitGraph.getAdjList().get(a1)) {
                    System.out.println(" -> End:" + xtest.getValue()+" "+xtest.getP());
                }
            }
            System.out.println("\n_________________________________");
            ////


                twoSat.makeSCC(implicitGraph,revGraph);
            long started = System.nanoTime();

            long time = System.nanoTime();
            long timeTaken= time - started;
            System.out.println("Time:" + timeTaken/1000000.0 + "ms");
        }
        catch(FileNotFoundException e){
            System.out.println("Error: File Not Found. Please Load File");
        }
    }
}
