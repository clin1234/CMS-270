import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** All test methods suffixed with 1 indicate that they
 * test with the constructor that takes a string, whereas
 * those prefixed with 2 indicate that they test with the
 * no-arg constructor.
 * <p>
 *     Equation.solve() has additional unit tests in
 *     the form of testSolve[t][n]() methods, where
 *     <ul>
 *         <li>[t] is multiply or divide</li>
 *         <li>[n] is one of the following:
 *         <ol>
 *             <li>lhs == 0, rhs > 0</li>
 *             <li>lhs == 0, rhs < 0</li>
 *             <li>lhs > 0, rhs == 0</li>
 *             <li>lhs < 0, rhs == 0</li>
 *             <li>both sides == 0</li>
 *         </ol>
 *         </li>
 *     </ul>
 * </p>
 * Assignment 4
 *
 * @author Charlie Lin
 * @since 12/1/2021
 */
class EquationTest {

    @Test
    void testSolve1() {
        Equation e = new Equation("2 + a = 2");
        e.solve();
        assertEquals(0, e.getValueOfUnknown());
    }

    @Test
    void testSolve2() {
        Equation p = new Equation();
        p.solve();
        assertEquals(0, p.getValueOfUnknown());
    }

    @Test
    void testSolveMultiply1() {
        Equation p = new Equation("0 * m = 3");
        p.solve();
        assertEquals(Double.POSITIVE_INFINITY, p.getValueOfUnknown());
    }

    @Test
    void testSolveMultiply2() {
        Equation p = new Equation("0 * m = -3");
        p.solve();
        assertEquals(Double.NEGATIVE_INFINITY, p.getValueOfUnknown());
    }

    @Test
    void testSolveMultiply3() {
        Equation p = new Equation("2 * m = 0");
        p.solve();
        assertEquals(0, p.getValueOfUnknown());
    }

    @Test
    void testSolveMultiply4() {
        Equation p = new Equation("-5 * m = 0");
        p.solve();
        double tmp = p.getValueOfUnknown();
        assertEquals(-0.0, tmp);
    }

    @Test
    void testSolveMultiply5() {
        Equation p = new Equation("0 * m = 0");
        p.solve();
        assertTrue(Double.isNaN(p.getValueOfUnknown()));
    }

    @Test
    void testSolveDivide1() {
        Equation p = new Equation("0 / m = 3");
        p.solve();
        assertEquals(0, p.getValueOfUnknown());
    }

    @Test
    void testSolveDivide2() {
        Equation p = new Equation("0 / m = -3");
        p.solve();
        assertEquals(-0.0, p.getValueOfUnknown());
    }

    @Test
    void testSolveDivide3() {
        Equation p = new Equation("2 * m = 0");
        p.solve();
        assertEquals(0, p.getValueOfUnknown());
    }

    @Test
    void testSolveDivide4() {
        Equation p = new Equation("-5 * m = 0");
        p.solve();
        double tmp = p.getValueOfUnknown();
        assertEquals(-0.0, tmp);
    }

    @Test
    void testSolveDivide5() {
        Equation p = new Equation("0 * m = 0");
        p.solve();
        assertTrue(Double.isNaN(p.getValueOfUnknown()));
    }

    @Test
    void testGetLefthandside1() {
        Equation e = new Equation("-23 / v = 46");
        assertEquals(e.getLefthandside(), -23);
    }

    @Test
    void testGetLefthandside2() {
        Equation p = new Equation();
        p.solve();
        assertEquals(0, p.getLefthandside());
    }

    @Test
    void testSetLefthandside1() {
        Equation e = new Equation("-23 / v = 46");
        int oldLeft = e.getLefthandside();
        assertEquals(oldLeft, -23);

        e.setLefthandside(46);
        assertNotEquals(oldLeft, e.getLefthandside());
        assertEquals(e.getLefthandside(), 46);
    }

    @Test
    void testSetLefthandside2() {
        Equation e = new Equation();
        int oldLeft = e.getLefthandside();
        assertEquals(oldLeft, 0);

        e.setLefthandside(2);
        assertNotEquals(oldLeft, e.getLefthandside());
        assertEquals(e.getLefthandside(), 2);
    }

