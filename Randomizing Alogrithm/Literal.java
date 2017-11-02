import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Li Yang on 31/10/2017.
 */

public class Literal {
    private int value;
    private boolean p;
    public static ArrayList<Literal> litMemory=new ArrayList<Literal>();

    Literal(int value, boolean p) {
        this.p=p;
        this.value=value;
    }

    public int getValue() {
        return value;
    }

    public boolean getP() {
        return p;
    }

    public boolean setP(Boolean newp){
        this.p = newp;
        return p;
    }

    public void flip(){
        p=!p;
    }


}