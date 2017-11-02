import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Li Yang on 31/10/2017.
 */

import java.util.ArrayList;
import java.util.Collections;

public class Clause {

    public static ArrayList<Clause> clauseMemory=new ArrayList<Clause>();
    Literal a;
    Literal b;
    boolean[] clauseRequire =new boolean[2];
    Clause(Literal a, Literal b,int lita, int litb){
        this.a=a;
        this.b=b;
        if(lita>0){
            clauseRequire[0]=true;
        }
        else
        {
            clauseRequire[0]=false;
        }
        if(litb>0){
            clauseRequire[1]=true;
        }
        else
        {
            clauseRequire[1]=false;
        }

    }
    public boolean checkSat(){
        if((a.getP()==clauseRequire[0])||(a.getP()==clauseRequire[0])){
            return true;
        }
        return false;


    }


}