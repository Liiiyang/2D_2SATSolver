import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

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
    private String filetype;
    private int numVar, numclauses;
    private int lita, litb;

    public String getFiletype(){
        return this.filetype;
    }

    public int getNumVar(){
        return this.numVar;
    }

    public int getNumclauses(){
        return this.numclauses;
    }

    public int getLita(){
        return this.lita;
    }
    public int getLitb(){
        return this.litb;
    }

    public FileExtract(String path){
        String line;
        try{
            FileReader file = new FileReader(path);
            BufferedReader bfile = new BufferedReader(file);
            line = bfile.readLine();
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
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Error: File Not Found. Please Load File");
        }
    }
}
