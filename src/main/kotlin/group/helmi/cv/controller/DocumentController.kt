package group.helmi.cv.controller

import group.helmi.cv.model.CvDTO
import group.helmi.cv.model.MimeTypedResource
import group.helmi.cv.service.CvService
import group.helmi.cv.util.Path
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Path.Document.DOCUMENT_BASE)
class DocumentController(private val cvService: CvService) {

    @PostMapping(Path.Document.POST_MAKE_DOCUMENT)
    fun buildDocument(@RequestBody source: CvDTO): ResponseEntity<UrlResource> {
        return makeUrlResponse(cvService.build(source))
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