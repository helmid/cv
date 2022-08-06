package group.helmi.cv.util

import group.helmi.cv.model.MimeTypedResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

object ControllerUtil {
    fun buildFileResponse(mimeTypedResource: MimeTypedResource?): ResponseEntity<ByteArray> {
        if (mimeTypedResource == null) {
            return ResponseEntity.notFound().build()
        }
        if (mimeTypedResource.resource.isEmpty()) {
            return ResponseEntity.badRequest().build()
        }
        val httpHeaders = HttpHeaders()
        httpHeaders.add("Content-Disposition", "inline; filename=${mimeTypedResource.filename}")
        mimeTypedResource.mimeType?.let { httpHeaders.set(HttpHeaders.CONTENT_TYPE, mimeTypedResource.mimeType) }
        httpHeaders.cacheControl = "must-revalidate, post-check=0, pre-check=0"
        return ResponseEntity(mimeTypedResource.resource, httpHeaders, HttpStatus.OK)
    }
}