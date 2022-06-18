package group.helmi.cv.util

import java.nio.file.Files
import java.nio.file.Path

object CvPathUtil {
    private val baseOutputPathName = "output"
    val baseOutputPath = getOutputPath()
    const val defaultCvFileName = "cv.tex"
    const val defaultClsFileName = "developercv.cls"
    const val defaultCvPdfFileName = "cv.pdf"

    fun getOutputPath(components: List<String> = listOf()): Path {
        return FileUtil.getPath(baseOutputPathName, components)
    }

    fun createBaseOutputPathIfNotExists() {
        if (!Files.exists(baseOutputPath)) {
            Files.createDirectory(baseOutputPath)
        }
    }
}