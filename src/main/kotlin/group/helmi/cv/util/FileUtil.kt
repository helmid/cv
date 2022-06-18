package group.helmi.cv.util

import group.helmi.cv.model.MimeTypedResource
import org.slf4j.LoggerFactory
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

object FileUtil {
    private val logger = LoggerFactory.getLogger(FileUtil::class.java)

    fun getPath(base: String, components: List<String> = listOf()): Path {
        return Paths.get(base, *components.toTypedArray())
    }

    fun write(data: String, file: File): Boolean {
        return try {
            val fw = FileWriter(file.absoluteFile)
            val bw = BufferedWriter(fw)
            bw.write(data)
            bw.close()
            true
        } catch (e: IOException) {
            false
        }
    }

    fun deleteDirectory(directory: File): String {
        val files = directory.listFiles() ?: arrayOf()
        for (file in files) {
            if (!file.isDirectory) {
                file.delete()
            } else {
                deleteDirectory(file)
            }
        }
        val deleted = directory.delete()
        val result = if (!deleted) {
            "Could not delete folder ${directory.absoluteFile}"
        } else {
            "Successfully deleted folder ${directory.absoluteFile}"
        }
        logger.error(result)
        return result
    }

    fun pathToMimeTypedResource(path: Path): MimeTypedResource {
        return MimeTypedResource(Files.probeContentType(path), path.fileName.toString(), path.toFile().readBytes())
    }
}