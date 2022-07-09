package group.helmi.cv.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import group.helmi.cv.dto.CvWebsiteDTO
import group.helmi.cv.dto.WebsiteContactFormDTO
import group.helmi.cv.mapper.CvWebsiteMetaDTOMapper
import group.helmi.cv.mapper.HtmlCvMapper
import group.helmi.cv.model.CvDTO
import group.helmi.cv.model.CvPermission
import group.helmi.cv.model.templates.web.cv1.Section
import group.helmi.cv.util.CvPathUtil
import org.springframework.stereotype.Service

@Service
class CvWebsiteServiceImpl : CvWebsiteService {
    override fun loadData(): CvWebsiteDTO {
        val cvPermission = CvPermission(contactDisclosureAllowed = false)
        val rawCv = jacksonObjectMapper().readValue<CvDTO>(CvPathUtil.getCvJson())
        val cv = HtmlCvMapper.formatCv(rawCv, cvPermission)
        val contact = jacksonObjectMapper().readValue<WebsiteContactFormDTO>(CvPathUtil.getContactJson())
        return CvWebsiteDTO(
            meta = CvWebsiteMetaDTOMapper.map(cv),
            cv = cv,
            contact = contact,
            formattedSections = Section.make(cv.sections)
        )
    }
}