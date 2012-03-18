package matrix;

/**
 *
 * @author svkoskin
 */
public class Matrix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        MatrixMultiplicationHelper mmh = new MatrixMultiplicationHelper();
        
                
        double[][] a = {{1, 2, 3},
                       {4, 5, 6},
                       {7, 8, 9}};

        double[][] b = {{0, 3, 9},
                       {2, 4, 6},
                       {5, 0, 5}};
        
        try {
            mmh.multiply(a, b, 2);
        } catch(Exception ex) {
            System.out.println("oho");
        }
        
    }
}
