import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String formule = "(p->q)";
        System.out.println("Formule à tester : " + formule);

        Node racine = Parser.parseFormula(formule);

        List<String> variables = new ArrayList<>();
        variables.add("p");
        variables.add("q");

        int n = variables.size();
        int[][] tableVerite = TablePattern.buildTable(n);

        boolean tautologie = true;
        boolean contradiction = true;

        System.out.println("\n--- Début de la Table de Vérité ---");
        System.out.println("p\tq\t| Résultat");
        System.out.println("-------------------------");

        //boucle pour tester cahque ligne de la table
        for (int[] ligne : tableVerite) {
            //a chaque tour de boucle, on crée un nouveau contexte pour les valuations des variables
            Map<String, Integer> contexte = new HashMap<>();

            //on remplit la map : variable -> valeur (0/1)
            for (int i = 0; i < n; i++) {
                String nomVariable = variables.get(i);
                int valeur = ligne[i];

                contexte.put(nomVariable, valeur);
                System.out.print(valeur + "\t");
            }

            int resultat = racine.eval(contexte);

            System.out.println("| " + resultat);

            //analyse finale
            if (resultat == 0) {
                tautologie = false;
            }
            if (resultat == 1) {
                contradiction = false;
            }
        }

        System.out.println("-------------------------");
        if (tautologie) {
            System.out.println("CONCLUSION : C'est une tautologie.");
        } else if (contradiction) {
            System.out.println("CONCLUSION : C'est une contradiction.");
        } else {
            System.out.println("CONCLUSION : Ni l'un ni l'autre (Satisfiable).");
        }
    }
}