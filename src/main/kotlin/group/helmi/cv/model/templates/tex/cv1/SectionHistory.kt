package group.helmi.cv.model.templates.tex.cv1

import group.helmi.cv.dto.HistoryEntryDTO

object SectionHistory {
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
        val skills = makeSkillStack(item.skills)
        return """
                \entry
                    {${item.getDate("--")}}
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