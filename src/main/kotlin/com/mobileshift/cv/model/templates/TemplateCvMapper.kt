package com.mobileshift.cv.model.templates

interface TemplateCvMapper {
    fun formatString(string: String): String
    fun formaOptionalString(string: String?): String?
}