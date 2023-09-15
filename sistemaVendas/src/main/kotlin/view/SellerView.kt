package view

import util.CpfValidator
import util.EmailValidator
import util.NameValidator

class SellerView {
    fun menu(){
        println("\n---------- VENDEDORES ----------\n" +
                "\t1.Cadastrar Vendedor\n" +
                "\t2.Mostrar Todos os Vendedores\n" +
                "\t3.Pesquisar Vendedor\n" +
                "\t4.Atualizar Vendedor\n" +
                "\t5.Deletar Vendedor\n" +
                "\t6.Mostrar Salários dos Vendedores\n" +
                "\t7.Voltar ao Menu Principal")
        print("Digite o número correspondente a opção desejada: ")
    }

    fun requestSellerCpf():String{
        var cpf:String
        do{
            print("Digite o cpf do vendedor: ")
            cpf = readln()
            if(!CpfValidator.isValidCpf(cpf)){
                println("\nPor favor, insira um cpf válido!")
            }
        }while (!CpfValidator.isValidCpf(cpf))

        return cpf
    }

    fun requestSellerName():String?{
        var name:String?
        do{
            print("Digite o nome do vendedor: ")
            name = readlnOrNull()
            if(name != null && !NameValidator.isValidName(name)){
                println("\nPor favor, insira um nome válido!")
            }
        }while (name != null && !NameValidator.isValidName(name))

        return name
    }

    fun requestSellerEmail():String?{
        var email:String?
        do{
            print("Digite o email do vendedor: ")
            email = readlnOrNull()
            if(email != null && !EmailValidator.isValidEmail(email)){
                println("\nPor favor, insira um email válido!")
            }
        }while (email != null && !EmailValidator.isValidEmail(email))

        return email
    }

    fun requestSellerSalary():Double?{
        var salary:Double?
        do{
            print("Digite o salário do vendedor: ")
            salary = readln().toDoubleOrNull()
           if(salary != null && salary <= 0){
               println("\nPor favor, insira um valor válido!")
           }
        }while (salary != null && salary <= 0)

        return salary
    }
}