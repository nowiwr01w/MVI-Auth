package com.nowiwr01.languator.logic.errors

data class SignUpTextError(
    val list: List<Int>,
    val message: String,
) {
    companion object {
        fun createWeakPasswordMessage() = SignUpTextError(
            listOf(3, 4),
            "Пароль должен содежрать хотя бы одну заглавную букву",
        )
        fun createShortPasswordMessage() = SignUpTextError(
            listOf(3, 4),
            "Пароль должен содежрать не менее 8 символов",
        )
        fun createNotEqualPasswordMessage() = SignUpTextError(
            listOf(3, 4),
            "Пароли должны совпадать",
        )
        fun createEmptyFieldMessage(emptyFields: List<Int>) = SignUpTextError(
            emptyFields,
            "У вас ${emptyFields.size} незаполненных поля. Заполните поля ${getFieldNames(emptyFields)}",
        )
        fun createInvalidEmailMessage() = SignUpTextError(
            listOf(1),
            "Неверный формат электронной почты"
        )

        private fun getFieldNames(list: List<Int>) = mutableListOf<String>().apply {
            list.forEach {
                when (it) {
                    1 -> add("email")
                    2 -> add("username")
                    3 -> add("password")
                    4 -> add("password_repeat")
                }
            }
        }
    }
}