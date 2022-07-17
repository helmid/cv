package group.helmi.cv.dto

data class ImprintDTO(
    val companyName: String,
    val address: AddressDTO,
    val phone: String,
    val email: String,
    val representative: String,
    val vatId: String?,
    val insurance: ImprintInsuranceDTO?,
    val imageSources: List<LinkableTextDTO>
)

data class ImprintInsuranceDTO(
    val scope: String,
    val name: String,
    val address: AddressDTO?
)