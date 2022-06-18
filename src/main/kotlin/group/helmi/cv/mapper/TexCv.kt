package group.helmi.cv.mapper

import group.helmi.cv.model.templates.TemplateCvMapperImpl

object TexCvMapper : TemplateCvMapperImpl() {
    override fun formatString(string: String): String {
        return texify(string)
    }

    override fun formaOptionalString(string: String?): String? {
        return if (string != null) texify(string) else null
    }

    private fun texify(string: String): String {
        return texifyNewlines(string)
    }

    private fun texifyNewlines(string: String): String {
        return string.replace("\n", "\\newline\n")
    }
}