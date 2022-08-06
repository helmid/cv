package group.helmi.cv.config.file

import java.util.*

interface PublicFolder {
    fun generatePublicFolders()
    fun getPublicFolder(locale: Locale): String
    fun deletePublicFolders(): String
}