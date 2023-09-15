package connection

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import kotlin.system.exitProcess

class Connection {
    fun startConnection(): Connection? {
        try {
            val connection = DriverManager.getConnection(
                "jdbc:postgresql://silly.db.elephantsql.com:5432/rjwnjffj",
                "rjwnjffj", "T9sf5fF48SFIH9X15jv2L5YTNkdsXMoq"
            )
            if (connection != null) {
                println("A CONEXÃO COM O BANCO DE DADOS FOI BEM SUCEDIDA!")
            } else {
                println("FALHA AO CONECTAR COM O BANCO DE DADOS!")
                exitProcess(-1)
            }
            return connection
        } catch (exception:SQLException) {
            exception.printStackTrace()
            return null
        }
    }
}