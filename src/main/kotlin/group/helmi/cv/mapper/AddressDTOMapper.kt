package group.helmi.cv.mapper

import group.helmi.cv.dto.AddressDTO
import group.helmi.cv.model.templates.StringMapper

object AddressDTOMapper {
    fun format(stringMapper: StringMapper, addressDTO: AddressDTO): AddressDTO {
        return AddressDTO(
            street = stringMapper.formatString(addressDTO.street),
            houseNo = stringMapper.formatString(addressDTO.houseNo),
            postalCode = stringMapper.formatString(addressDTO.postalCode),
            city = stringMapper.formatString(addressDTO.city),
            country = stringMapper.formatString(addressDTO.country),
            co = stringMapper.formaOptionalString(addressDTO.co)
        )
    }
}