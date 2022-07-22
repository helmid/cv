package group.helmi.cv.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import group.helmi.cv.dto.GdprDTO
import group.helmi.cv.mapper.HtmlGdprMapper
import group.helmi.cv.util.CvPathUtil
import org.springframework.stereotype.Service

@Service
class GdprServiceImpl : GdprService {
    override fun loadData(): GdprDTO {
        val imprint = jacksonObjectMapper().readValue<GdprDTO>(CvPathUtil.getGdprJson())
        return HtmlGdprMapper.formatGdpr(imprint)
    }
}