package group.helmi.cv.service

import group.helmi.cv.mapper.TexCvMapper
import group.helmi.cv.model.CvDTO
import group.helmi.cv.model.MimeTypedResource
import group.helmi.cv.model.templates.tex.cv1.Cls
import group.helmi.cv.model.templates.tex.cv1.Cv
import group.helmi.cv.util.CvPathUtil
import group.helmi.cv.util.FileUtil
import group.helmi.cv.util.ProcessUtil
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.io.path.createDirectories

@Service
class CvServiceImpl: CvService {
    init {
        CvPathUtil.createBaseOutputPathIfNotExists()
    }

    override fun build(cvDTO: CvDTO): String {
        val uuid = UUID.randomUUID().toString() //TODO: Access control
        return createAndCompileTex(uuid, cvDTO)
    }

    private fun createAndCompileTex(id: String, cvDTO: CvDTO): String {
        val source = Cv.make(TexCvMapper.texifyCv(cvDTO))
        val texFileName = makeFilename(cvDTO)
        return if (writeFile(source, listOf(id))) {
            compileTex(id, texFileName)
        } else {
            ""
        }
    }

    private fun makeFilename(cvDTO: CvDTO): String {
        val date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_hhmmss"))
        return "${cvDTO.firstName}_${cvDTO.lastName}_${date}.${CvPathUtil.pdfFileType}"
    }

    private fun compileTex(id: String, filename: String): String {
        val workingDir = CvPathUtil.getOutputPath(components = listOf(id)).toFile()
        val command = "pdflatex -shell-escape -halt-on-error ${CvPathUtil.defaultCvFileName}"
        ProcessUtil.runCommand(command = command, workingDir = workingDir)
        return "$id/$filename"
    }

    override fun loadFile(id: String, filename: String): MimeTypedResource? {
        val resultPath = listOf(id, filename)
        return FileUtil.pathToMimeTypedResource(CvPathUtil.getOutputPath(components = resultPath))
    }

    fun writeFile(data: String, pathComponents: List<String>): Boolean {
        val path = CvPathUtil.getOutputPath(components = pathComponents)
        path.createDirectories()
        val filePathComponents = pathComponents.toMutableList()
        filePathComponents.add(CvPathUtil.defaultCvFileName)
        val clsPathComponents = pathComponents.toMutableList()
        clsPathComponents.add(CvPathUtil.defaultClsFileName)
        val cvFile = CvPathUtil.getOutputPath(components = filePathComponents).toFile()
        val clsFile = CvPathUtil.getOutputPath(components = clsPathComponents).toFile()
        FileUtil.write(data, cvFile)
        return FileUtil.write(Cls.clsData, clsFile)
    }
}