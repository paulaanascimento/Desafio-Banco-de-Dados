package model

class Sales (val sellerCPF: String, val clientCPF: String, val productId: Long){
    var productQuantity: Int = 0
    var totalValue: Double? = null
}