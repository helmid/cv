package group.helmi.cv.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import group.helmi.cv.dto.CvWebsiteDTO
import group.helmi.cv.dto.WebsiteContactFormDTO
import group.helmi.cv.mapper.CvWebsiteMetaDTOMapper
import group.helmi.cv.model.CvDTO
import group.helmi.cv.util.CvPathUtil
import org.springframework.stereotype.Service

@Service
class CvWebsiteServiceImpl : CvWebsiteService {
    override fun loadData(): CvWebsiteDTO {
        val cv = jacksonObjectMapper().readValue<CvDTO>(CvPathUtil.getCvJson())
        val contact = jacksonObjectMapper().readValue<WebsiteContactFormDTO>(CvPathUtil.getContactJson())
        return CvWebsiteDTO(
            meta = CvWebsiteMetaDTOMapper.map(cv),
            cv = cv,
            contact = contact
        )
    }
}