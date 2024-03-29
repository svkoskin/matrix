package matrix;

/**
 * Rinnakkaisen matriisikertolaskun toteuttava luokka.
 *
 * @author svkoskin
 */
public class MatrixMultiplicationHelper {

    private double[][] a;
    private double[][] b;
    private double[][] result;

    /**
     * Luo uuden matriisikertolaskijaolion.
     */
    public MatrixMultiplicationHelper() {
    }
    
    public double[][] transpose(double[][] matrix) {
        double[][] transposed = new double[matrix.length][matrix[0].length];
        
        for(int i=0; i < matrix.length; i++) {
            for(int j=0; j < matrix[0].length; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        
        return transposed;
    }

    /**
     * Matriisikertolaskun toteuttava metodi.
     *
     * @param a m x n -matriisi.
     * @param b n x p -matriisi transponoituna.
     * @param threadCount Käytettävien laskusäikeiden määrän yläraja.
     * @return Tulo, m x p -matriisi AB.
     * @throws Exception
     */
    public double[][] multiply(double[][] a, double[][] b, int threadCount) throws Exception {

        if (a == null || b == null || a[0] == null || b[0] == null) {
            throw new Exception("Kaksi matriisia on annettava parametreina.");
        }

        if (a[0].length != b.length) {
            throw new Exception("Parametrien tuloa ei ole määritelty.");
        }

        this.a = a;
        this.b = b;
        this.result = new double[a.length][b.length];

        MatrixMultiplicationThread[] threads = new MatrixMultiplicationThread[threadCount];

        float div = (float) result.length / threadCount;

        for (int i = 0; i < threads.length; i++) {
            int min = Math.round(div * i);
            int max = Math.round(div * (i + 1));
            threads[i] = new MatrixMultiplicationThread(min, max);
            threads[i].start();
        }

        // Odotellaan kaikkien säikeiden valmistuminen
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        return this.result;
    }

    class MatrixMultiplicationThread extends Thread {

        /**
         * Ensimmäinen rivi, jonka laskemisesta säie on vastuussa.
         */
        private int min;
        
        /**
         * Ensimmäinen rivi, jonka laskemisesta säie EI OLE vastuussa.
         */
        private int max;

        MatrixMultiplicationThread(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        /**
         * Matriisikertolaskun toteuttava metodi. Kirjoittaa temp-muuttujaan.
         */
        public void run() {
            // Käy läpi tulosmatriisin rivit [min, max[ ja kirjoittaa niihin
            // oikean alkion.

            for (int i = min; i < max; i++) {
                for (int j = 0; j < result[i].length; j++) {


                    // Lasketaan tulosmatriisin alkion i,j huomioiden, että
                    // matriisi B on tallennettu rivit ja sarakkeet vaihtaen
                    // välimuistin käytön tehostamiseksi

                    /*
                     * perinteisesti:
                     * [ 1 2 3 ]   [ a b c ]   [ 1a+2d+3g 1b+2e+3h 1c+2f+3i ]
                     * [ 4 5 6 ] * [ d e f ] = [ 4a+5d+6g 4b+5e+6h 4c+5f+6i ]
                     * [ 7 8 9 ]   [ g h i ]   [ 7a+8d+9g 7b+8e+9h 7c+8f+9i ]
                     *
                     * transponoidun B-matriisin tapaus, jota koodissa käytetään:
                     * [ 1 2 3 ]   [ a d g ]   [ 1a+2d+3g 1b+2e+3h 1c+2f+3i ]
                     * [ 4 5 6 ] * [ b e h ] = [ 4a+5d+6g 4b+5e+6h 4c+5f+6i ]
                     * [ 7 8 9 ]   [ c f i ]   [ 7a+8d+9g 7b+8e+9h 7c+8f+9i ]
                     */

                    result[i][j] = 0;

                    for (int k = 0; k < a[0].length; k++) {
                        result[i][j] += a[i][k] * b[j][k];
                    }
                }
            }
        }
    }
}
