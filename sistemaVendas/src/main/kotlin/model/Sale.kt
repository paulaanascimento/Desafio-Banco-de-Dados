package model

class Sale (val sellerCPF: String, val clientCPF: String){
    var id: Long = 0
    var totalValue: Double? = null
}