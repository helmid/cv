package com.mobileshift.crawlableCv.service

import com.mobileshift.crawlableCv.model.MimeTypedResource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import java.io.FileNotFoundException
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

    override fun loadFile(token: String): MimeTypedResource {
        val file = basePath.resolve(token)
        val resource = MimeTypedResource(Files.probeContentType(file), UrlResource(file.toUri()))
        if (resource.resource.exists() || resource.resource.isReadable) {
            return resource
        } else {
            throw FileNotFoundException()
        }
    }
}