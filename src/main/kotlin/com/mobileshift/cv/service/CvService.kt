package com.mobileshift.cv.service

import com.mobileshift.cv.model.CvDTO
import com.mobileshift.cv.model.MimeTypedResource

interface CvService {
    fun load(token: String): MimeTypedResource?
    fun build(cvDTO: CvDTO): MimeTypedResource?
}