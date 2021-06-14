package com.nowiwr01.languator.logic.errors

data class SignInTextError(
    val list: List<Int>,
    val message: String
) {
    companion object {
        fun createInvalidEmailMessage() = SignInTextError(
            listOf(1),
            "Неверный формат электронной почты"
        )
        fun createEmptyFieldMessage(emptyFields: List<Int>) = SignInTextError(
            emptyFields,
            "У вас ${emptyFields.size} незаполненных поля. Заполните поля ${getFieldNames(emptyFields)}"
        )

        private fun getFieldNames(emptyFields: List<Int>) = mutableListOf<String>().apply {
            emptyFields.forEach {
                when (it) {
                    1 -> add("email")
                    2 -> add("password")
                }
            }
        }
    }

}