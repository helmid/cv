package group.helmi.cv.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import group.helmi.cv.dto.ImprintDTO
import group.helmi.cv.mapper.HtmlImprintMapper
import group.helmi.cv.util.CvPathUtil
import group.helmi.cv.util.StringObfuscator
import org.springframework.stereotype.Service

@Service
class ImprintServiceImpl : ImprintService {
    override fun loadData(key: ByteArray?): ImprintDTO {
        val imprint = jacksonObjectMapper().readValue<ImprintDTO>(CvPathUtil.getImprintJson())
        return HtmlImprintMapper.formatImprint(imprint, key)
    }

    override fun generateKey(): ByteArray {
        return StringObfuscator.generateKey()
    }
}