package util

import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress

class EmailValidator {
    companion object{
        fun isValidEmail(email: String): Boolean {
            return try {
                val email = InternetAddress(email)
                email.validate()
                true
            } catch (exception: AddressException) {
                false
            }
        }
    }
}