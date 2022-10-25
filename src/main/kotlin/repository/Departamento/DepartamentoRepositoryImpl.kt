package repository.Departamento

import db.DataBaseManager
import models.Departamento
import java.util.*

class DepartamentoRepositoryImpl : DepartamentoRepository {
        // Lista todos los
        override fun findAll(): List<Departamento> {
            // Creamos la consulta
            val query = "SELECT * FROM departamento"

            DataBaseManager.open()
            val result = DataBaseManager.select(query)
            // Creamos la lista de departamentos
            val departamentos = mutableListOf<Departamento>()
            // Recorremos el resultado
            result?.let {
                while (result.next()) {
                    // Creamos el departamento
                    val departamento = Departamento(
                        uuid = it.getObject("uuid") as UUID,
                        nombre = it.getString("nombre"),
                        presupuesto = it.getDouble("presupuesto"),
                    )
                    // Añadimos el departamento a la lista
                    departamentos.add(departamento)
                }
            }

            // Cerramos la conexión
            DataBaseManager.close()
            // Devolvemos la lista de departamentos

            return departamentos.toList()
        }

        override fun findById(id: UUID): Departamento? {
            // Creamos la consulta
            val query = "SELECT * FROM departamento WHERE uuid = ?"
            // Ejecutamos la consulta
            DataBaseManager.open()
            val result = DataBaseManager.select(query, id)
            // Creamos el departamento
            var departamento: Departamento? = null
            // Recorremos el resultado
            result?.let {
                if (result.next()) {
                    // Creamos el tenista
                    departamento = Departamento(
                        uuid = it.getObject("uuid") as UUID,
                        nombre = it.getString("nombre"),
                        presupuesto = it.getDouble("presupuesto"),
                    )
                }
            }
            DataBaseManager.close()
            return departamento
        }

        override fun save(entity: Departamento): Departamento {
            // Si no existe salvamos si existe actualizamos
            // Let run es un if else pero en una sola línea, es más ideomático en Kotlin, no te asustes y usa to if else con null ;)
            val departamento = findById(entity.uuid)
            departamento?.let {
                // Actualizamos
                return update(entity)
            } ?: run {
                // Salvamos
                return insert(entity)
            }
        }

        private fun insert(departamento: Departamento): Departamento {
            // Creamos la consulta
            val query = """INSERT INTO departamento 
            (uuid, nombre, presupuesto) 
            VALUES (?, ?, ?)"""
                .trimIndent()
            // Ejecutamos la consulta
            DataBaseManager.open()
            val result = DataBaseManager.insert(
                query, departamento.uuid, departamento.nombre, departamento.presupuesto
            )
            // Cerramos la conexión
            DataBaseManager.close()
            // Devolvemos el departamento

            return departamento
        }

        private fun update(departamento: Departamento): Departamento {
            // Creamos la consulta
            val query = """UPDATE departamento 
            SET nombre = ?, presupuesto = ?"""
                .trimIndent()
            // Ejecutamos la consulta
            DataBaseManager.open()
            val result = DataBaseManager.update(
                query, departamento.nombre, departamento.presupuesto
            )
            // Cerramos la conexión
            DataBaseManager.close()
            // Devolvemos el tenista

            return departamento
        }

        override fun delete(entity: Departamento): Boolean {
            // Creamos la consulta
            val query = "DELETE FROM departamento WHERE uuid = ?"
            // Ejecutamos la consulta
            DataBaseManager.open()
            val result = DataBaseManager.delete(query, entity.uuid)
            // Cerramos la conexión
            DataBaseManager.close()
            // Devolvemos el resultado
            return result == 1
        }
}