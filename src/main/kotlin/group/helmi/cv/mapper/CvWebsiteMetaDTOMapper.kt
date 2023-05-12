package group.helmi.cv.mapper

import group.helmi.cv.dto.AboutEntryDTO
import group.helmi.cv.dto.CvDTO
import group.helmi.cv.dto.CvWebsiteMetaDTO
import group.helmi.cv.dto.HistoryEntryDTO

object CvWebsiteMetaDTOMapper {
    fun map(metadata: CvWebsiteMetaDTO, cv: CvDTO): CvWebsiteMetaDTO {
        return metadata.copy(
            keywords = makeKeyWords(metadata, cv)
        )
    }

    private fun makeKeyWords(metadata: CvWebsiteMetaDTO, cv: CvDTO): List<String> {
        val skills: MutableList<String> = mutableListOf()
        cv.sections.forEach { section ->
            section.entries.forEach { entry ->
                if (entry is AboutEntryDTO) {
                    entry.chartSection?.let { chartSection ->
                        chartSection.barchart.forEach { chartItem -> skills.add(chartItem.title) }
                        chartSection.bubbles?.let { bubble -> bubble.forEach { chartItem -> skills.add(chartItem.title) } }
                    }
                } else if (entry is HistoryEntryDTO) {
                    skills.addAll(entry.skills)
                }
            }
        }
        val keywords: MutableList<String> = metadata.keywords.toMutableList()
        keywords.addAll(skills.distinct())
        return keywords
    }
}