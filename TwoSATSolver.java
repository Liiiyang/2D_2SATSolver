/**
 * Created by Li Yang on 30/10/2017.
 */
import java.util.*;
import java.io.*;
public class TwoSATSolver {

    public class InvalidInputException extends Exception {
        InvalidInputException() {
            System.out.println("INVALID INPUT");
        }
    }

    public TwoSATSolver(FileExtract fe) throws InvalidInputException{
        if(!fe.getFiletype().equals("cnf")){
            throw new InvalidInputException();
        }
        Literal a= new Literal(fe.getLita());
        Literal b= new Literal(fe.getLitb());
        Clause x= new Clause(a,b);
        Graph.addEdge(x.a, x.b);
    }

}
