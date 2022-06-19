package group.helmi.cv.mapper

import group.helmi.cv.dto.CvWebsiteMetaDTO
import group.helmi.cv.dto.CvWebsiteOpenGraphDTO
import group.helmi.cv.model.AboutEntryDTO
import group.helmi.cv.model.CvDTO
import group.helmi.cv.model.HistoryEntryDTO
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

object CvWebsiteMetaDTOMapper {
    fun map(cv: CvDTO): CvWebsiteMetaDTO {
        val name = "${cv.firstName} ${cv.lastName} - ${cv.jobTitle}"
        val url = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()
        val openGraphDTO = CvWebsiteOpenGraphDTO(
            name = name,
            url = url,
            type = "website",
            title = name
        )
        return CvWebsiteMetaDTO(
            keywords = makeKeyWords(cv),
            description = name,
            title = name,
            openGraph = openGraphDTO
        )
    }

    private fun makeKeyWords(cv: CvDTO): String {
        val result: MutableList<String> = mutableListOf()
        cv.sections.forEach { section ->
            section.entries.forEach { entry ->
                if (entry is AboutEntryDTO) {
                    entry.barchart.forEach { chartItem -> result.add(chartItem.title) }
                    entry.bubbles?.let { bubble -> bubble.forEach { chartItem -> result.add(chartItem.title) } }
                } else if (entry is HistoryEntryDTO) {
                    result.addAll(entry.skills)
                }
            }
        }
        return result.joinToString(", ")
    }
}