package alumno.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.example.alumno.errors.AlumnoException
import org.example.alumno.service.AlumnoServiceImpl
import org.example.alumno.validator.DniValidator
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class AlumnoServiceImplTest{

    @MockK
    lateinit var dniValidator : DniValidator

    @InjectMockKs
    lateinit var service : AlumnoServiceImpl

    @Test
    fun alumnoValido(){
        //Arrange
        val nombre = "Steve"
        val edad = 28
        val dni = "12345678Z"

        every { dniValidator.validate(dni) } returns true

        //Act
        val result = service.checkAlumno(nombre = nombre, edad = edad, dni = dni)

        //Assert
        assertTrue(result == Unit)
    }

    @Test
    fun alumnoConDniInvalido(){
        //Arrange
        val nombre = "Steve"
        val edad = 28
        val dni = "12345Z"

        every { dniValidator.validate(dni) } returns false

        //Assert
        assertThrows<AlumnoException.AlumnoDniValidationException> {
            service.checkAlumno(nombre = nombre, edad = edad, dni = dni)
        }
    }

    @Test
    fun edadEsNegativa() {
        //Arrange
        val nombre = "Steve"
        val edad = -9
        val dni = "12345678Z"

        every { dniValidator.validate(dni) } returns true

        //Assert
        assertThrows<AlumnoException.AlumnoEdadValidationException> {
            service.checkAlumno(nombre = nombre, edad = edad, dni = dni)
        }
    }

    @Test
    fun ambosSonIncorrectos(){
        //Arrange
        val nombre = "Steve"
        val edad = -9
        val dni = "12348Z"

        every { dniValidator.validate(dni) } returns false

        //Assert
        assertThrows<AlumnoException.AlumnoDniValidationException> {
            service.checkAlumno(nombre = nombre, edad = edad, dni = dni)
        }
    }

    @Test
    fun edadEs0yTodoEsValido(){
        //Arrange
        val nombre = "Steve"
        val edad = 0
        val dni = "12345678Z"

        every { dniValidator.validate(dni) } returns true

        //Act
        val result = service.checkAlumno(nombre = nombre, edad = edad, dni = dni)

        //Assert
        assertTrue(result == Unit)
    }
}