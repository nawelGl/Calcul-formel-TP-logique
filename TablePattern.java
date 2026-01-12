public class TablePattern {

    public static int[][] buildTable(int n) {
        // n nombre de variable
        int rows = 1 << n;        // 2^n
        int[][] table = new int[rows][n];

        for (int c = 0; c < n; c++) {
            int k=1 << c;   // 2^c   
            for (int r=0; r< rows; r++){
               
                table[r][c]=(r/k)%2; // la valuer de case 
        } 
    }return table;
}

    public static void print(int[][] t) {
        for (int r = 0; r < t.length; r++) {
            for (int c = 0; c < t[r].length; c++) {
                System.out.print(t[r][c] + " ");
            }
            System.out.println();
        }
    }

/* 
    public static void main(String[] args) {
        int[][] t = buildTable(4);
        print(t);
    }
 */
}
