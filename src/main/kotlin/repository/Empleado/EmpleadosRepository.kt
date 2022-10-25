package repository.Empleado


import models.Empleados
import repository.CrudRepository
import java.util.*

interface EmpleadosRepository : CrudRepository<Empleados, UUID> {
}