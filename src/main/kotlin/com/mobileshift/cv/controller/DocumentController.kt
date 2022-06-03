package com.mobileshift.cv.controller

import com.mobileshift.cv.model.MimeTypedResource
import com.mobileshift.cv.service.FileService
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import com.mobileshift.cv.util.Path

@RestController
@RequestMapping(Path.DOCUMENT)
class DocumentController(private val fileRepository: FileService) {
    @GetMapping(Path.GET_DOCUMENT_TOKEN)

    fun getDocument(@PathVariable token: String): ResponseEntity<UrlResource> {
        return makeUrlResponse(fileRepository.load(token))
    }

    @PostMapping(Path.POST_MAKE_DOCUMENT)
    fun buildDocument(@RequestBody source: MultipartFile): ResponseEntity<UrlResource> {
        return makeUrlResponse(fileRepository.build(source))
    }

    private fun makeUrlResponse(file: MimeTypedResource?): ResponseEntity<UrlResource> {
        if (file?.resource == null) {
            return ResponseEntity.badRequest().build()
        }
        val response = ResponseEntity.ok()
        response.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.resource.filename + "\"")
        file.mimeType?.let { response.header(HttpHeaders.CONTENT_TYPE, file.mimeType) }
        response.body(file.resource)
        return response.build()
    }

    @ExceptionHandler
    fun handleException(): ResponseEntity<Any> {
        return ResponseEntity.internalServerError().build()
    }
}