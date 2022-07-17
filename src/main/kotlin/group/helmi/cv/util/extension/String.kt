package group.helmi.cv.util.extension

import group.helmi.cv.config.localization.Translator

fun String.toKebapCase() = concatAndSnakeCase().replace("(?<=.)(?=\\p{Upper})".toRegex(), "-").lowercase()

fun String.concatAndSnakeCase() =
    split("\\s".toRegex()).joinToString("") { it.replaceFirstChar { char -> char.uppercase() } }

fun String.localized(args: Array<String>? = null) = Translator.toLocale(this, args)