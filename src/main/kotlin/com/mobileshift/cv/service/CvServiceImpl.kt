package com.mobileshift.cv.service

import com.mobileshift.cv.model.CvDTO
import com.mobileshift.cv.model.MimeTypedResource
import com.mobileshift.cv.model.templates.tex.cv1.Cls
import com.mobileshift.cv.model.templates.tex.cv1.Cv
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import kotlin.io.path.createDirectories


@Service
class CvServiceImpl: CvService {
    companion object {
        private val basePathName = "output"
        private val basePath = getPath()
        private val defaultCvFileName = "cv.tex"
        private val defaultClsFileName = "developercv.cls"

        private fun getPath(base: String = basePathName, components: List<String> = listOf()): Path {
            return Paths.get(base, *components.toTypedArray())
        }

        private fun getResourceAsText(path: String): String {
            return object {}.javaClass.getResource(path)?.readText() ?: ""
        }
    }

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

    override fun build(cvDTO: CvDTO): MimeTypedResource? {
        return compileTex(Cv.make(cvDTO))
    }

    private fun compileTex(source: String): MimeTypedResource? {
        writeFile(source, listOf(UUID.randomUUID().toString()))
        return null
    }

    private fun storeTexSource(folder: String, content: String) {

    }

    fun writeFile(data: String, pathComponents: List<String>) {
        val path = getPath(components = pathComponents)
        path.createDirectories()
        val filePathComponents = pathComponents.toMutableList()
        filePathComponents.add(defaultCvFileName)
        val clsPathComponents = pathComponents.toMutableList()
        clsPathComponents.add(defaultClsFileName)
        val cvFile = getPath(components = filePathComponents).toFile()
        val clsFile = getPath(components = clsPathComponents).toFile()
        write(data, cvFile)
        write(Cls.clsData, clsFile)
    }

    private fun write(data: String, file: File) {
        try {
            val fw = FileWriter(file.absoluteFile)
            val bw = BufferedWriter(fw)
            bw.write(data)
            bw.close()
        } catch (e: IOException) {
            //TODO: handle it
        }
    }
}