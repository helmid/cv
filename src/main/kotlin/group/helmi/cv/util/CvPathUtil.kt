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
    private const val contactFormMailFilename = "contact_form_mail.json"
    private const val imprintFilenameKey = "imprint_source"
    private const val gdprFilenameKey = "gdpr_source"
    private const val websiteMetadataKey = "website_metadata"
    const val profilePicture = "cvProfilePicture"

    val baseOutputPath = getOutputPath()
    const val defaultClsFileName = "developercv.cls"
    const val pdfFileType = "pdf"
    const val texFileType = "tex"
    const val webpFileType = "webp"
    const val pngFileType = "png"
    fun getOutputPath(components: List<String> = listOf()): Path {
        return FileUtil.getPath(baseOutputPathName, components)
    }

    fun getCvJsonForceLocale(locale: Locale): String = getDataFile(Translator.toForcedLocale(locale, cvFilenameKey))
    fun getCvJson(): String = getDataFile(Translator.toLocale(cvFilenameKey))

    fun getContactJson(): String = getDataFile(Translator.toLocale(contactFilenameKey))
    fun getContactFormMailJson(): String = getDataFile(contactFormMailFilename)

    fun getImprintJson(): String = getDataFile(Translator.toLocale(imprintFilenameKey))

    fun getGdprJson(): String = getDataFile(Translator.toLocale(gdprFilenameKey))

    fun getWebsiteMetadataJson(): String = getDataFile(Translator.toLocale(websiteMetadataKey))

    fun getPngProfilePicture(): Path = FileUtil.getPath(baseDataPathName, listOf("$profilePicture.$pngFileType"))

    fun getProfilePicture(addWebSrcPrefix: Boolean): String {
        val path = FileUtil.getPath(baseDataPathName, listOf("$profilePicture.$webpFileType"))
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