package group.helmi.cv.util

import java.nio.file.Files
import java.nio.file.Path
import java.util.*

object CvPathUtil {
    private const val baseOutputPathName = "output"
    private const val baseDataPathName = "data"
    private const val cvFilename = "cv.json"
    private const val contactFilename = "contactForm.json"
    private const val imprintFilename = "imprint.json"
    private const val gdprFilename = "gdpr.json"
    private const val profilePicture = "cvProfilePicture.webp"
    val baseOutputPath = getOutputPath()
    const val defaultClsFileName = "developercv.cls"
    const val pdfFileType = "pdf"
    const val texFileType = "tex"

    fun getOutputPath(components: List<String> = listOf()): Path {
        return FileUtil.getPath(baseOutputPathName, components)
    }

    fun getCvJson(): String = getDataFile(cvFilename)

    fun getContactJson(): String = getDataFile(contactFilename)

    fun getImprintJson(): String = getDataFile(imprintFilename)

    fun getGdprJson(): String = getDataFile(gdprFilename)

    fun getProfilePicture(addWebSrcPrefix: Boolean): String {
        val path = FileUtil.getPath(baseDataPathName, listOf(profilePicture))
        val base64 = String(Base64.getEncoder().encode(Files.readAllBytes(path)))
        var prefix = ""
        if (addWebSrcPrefix) {
            val mimeType = FileUtil.mimeTypeForPath(path)
            prefix = "data:image/$mimeType;base64,"
        }

        return "$prefix$base64"
    }

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