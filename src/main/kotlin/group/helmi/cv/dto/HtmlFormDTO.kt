package group.helmi.cv.dto

data class HtmlFormDTO(
    val id: String?,
    val target: FormTargetDTO,
    val submit: String,
    val input: List<InputFieldDTO>
)

data class FormTargetDTO(
    val action: String,
    val id: String
)

data class InputFieldDTO(
    val id: String,
    val type: String,
    val name: String,
    val label: String?,
    val placeholder: String,
    val required: String?,
    val maxLength: Int
)