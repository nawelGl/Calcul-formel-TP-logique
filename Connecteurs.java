public class Connecteurs {

    public static final String IMPLIQUE = "->";
    public static final String EQUIVAUT = "<->";
    public static final String AND = "&";
    public static final String OR = "|";
    public static final String NOT = "!";


    public static int negation(int a) {
        if (a == 1) {
            return 0;
        } else {
            return 1;
        }
    }

    public static int or(int a, int b) {
        if (a == 1 || b == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int and(int a, int b) {
        if (a == 1 && b == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int equivaut(int a, int b) {
        if (a == b) {
            return 1;
        } else {
            return 0;
        }
    }

    public static int implique(int a, int b) {
        // Faux uniquement si vrai implique faux
        if (a == 1 && b == 0) {
            return 0;
        } else {
            return 1;
        }
    }
}