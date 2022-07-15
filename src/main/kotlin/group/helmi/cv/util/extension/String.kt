package group.helmi.cv.util.extension

import group.helmi.cv.config.language.Translator

fun String.toKebapCase() = concatAndSnakeCase().replace("(?<=.)(?=\\p{Upper})".toRegex(), "-").lowercase()

fun String.concatAndSnakeCase() =
    split("\\s".toRegex()).joinToString("") { it.replaceFirstChar { char -> char.uppercase() } }

fun String.localized() = Translator.toLocale(this)