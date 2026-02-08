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
            if (value.equals("1")) return 1;
            if (value.equals("0")) return 0;
            if (variables.containsKey(value)) {
                return variables.get(value);
            } else {
                throw new RuntimeException("Erreur : La variable '" + value + "' n'a pas de valeur définie !");
            }
        }

        // --- 2. CAS RÉCURSIF : C'est un opérateur ---
        
        // On calcule d'abord la valeur des enfants (Récursivité)
        // Note : Pour la Négation (!), on suppose que la valeur est à gauche (left)
        int valLeft = (left != null) ? left.eval(variables) : 0;
        int valRight = (right != null) ? right.eval(variables) : 0;

        // On applique l'opération correspondante
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
                throw new RuntimeException("Opérateur inconnu : " + value);
        }
    }
}