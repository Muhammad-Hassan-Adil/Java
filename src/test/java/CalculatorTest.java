import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class CalculatorTest {
    private Calculator calculator;
    private Calculator calculatorSpy;
    private Calculator calculatormock;
    private Calculator calculatorService;

    @BeforeEach
    public void setUp() {
        System.out.println("Setting up...");
        calculator = new Calculator();
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Tearing down...");
        calculator = null;
    }

    @Test
    public void testAdd() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    public void testSubtract() {
        assertEquals(1, calculator.subtract(3, 2));
    }

    @Test
    public void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3));
    }

    @Test
    public void testDivide() {
        assertEquals(2, calculator.divide(6, 3));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void testAddWithMultipleArguments(int arg) {
        assertEquals(arg + 1, calculator.add(arg, 1));
    }

    //Mockito
    @BeforeEach
    public void setUpMock() {
        calculatormock = mock(Calculator.class);
    }

    @Test
    public void testAddWithMock() {
        when(calculatormock.add(1, 2)).thenReturn(3);
        assertEquals(3, calculatormock.add(1, 2));
        verify(calculatormock).add(1, 2);
    }

    @Test
    public void testwithSpy() {
        calculatorSpy = spy(Calculator.class);
        assertEquals(3, calculatorSpy.add(1, 2));
        verify(calculatorSpy).add(1, 2);
    }

    @Test
    public void exceptionHandlingTest() {
        calculatorService = mock(Calculator.class);
        doThrow(new RuntimeException("Add operation not implemented"))
                .when(calculatorService).add(1, 2);
        try {
            calculatorService.add(1, 2);
            throw new AssertionError("Exception not thrown");
        } catch (RuntimeException e) {
            assertEquals("Add operation not implemented", e.getMessage());
        }
    }
}
