package service

import connection.Connection
import model.Client
import java.sql.SQLException
import java.sql.Statement

class ClientService {
    private lateinit var statement: Statement

    init {
        try {
            statement = Connection.startConnection()!!.createStatement()
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun createClient(client: Client) {
        try {
            statement.executeUpdate(
                "INSERT INTO clients (cpf, name, email, address) VALUES " +
                        "('${client.cpf}', '${client.name}', '${client.email}', '${client.address}')")
            println("\nCliente cadastrado com sucesso!")
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun readClient(sql: String) {
        try {
            val resultSet = statement.executeQuery(sql)
            if (!resultSet.next()) {
                println("\nNenhum cliente foi cadastrado!\n")
            } else {
                do {
                    println("\nCPF: ${resultSet.getString("cpf")}" +
                            "\nNome:  ${resultSet.getString("name")}" +
                            "\nEmail:  ${resultSet.getString("email")}" +
                            "\nEndereço:  ${resultSet.getString("address")}")
                } while (resultSet.next())
            }
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun updateClient(client: Client) {
        try {
            statement.executeUpdate(
                "UPDATE clients SET name = '${client.name}'," +
                        "email = '${client.email}', " +
                        "address = '${client.address}' " +
                    "WHERE cpf = '${client.cpf}'")
            println("\nCliente com CPF ${client.cpf} foi atualizado com sucesso!")
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun deleteClient(cpf: String) {
        try {
            statement.executeUpdate("DELETE FROM clients WHERE cpf = $cpf")
            println("\nCliente deletado com sucesso")
        } catch (e: SQLException) {
            println(e.message)
        }
    }

    fun clientCpfExists(cpf: String): Boolean {
        val sql = "SELECT * FROM clients WHERE cpf = $cpf"
        return try {
            val resultSet = statement.executeQuery(sql)
            resultSet != null && resultSet.next()
        } catch (e: SQLException) {
            println(e.message)
            false
        }
    }
}