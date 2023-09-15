package controller

import model.Client
import service.ClientService
import view.ClientView


class ClientController {
    private var clientService = ClientService()
    private var clientView = ClientView()

    private fun registerClient(){
        val client = Client(clientView.requestClientCpf())
        if(!clientService.clientCpfExists(client.cpf)){
            client.name = clientView.requestClientName()
            client.email = clientView.requestClientEmail()
            client.address = clientView.requestClientAddress()
            clientService.createClient(client)
        } else println("Cliente já cadastrado!")
    }

    private fun readAllClients(){
        clientService.readClient("SELECT * FROM clients")
    }

    private fun readSpecificClient() {
        val cpf: String = clientView.requestClientCpf()
        if (clientService.clientCpfExists(cpf)) {
            clientService.readClient("SELECT * FROM clients WHERE cpf = '$cpf'")
        } else println("\nNão há cliente cadastrado com o cpf informado")
    }

    fun readPeopleWithEmailZup(){
        clientService.readClient("SELECT * FROM clients WHERE email LIKE '%zup.com.br%' UNION ALL " +
                "SELECT * FROM sellers WHERE email LIKE '%zup.com.br%';")
    }

    private fun update() {
        val cpf = clientView.requestClientCpf()
        if (clientService.clientCpfExists(cpf)) {
            val client = Client(cpf)
            client.name = clientView.requestClientName()
            client.email = clientView.requestClientEmail()
            client.address = clientView.requestClientAddress()
            clientService.updateClient(client)
        } else println("\nNão há autor cadastrado com o id informado")
    }

    private fun delete() {
        val cpf = clientView.requestClientCpf()
        if (clientService.clientCpfExists(cpf)) {
            clientService.deleteClient(cpf)
        } else println("\nNão há cliente cadastrado com o cpf informado")
    }

    fun runMenu(){
        while (true){
            clientView.menu()
            when(readln()){
                "1" -> registerClient()
                "2" -> readAllClients()
                "3" -> readSpecificClient()
                "4" -> update()
                "5" -> delete()
                "6" -> return
                else -> println("Opção inválida! Tente novamente!")
            }
        }
    }
}