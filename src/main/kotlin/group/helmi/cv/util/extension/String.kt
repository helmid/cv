package group.helmi.cv.util.extension

fun String.toKebapCase() = concatAndSnakeCase().replace("(?<=.)(?=\\p{Upper})".toRegex(), "-").lowercase()

fun String.concatAndSnakeCase() =
    split("\\s".toRegex()).map { it.replaceFirstChar { char -> char.uppercase() } }.joinToString("")