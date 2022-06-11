package com.mobileshift.cv.util

import org.slf4j.LoggerFactory
import java.io.File
import java.util.concurrent.TimeUnit

object ProcessUtil {
    private val logger = LoggerFactory.getLogger(ProcessUtil::class.java)

    fun runCommand(
        command: String,
        workingDir: File,
        timeoutAmount: Long = 60,
        timeoutUnit: TimeUnit = TimeUnit.SECONDS
    ): String? {
        logger.info("Running command $command in ${workingDir.absolutePath}")
        val result = runCatching {
            ProcessBuilder("\\s".toRegex().split(command))
                .directory(workingDir)
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
                .start().also { it.waitFor(timeoutAmount, timeoutUnit) }
                .inputStream.bufferedReader().readText()
        }.onFailure { it.printStackTrace() }.getOrNull()
        logger.info(result)
        return result
    }
}