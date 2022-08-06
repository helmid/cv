package group.helmi.cv.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import group.helmi.cv.config.file.PublicFolder
import group.helmi.cv.dto.CvDTO
import group.helmi.cv.mapper.TexCvMapper
import group.helmi.cv.model.CvPermission
import group.helmi.cv.model.MimeTypedResource
import group.helmi.cv.model.templates.tex.cv1.Cls
import group.helmi.cv.model.templates.tex.cv1.Cv
import group.helmi.cv.util.CvPathUtil
import group.helmi.cv.util.FileUtil
import group.helmi.cv.util.ProcessUtil
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.io.path.createDirectories

@Service
class CvServiceImpl(private val publicFolder: PublicFolder) : CvService {

    init {
        CvPathUtil.createBaseOutputPathIfNotExists()
    }

    override fun getPublicProfile(locale: Locale): MimeTypedResource? {
        val cvDTO = jacksonObjectMapper().readValue<CvDTO>(CvPathUtil.getCvJson())
        val folder = publicFolder.getPublicFolder(locale)
        val cvFilename = makeFilename(cvDTO, false)
        val path = CvPathUtil.getOutputPath(components = listOf(folder, "$cvFilename.${CvPathUtil.pdfFileType}"))
        if (!Files.exists(path)) {
            createAndCompileTex(folder, cvDTO, false)
        }
        return loadFile(folder, cvFilename)
    }

    override fun build(cvDTO: CvDTO): String {
        val uuid = UUID.randomUUID().toString() //TODO: Access control
        return createAndCompileTex(uuid, cvDTO, true)
    }

    private fun createAndCompileTex(id: String, cvDTO: CvDTO, useTimestamp: Boolean): String {
        val cvPermission = CvPermission(contactDisclosureAllowed = false)
        val source = Cv.make(TexCvMapper.formatCv(cvDTO, cvPermission))
        val filename = makeFilename(cvDTO, useTimestamp)
        return if (writeFile(source, filename, listOf(id))) {
            compileTex(id, filename)
        } else {
            ""
        }
    }

    private fun makeFilename(cvDTO: CvDTO, useTimestamp: Boolean): String {
        val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_hhmmss"))
        val timestamp = if (useTimestamp) "_${date}" else ""
        return "${cvDTO.firstName}_${cvDTO.lastName}$timestamp"
    }

    private fun compileTex(id: String, filename: String): String {
        val workingDir = CvPathUtil.getOutputPath(components = listOf(id)).toFile()
        val command = "pdflatex -shell-escape -halt-on-error $filename.${CvPathUtil.texFileType}"
        ProcessUtil.runCommand(command = command, workingDir = workingDir)
        return "$id/$filename"
    }

    override fun loadFile(id: String, filename: String): MimeTypedResource? {
        val resultPath = listOf(id, "$filename.${CvPathUtil.pdfFileType}")
        return FileUtil.pathToMimeTypedResource(CvPathUtil.getOutputPath(components = resultPath))
    }

    private fun writeFile(data: String, filename: String, pathComponents: List<String>): Boolean {
        val path = CvPathUtil.getOutputPath(components = pathComponents)
        path.createDirectories()
        val filePathComponents = pathComponents.toMutableList()
        filePathComponents.add("$filename.${CvPathUtil.texFileType}")
        val clsPathComponents = pathComponents.toMutableList()
        clsPathComponents.add(CvPathUtil.defaultClsFileName)
        val imagePathComponents = pathComponents.toMutableList()
        imagePathComponents.add("${CvPathUtil.profilePicture}.${CvPathUtil.pngFileType}")
        val cvFile = CvPathUtil.getOutputPath(components = filePathComponents).toFile()
        val clsFile = CvPathUtil.getOutputPath(components = clsPathComponents).toFile()
        val imageFile = CvPathUtil.getOutputPath(components = imagePathComponents).toFile()
        CvPathUtil.getPngProfilePicture().toFile().copyTo(imageFile)
        return FileUtil.write(Cls.clsData, clsFile) &&
                FileUtil.write(data, cvFile)
    }
}