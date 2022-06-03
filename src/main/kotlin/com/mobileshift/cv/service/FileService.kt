package com.mobileshift.cv.service

import com.mobileshift.cv.model.MimeTypedResource
import org.springframework.web.multipart.MultipartFile

interface FileService {
    fun load(token: String): MimeTypedResource?
    fun build(file: MultipartFile): MimeTypedResource?
}