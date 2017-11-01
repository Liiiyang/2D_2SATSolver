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

public class FileExtract {

    public static void main(String[] args) {
        String filetype;
        int numVar, numclauses;
        String line=null;
        String path="Test";

        try{
            FileReader file = new FileReader(path);
            BufferedReader bfile = new BufferedReader(file);
            try {
                line = bfile.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //varibles for Sat
            int lita, litb;
            Graph implicatGraph=new Graph();
            Graph revGraph =new Graph();
            TwoSATSolver twoSat =new TwoSATSolver();
            while(line != null){
                // Comment Line
                if(line.charAt(0) == 'c') {
                    System.out.println("Comment: " + line);
                }
                // Problem Line
                else if(line.charAt(0) == 'p'){
                    String[] problemline = line.split(" ");
                    filetype = problemline[1];
                    numVar = Integer.parseInt(problemline[2]);
                    numclauses = Integer.parseInt(problemline[3]);
                }
                //Literals and Clauses
                else{
                    String[] current=line.split(" ");
                    lita =Integer.parseInt(current[0]);
                    litb=Integer.parseInt(current[1]);
                    twoSat.addLine(lita,litb,implicatGraph,revGraph);
                }
            }
            System.out.println(twoSat.isSat());
        }
        catch(FileNotFoundException e){
            System.out.println("Error: File Not Found. Please Load File");
        }
    }
}
