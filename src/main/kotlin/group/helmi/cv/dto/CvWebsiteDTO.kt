package group.helmi.cv.dto

data class CvWebsiteDTO(
    val meta: CvWebsiteMetaDTO,
    val cv: CvDTO,
    val contact: HtmlFormDTO,
    val formattedSections: List<String>,
    val sectionIds: Map<String, String>,
    val cvProfilePicture: String
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