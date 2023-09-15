package view

class ProductView {
    fun menu(){
        println("\n---------- PRODUTOS ----------\n" +
                "\t1.Cadastrar Produto\n" +
                "\t2.Mostrar Todos os Produtos\n" +
                "\t4.Pesquisar Produto\n" +
                "\t5.Atualizar Produto\n" +
                "\t6.Deletar Produto\n" +
                "\t7.Voltar ao Menu Principal")
        print("Digite o número correspondente a opção desejada: ")
    }

    fun requestProductId(): Long {
        print("Digite o id do produto: ")
        return readln().toLongOrNull()?:0
    }

    fun requestProductName(): String {
        print("Digite o nome do produto: ")
        return readln()
    }

    fun requestProductPrice(): Double {
        var price:Double
        do{
            print("Digite o preço do produto: ")
            price = readln().toDoubleOrNull()?:0.0

            if(price <=0){
                println("\nPor favor, insira um valor válido!")
            }
        }while (price <= 0)

        return price
    }
}