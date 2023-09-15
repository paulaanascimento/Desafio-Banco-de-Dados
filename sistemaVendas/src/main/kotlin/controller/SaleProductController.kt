package controller

import model.SaleProducts
import service.SaleProductService
import view.SaleProductView

class SaleProductController {
    private val saleProductService = SaleProductService()
    private val saleProductView = SaleProductView()

    private fun registerSaleProduct() {
        val saleId = saleProductView.requestSaleID()
        val productId = saleProductView.requestProductID()
        val saleProduct = SaleProducts(saleId, productId)
        saleProduct.quantity = saleProductView.requestSaleProductQuantity()
        saleProductService.createSaleProduct(saleProduct)
    }

    private fun readAllSaleProducts() {
        saleProductService.readSaleProducts("SELECT * FROM sales_products")
    }

    private fun readSpecificSaleProduct() {
        val saleId = saleProductView.requestSaleID()
        val productId = saleProductView.requestProductID()
        val sql = "SELECT * FROM sales_products WHERE sale_id = $saleId AND product_id = $productId"
        saleProductService.readSaleProducts(sql)
    }

    private fun updateSaleProduct() {
        val saleId = saleProductView.requestSaleID()
        val productId = saleProductView.requestProductID()
        val saleProduct = SaleProducts(saleId, productId)
        saleProduct.quantity = saleProductView.requestSaleProductQuantity()
        saleProductService.updateSaleProduct(saleProduct)
    }

    private fun deleteSaleProduct() {
        val saleId = saleProductView.requestSaleID()
        val productId = saleProductView.requestProductID()
        saleProductService.deleteSaleProduct(saleId.toString(), productId.toString())
    }

    fun runMenu() {
        while (true) {
            saleProductView.menu()
            when (readln()) {
                "1" -> registerSaleProduct()
                "2" -> readAllSaleProducts()
                "3" -> readSpecificSaleProduct()
                "4" -> updateSaleProduct()
                "5" -> deleteSaleProduct()
                "6" -> return
                else -> println("Opção inválida! Tente novamente!")
            }
        }
    }
}