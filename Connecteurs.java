import java.util.List;

public class Connecteurs {

    public int negation(int a){
        if(a==1) {return 0;} else {return 1;}
    }

    public int or(List<Integer> params){
        for(int i = 0; i < params.size(); i++){
            if(params.get(i) == 1){
                return 1;
            }
        }
        return 0;
    }

    public int and(List<Integer> params) {
        for (int i = 0; i < params.size(); i++) {
            if (params.get(i) == 0) {
                return 0;
            }
        }
        return 1;
    }
    
    public int equivaut(int param1, int param2){
        if(param1 == param2) {
            return 1;
        } else return 0;
    }

    public int implique(int param1, int param2){
        if(param1 == 1 && param2 == 0){
            return 0;
        } else return 1;
    }
}
