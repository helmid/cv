package com.mobileshift.cv.model.templates.tex.cv1

import com.mobileshift.cv.model.*


class Section {
    companion object {
        fun make(sections: List<SectionElementDTO>): String {
            return sections.map { makeSection(it) }.joinToString("\n")
        }

        @Suppress("UNCHECKED_CAST")
        private fun makeSection(section: SectionElementDTO): String {
            if (section.entries.isEmpty() || (!allSameType(section.entries))) return ""
            return when (section.entries.firstOrNull()) {
                is AboutEntryDTO -> SectionAbout.make(section.title, section.entries as List<AboutEntryDTO>)
                is HistoryEntryDTO -> SectionHistory.make(section.title, section.entries as List<HistoryEntryDTO>)
                is EducationEntryDTO -> SectionEducation.make(section.title, section.entries as List<EducationEntryDTO>)
                is ColsEntryDTO -> SectionCols.make(section.title, section.entries as List<ColsEntryDTO>)
                else -> ""
            }
        }

        private fun <T> allSameType(vararg entries: T): Boolean = entries.all { it == entries[0] }
    }
}
