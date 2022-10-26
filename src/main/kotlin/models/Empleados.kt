package models

import java.time.LocalDate
import java.util.*

class Empleados(
    val uuid: UUID,
    val nombreEmpleado:String,
    val fechaAlta: LocalDate,
    val departamento:Departamento?
    ) {
}