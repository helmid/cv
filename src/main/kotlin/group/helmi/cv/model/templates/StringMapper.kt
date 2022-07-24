package group.helmi.cv.model.templates

interface StringMapper {
    fun formatString(string: String): String
    fun formatOptionalString(string: String?): String?
}