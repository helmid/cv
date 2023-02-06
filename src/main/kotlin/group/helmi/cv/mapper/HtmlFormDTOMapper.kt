package group.helmi.cv.mapper

import group.helmi.cv.dto.FormTargetDTO
import group.helmi.cv.dto.HtmlFormDTO
import group.helmi.cv.dto.InputFieldDTO
import group.helmi.cv.model.templates.web.cv1.StringMapperWeb
import group.helmi.cv.util.extension.toKebapCase

object HtmlFormDTOMapper {
    fun formatForm(htmlFormDTO: HtmlFormDTO, formId: String): HtmlFormDTO {
        return if (htmlFormDTO.showContact) {
            HtmlFormDTO.initial
        } else {
            HtmlFormDTO(
                id = formId,
                showContact = htmlFormDTO.showContact,
                target = FormTargetDTO(
                    id = htmlFormDTO.target.id,
                    action = htmlFormDTO.target.action,
                ),
                submit = StringMapperWeb.formatString(htmlFormDTO.submit),
                input = htmlFormDTO.input.map { formatForm(it) }
            )
        }
    }


    private fun formatForm(inputFieldDTO: InputFieldDTO): InputFieldDTO {
        return InputFieldDTO(
            id = inputFieldDTO.id.toKebapCase(),
            type = inputFieldDTO.type,
            name = inputFieldDTO.name,
            label = StringMapperWeb.formatOptionalString(inputFieldDTO.label),
            placeholder = StringMapperWeb.formatString(inputFieldDTO.placeholder),
            required = inputFieldDTO.required,
            maxLength = inputFieldDTO.maxLength
        )
    }
}