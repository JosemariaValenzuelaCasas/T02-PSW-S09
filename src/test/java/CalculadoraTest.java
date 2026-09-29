import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraTest {

    @Test
    void debeSumarCorrectamente() {
        Calculadora calc = new Calculadora();
        assertEquals(9, calc.sumar(5, 3));
    }

    @Test
    void debeRestarCorrectamente() {
        Calculadora calc = new Calculadora();
        assertEquals(2, calc.restar(5, 3));
    }

    @Test
    void debeMultiplicarCorrectamente() {
        Calculadora calc = new Calculadora();
        assertEquals(15, calc.multiplicar(5, 3));
    }

    @Test
    void debeDividirCorrectamente() {
        Calculadora calc = new Calculadora();
        assertEquals(2, calc.dividir(6, 3));
    }
}
