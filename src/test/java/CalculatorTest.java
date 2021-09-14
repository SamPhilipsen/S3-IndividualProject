import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    void AddTwoNumbersTest()
    {
        Calculator c = new Calculator();
        assertEquals(2, c.AddTwoNumbers(1, 1));
    }
}
