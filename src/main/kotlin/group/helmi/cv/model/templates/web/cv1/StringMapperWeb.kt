package group.helmi.cv.model.templates.web.cv1

import group.helmi.cv.model.templates.StringMapper

object StringMapperWeb : StringMapper {
    override fun formatString(string: String): String {
        return htmlify(string)
    }

    override fun formaOptionalString(string: String?): String? {
        return if (string != null) htmlify(string) else null
    }

    private fun htmlify(string: String): String {
        val result = htmlifyNewlines(string)
        return htmlifySpecialChars(result)
    }

    private fun htmlifyNewlines(string: String): String {
        return string.replace("\n", "<br>")
    }

    private fun htmlifySpecialChars(string: String): String {
        return string
            .replace("ä", "&auml;")
            .replace("Ä", "&Auml;")
            .replace("ö", "&ouml;")
            .replace("Ö", "&Ouml;")
            .replace("ü", "&uuml;")
            .replace("Ü", "&Uuml;")
            .replace("ß", "&szlig;")
            .replace("§", "&sect;")
    }
}