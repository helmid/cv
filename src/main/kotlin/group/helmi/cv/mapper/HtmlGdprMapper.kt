package group.helmi.cv.mapper

import group.helmi.cv.dto.GdprDTO
import group.helmi.cv.model.templates.web.cv1.StringMapperWeb

object HtmlGdprMapper {
    fun formatGdpr(gdprDTO: GdprDTO): GdprDTO {
        return GdprDTO(
            responsibleEntity = StringMapperWeb.formatString(gdprDTO.responsibleEntity),
            address = AddressDTOMapper.format(StringMapperWeb, gdprDTO.address),
        )
    }
}