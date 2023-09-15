package controller

import model.Product
import service.ProductService
import view.ProductView

class ProductController {
    private var productService = ProductService()
    private var productView = ProductView()

    private fun registerProduct() {
        val product = Product()
        product.name = productView.requestProductName()
        product.price = productView.requestProductPrice()
        productService.createProduct(product)
    }

    private fun readAllProducts() {
        productService.readProduct("SELECT * FROM products")
    }

    private fun readSpecificProduct() {
        val id = productView.requestProductId()
        if (productService.productIdExists(id)) {
            productService.readProduct("SELECT * FROM products WHERE product_id = $id")
        } else println("\nNão há produto cadastrado com o ID informado")
    }

    private fun update() {
        val id = productView.requestProductId()
        if (productService.productIdExists(id)) {
            val product = Product()
            product.id = id
            product.name = productView.requestProductName()
            product.price = productView.requestProductPrice()

            productService.updateProduct(product)
        } else println("\nNão há produto cadastrado com o ID informado")
    }

    private fun delete() {
        val id = productView.requestProductId()
        if (productService.productIdExists(id)) {
            productService.deleteProduct(id)
        } else println("\nNão há produto cadastrado com o ID informado")
    }

    fun runMenu() {
        while (true) {
            productView.menu()
            when (readln()) {
                "1" -> registerProduct()
                "2" -> readAllProducts()
                "3" -> readSpecificProduct()
                "4" -> update()
                "5" -> delete()
                "6" -> return
                else -> println("Opção inválida! Tente novamente!")
            }
        }
    }
}