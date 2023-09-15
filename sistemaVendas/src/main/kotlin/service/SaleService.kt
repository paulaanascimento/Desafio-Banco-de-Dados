package service

import connection.Connection
import model.Sale
import java.sql.SQLException
import java.sql.Statement

class SaleService {
    private lateinit var statement: Statement

    init {
        try {
            statement = Connection.startConnection()!!.createStatement()
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun createSale(sale: Sale) {
        try {
            statement.executeUpdate(
                "INSERT INTO sales (seller_cpf, client_cpf, total_value) VALUES " +
                        "('${sale.sellerCPF}', '${sale.clientCPF}', '${sale.totalValue}')")
            println("\nVenda cadastrada com sucesso!")
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun readSale(sql: String) {
        try {
            val resultSet = statement.executeQuery(sql)
            if (!resultSet.next()) {
                println("\nNenhuma venda foi cadastrada!\n")
            } else {
                do {
                    println("\nID: ${resultSet.getString("sale_id")}" +
                            "\nCPF do Vendedor:  ${resultSet.getString("seller_cpf")}" +
                            "\nCPF do Cliente: ${resultSet.getString("client_cpf")}" +
                            "\nValor Total: ${resultSet.getString("total_value")}")
                } while (resultSet.next())
            }
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun updateSale(sale: Sale) {
        try {
            statement.executeUpdate(
                "UPDATE sales SET seller_cpf = '${sale.sellerCPF}'," +
                        "client_cpf = '${sale.clientCPF}' " +
                        "total_value = '${sale.totalValue}' " +
                        "WHERE sale_id = '${sale.id}'")
            println("\nVenda com id ${sale.id} foi atualizado com sucesso!")
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun updateTotalValueForNullsToZero(){
        try {
            statement.executeUpdate("UPDATE sales SET total_value = 0 WHERE total_value IS NULL")
            println("\nValores totais nulos foram atualizados com sucesso!")
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun deleteSale(id: String) {
        try {
            statement.executeUpdate("DELETE FROM sales WHERE sale_id = $id")
            println("\nVenda deletada com sucesso")
        } catch (e: SQLException) {
            println(e.message)
        }
    }

    fun saleIdExists(id: String): Boolean {
        val sql = "SELECT * FROM sales WHERE sale_id = $id"
        return try {
            val resultSet = statement.executeQuery(sql)
            resultSet != null && resultSet.next()
        } catch (e: SQLException) {
            println(e.message)
            false
        }
    }
}