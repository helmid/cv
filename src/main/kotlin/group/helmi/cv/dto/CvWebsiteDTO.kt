package group.helmi.cv.dto

import group.helmi.cv.model.CvDTO

data class CvWebsiteDTO(
    val meta: CvWebsiteMetaDTO,
    val cv: CvDTO,
    val contact: WebsiteContactFormDTO
)

data class CvWebsiteMetaDTO(
    val keywords: String,
    val description: String,
    val title: String,
    val openGraph: CvWebsiteOpenGraphDTO
)

data class CvWebsiteOpenGraphDTO(
    val name: String,
    val url: String,
    val type: String,
    val title: String
)

data class WebsiteContactFormDTO(
    val target: String,
    val submit: String,
    val input: List<InputFieldDTO>
)

data class InputFieldDTO(
    val type: String,
    val name: String,
    val placeholder: String,
    val required: String?
)