package com.mobileshift.crawlableCv.controller

import com.mobileshift.crawlableCv.service.FileServiceImpl
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/document")
class DocumentController(private val fileRepository: FileServiceImpl) {
    @GetMapping("/{token}")
    fun getDocument(@PathVariable token: String): ResponseEntity<UrlResource> {
        val file = fileRepository.loadFile(token)
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.resource.filename + "\"")
            .header(HttpHeaders.CONTENT_TYPE, file.mimeType)
            .body(file.resource)
    }
}