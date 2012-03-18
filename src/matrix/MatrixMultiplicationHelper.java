package matrix;

/**
 * Rinnakkaisen matriisikertolaskun toteuttava luokka.
 *
 * @author svkoskin
 */
public class MatrixMultiplicationHelper {

    private double[][] a;
    private double[][] b;
    private double[][] temp;

    /**
     * Luo uuden matriisikertolaskijaolion.
     */
    public MatrixMultiplicationHelper() {
    }

    /**
     * Matriisikertolaskun toteuttava metodi.
     *
     * @param a m x n -matriisi.
     * @param b n x p -matriisi.
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
        this.temp = new double[a.length][b.length];

        MatrixMultiplicationThread[] threads = new MatrixMultiplicationThread[threadCount];
       
        float div = (float) temp.length / threadCount;
        
        for(int i = 0; i < threads.length; i++) {
            int min = Math.round(div * i);
            int max = Math.round(div * (i + 1));
            threads[i] = new MatrixMultiplicationThread(min, max);
            threads[i].start();
        }

        // Odotellaan kaikkien säikeiden valmistuminen
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

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
