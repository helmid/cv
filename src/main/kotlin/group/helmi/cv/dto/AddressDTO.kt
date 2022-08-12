package group.helmi.cv.dto

data class AddressDTO(
    val street: String,
    val houseNo: String,
    val postalCode: String,
    val city: String,
    val country: String,
    val co: String?,
    val notice: String?
)