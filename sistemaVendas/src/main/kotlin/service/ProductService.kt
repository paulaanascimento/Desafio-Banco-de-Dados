package service

import connection.Connection
import model.Product
import java.sql.SQLException
import java.sql.Statement

class ProductService {
    private lateinit var statement: Statement

    init {
        try {
            statement = Connection.startConnection()!!.createStatement()
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun createProduct(product: Product) {
        try {
            statement.executeUpdate(
                "INSERT INTO products (product_name, product_price) VALUES " +
                        "('${product.name}', '${product.price}')")
            println("\nProduto cadastrado com sucesso!")
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun readProduct(sql: String) {
        try {
            val resultSet = statement.executeQuery(sql)
            if (!resultSet.next()) {
                println("\nNenhum produto foi cadastrado!\n")
            } else {
                do {
                    println("\nID: ${resultSet.getString("product_id")}" +
                            "\nNome: ${resultSet.getString("product_name")}" +
                            "\nPreço:  ${resultSet.getString("product_price")}")
                } while (resultSet.next())
            }
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun updateProduct(product: Product) {
        try {
            statement.executeUpdate(
                "UPDATE sellers SET product_name = '${product.name}'," +
                        "product_price = '${product.price}' " +
                        "WHERE product_id = '${product.id}'")
            println("\nProduto com id ${product.id} foi atualizado com sucesso!")
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun deleteProduct(id: Long) {
        try {
            statement.executeUpdate("DELETE FROM products WHERE product_id = $id")
            println("\nProduto deletado com sucesso")
        } catch (e: SQLException) {
            println(e.message)
        }
    }

    fun productIdExists(id: Long): Boolean {
        val sql = "SELECT * FROM products WHERE product_id = $id"
        return try {
            val resultSet = statement.executeQuery(sql)
            resultSet != null && resultSet.next()
        } catch (e: SQLException) {
            println(e.message)
            false
        }
    }
}