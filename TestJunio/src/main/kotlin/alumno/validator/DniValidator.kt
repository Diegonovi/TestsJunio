package org.example.alumno.validator

import org.lighthousegames.logging.logging

private val logger = logging()

class DniValidator {
    /**
     * Valida un dni dado
     * @param dni el dni que queremos validar en un string
     * @return un Boolean que indica si es valido o no
     */
    fun validate(dni : String) : Boolean {
        logger.debug { "Validando el Dni: $dni" }
        val regex = "[0-9]{8}[A-Z]".toRegex()
        val number = dni.substring(0,8).toIntOrNull() ?: -1
        val letters = listOf("T","R","W","A","G","M","Y","F","P","D","X",
            "B","N","J","Z","S","Q","V","H","L","C","K","E")
        val result = number % 23
        return (
                dni.matches(regex)
                && dni.length == 9
                && dni[8].toString() == letters[result]
                )
    }
}