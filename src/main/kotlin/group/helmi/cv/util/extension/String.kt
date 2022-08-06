package group.helmi.cv.util.extension

import group.helmi.cv.config.localization.Translator

fun String.toKebapCase() = concatAndCamelCase().replace("(?<=.)(?=\\p{Upper})".toRegex(), "-").lowercase()

fun String.toScreamingSnakeCase() = toSnakeCase().uppercase()

fun String.toSnakeCase() = toKebapCase().replace("-", "_")

fun String.concatAndCamelCase() =
    split("(\\s|_|-)".toRegex()).joinToString("") { it.replaceFirstChar { char -> char.uppercase() } }
        .replaceFirstChar { it.lowercase() }

fun String.localized(args: Array<String>? = null) = Translator.toLocale(this, args)