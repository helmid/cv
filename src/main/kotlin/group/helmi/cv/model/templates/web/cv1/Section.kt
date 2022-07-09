package group.helmi.cv.model.templates.web.cv1

import group.helmi.cv.model.*
import group.helmi.cv.util.extension.toKebapCase

object Section {
    fun make(sections: List<SectionElementDTO>): List<String> {
        return sections.map { makeSection(it) }
    }

    @Suppress("UNCHECKED_CAST")
    private fun makeSection(section: SectionElementDTO): String {
        if (section.entries.isEmpty()) return ""
        return when (section.entries.firstOrNull()) {
            is AboutEntryDTO -> SectionAbout.make(section.title, section.entries as List<AboutEntryDTO>)
            is HistoryEntryDTO -> SectionHistory.make(section.title, section.entries as List<HistoryEntryDTO>)
            is EducationEntryDTO -> SectionEducation.make(section.title, section.entries as List<EducationEntryDTO>)
            is ColsEntryDTO -> SectionCols.make(section.title, section.entries as List<ColsEntryDTO>)
            else -> ""
        }
    }

    fun makeSectionWrapper(title: String, content: String): String {
        return """
            <div class="section" id="${title.toKebapCase()}">
            <div class="container">
                <div class="h4 text-center mb-4 title">$title</div>
                <div class="card" data-aos="fade-up" data-aos-anchor-placement="top-bottom">
                    <div class="card-body">
                        $content
                    </div>
                </div>
            </div>
        </div>
        """.trimIndent()
    }
}