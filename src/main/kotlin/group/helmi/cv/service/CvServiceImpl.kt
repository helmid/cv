package group.helmi.cv.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import group.helmi.cv.dto.CvDTO
import group.helmi.cv.mapper.TexCvMapper
import group.helmi.cv.model.CvPermission
import group.helmi.cv.model.MimeTypedResource
import group.helmi.cv.model.templates.tex.cv1.Cls
import group.helmi.cv.model.templates.tex.cv1.Cv
import group.helmi.cv.util.CvPathUtil
import group.helmi.cv.util.FileUtil
import group.helmi.cv.util.ProcessUtil
import group.helmi.cv.util.extension.concatAndCamelCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.io.path.createDirectories

@Service
class CvServiceImpl: CvService {
    private var publicFolder: String = ""

    init {
        CvPathUtil.createBaseOutputPathIfNotExists()
    }

    override fun buildPublicProfile() {
        val rawCv = jacksonObjectMapper().readValue<CvDTO>(CvPathUtil.getCvJson())
        publicFolder = "dhe_${UUID.randomUUID()}".concatAndCamelCase()
        createAndCompileTex(publicFolder, rawCv)
    }

    override fun build(cvDTO: CvDTO): String {
        val uuid = UUID.randomUUID().toString() //TODO: Access control
        return createAndCompileTex(uuid, cvDTO)
    }

    private fun createAndCompileTex(id: String, cvDTO: CvDTO): String {
        val cvPermission = CvPermission(contactDisclosureAllowed = false)
        val source = Cv.make(TexCvMapper.formatCv(cvDTO, cvPermission))
        val filename = makeFilename(cvDTO)
        return if (writeFile(source, filename, listOf(id))) {
            compileTex(id, filename)
        } else {
            ""
        }
    }

    private fun makeFilename(cvDTO: CvDTO): String {
        val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_hhmmss"))
        return "${cvDTO.firstName}_${cvDTO.lastName}_${date}"
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

    fun writeFile(data: String, filename: String, pathComponents: List<String>): Boolean {
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