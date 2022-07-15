package group.helmi.cv.controller

import group.helmi.cv.dto.CvDTO
import group.helmi.cv.model.MimeTypedResource
import group.helmi.cv.service.CvService
import group.helmi.cv.util.Path
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.net.URI


@RestController
@RequestMapping(Path.Document.DOCUMENT_BASE)
class DocumentController(private val cvService: CvService) {

    @PostMapping
    fun buildDocument(@RequestBody source: CvDTO): ResponseEntity<URI> {
        val id = cvService.build(source)
        val location: URI = ServletUriComponentsBuilder.fromCurrentRequest().path("/$id").buildAndExpand().toUri()
        return ResponseEntity.created(location).build()
    }

    @GetMapping(Path.Document.GET_DOCUMENT_BY_ID)
    fun getDocument(@PathVariable id: String, @PathVariable filename: String): ResponseEntity<ByteArray> {
        return makeUrlResponse(cvService.loadFile(id, filename))
    }

    private fun makeUrlResponse(file: MimeTypedResource?): ResponseEntity<ByteArray> {
        if (file?.resource == null) {
            return ResponseEntity.badRequest().build()
        }
        val headers = HttpHeaders()
        headers.setContentDispositionFormData(file.filename, file.filename)
        file.mimeType?.let { headers.set(HttpHeaders.CONTENT_TYPE, file.mimeType) }
        headers.cacheControl = "must-revalidate, post-check=0, pre-check=0"
        return ResponseEntity(file.resource, headers, HttpStatus.OK)
    }

    @ExceptionHandler
    fun handleException(): ResponseEntity<Any> {
        return ResponseEntity.internalServerError().build()
    }
}