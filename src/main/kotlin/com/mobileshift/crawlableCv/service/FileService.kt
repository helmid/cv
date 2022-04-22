package com.mobileshift.crawlableCv.service

import com.mobileshift.crawlableCv.model.MimeTypedResource

interface FileService {
    fun loadFile(token: String): MimeTypedResource
}