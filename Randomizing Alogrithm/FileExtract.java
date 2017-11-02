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
    InvalidInputException(){
        System.out.println("INVALID INPUT");
    }
}
public class FileExtract {
    public static int numVar, numClauses;

    public static void main(String[] args) throws IOException, InvalidInputException {
        String filetype;
        String line=null;
        String path="C:\\Users\\Li Yang\\Desktop\\test4.cnf";


        try{
            FileReader file = new FileReader(path);
            BufferedReader bfile = new BufferedReader(file);
            line = bfile.readLine();

            //varibles for Sat
            int lita, litb;
            String[][] Clauses = new String[numClauses][2];
            //RandomizerTest r = new RandomizerTest(Clauses,numVar);
            Randomizer ranDom = new Randomizer(numVar);
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
                    numClauses = Integer.parseInt(problemline[3]);
                    if(!filetype.equals("cnf")){
                        throw new InvalidInputException();
                    }
                }
                //Literals and Clauses
                else{
                    String[] current=line.split(" ");
                    lita =Integer.parseInt(current[0]);
                    litb=Integer.parseInt(current[1]);
                    String litA = Integer.toString(lita);
                    String litB = Integer.toString(litb);
                    String[] clause = new String[2];
                    clause[0] = litA;
                    clause[1] = litB;
                    for (int i = 0;i<Clauses.length;i++){
                        Clauses[i] = clause;
                    }
                    ranDom.addline(lita,litb);
                }
                line=bfile.readLine();
            }

            long started = System.nanoTime();
            ranDom.GenSol();
            long time = System.nanoTime();
            long timeTaken= time - started;
            System.out.println("Time:" + timeTaken/1000000.0 + "ms");
            //r.run();
        }
        catch(FileNotFoundException e){
            System.out.println("Error: File Not Found. Please Load File");
        }
    }
}