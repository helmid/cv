package group.helmi.cv.model.templates

interface TemplateCvMapper {
    fun formatString(string: String): String
    fun formaOptionalString(string: String?): String?
    fun formatFontAwesome(string: String): String
}