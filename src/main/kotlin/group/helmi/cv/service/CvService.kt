package group.helmi.cv.service

import group.helmi.cv.model.CvDTO
import group.helmi.cv.model.MimeTypedResource

interface CvService {
    fun build(cvDTO: CvDTO): MimeTypedResource?
}