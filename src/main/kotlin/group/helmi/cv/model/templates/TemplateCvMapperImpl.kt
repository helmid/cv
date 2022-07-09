package group.helmi.cv.model.templates

import group.helmi.cv.model.*

abstract class TemplateCvMapperImpl : TemplateCvMapper {
    fun formatCv(cvDTO: CvDTO): CvDTO {
        return CvDTO(
            firstName = formatString(cvDTO.firstName),
            lastName = formatString(cvDTO.lastName),
            jobTitle = formatString(cvDTO.jobTitle),
            contact = cvDTO.contact.map { formatContact(it) },
            sections = cvDTO.sections.map { formatSection(it) }
        )
    }

    private fun formatContact(contactItemDTO: ContactItemDTO): ContactItemDTO {
        return ContactItemDTO(
            icon = formatFontAwesome(contactItemDTO.icon),
            text = formatString(contactItemDTO.text),
            href = contactItemDTO.href,
            disclose = contactItemDTO.disclose
        )
    }

    private fun formatSection(sectionElementDTO: SectionElementDTO): SectionElementDTO {
        return SectionElementDTO(
            title = formatString(sectionElementDTO.title),
            entries = sectionElementDTO.entries.map { formatSectionEntry(it) }
        )
    }

    private fun formatSectionEntry(entryDTO: EntryDTO): EntryDTO {
        return when (entryDTO) {
            is AboutEntryDTO -> formatAbout(entryDTO)
            is HistoryEntryDTO -> formatHistory(entryDTO)
            is EducationEntryDTO -> formatEducation(entryDTO)
            is ColsEntryDTO -> formatCols(entryDTO)
            else -> entryDTO
        }
    }

    private fun formatAbout(aboutEntryDTO: AboutEntryDTO): EntryDTO {
        return AboutEntryDTO(
            text = formatString(aboutEntryDTO.text),
            barchart = aboutEntryDTO.barchart.map { formatChartItem(it) },
            bubbles = aboutEntryDTO.bubbles?.map { formatChartItem(it) },
            country = formatString(aboutEntryDTO.country),
            operatingRadius = formatString(aboutEntryDTO.operatingRadius),
            workingMode = formatString(aboutEntryDTO.workingMode),
            bubbleMaxSkill = aboutEntryDTO.bubbleMaxSkill,
            bubblesTitle = formatString(aboutEntryDTO.bubblesTitle),
            barTitle = formatString(aboutEntryDTO.barTitle)
        )
    }

    private fun formatHistory(historyEntryDTO: HistoryEntryDTO): EntryDTO {
        return HistoryEntryDTO(
            start = formatString(historyEntryDTO.start),
            end = formaOptionalString(historyEntryDTO.end),
            jobTitle = formatString(historyEntryDTO.jobTitle),
            customer = formatString(historyEntryDTO.customer),
            description = formatString(historyEntryDTO.description),
            skills = historyEntryDTO.skills.map { formatString(it) }
        )
    }

    private fun formatEducation(educationEntryDTO: EducationEntryDTO): EntryDTO {
        return EducationEntryDTO(
            start = formatString(educationEntryDTO.start),
            end = formatString(educationEntryDTO.end),
            title = formatString(educationEntryDTO.title),
            facility = formatString(educationEntryDTO.facility),
            content = formatLinkableText(educationEntryDTO.content)
        )
    }

    private fun formatCols(colsEntryDTO: ColsEntryDTO): EntryDTO {
        return ColsEntryDTO(
            title = formatString(colsEntryDTO.title),
            barchart = colsEntryDTO.barchart?.map { formatChartItem(it) }
        )
    }

    private fun formatChartItem(chartItemDTO: ChartItemDTO): ChartItemDTO {
        return ChartItemDTO(
            title = formatString(chartItemDTO.title),
            value = chartItemDTO.value
        )
    }

    private fun formatLinkableText(linkableTextDTO: LinkableTextDTO): LinkableTextDTO {
        return LinkableTextDTO(
            text = formatString(linkableTextDTO.text),
            url = linkableTextDTO.url
        )
    }
}
