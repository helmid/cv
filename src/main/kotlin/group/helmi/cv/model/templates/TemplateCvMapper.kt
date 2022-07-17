package group.helmi.cv.model.templates

import group.helmi.cv.dto.CvDTO
import group.helmi.cv.model.CvPermission

interface TemplateCvMapper : StringMapper {
    fun formatFontAwesome(string: String): String
    fun formatCv(cvDTO: CvDTO, cvPermission: CvPermission): CvDTO
}