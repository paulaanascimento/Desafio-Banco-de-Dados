package view

import util.CpfValidator
import util.EmailValidator
import util.NameValidator

class ClientView {
    fun menu() {
        println(
            "\n---------- CLIENTES ----------\n" +
                    "\t1.Cadastrar Cliente\n" +
                    "\t2.Mostrar Todos os Clientes\n" +
                    "\t3.Pesquisar Cliente\n" +
                    "\t4.Atualizar Cliente\n" +
                    "\t5.Deletar Cliente\n" +
                    "\t6.Voltar ao Menu Principal"
        )
        print("Digite o número correspondente a opção desejada: ")
    }

    fun requestClientCpf(): String {
        var cpf: String
        do {
            print("Digite o cpf do cliente: ")
            cpf = readln()
            if (!CpfValidator.isValidCpf(cpf)) {
                println("\nPor favor, insira um cpf válido!")
            }
        } while (!CpfValidator.isValidCpf(cpf))

        return cpf
    }

    fun requestClientName(): String? {
        var name: String?
        do {
            print("Digite o nome do cliente: ")
            name = readlnOrNull()
            if (name != null && !NameValidator.isValidName(name)) {
                println("\nPor favor, insira um nome válido!")
            }
        } while (name != null && !NameValidator.isValidName(name))

        return name
    }

    fun requestClientEmail(): String? {
        var email: String?
        do {
            print("Digite o email do cliente: ")
            email = readlnOrNull()
            if (email != null && !EmailValidator.isValidEmail(email)) {
                println("\nPor favor, insira um email válido!")
            }
        } while (email != null && !EmailValidator.isValidEmail(email))

        return email
    }

    fun requestClientAddress(): String {
        print("Digite o endereço do cliente: ")
        return readln()
    }
}