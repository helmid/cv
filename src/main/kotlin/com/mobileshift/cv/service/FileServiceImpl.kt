package com.mobileshift.cv.service

import com.mobileshift.cv.model.MimeTypedResource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


@Service
class FileServiceImpl : FileService {
    private val basePath: Path = Paths.get("output")

    init {
        if (!Files.exists(basePath)) {
            Files.createDirectory(basePath)
        }
    }

    override fun load(token: String): MimeTypedResource? {
        val file = basePath.resolve(token)
        val resource = MimeTypedResource(Files.probeContentType(file), UrlResource(file.toUri()))
        if (resource.resource != null && (resource.resource.exists() || resource.resource.isReadable)) {
            return resource
        } else {
            return null
        }
    }

    override fun build(file: MultipartFile): MimeTypedResource? {
        val name = file.originalFilename ?: file.name
        val path: Path
        try {
            path = basePath.resolve(name)
            Files.copy(file.inputStream, path)
        } catch (e: Exception) {
            return null
        }
        val result = load(name)
        //Files.delete(path)
        return result
    }
}