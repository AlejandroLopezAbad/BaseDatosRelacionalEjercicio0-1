package repository.Empleado

import db.DataBaseManager
import db.DataBaseManager.insert
import db.DataBaseManager.update
import models.Departamento
import models.Empleados
import repository.Departamento.DepartamentoRepository
import repository.Departamento.DepartamentoRepositoryImpl
import java.time.LocalDate
import java.util.*

class EmpleadosRepositoryImpl(private val dp: DepartamentoRepositoryImpl) : EmpleadosRepository {


    override fun findAll(): List<Empleados> {

        val query = "SELECT * FROM empleados"

        DataBaseManager.open()
        val result = DataBaseManager.select(query)

        val listaEmpleados = mutableListOf<Empleados>()

        result?.let {
            while (result.next()) {
                val empleado = Empleados(
                    uuid = it.getObject("uuid") as UUID,
                    nombreEmpleado = it.getString("nombreEmpleado"),
                    fechaAlta = LocalDate.parse(it.getObject("fechaAlta").toString()),
                    departamento = dp.findById(it.getObject("departamento_uuid") as UUID)
                )
                listaEmpleados.add(empleado)
            }
        }
        DataBaseManager.close()

        return listaEmpleados.toList()

    }

    /*el let en caso de que encuentre el departamento ejecuta la creacion del objeto empleado con el departamento en caso de no encontrarlo el empleado
    es nulo y por tanto no se añade a la lista*/


    override fun findById(id: UUID): Empleados? {
        // Creamos la consulta
        val query = "SELECT * FROM empleados WHERE uuid = ?"
        // Ejecutamos la consulta
        DataBaseManager.open()
        val result = DataBaseManager.select(query, id)
        // Creamos el departamento
        var empleado: Empleados? = null
        // Recorremos el resultado
        result?.let {
            if (result.next()) {
                // Creamos el empleado
                empleado = Empleados(
                    uuid = it.getObject("uuid") as UUID,
                    nombreEmpleado = it.getString("nombreEmpleado"),
                    fechaAlta = LocalDate.parse(it.getObject("fechaAlta").toString()),
                    departamento = dp.findById(it.getObject("departamento_uuid") as UUID)
                )

            }
        }
        DataBaseManager.close()
        return empleado
    }


    override fun save(entity: Empleados): Empleados {
        // Si no existe salvamos si existe actualizamos
        // Let run es un if else pero en una sola línea, es más ideomático en Kotlin, no te asustes y usa to if else con null ;)
        val empleados = findById(entity.uuid)
        empleados?.let {
            // Actualizamos
            return update(entity)
        } ?: run {
            // Salvamos
            return insert(entity)
        }
    }


    private fun insert(empleado: Empleados): Empleados {
        // Creamos la consulta
        val query = """INSERT INTO empleados 
            (uuid, nombre, fechaAlta, departamento_uuid) 
            VALUES (?, ?, ?,?)"""
            .trimIndent()
        // Ejecutamos la consulta
        DataBaseManager.open()
        val result = DataBaseManager.insert(
            query, empleado.uuid, empleado.nombreEmpleado, empleado.fechaAlta, empleado.departamento
        )
        // Cerramos la conexión
        DataBaseManager.close()
        // Devolvemos el empleado

        return empleado
    }

    private fun update(empleado: Empleados): Empleados {
        // Creamos la consulta
        val query = """UPDATE empleados 
            SET nombreEmpleado = ?, fechaAlta = ?, departamento_uuid = ?"""
            .trimIndent()
        // Ejecutamos la consulta
        DataBaseManager.open()
        val result = DataBaseManager.update(
            query, empleado.nombreEmpleado, empleado.fechaAlta, empleado.departamento
        )
        // Cerramos la conexión
        DataBaseManager.close()
        // Devolvemos el tenista

        return empleado
    }

    override fun delete(entity: Empleados): Boolean {

        val query = "DELETE * FROM empleados WHERE uuid = ?"
        DataBaseManager.open()
        val result = DataBaseManager.delete(query, entity.uuid)
        // Cerramos la conexión
        DataBaseManager.close()
        // Devolvemos el resultado
        return result == 1


    }


}