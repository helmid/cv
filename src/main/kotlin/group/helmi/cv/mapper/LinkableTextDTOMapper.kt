package group.helmi.cv.mapper

import group.helmi.cv.dto.LinkableTextDTO
import group.helmi.cv.model.templates.StringMapper

object LinkableTextDTOMapper {
    fun format(stringMapper: StringMapper, linkableTextDTO: LinkableTextDTO): LinkableTextDTO {
        return LinkableTextDTO(
            text = stringMapper.formatString(linkableTextDTO.text),
            url = linkableTextDTO.url
        )
    }
}