package service

import connection.Connection
import model.SaleProducts
import java.sql.SQLException
import java.sql.Statement

class SaleProductService {
    private lateinit var statement: Statement

    init {
        try {
            statement = Connection.startConnection()!!.createStatement()
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }
    fun createSaleProduct(saleProduct: SaleProducts) {
        try {
            statement.executeUpdate(
                "INSERT INTO sales_products (sale_id, product_id, quantity) VALUES " +
                        "('${saleProduct.saleId}', '${saleProduct.productId}', '${saleProduct.quantity}')")
            println("\nProduto de venda cadastrado com sucesso!")
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun readSaleProducts(sql: String) {
        try {
            val resultSet = statement.executeQuery(sql)
            if (!resultSet.next()) {
                println("\nNenhum produto de venda foi cadastrado!\n")
            } else {
                do {
                    println("\nID da Venda: ${resultSet.getString("sale_id")}" +
                            "\nID do Produto: ${resultSet.getString("product_id")}" +
                            "\nQuantidade: ${resultSet.getString("quantity")}")
                } while (resultSet.next())
            }
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun updateSaleProduct(saleProduct: SaleProducts) {
        try {
            statement.executeUpdate(
                "UPDATE sales_products SET quantity = '${saleProduct.quantity}' " +
                        "WHERE sale_id = '${saleProduct.saleId}' AND product_id = '${saleProduct.productId}'")
            println("\nProduto de venda atualizado com sucesso!")
        } catch (exception: SQLException) {
            println(exception.message)
        }
    }

    fun deleteSaleProduct(saleId: String, productId: String) {
        try {
            statement.executeUpdate("DELETE FROM sales_products " +
                    "WHERE sale_id = '$saleId' AND product_id = '$productId'")
            println("\nProduto de venda deletado com sucesso")
        } catch (e: SQLException) {
            println(e.message)
        }
    }
}