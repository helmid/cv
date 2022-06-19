package group.helmi.cv.util

import java.nio.file.Files
import java.nio.file.Path

object CvPathUtil {
    private val baseOutputPathName = "output"
    private val baseDataPathName = "data"
    private val cvFilename = "cv.json"
    private val contactFilename = "contact.json"
    val baseOutputPath = getOutputPath()
    const val defaultClsFileName = "developercv.cls"
    const val pdfFileType = "pdf"
    const val texFileType = "tex"

    fun getOutputPath(components: List<String> = listOf()): Path {
        return FileUtil.getPath(baseOutputPathName, components)
    }

    fun getCvJson(): String = getDataFile(cvFilename)

    fun getContactJson(): String = getDataFile(contactFilename)

    private fun getDataFile(filename: String): String {
        val path = FileUtil.getPath(baseDataPathName, listOf(filename))
        return Files.readString(path)
    }

    fun createBaseOutputPathIfNotExists() {
        if (!Files.exists(baseOutputPath)) {
            Files.createDirectory(baseOutputPath)
        }
    }
}