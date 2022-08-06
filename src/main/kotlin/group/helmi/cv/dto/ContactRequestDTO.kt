package group.helmi.cv.dto

data class ContactRequestDTO(
    val name: String,
    val email: String,
    val phone: String?,
    val subject: String,
    val message: String?
) {
    fun toMailDetails(): String {
        var result = "Name: $name\n"
        result += "E-Mail: $email\n"
        result += "Phone: $phone\n"
        result += "Subject: $subject\n"
        if (message != null) {
            result += "Message: $message"
        }
        return result
    }
}