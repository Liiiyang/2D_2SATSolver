import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Li Yang on 3/11/2017.
 */

public class RandAlgorithm {
    /*
    Create a Hashmap to store the literals and randomly assign Boolean Variable to it.
    0 - False, 1 - True
     */
    HashMap<String, Integer> truthassign = new HashMap<String, Integer>();
    String[][] Clauses;
    int clauseIndex;
    int runCount = 0;
    int numofVar;

    RandAlgorithm(String[][] Clauses, int numofVar) {
        this.numofVar = numofVar;
        randomLiterals(this.numofVar);
        this.Clauses = Clauses;
        this.clauseIndex = 0;
    }

    public int randomBool() {
        return ThreadLocalRandom.current().nextInt(0, 2);
    }

    public void randomLiterals(int varNo) {
        for (int i = 1; i <= varNo; i++) {

            this.truthassign.put(String.valueOf(i), randomBool());

        }
    }

    public boolean CheckLit(String Literals) {
        if (Literals.startsWith("-")) {
            return false;
        } else {
            return true;
        }
    }

    //Randomly chooses a Literal in the Clause

    public void ChangeLit(int clauseIndex) {
        int literalValue;
        int num = randomBool();
        String literals = this.Clauses[clauseIndex][num];
        if (CheckLit(literals)) {
            literalValue = this.truthassign.get(literals);
        } else {
            if (truthassign.get(literals.substring(1, literals.length())) == 0) {
                literalValue = 1;
            } else {
                literalValue = 0;
            }
        }
        //flip the value of the literal
        if (literalValue == 1) {
            this.truthassign.put(literals, 0);
        }
        else {
            this.truthassign.put(literals, 1);
        }

    }

    public void GenSol(){
        generate();
        this.runCount+=1;
        //Average number of iterations is n^2
        int randomwalks = this.numofVar*this.numofVar;
        if(isSAT()){
            System.out.println("FORMULA SATISFIABLE!");
            System.out.println(this);
        }
        else if(this.runCount>=randomwalks){
            System.out.println("No answer found within " + randomwalks + " iterations.");
        }
        else{
            ChangeLit(this.clauseIndex);
            GenSol();
        }
    }

    public boolean isSAT() {
        if (this.clauseIndex > this.Clauses.length) {
            return true;
        } else {
            return false;
        }
    }

    public void generate() {
        int negative;
        this.clauseIndex = 0;
        int clauseNo = this.Clauses.length;
        outerloop:
        for (String[] clause : this.Clauses) {
            negative = 0;
            for (String literal : clause) {
                if (CheckLit(literal)) {
                    if (this.truthassign.get(literal) == 1) {
                        break;
                    } else {
                        if (negative == 0) {
                            negative += 1;
                        } else {
                            break outerloop;
                        }
                    }

                } else {
                    if (this.truthassign.get(literal.substring(1, literal.length())) == 0) {
                        break;
                    } else {
                        if (negative == 0) {
                            negative += 1;
                        } else {
                            break outerloop;
                        }
                    }


                }


            }
            this.clauseIndex += 1;
        }

        if (this.clauseIndex == clauseNo) {
            this.clauseIndex += 1;
        }

    }

    public String toString() {
        String Output = "";
        for (int j = 1; j <= this.numofVar; j++) {
            this.truthassign.get(String.valueOf(j));
            Output = Output + this.truthassign.get(String.valueOf(j)).toString() + " ";
        }
        return Output;
    }
}

