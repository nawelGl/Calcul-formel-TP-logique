import java.util.Map;

public class Node {
    String value; //opérateur ou variable
    Node left;
    Node right;

    //pour les variables
    public Node(String value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
    
    //pour les opérateurs : on précise le noeud de gauche et de droite
    public Node(String value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int eval(Map<String, Integer> variables) {
        
        //on regard si le noeud de gauche et de droite sont nuls : alors c'est une variable
        if (this.left == null && this.right == null) {

            //si on a une valeur vrai (1) ou faux (0) dans l'expression, on la value comme tel
            if (value.equals("1")) return 1;
            if (value.equals("0")) return 0;

            //sinon on regard dans la map quelle est la valuation de la variable
            if (variables.containsKey(value)) {
                return variables.get(value);
            } else {
                System.err.println("ERREUR : Impossible de trouver la valeur de : " + value);
                System.exit(1);
                return 0;
            }
        }

        
        //on récupère d'abord la valuation des variables enfants
        //note : pour la négation, on supose que la variable est a gauche
        int valLeft = (left != null) ? left.eval(variables) : 0;
        int valRight = (right != null) ? right.eval(variables) : 0;

        //on applique l'opérateur trouvé
        switch (value) {
            case Connecteurs.NOT:      
                return Connecteurs.negation(valLeft);
            
            case Connecteurs.AND:      
                return Connecteurs.and(valLeft, valRight);
            
            case Connecteurs.OR:       
                return Connecteurs.or(valLeft, valRight);
            
            case Connecteurs.IMPLIQUE: 
                return Connecteurs.implique(valLeft, valRight);
            
            case Connecteurs.EQUIVAUT: 
                return Connecteurs.equivaut(valLeft, valRight);
            
            default: 
                System.err.println("ERREUR : Opérateur non reconnu : " + value);
                System.exit(1);
                return 0;
        }
    }
}