package view

class SaleProductView {
    fun menu() {
        println(
            "\n---------- PRODUTOS DE VENDA ----------\n" +
                    "\t1.Cadastrar Produto de Venda\n" +
                    "\t2.Mostrar Todos os Produtos de Venda\n" +
                    "\t3.Pesquisar Produto de Venda\n" +
                    "\t4.Atualizar Produto de Venda\n" +
                    "\t5.Deletar Produto de Venda\n" +
                    "\t6.Voltar ao Menu Anterior"
        )
        print("Digite o número correspondente a opção desejada: ")
    }

    fun requestSaleID(): Long {
        print("Digite o ID da venda: ")
        return readln().toLongOrNull() ?: 0
    }

    fun requestProductID(): Long {
        print("Digite o ID do produto: ")
        return readln().toLongOrNull() ?: 0
    }

    fun requestSaleProductQuantity(): Int {
        var quantity: Int
        do {
            print("Digite a quantidade do produto: ")
            quantity = readln().toIntOrNull() ?: 0
            if (quantity <= 0) {
                println("\nPor favor, insira uma quantidade válida!")
            }
        } while (quantity <= 0)
        return quantity
    }
}