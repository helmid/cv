package group.helmi.cv.model.templates

import group.helmi.cv.model.*

abstract class TemplateCvMapperImpl : TemplateCvMapper {
    fun texifyCv(cvDTO: CvDTO): CvDTO {
        return CvDTO(
            firstName = formatString(cvDTO.firstName),
            lastName = formatString(cvDTO.lastName),
            jobTitle = formatString(cvDTO.jobTitle),
            contact = cvDTO.contact.map { texifyContact(it) },
            sections = cvDTO.sections.map { texifySection(it) }
        )
    }

    private fun texifyContact(contactItemDTO: ContactItemDTO): ContactItemDTO {
        return ContactItemDTO(
            icon = contactItemDTO.icon,
            text = formatString(contactItemDTO.text),
            href = contactItemDTO.href
        )
    }

    private fun texifySection(sectionElementDTO: SectionElementDTO): SectionElementDTO {
        return SectionElementDTO(
            title = formatString(sectionElementDTO.title),
            entries = sectionElementDTO.entries.map { texifySectionEntry(it) }
        )
    }

    private fun texifySectionEntry(entryDTO: EntryDTO): EntryDTO {
        return when (entryDTO) {
            is AboutEntryDTO -> texifyAbout(entryDTO)
            is HistoryEntryDTO -> texifyHistory(entryDTO)
            is EducationEntryDTO -> texifyEducation(entryDTO)
            is ColsEntryDTO -> texifyCols(entryDTO)
            else -> entryDTO
        }
    }

    private fun texifyAbout(aboutEntryDTO: AboutEntryDTO): EntryDTO {
        return AboutEntryDTO(
            text = formatString(aboutEntryDTO.text),
            barchart = aboutEntryDTO.barchart.map { texifyChartItem(it) },
            bubbles = aboutEntryDTO.bubbles?.map { texifyChartItem(it) }
        )
    }

    private fun texifyHistory(historyEntryDTO: HistoryEntryDTO): EntryDTO {
        return HistoryEntryDTO(
            start = formatString(historyEntryDTO.start),
            end = formaOptionalString(historyEntryDTO.end),
            jobTitle = formatString(historyEntryDTO.jobTitle),
            customer = formatString(historyEntryDTO.customer),
            description = formatString(historyEntryDTO.description),
            skills = historyEntryDTO.skills.map { formatString(it) }
        )
    }

    private fun texifyEducation(educationEntryDTO: EducationEntryDTO): EntryDTO {
        return EducationEntryDTO(
            start = formatString(educationEntryDTO.start),
            end = formatString(educationEntryDTO.end),
            title = formatString(educationEntryDTO.title),
            facility = formatString(educationEntryDTO.facility),
            content = texifyLinkableText(educationEntryDTO.content)
        )
    }

    private fun texifyCols(colsEntryDTO: ColsEntryDTO): EntryDTO {
        return ColsEntryDTO(
            title = formatString(colsEntryDTO.title),
            barchart = colsEntryDTO.barchart?.map { texifyChartItem(it) }
        )
    }

    private fun texifyChartItem(chartItemDTO: ChartItemDTO): ChartItemDTO {
        return ChartItemDTO(
            title = formatString(chartItemDTO.title),
            value = chartItemDTO.value
        )
    }

    private fun texifyLinkableText(linkableTextDTO: LinkableTextDTO): LinkableTextDTO {
        return LinkableTextDTO(
            text = formatString(linkableTextDTO.text),
            url = linkableTextDTO.url
        )
    }
}
