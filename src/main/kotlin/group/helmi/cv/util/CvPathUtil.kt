package group.helmi.cv.util

import group.helmi.cv.config.localization.Translator
import java.nio.file.Files
import java.nio.file.Path
import java.util.*

object CvPathUtil {
    private const val baseOutputPathName = "output"
    private const val baseDataPathName = "data"
    private const val cvFilenameKey = "cv_source"
    private const val contactFilenameKey = "contact_form_source"
    private const val imprintFilenameKey = "imprint_source"
    private const val gdprFilenameKey = "gdpr_source"
    private const val profilePicture = "cvProfilePicture.webp"

    private val cvFilename = Translator.toLocale(cvFilenameKey)
    private val contactFilename = Translator.toLocale(contactFilenameKey)
    private val imprintFilename = Translator.toLocale(imprintFilenameKey)
    private val gdprFilename = Translator.toLocale(gdprFilenameKey)

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