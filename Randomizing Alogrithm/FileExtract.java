import org.omg.CORBA.Environment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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
        String path="C:\\Users\\Li Yang\\Desktop\\test1.cnf";
        try{
            ArrayList<Integer> list = new ArrayList<>();
            FileReader file = new FileReader(path);
            BufferedReader bfile = new BufferedReader(file);
            line = bfile.readLine();
            //varibles for Sat
            int lita, litb,litc;
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
                else {
                    String[] current = line.split(" ");
                    lita = Integer.parseInt(current[0]);
                    litb = Integer.parseInt(current[1]);
                    litc = Integer.parseInt(current[2]);
                    //ranDom.addline(lita,litb);
                    list.add(lita);
                    list.add(litb);
                    list.add(litc);

                    }
                line = bfile.readLine();
            }
            file.close();
            bfile.close();
            ArrayList<String[]> a = new ArrayList<>();
            ArrayList<Integer> IntofClause = new ArrayList<>();
            for(int item:list) {
                if(item != 0){

                    try {
                        IntofClause.add(item);
                    } catch (NumberFormatException nfe) {
                        System.out.println("cannot parse this: " + item);
                    }
                } else if (item == 0){
                    String[] clause = new String[IntofClause.size()];
                    for (int i=0; i<clause.length; i++){
                        clause[i]=Integer.toString(IntofClause.get(i));
                    }
                    a.add(clause);
                    IntofClause.clear();

                }
            }
            String[][] Clauses = new String[a.size()][];
            for (int j=0; j<Clauses.length; j++){
                Clauses[j]=a.get(j);
            }
            //System.out.println(Arrays.deepToString(Clauses));
            RandAlgorithm r = new RandAlgorithm(Clauses,numVar);
            System.out.println(numVar);
            long started = System.nanoTime();
            r.GenSol();
            long time = System.nanoTime();
            long timeTaken= time - started;
            System.out.println("Time:" + timeTaken/1000000.0 + "ms");

        }

        catch(FileNotFoundException e){
            System.out.println("Error: File Not Found. Please Load File");
        }
    }
}