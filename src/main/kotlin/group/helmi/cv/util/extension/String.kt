package group.helmi.cv.util.extension

fun String.toKebapCase() = replace("(?<=.)(?=\\p{Upper})".toRegex(), "-").lowercase()