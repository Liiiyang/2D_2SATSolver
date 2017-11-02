import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Li Yang on 3/11/2017.
 */

public class Randomizer {

    int numofRun = 0;
    int numofVar;

    public Randomizer(int numofVar){
        this.numofVar = numofVar;
    }

    public static void addline(int lita, int litb){
        Literal a , b;
        a = new Literal(Math.abs(lita),false);
        b = new Literal(Math.abs(litb),false);
        Clause clauseObject=new Clause(a,b,lita,litb);
    }
    public boolean checkSat(){
        //goes through entire clauses list make sure all sat
        ArrayList<Clause> clauseMemory = Clause.clauseMemory;
        for (Clause x:clauseMemory){
            if(false ==x.checkSat()){
                return false;
            }
        }
        return true;
    }

    /*
        Picks a random Literal to flip
    */

    public int randomLiteral() {
        return ThreadLocalRandom.current().nextInt(0,numofVar);
    }

    //Generates Solutions
    public void GenSol() {
        ArrayList<Literal> LiteralMemory = Literal.litMemory;
        this.numofRun+=1;
        int randomwalks = this.numofVar*this.numofVar;
        if(checkSat()){
            System.out.println("FORMULA SATISFIABLE");
        }
        else if(this.numofRun>= randomwalks){
            System.out.println("No Solution Found within " + randomwalks + "Iterations");
        }
        else {
            for(int i = 0; i<LiteralMemory.size(); i++){
                if(i == randomLiteral()){
                    LiteralMemory.get(i).flip();
                }
            }
        }

    }
}

