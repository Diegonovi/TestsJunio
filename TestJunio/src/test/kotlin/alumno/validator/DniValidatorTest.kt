package alumno.validator

import org.example.alumno.validator.DniValidator
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DniValidatorTest{

    private val validator = DniValidator()

    @Test
    fun dniValido(){
        //Arrange
        val dniValido = "12345678Z"

        //Act
        val resultado = validator.validate(dniValido)

        //Assert
        assertTrue { resultado }
    }

    @Test
    fun dniMuyLargo(){
        //Arrange
        val dniInvalido = "12345678Z3"

        //Act
        val resultado = validator.validate(dniInvalido)

        //Assert
        assertFalse { resultado }
    }

    @Test
    fun inputQueNoSigueLaReglaRegex(){
        //Arrange
        val dniInvalido = "1234567RZ"

        //Act
        val resultado = validator.validate(dniInvalido)

        //Assert
        assertFalse { resultado }
    }

    @Test
    fun dniNoSigueLaRegexYEsMuyLargo(){
        //Arrange
        val dniInvalido = "1234567RZ3"

        //Act
        val resultado = validator.validate(dniInvalido)

        //Assert
        assertFalse { resultado }
    }
}