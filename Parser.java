public class Parser {

    public static Node parseFormula(String formule) {
        //on eneve les espaces
        return parseExpression(formule.replace(" ", ""));
    }

    private static Node parseExpression(String expr) {
        //on enleve les parentheses
        while (expr.startsWith("(") && expr.endsWith(")")) {
            // Attention : il faut vérifier qu'elles se correspondent vraiment (gestion
            // simple ici)
            // Pour un TP simple, on suppose que si ça commence et finit par (), on peut les
            // virer
            // SAUF si c'est "(A)&(B)". Une vraie gestion compte les parenthèses
            // ouvertes/fermées.
            if (isWrappedInParentheses(expr)) {
                expr = expr.substring(1, expr.length() - 1);
            } else {
                break;
            }
        }

        // 2. Si pas d'opérateur, c'est une variable ou une constante
        // On cherche le "split index" (l'endroit où couper)
        int splitIndex = findSplitIndex(expr);

        if (splitIndex == -1) {
            // Pas d'opérateur trouvé -> C'est une feuille (Variable)
            return new Node(expr);
        }

        // 3. On a trouvé un opérateur, on coupe !
        // On doit identifier quel opérateur c'est pour savoir combien de caractères
        // sauter
        String op = identifyOperatorAt(expr, splitIndex);

        String leftPart = expr.substring(0, splitIndex);
        String rightPart = expr.substring(splitIndex + op.length());

        // Cas spécial Négation (unaire) : !P (gauche vide)
        if (op.equals(Connecteurs.NOT)) {
            // Pour !P, on met P à gauche par convention dans ton eval, ou à droite,
            // Mais vu ton eval (case NOT: return negation(valLeft)), on met la suite à
            // GAUCHE (left).
            return new Node(op, parseExpression(rightPart), null);
        }

        return new Node(op, parseExpression(leftPart), parseExpression(rightPart));
    }

    // Trouve l'index de l'opérateur avec la plus FAIBLE priorité (celui qu'on
    // exécute en dernier)
    private static int findSplitIndex(String expr) {
        int parentheses = 0;
        int bestIndex = -1;
        int lowestPriority = 999;

        // On parcourt la string
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (c == '(')
                parentheses++;
            else if (c == ')')
                parentheses--;
            else if (parentheses == 0) {
                // On est hors parenthèses, on cherche les opérateurs
                String sub = expr.substring(i);
                int priority = -1;

                // On donne des priorités (plus c'est haut, plus c'est prioritaire)
                // <-> (1), -> (2), | (3), & (4), ! (5) -> L'ordre inverse d'exécution
                if (sub.startsWith(Connecteurs.EQUIVAUT))
                    priority = 1;
                else if (sub.startsWith(Connecteurs.IMPLIQUE))
                    priority = 2;
                else if (sub.startsWith(Connecteurs.OR))
                    priority = 3;
                else if (sub.startsWith(Connecteurs.AND))
                    priority = 4;
                // else if (sub.startsWith(Connecteurs.NOT)) priority = 5; // La négation se
                // gère souvent différemment

                if (priority != -1 && priority <= lowestPriority) {
                    lowestPriority = priority;
                    bestIndex = i;
                }
            }
        }
        return bestIndex;
    }

    private static String identifyOperatorAt(String expr, int index) {
        String sub = expr.substring(index);
        if (sub.startsWith(Connecteurs.EQUIVAUT))
            return Connecteurs.EQUIVAUT;
        if (sub.startsWith(Connecteurs.IMPLIQUE))
            return Connecteurs.IMPLIQUE;
        if (sub.startsWith(Connecteurs.OR))
            return Connecteurs.OR;
        if (sub.startsWith(Connecteurs.AND))
            return Connecteurs.AND;
        if (sub.startsWith(Connecteurs.NOT))
            return Connecteurs.NOT;
        return "";
    }

    // Vérifie si (A) & (B) ou si (A & B)
    private static boolean isWrappedInParentheses(String expr) {
        if (!expr.startsWith("(") || !expr.endsWith(")"))
            return false;
        int count = 0;
        for (int i = 0; i < expr.length() - 1; i++) {
            if (expr.charAt(i) == '(')
                count++;
            else if (expr.charAt(i) == ')')
                count--;
            if (count == 0)
                return false; // Parenthèse fermée trop tôt ex: (A)&(B)
        }
        return true;
    }
}