package group.helmi.cv.mapper

import group.helmi.cv.dto.ImprintDTO
import group.helmi.cv.dto.ImprintInsuranceDTO
import group.helmi.cv.model.templates.web.cv1.StringMapperWeb
import group.helmi.cv.util.StringObfuscator

object HtmlImprintMapper {
    fun formatImprint(imprintDTO: ImprintDTO, key: ByteArray?): ImprintDTO {
        var phone = StringMapperWeb.formatString(imprintDTO.phone)
        var email = StringMapperWeb.formatString(imprintDTO.email)
        if (key != null) {
            phone = StringObfuscator.crypt(phone, key)
            email = StringObfuscator.crypt(email, key)
        }
        return ImprintDTO(
            companyName = StringMapperWeb.formatString(imprintDTO.companyName),
            address = AddressDTOMapper.format(StringMapperWeb, imprintDTO.address),
            phone = phone,
            email = email,
            representative = StringMapperWeb.formatString(imprintDTO.representative),
            vatId = StringMapperWeb.formaOptionalString(imprintDTO.vatId),
            insurance = formatInsurance(imprintDTO.insurance),
            imageSources = imprintDTO.imageSources.map { LinkableTextDTOMapper.format(StringMapperWeb, it) }
        )
    }

    private fun formatInsurance(imprintInsuranceDTO: ImprintInsuranceDTO?): ImprintInsuranceDTO? {
        return if (imprintInsuranceDTO != null) {
            val address = if (imprintInsuranceDTO.address != null) AddressDTOMapper.format(
                StringMapperWeb,
                imprintInsuranceDTO.address
            ) else null
            ImprintInsuranceDTO(
                scope = StringMapperWeb.formatString(imprintInsuranceDTO.scope),
                name = StringMapperWeb.formatString(imprintInsuranceDTO.name),
                address = address
            )
        } else {
            null
        }
    }
}