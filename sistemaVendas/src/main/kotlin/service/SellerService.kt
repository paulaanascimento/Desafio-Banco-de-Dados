package service

import connection.Connection
import model.Seller
import java.sql.SQLException
import java.sql.Statement

class SellerService {
    private lateinit var statement: Statement

    init {
        try {
            statement = Connection.startConnection()!!.createStatement()
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun createSeller(seller: Seller) {
        try {
            statement.executeUpdate(
                "INSERT INTO sellers (cpf, name, email, salary) VALUES " +
                        "('${seller.cpf}', '${seller.name}', '${seller.email}', '${seller.salary}')")
            println("\nVendedor cadastrado com sucesso!")
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun readSeller(sql: String) {
        try {
            val resultSet = statement.executeQuery(sql)
            if (!resultSet.next()) {
                println("\nNenhum vendedor foi cadastrado!\n")
            } else {
                do {
                    println("\nCPF: ${resultSet.getString("cpf")}" +
                            "\nNome:  ${resultSet.getString("name")}" +
                            "\nEmail:  ${resultSet.getString("email")}" +
                            "\nSalário:  ${resultSet.getString("salary")}")
                } while (resultSet.next())
            }
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun updateSeller(seller: Seller) {
        try {
            statement.executeUpdate(
                "UPDATE sellers SET name = '${seller.name}'," +
                        "email = '${seller.email}', " +
                        "salary = '${seller.salary}' " +
                        "WHERE cpf = '${seller.cpf}'")
            println("\nVendedor com CPF ${seller.cpf} foi atualizado com sucesso!")
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun deleteSeller(cpf: String) {
        try {
            statement.executeUpdate("DELETE FROM sellers WHERE cpf = $cpf")
            println("\nVendedor deletado com sucesso")
        } catch (e: SQLException) {
            println(e.message)
        }
    }

    fun sellerCpfExists(cpf: String): Boolean {
        val sql = "SELECT * FROM sellers WHERE cpf = $cpf"
        return try {
            val resultSet = statement.executeQuery(sql)
            resultSet != null && resultSet.next()
        } catch (e: SQLException) {
            println(e.message)
            false
        }
    }
}