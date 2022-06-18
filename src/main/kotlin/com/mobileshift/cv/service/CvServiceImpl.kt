package group.helmi.cv.service

import group.helmi.cv.mapper.TexCvMapper
import group.helmi.cv.model.CvDTO
import group.helmi.cv.model.MimeTypedResource
import group.helmi.cv.model.templates.tex.cv1.Cls
import group.helmi.cv.model.templates.tex.cv1.Cv
import group.helmi.cv.util.FileUtil
import group.helmi.cv.util.ProcessUtil
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.io.path.createDirectories

@Service
class CvServiceImpl: CvService {
    companion object {
        private val baseOutputPathName = "output"
        private val baseOutputPath = getOutputPath()
        private val defaultCvFileName = "cv.tex"
        private val defaultClsFileName = "developercv.cls"
        private val defaultCvPdfFileName = "cv.pdf"

        private fun getOutputPath(components: List<String> = listOf()): Path {
            return FileUtil.getPath(baseOutputPathName, components)
        }
    }

    init {
        if (!Files.exists(baseOutputPath)) {
            Files.createDirectory(baseOutputPath)
        }
    }

    override fun load(token: String): MimeTypedResource? {
        val resource = FileUtil.pathToMimeTypedResource(baseOutputPath.resolve(token))
        return if (resource.resource != null && (resource.resource.exists() || resource.resource.isReadable)) {
            resource
        } else {
            null
        }
    }

    override fun build(cvDTO: CvDTO): MimeTypedResource? {
        val uuid = UUID.randomUUID().toString() //TODO: Access control
        cleanOutput()
        return createAndCompileTex(uuid, cvDTO)
    }

    private fun createAndCompileTex(token: String, cvDTO: CvDTO): MimeTypedResource? {
        val source = Cv.make(TexCvMapper.texifyCv(cvDTO))
        return if (writeFile(source, listOf(token))) {
            compileTex(listOf(token))
        } else {
            null
        }
    }

    private fun compileTex(pathComponents: List<String>): MimeTypedResource? {
        val workingDir = getOutputPath(components = pathComponents).toFile()
        val command = "pdflatex -shell-escape -halt-on-error $defaultCvFileName"
        ProcessUtil.runCommand(command = command, workingDir = workingDir)
        val resultPath = pathComponents.toMutableList()
        resultPath.add(defaultCvPdfFileName)
        return FileUtil.pathToMimeTypedResource(getOutputPath(components = resultPath))
    }

    private fun cleanOutput() {
        FileUtil.deleteDirectory(baseOutputPath.toFile())
    }

    fun writeFile(data: String, pathComponents: List<String>): Boolean {
        val path = getOutputPath(components = pathComponents)
        path.createDirectories()
        val filePathComponents = pathComponents.toMutableList()
        filePathComponents.add(defaultCvFileName)
        val clsPathComponents = pathComponents.toMutableList()
        clsPathComponents.add(defaultClsFileName)
        val cvFile = getOutputPath(components = filePathComponents).toFile()
        val clsFile = getOutputPath(components = clsPathComponents).toFile()
        FileUtil.write(data, cvFile)
        return FileUtil.write(Cls.clsData, clsFile)
    }
}