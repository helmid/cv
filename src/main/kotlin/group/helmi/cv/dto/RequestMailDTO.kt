package group.helmi.cv.dto

data class RequestMailDTO(
    val from: String,
    val to: String,
    val subject: String,
    val text: String
)