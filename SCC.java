import java.util.ArrayList;

public class SCC {

    public static void dfs(Graph implicitGraph){
        //run dfs and return a stack for first part

    }
    public static void getConnectedSCC(Graph revGraph){

    }
    public static boolean transversingArrays(ArrayList<ArrayList<Literal>> sccArrays){
        for( ArrayList<Literal> i: sccArrays){
            if (SCC.checkSat(i)==false){
                return false;
            }
        }
        return true;
    }
    public static boolean checkSat(ArrayList<Literal> arrayList){
        for (Literal a:arrayList){
            //add the true false solution
            for(Literal b:arrayList)
            { if (a.neg().equals(b))return false;}
        }
        return true;
    }

}
