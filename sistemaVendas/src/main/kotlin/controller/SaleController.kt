package controller

import model.Sale
import service.SaleService
import view.SaleView

class SaleController {
    private val saleService = SaleService()
    private val saleView = SaleView()

    private fun registerSale() {
        val sellerCPF = saleView.requestSaleSellerCPF()
        val clientCPF = saleView.requestSaleClientCPF()
        val sale = Sale(sellerCPF, clientCPF)
        sale.totalValue = saleView.requestSaleTotalValue()
        saleService.createSale(sale)
    }

    private fun readAllSales() {
        saleService.readSale("SELECT * FROM sales")
    }

    private fun readSpecificSale() {
        val saleId = saleView.requestSaleId()
        if (saleService.saleIdExists(saleId)) {
            saleService.readSale("SELECT * FROM sales WHERE sale_id = $saleId")
        } else {
            println("\nNão há venda cadastrada com o ID informado")
        }
    }

    private fun updateSale() {
        val saleId = saleView.requestSaleId()
        if (saleService.saleIdExists(saleId)) {
            val sellerCPF = saleView.requestSaleSellerCPF()
            val clientCPF = saleView.requestSaleClientCPF()
            val sale = Sale(sellerCPF, clientCPF)
            sale.id = saleId
            sale.totalValue = saleView.requestSaleTotalValue()
            saleService.updateSale(sale)
        } else {
            println("\nNão há venda cadastrada com o ID informado")
        }
    }

    private fun deleteSale() {
        val saleId = saleView.requestSaleId()
        if (saleService.saleIdExists(saleId)) {
            saleService.deleteSale(saleId)
        } else {
            println("\nNão há venda cadastrada com o ID informado")
        }
    }

    private fun filterSalesAboveValue() {
        val sql = "SELECT * FROM sales WHERE total_value > 10.0"
        saleService.readSale(sql)
    }

    private fun updateTotalValuesForNullsToZero() {
        saleService.updateTotalValueForNullsToZero()
    }

    fun runMenu() {
        while (true) {
            saleView.menu()
            when (readln()) {
                "1" -> registerSale()
                "2" -> readAllSales()
                "3" -> readSpecificSale()
                "4" -> updateSale()
                "5" -> deleteSale()
                "6" -> filterSalesAboveValue()
                "7" -> updateTotalValuesForNullsToZero()
                "8" -> return
                else -> println("Opção inválida! Tente novamente!")
            }
        }
    }
}