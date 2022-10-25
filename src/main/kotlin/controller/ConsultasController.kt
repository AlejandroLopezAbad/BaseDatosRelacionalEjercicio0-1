package controller

import models.Empleados
import repository.Empleado.EmpleadosRepositoryImpl

class ConsultasController(private val empleadoRepository:EmpleadosRepositoryImpl) {

    fun getAllEmpleados():List<Empleados>{
        return empleadoRepository.findAll()
    }
}