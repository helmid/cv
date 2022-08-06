package group.helmi.cv.util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import group.helmi.cv.util.extension.toScreamingSnakeCase

object LoggingUtil {
    fun stringify(tag: String, any: Any): String {
        return "${tag.toScreamingSnakeCase()}\n${jacksonObjectMapper().writeValueAsString(any)}"
    }
}