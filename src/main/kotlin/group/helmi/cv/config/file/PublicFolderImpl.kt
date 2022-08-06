package group.helmi.cv.config.file

import group.helmi.cv.config.localization.CustomLocaleResolver
import group.helmi.cv.util.CvPathUtil
import group.helmi.cv.util.FileUtil
import group.helmi.cv.util.extension.toSnakeCase
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.io.path.createDirectories

@Configuration
class PublicFolderImpl : PublicFolder {
    private var publicFolder = ""

    override fun generatePublicFolders() {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        val formatted = current.format(formatter)
        val folder = "${UUID.randomUUID().toString().toSnakeCase()}_dhe_$formatted"
        CustomLocaleResolver.locales.forEach {
            val path = CvPathUtil.getOutputPath(components = listOf("${folder}_${it.locale.language}"))
            path.createDirectories()
        }
        publicFolder = folder
    }

    override fun getPublicFolder(locale: Locale): String {
        if (publicFolder.isEmpty()) {
            generatePublicFolders()
        }
        return "${publicFolder}_${locale.language}"
    }

    override fun deletePublicFolders(): String {
        val result = mutableListOf<String>()
        CustomLocaleResolver.locales.forEach {
            result.add(
                FileUtil.deleteDirectory(
                    CvPathUtil.getOutputPath(listOf("${publicFolder}_${it.locale.language}")).toFile()
                )
            )
        }
        return result.joinToString("\n")
    }
}