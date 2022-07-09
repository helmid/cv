package group.helmi.cv.util.extension

fun String.toKebapCase() = concatAndSnakeCase().replace("(?<=.)(?=\\p{Upper})".toRegex(), "-").lowercase()

fun String.concatAndSnakeCase() =
    replaceFirstChar { it.uppercase() }.replace("\\s".toRegex(), "").replaceFirstChar { it.lowercase() }