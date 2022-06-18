package group.helmi.cv.model.templates.tex.cv1

import group.helmi.cv.model.ColsEntryDTO

object SectionCols {
    private val barChartMaxWidth = 3.0

    fun make(title: String, items: List<ColsEntryDTO>): String {
        if (items.isEmpty()) return ""
        val titleText = if (title.isEmpty()) "" else "\\cvsect{$title}"
        val width: Double = 1.0 / items.size
        val minipages = items.map { makeCol(it, width) }.joinToString("\n")
        return """
                $titleText
                
                $minipages
            """.trimIndent()
    }

    private fun makeCol(item: ColsEntryDTO, width: Double): String {
        if (item.barchart.isNullOrEmpty()) return ""
        val content = BarChart.make(item.barchart, barChartMaxWidth)
        return """
            \begin{minipage}[t]{$width\textwidth}
                \vspace{-\baselineskip} % Required for vertically aligning minipages
                \cvsect{${item.title}}
                	$content
            \end{minipage}
        """.trimIndent()
    }
}