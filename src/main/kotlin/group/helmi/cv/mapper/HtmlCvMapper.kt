package group.helmi.cv.mapper

import group.helmi.cv.model.templates.TemplateCvMapperImpl
import group.helmi.cv.model.templates.web.cv1.StringMapperWeb
import group.helmi.cv.util.extension.toKebapCase

object HtmlCvMapper : TemplateCvMapperImpl() {
    override fun formatString(string: String): String {
        return StringMapperWeb.formatString(string)
    }

    override fun formaOptionalString(string: String?): String? {
        return StringMapperWeb.formaOptionalString(string)
    }

    override fun formatFontAwesome(string: String): String {
        return "fa-" + string.toKebapCase()
    }
}