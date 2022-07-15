package group.helmi.cv.model.templates.web.cv1

import group.helmi.cv.dto.ColsEntryDTO
import group.helmi.cv.util.extension.toKebapCase

object SectionCols {
    fun make(title: String, items: List<ColsEntryDTO>): String {
        val sections = items.map {
            if (it.barchart.isNullOrEmpty()) {
                ""
            } else {
                LineChart.make(it.title, it.barchart)
            }
        }
            .joinToString("\n")

        return """
            <div id="${title.toKebapCase()}">
                $sections
            </div>
        """.trimIndent()
    }
}