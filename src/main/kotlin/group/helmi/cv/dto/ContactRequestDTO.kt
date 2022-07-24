package group.helmi.cv.dto

data class ContactRequestDTO(
    val name: String,
    val email: String,
    val phone: String?,
    val subject: String,
    val message: String?
)