    @Test
    void testGetRighthandside1() {
        Equation e = new Equation("-4 - v = 46");
        assertEquals(e.getRighthandside(), 46);
    }

    @Test
    void testGetRighthandside2() {
        Equation p = new Equation();
        assertEquals(0, p.getRighthandside());
    }

    @Test
    void testSetRighthandside1() {
        Equation e = new Equation("-4 - v = 46");
        int old = e.getRighthandside();
        assertEquals(old, 46);

        e.setRighthandside(-3);
        assertNotEquals(old, e.getRighthandside());
        assertEquals(-3, e.getRighthandside());
    }

    @Test
    void testSetRighthandside2() {
        Equation e = new Equation();
        int old = e.getRighthandside();
        assertEquals(old, 0);

        e.setRighthandside(-3);
        assertNotEquals(old, e.getRighthandside());
        assertEquals(-3, e.getRighthandside());
    }

    @Test
    void testGetOperation1() {
        Equation e = new Equation("-4 - v = 46");
        assertEquals('-', e.getOperation());
    }

    @Test
    void testGetOperation2() {
        Equation p = new Equation();
        assertEquals(' ', p.getOperation());
    }

    @Test
    void setOperation1() {
        Equation p = new Equation("-4 * l = 28");
        char old = p.getOperation();
        assertEquals(old, '*');
        p.solve();
        assertEquals(-7, p.getValueOfUnknown());

        p.setOperation('+');
        assertNotEquals(old, p.getOperation());
        p.solve();
        assertEquals(p.getValueOfUnknown(), 32);
    }

    @Test
    void setOperation2() {
        Equation p = new Equation();
        char old = p.getOperation();
        assertEquals(old, ' ');

        p.setOperation('*');
        assertNotEquals(old, p.getOperation());
        p.solve();
        double temp = p.getValueOfUnknown();
        assertNotEquals(0, temp);
        // Multiplying in solve() is done by division
        assertTrue(Double.isNaN(temp));
    }

    @Test
    void testGetUnknown1() {
        Equation e = new Equation("23 - k = 2");
        assertEquals('k', e.getUnknown());
    }

    @Test
    void testGetUnknown2() {
        Equation p = new Equation();
        assertEquals(' ', p.getUnknown());
    }

    @Test
    void testSetUnknown1() {
        Equation e = new Equation("23 - l = 2");
        char old = e.getUnknown();
        assertEquals('l', old);

        e.setUnknown('o');
        assertNotEquals(old, e.getUnknown());
        assertEquals('o', e.getUnknown());
    }

    @Test
    void testSetUnknown2() {
        Equation e = new Equation();
        char old = e.getUnknown();
        assertEquals(' ', old);

        e.setUnknown('b');
        assertNotEquals(old, e.getUnknown());
        assertEquals('b', e.getUnknown());
    }

    @Test
    void testGetValueOfUnknown1() {
        Equation e = new Equation("23 - m = -34");
        assertEquals(0, e.getValueOfUnknown());
        e.solve();
        assertNotEquals(0, e.getValueOfUnknown());
        assertEquals(e.getValueOfUnknown(), 57);
    }

    @Test
    void testGetValueOfUnknown2() {
        Equation p = new Equation();
        double res = p.getValueOfUnknown();
        assertEquals(res, 0);
        p.solve();
        assertEquals(res, p.getValueOfUnknown());
    }

    @Test
    void testSetValueOfUnknown1() {
        Equation e = new Equation("23 - l = 2");
        e.solve();
        double old = e.getValueOfUnknown();
        assertEquals(21, old);

        e.setValueOfUnknown(1);
        assertNotEquals(old, e.getValueOfUnknown());
        assertEquals(1, e.getValueOfUnknown());
    }

    @Test
    void testSetValueOfUnknown2() {
        Equation e = new Equation();
        double old = e.getValueOfUnknown();
        assertEquals(0, old);

        e.setValueOfUnknown(2);
        assertNotEquals(old, e.getValueOfUnknown());
        assertEquals(2, e.getValueOfUnknown());
    }
}