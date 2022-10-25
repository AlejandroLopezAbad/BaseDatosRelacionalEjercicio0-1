package repository.Empleado

import db.DataBaseManager
import models.Departamento
import models.Empleados
import repository.Departamento.DepartamentoRepository
import repository.Departamento.DepartamentoRepositoryImpl
import java.time.LocalDate
import java.util.*

class EmpleadosRepositoryImpl( private val dp :DepartamentoRepositoryImpl) : EmpleadosRepository {




    override fun findAll(): List<Empleados> {

        val query = "SELECT * FROM empleados"

        DataBaseManager.open()
        val result = DataBaseManager.select(query)

        val listaEmpleados = mutableListOf<Empleados>()

        result?.let {
            while (result.next()) {
                val depID = it.getObject("departamento_uuid") as UUID;
                val empleado = dp.findById(depID)?.let { it1 ->
                    Empleados(
                        uuid = it.getObject("uuid") as UUID,
                        nombreEmpleado = it.getString("nombreEmpleado"),
                        fechaAlta = LocalDate.parse(it.getObject("fechaAlta").toString()),

                        departamento = it1
                    )
                }
                if (empleado != null) {
                    listaEmpleados.add(empleado)
                }
            }

        }
        DataBaseManager.close()

        return listaEmpleados.toList()
        /*el let en caso de que encuentre el departamento ejecuta la creacion del objeto empleado con el departamento en caso de no encontrarlo el empleado
        es nulo y por tanto no se añade a la lista*/
    }





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
                val depID = it.getObject("departamento_uuid") as UUID;
                empleado = dp.findById(depID)?.let { it1 ->
                    Empleados(
                        uuid = it.getObject("uuid") as UUID,
                        nombreEmpleado = it.getString("nombreEmpleado"),
                        fechaAlta = LocalDate.parse(it.getObject("fechaAlta").toString()),
                        departamento = it1
                    )
                }
            }
        }
        DataBaseManager.close()
        return empleado
    }




    override fun save(entity: Empleados): Empleados {
        TODO("Not yet implemented")
    }

    override fun delete(entity: Empleados): Boolean {
        TODO("Not yet implemented")
    }


}