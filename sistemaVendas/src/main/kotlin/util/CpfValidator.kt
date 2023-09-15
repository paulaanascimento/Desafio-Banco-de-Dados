package util

import br.com.caelum.stella.validation.CPFValidator

class CpfValidator {
    companion object{
        fun isValidCpf(cpf: String): Boolean {
            return try {
                val cpfValidator = CPFValidator()
                cpfValidator.assertValid(cpf)
                true
            } catch (exception: Exception) {
                false
            }
        }
    }
}