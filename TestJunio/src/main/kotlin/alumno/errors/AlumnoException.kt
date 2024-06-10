package org.example.alumno.errors

sealed class AlumnoException(msg : String) : Exception(msg) {
    class AlumnoEdadValidationException(msg: String) : AlumnoException(msg)
    class AlumnoDniValidationException(msg: String) : AlumnoException(msg)
}