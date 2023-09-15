import controller.*
import kotlin.system.exitProcess

fun main() {
    val clientView = ClientController()
    val sellerView = SellerController()
    val productController = ProductController()
    val saleView = SaleController()
    val saleProductView = SaleProductController()

    do{
        println("---------- MENU PRINCIPAL ----------\n" +
                "\t1.Clientes\n" +
                "\t2.Vendedores\n" +
                "\t3.Produtos\n" +
                "\t4.Vendas\n" +
                "\t5.Produtos de Venda\n" +
                "\t6.Mostrar Pessoas com Email zup.com.br\n" +
                "\t7.Encerrar Aplicação")
        print("Digite o número correspondente a opção desejada: ")

        when(readln()){
            "1" -> clientView.runMenu()
            "2" -> sellerView.runMenu()
            "3" -> productController.runMenu()
            "4" -> saleView.runMenu()
            "5" -> saleProductView.runMenu()
            "6" -> clientView.readPeopleWithEmailZup()
            "7" -> exitProcess(1)
            else -> println("Opção inválida! Tente novamente!")
        }
    } while (true)
}