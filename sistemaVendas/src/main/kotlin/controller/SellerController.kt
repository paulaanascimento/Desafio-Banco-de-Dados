package controller

import model.Seller
import service.SellerService
import view.SellerView

class SellerController {
    private var sellerService = SellerService()
    private var sellerView = SellerView()

    private fun registerSeller() {
        val seller = Seller(sellerView.requestSellerCpf())
        if (!sellerService.sellerCpfExists(seller.cpf)) {
            seller.name = sellerView.requestSellerName()
            seller.email = sellerView.requestSellerEmail()
            seller.salary = sellerView.requestSellerSalary()
            sellerService.createSeller(seller)
        } else println("Vendedor já cadastrado!")
    }

    private fun readAllSellers() {
        sellerService.readSeller("SELECT * FROM sellers")
    }

    private fun readSpecificSeller() {
        val cpf: String = sellerView.requestSellerCpf()
        if (sellerService.sellerCpfExists(cpf)) {
            sellerService.readSeller("SELECT * FROM sellers WHERE cpf = '$cpf'")
        } else println("\nNão há vendedor cadastrado com o CPF informado")
    }

    private fun update() {
        val cpf = sellerView.requestSellerCpf()
        if (sellerService.sellerCpfExists(cpf)) {
            val seller = Seller(cpf)
            seller.name = sellerView.requestSellerName()
            seller.email = sellerView.requestSellerEmail()
            seller.salary = sellerView.requestSellerSalary()
            sellerService.updateSeller(seller)
        } else println("\nNão há vendedor cadastrado com o CPF informado")
    }

    private fun delete() {
        val cpf = sellerView.requestSellerCpf()
        if (sellerService.sellerCpfExists(cpf)) {
            sellerService.deleteSeller(cpf)
        } else println("\nNão há vendedor cadastrado com o CPF informado")
    }

    private fun readSellerSalary(){
        sellerService.readSeller("SELECT salary FROM sellers ORDER BY salary DESC")
    }

    fun runMenu() {
        while (true) {
            sellerView.menu()
            when (readln()) {
                "1" -> registerSeller()
                "2" -> readAllSellers()
                "3" -> readSpecificSeller()
                "4" -> update()
                "5" -> delete()
                "6" -> readSellerSalary()
                "7" -> return
                else -> println("Opção inválida! Tente novamente!")
            }
        }
    }
}