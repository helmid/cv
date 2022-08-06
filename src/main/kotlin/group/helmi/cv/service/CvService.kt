package group.helmi.cv.service

import group.helmi.cv.dto.CvDTO
import group.helmi.cv.model.MimeTypedResource
import java.util.*

interface CvService {
    fun getPublicProfile(locale: Locale): MimeTypedResource?
    fun build(cvDTO: CvDTO): String
    fun loadFile(id: String, filename: String): MimeTypedResource?
}