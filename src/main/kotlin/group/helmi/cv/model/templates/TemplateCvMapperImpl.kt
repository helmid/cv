package group.helmi.cv.model.templates

import group.helmi.cv.dto.*
import group.helmi.cv.mapper.LinkableTextDTOMapper
import group.helmi.cv.model.CvPermission

abstract class TemplateCvMapperImpl : TemplateCvMapper {
    override fun formatCv(cvDTO: CvDTO, cvPermission: CvPermission): CvDTO {
        val contact = cvDTO.contact.map { formatContact(it) }
            .filter { cvPermission.contactDisclosureAllowed || it.disclose }
        return CvDTO(
            firstName = formatString(cvDTO.firstName),
            lastName = formatString(cvDTO.lastName),
            jobTitle = formatString(cvDTO.jobTitle),
            contact = contact,
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
            chartSection = aboutEntryDTO.chartSection?.let { formatSkills(it) },
            country = formatString(aboutEntryDTO.country),
            operatingRadius = formatString(aboutEntryDTO.operatingRadius),
            workingMode = formatString(aboutEntryDTO.workingMode)
        )
    }

    private fun formatSkills(chartSectionDTO: ChartSectionDTO): ChartSectionDTO {
        return ChartSectionDTO(
            title = formatString(chartSectionDTO.title),
            text = formatString(chartSectionDTO.text),
            barchart = chartSectionDTO.barchart.map { formatChartItem(it) },
            bubbles = chartSectionDTO.bubbles?.map { formatChartItem(it) },
            bubbleMaxSkill = chartSectionDTO.bubbleMaxSkill,
            bubblesTitle = formatString(chartSectionDTO.bubblesTitle),
            barTitle = formatString(chartSectionDTO.barTitle)
        )
    }

    private fun formatHistory(historyEntryDTO: HistoryEntryDTO): EntryDTO {
        return HistoryEntryDTO(
            start = formatString(historyEntryDTO.start),
            end = formatOptionalString(historyEntryDTO.end),
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
            content = LinkableTextDTOMapper.format(this, educationEntryDTO.content)
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
            longTitle = formatOptionalString(chartItemDTO.longTitle),
            value = chartItemDTO.value
        )
    }
}