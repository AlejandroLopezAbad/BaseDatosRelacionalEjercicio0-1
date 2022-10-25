package db

import models.Empleados
import java.util.*

fun createTablesDepartamento() = """
    CREATE TABLE IF NOT EXISTS departamento(
    uuid UUID PRIMARY KEY,    
    nombre VARCHAR(255) NOT NULL,
    presupuesto DOUBLE NOT NULL,                        
    )
    """.trimIndent()

fun createTablesEmpleados()="""
     CREATE TABLE IF NOT EXISTS empleados(
     uuid UUID PRIMARY KEY,
     nombre VARCHAR(255) NOT NULL,
     fechaAlta DATE NOT NULL,
     departamento_uuid UUID NOT NULL,
     FOREING KEY(departamento_uuid) reference departamento(uuid)
     ) 
""".trimIndent()


