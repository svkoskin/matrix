package matrix;

/**
 * Rinnakkaisen matriisikertolaskun toteuttava luokka.
 *
 * @author svkoskin
 */
public class MatrixMultiplicationHelper {

    private int threadCount;
    private double[][] a;
    private double[][] b;
    private double[][] temp;

    /**
     * Luo uuden matriisikertolaskijaolion, joka käyttää yhtä säiettä.
     */
    public MatrixMultiplicationHelper() {
        this.threadCount = 1;
    }

    /**
     * Luo uuden matriisikertolaskijaolion, joka käyttää korkeintaan annettua
     * määrää säikeitä.
     *
     * @param threadCount Käytettävien säikeiden määrä.
     */
    public MatrixMultiplicationHelper(int threadCount) {
        this.threadCount = threadCount;
    }

    /**
     * Matriisikertolaskun toteuttava metodi.
     *
     * @param a m x n -matriisi.
     * @param b n x p -matriisi.
     * @return Tulo, m x p -matriisi AB.
     * @throws Exception
     */
    public double[][] multiply(double[][] a, double[][] b) throws Exception {

        if (a == null || b == null || a[0] == null || b[0] == null) {
            throw new Exception("Kaksi matriisia on annettava parametreina.");
        }

        if (a[0].length != b.length) {
            throw new Exception("Parametrien tuloa ei ole määritelty.");
        }
        
        this.a = a;
        this.b = b;

        int m = a.length;
        int n = a[0].length;
        int p = b.length;

        this.temp = new double[m][p];
        
        MatrixMultiplicationThread laskia1 = new MatrixMultiplicationThread(0, 2);
        MatrixMultiplicationThread laskia2 = new MatrixMultiplicationThread(2, 3);
        laskia1.start();
        laskia2.start();
        laskia1.join();
        laskia2.join();

        return this.temp;
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
            System.out.println("Konstruktori");
        }

        @Override
        /**
         * Matriisikertolaskun toteuttava metodi. Kirjoittaa temp-muuttujaan.
         */
        public void run() {
            // Käy läpi tulosmatriisin rivit [min, max[ ja kirjoittaa niihin
            // oikean alkion.

            for (int i = min; i < max; i++) {
                for (int j = 0; j < temp[i].length; j++) {

                    // Lasketaan tulosmatriisin alkion i,j arvo matriisitulon
                    // määritelmän mukaisesti
                    temp[i][j] = 0;
                    
                    for (int k = 0; k < a[0].length; k++) {
                        temp[i][j] += a[i][k] * b[k][j];
                    }
                }
            }
        }
    }
}
