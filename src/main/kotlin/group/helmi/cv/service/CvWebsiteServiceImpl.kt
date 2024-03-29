package group.helmi.cv.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import group.helmi.cv.dto.CvDTO
import group.helmi.cv.dto.CvWebsiteDTO
import group.helmi.cv.dto.CvWebsiteMetaDTO
import group.helmi.cv.dto.HtmlFormDTO
import group.helmi.cv.mapper.CvWebsiteMetaDTOMapper
import group.helmi.cv.mapper.HtmlCvMapper
import group.helmi.cv.mapper.HtmlFormDTOMapper
import group.helmi.cv.model.CvPermission
import group.helmi.cv.model.templates.web.cv1.Section
import group.helmi.cv.util.CvPathUtil
import group.helmi.cv.util.extension.toKebapCase
import org.springframework.stereotype.Service
import java.util.*

@Service
class CvWebsiteServiceImpl : CvWebsiteService {
    override fun loadData(): CvWebsiteDTO {
        val cvPermission = CvPermission(contactDisclosureAllowed = false)
        val rawCv = jacksonObjectMapper().readValue<CvDTO>(CvPathUtil.getCvJson())
        val metadata = jacksonObjectMapper().readValue<CvWebsiteMetaDTO>(CvPathUtil.getWebsiteMetadataJson())
        val cv = HtmlCvMapper.formatCv(rawCv, cvPermission)
        return CvWebsiteDTO(
            meta = CvWebsiteMetaDTOMapper.map(metadata, cv),
            cv = cv,
            contact = loadContact(),
            formattedSections = Section.make(cv.sections),
            sectionIds = cv.sections.associate { it.title to it.title.toKebapCase() },
            cvProfilePicture = CvPathUtil.getProfilePicture(true)
        )
    }

    private fun loadContact(): HtmlFormDTO {
        val contact = jacksonObjectMapper().readValue<HtmlFormDTO>(CvPathUtil.getContactJson())
        return HtmlFormDTOMapper.formatForm(contact, UUID.randomUUID().toString())
    }
}