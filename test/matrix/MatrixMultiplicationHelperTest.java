/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author svkoskin
 */
public class MatrixMultiplicationHelperTest {

    private float[][] x;
    private float[][] y;

    public MatrixMultiplicationHelperTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of multiply method, of class MatrixMultiplicationHelper.
     */
    @Test
    public void testMultiply() throws Exception {
        
        System.out.println("multiply");
        
        double[][] a = {{1, 2, 3},
                       {4, 5, 6},
                       {7, 8, 9}};

        double[][] b = {{0, 3, 9},
                       {2, 4, 6},
                       {5, 0, 5}};
        
        double[][] expResult = {{19, 11, 36},
                               {40, 32, 96},
                               {61, 53, 156}};        
        
        MatrixMultiplicationHelper instance = new MatrixMultiplicationHelper();
        double[][] result = instance.multiply(a, b, 2);
        assertArrayEquals(expResult, result);
    }
}
