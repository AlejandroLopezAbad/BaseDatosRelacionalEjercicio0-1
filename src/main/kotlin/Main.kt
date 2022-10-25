import controller.ConsultasController
import db.DataBaseManager
import db.createTablesDepartamento
import db.createTablesEmpleados
import repository.Departamento.DepartamentoRepositoryImpl
import repository.Empleado.EmpleadosRepositoryImpl
import java.io.File

fun main(args: Array<String>) {
   /* println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")*/


    val controller= ConsultasController(EmpleadosRepositoryImpl(DepartamentoRepositoryImpl()))

    createTablesEmpleados()
    createTablesDepartamento()

    println(controller.getAllEmpleados())


}








fun initDataBase() {
    DataBaseManager.open()
    // Si lo estoy haciendo yo desde el código!!!
    // DataBaseManager.createTables(createTables())
    // Si lo hago paśnadole un fichero SQL, podría crear los datos de la base de datos
    val sqlFile = System.getProperty("user.dir") + File.separator + "db" + File.separator + "init.sql"
    DataBaseManager.initData(sqlFile)

    DataBaseManager.close()
}