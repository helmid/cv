package com.mobileshift.cv.model.templates.tex.cv1

import com.mobileshift.cv.model.HistoryEntryDTO

class SectionHistory {
    companion object {
        fun make(title: String, items: List<HistoryEntryDTO>): String {
            val list = makeEntry(items)
            return """
                \cvsect{$title}

                \begin{entrylist}
                	$list
                \end{entrylist}
            """.trimIndent()
        }

        private fun makeEntry(items: List<HistoryEntryDTO>): String {
            return items.map { makeEntry(it) }.joinToString("\n")
        }

        private fun makeEntry(item: HistoryEntryDTO): String {
            val date = if (item.end == null) {
                "since ${item.start}" //TODO: Localize
            } else {
                "${item.start} -- ${item.end}"
            }
            val skills = makeSkillStack(item.skills)
            return """
                \entry
                    {$date}
                    {${item.jobTitle}}
                    {${item.customer}}
                    {${item.description}
                    $skills}
            """.trimIndent()
        }

        private fun makeSkillStack(items: List<String>): String {
            if (items.isEmpty()) return ""
            val stack = items.map { "\\texttt{$it}" }.joinToString("\\slashsep")
            return "\\newline $stack"
        }
    }
}