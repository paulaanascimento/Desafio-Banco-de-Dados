package util


class NameValidator {
    companion object{
        fun isValidName(name: String): Boolean {
            val namePattern = "^[\\p{L}\\s]+$".toRegex()
            return name.matches(namePattern)
        }
    }
}