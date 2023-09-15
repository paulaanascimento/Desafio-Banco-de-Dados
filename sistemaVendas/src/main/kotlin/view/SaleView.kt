package view

class SaleView {
    fun menu(){
        println("\n---------- VENDAS ----------\n" +
                "\t1.Cadastrar Venda\n" +
                "\t2.Mostrar Todas as Vendas\n" +
                "\t3.Pesquisar Venda\n" +
                "\t4.Atualizar Venda\n" +
                "\t5.Deletar Venda\n" +
                "\t6.Pesquisar vendas acima de R$10.00\n" +
                "\t7.Alterar valores totais nulos para zero\n" +
                "\t8.Voltar ao Menu Principal")
        print("Digite o número correspondente a opção desejada: ")
    }

    fun requestSaleId(): Long {
        print("Digite o ID da venda: ")
        return readln().toLongOrNull()?:0
    }

    fun requestSaleSellerCPF(): String {
        print("Digite o CPF do vendedor: ")
        return readln()
    }

    fun requestSaleClientCPF(): String {
        print("Digite o CPF do cliente: ")
        return readln()
    }

    fun requestSaleTotalValue(): Double {
        var totalValue: Double
        do {
            print("Digite o valor total da venda: ")
            totalValue = readln().toDoubleOrNull() ?: 0.0
            if (totalValue <= 0) {
                println("\nPor favor, insira um valor válido!")
            }
        } while (totalValue <= 0)
        return totalValue
    }
}