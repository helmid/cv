package group.helmi.cv.service

import group.helmi.cv.dto.CvDTO
import group.helmi.cv.model.MimeTypedResource

interface CvService {
    fun buildPublicProfile(publicFolder: String)
    fun build(cvDTO: CvDTO): String
    fun loadFile(id: String, filename: String): MimeTypedResource?
}