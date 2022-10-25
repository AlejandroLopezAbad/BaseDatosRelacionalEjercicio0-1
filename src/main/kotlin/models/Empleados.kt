package models

import java.time.LocalDate
import java.util.*

class Empleados(
    val id: UUID,
    val nombreEmpleado:String,
    val fechaContratacion: LocalDate,
    val departamento:Departamento
    ) {
}