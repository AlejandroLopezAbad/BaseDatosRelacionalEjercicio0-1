package db

import models.Departamento
import models.Empleados
import java.time.LocalDate
import java.util.*


fun getEmpleadosInit() = listOf(
    Empleados(
        uuid = UUID.randomUUID(),
        nombreEmpleado = "Rafael Nadal",
        fechaAlta = LocalDate.parse("1985-06-04"),
        departamento = getDepartamentosInit().get(0)
    ),
    Empleados(
        uuid = UUID.randomUUID(),
        nombreEmpleado = "Suscribete a xCard_",
        fechaAlta = LocalDate.parse("1985-06-04"),
        departamento = getDepartamentosInit().get(1)
    ),
    Empleados(
        uuid = UUID.randomUUID(),
        nombreEmpleado = "Yisus",
        fechaAlta = LocalDate.parse("1985-06-04"),
        departamento = getDepartamentosInit().get(3)
    ),
    Empleados(
        uuid = UUID.randomUUID(),
        nombreEmpleado = "Manuel el de la paja",
        fechaAlta = LocalDate.parse("1985-06-04"),
        departamento = getDepartamentosInit().get(4)
    ),
)

fun getDepartamentosInit ()= listOf(
    Departamento(
        uuid = UUID.randomUUID(),
        nombre = "xCard",
        presupuesto = 1200.0


    ),
    Departamento(
        uuid = UUID.randomUUID(),
        nombre = "ByFusion",
        presupuesto = 3200.0


    ),
    Departamento(
        uuid = UUID.randomUUID(),
        nombre = "xCardCONPASTA",
        presupuesto = 1.0


    ),
    Departamento(
        uuid = UUID.randomUUID(),
        nombre = "ByalexittoArbitros",
        presupuesto = 30000000.0 // paris major

    ),





)




