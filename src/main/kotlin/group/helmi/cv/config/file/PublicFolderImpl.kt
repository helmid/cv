package group.helmi.cv.config.file

import group.helmi.cv.util.CvPathUtil
import group.helmi.cv.util.extension.toSnakeCase
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.io.path.createDirectories

@Configuration
class PublicFolderImpl : PublicFolder {
    private var publicFolder = ""

    final override fun generatePublicFolder() {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        val formatted = current.format(formatter)
        val folder = "${UUID.randomUUID().toString().toSnakeCase()}_dhe_$formatted"
        val path = CvPathUtil.getOutputPath(components = listOf(folder))
        path.createDirectories()
        publicFolder = folder
    }

    override fun getPublicFolder(): String {
        if (publicFolder.isEmpty()) {
            generatePublicFolder()
        }
        return publicFolder
    }
}