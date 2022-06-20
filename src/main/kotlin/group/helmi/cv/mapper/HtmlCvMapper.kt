package group.helmi.cv.mapper

import group.helmi.cv.model.templates.TemplateCvMapperImpl
import group.helmi.cv.util.extension.toKebapCase

object HtmlCvMapper : TemplateCvMapperImpl() {
    override fun formatString(string: String): String {
        return htmlify(string)
    }

    override fun formaOptionalString(string: String?): String? {
        return if (string != null) htmlify(string) else null
    }

    override fun formatFontAwesome(string: String): String {
        return "fa-" + string.toKebapCase()
    }

    private fun htmlify(string: String): String {
        val result = htmlifyNewlines(string)
        return htmlifyUmlauts(result)
    }

    private fun htmlifyNewlines(string: String): String {
        return string.replace("\n", "<br>")
    }

    private fun htmlifyUmlauts(string: String): String {
        return string
            .replace("ä", "&auml;")
            .replace("Ä", "&Auml;")
            .replace("ö", "&ouml;")
            .replace("Ö", "&Ouml;")
            .replace("ü", "&uuml;")
            .replace("Ü", "&Uuml;")
            .replace("ß", "&szlig;")
    }
}