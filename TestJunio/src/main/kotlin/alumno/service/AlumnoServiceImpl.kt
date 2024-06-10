package org.example.alumno.service

import org.example.alumno.errors.AlumnoException
import org.example.alumno.validator.DniValidator

class AlumnoServiceImpl(
    private val dniValidator: DniValidator
) : AlumnoService {
    /**
     * Valida dos datos de un alumno, la edad y el dni
     * @see DniValidator.validate se hace uso de esa funcion para validar el dni
     * @param dni el dni del alumno
     * @param nombre el nombre del alumno (que no estamos validando)
     * @param edad la edad del alumno que queremos validar
     */
    override fun checkAlumno(dni: String, nombre: String, edad: Int) {
        if (!dniValidator.validate(dni)){
            throw AlumnoException.AlumnoDniValidationException("El dni $dni no es válido")
        }
        if (edad < 0){
            throw AlumnoException.AlumnoEdadValidationException("La edad no puede ser negativa")
        }
        println("El alumno con nombre $nombre, edad: $edad y dni $dni es válido")
    }
}