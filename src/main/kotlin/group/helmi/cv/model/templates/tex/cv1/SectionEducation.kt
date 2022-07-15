package group.helmi.cv.model.templates.tex.cv1

import group.helmi.cv.dto.EducationEntryDTO

object SectionEducation {
    fun make(title: String, items: List<EducationEntryDTO>): String {
        val list = items.map { makeItem(it) }.joinToString("\n")
        return """
                \cvsect{$title}
                    
                \begin{entrylist}
                    $list
                \end{entrylist}
            """.trimIndent()
    }

    private fun makeItem(item: EducationEntryDTO): String {
        val href = if (item.content.url.isNullOrEmpty()) {
            item.content.text
        } else {
            "\\href{${item.content.url}}{${item.content.url}}"
        }
        val date = if (item.start == item.end) item.start else "${item.start} -- ${item.end}"
        return """
                \entry
                	{$date}
                	{${item.title}}
                	{${item.facility}}
                	{$href}
            """.trimIndent()
    }
}