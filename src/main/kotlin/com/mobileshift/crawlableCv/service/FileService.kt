package com.mobileshift.crawlableCv.service

import com.mobileshift.crawlableCv.model.MimeTypedResource
import org.springframework.web.multipart.MultipartFile

interface FileService {
    fun load(token: String): MimeTypedResource?
    fun build(file: MultipartFile): MimeTypedResource?
}