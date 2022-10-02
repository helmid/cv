package group.helmi.cv.mapper

import group.helmi.cv.model.templates.TemplateCvMapperImpl

object TexCvMapper : TemplateCvMapperImpl() {
    override fun formatString(string: String): String {
        return texify(string)
    }

    override fun formatOptionalString(string: String?): String? {
        return if (string != null) texify(string) else null
    }

    override fun formatFontAwesome(string: String): String {
        return string
    }

    private fun texify(string: String): String {
        return texifySpecialCharacters(texifyNewlines(string))
    }

    private fun texifyNewlines(string: String): String {
        return string.replace("\n", "\\newline\n")
    }

    private fun texifySpecialCharacters(string: String): String {
        return string.replace("&", "\\&")
            .replace("ä", "{\\\"a}")
            .replace("Ä", "{\\\"A}")
            .replace("ö", "{\\\"o}")
            .replace("Ö", "{\\\"O}")
            .replace("ü", "{\\\"u}")
            .replace("Ü", "{\\\"U}")
            .replace("ß", "{\\ss}")
    }
}