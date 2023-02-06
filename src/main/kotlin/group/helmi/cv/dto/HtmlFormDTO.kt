package group.helmi.cv.dto

data class HtmlFormDTO(
    val id: String?,
    val showContact: Boolean,
    val target: FormTargetDTO,
    val submit: String,
    val input: List<InputFieldDTO>
) {
    companion object {
        val initial = HtmlFormDTO(null, false, FormTargetDTO.initial, "", listOf())
    }
}

data class FormTargetDTO(
    val action: String,
    val id: String
) {
    companion object {
        val initial = FormTargetDTO(action = "", id = "")
    }
}

data class InputFieldDTO(
    val id: String,
    val type: String,
    val name: String,
    val label: String?,
    val placeholder: String,
    val required: String?,
    val maxLength: Int
)