package group.helmi.cv.model.templates.tex.cv1

import group.helmi.cv.dto.AboutEntryDTO
import group.helmi.cv.dto.ChartSectionDTO

object SectionAbout {
    private val barChartMaxWidth = 5.5
    fun make(title: String, items: List<AboutEntryDTO>): String {
        if (items.isEmpty()) return ""
        val aboutMe = items.map { makeAboutMe(it) }.joinToString("\n")
        return """
                \cvsect{$title}
                $aboutMe
            """.trimIndent()
    }

    private fun makeAboutMe(item: AboutEntryDTO): String {
        val chartSection = item.chartSection?.let { makeChartSection(it) } ?: ""
        return """

\begin{multicols}{2}

${item.text}
\end{multicols}
                
$chartSection
        """.trimIndent()
    }

    private fun makeChartSection(chartSectionDTO: ChartSectionDTO): String {
        val barChart = BarChart.make(chartSectionDTO.barchart, barChartMaxWidth)
        val bubbles = Bubbles.make(chartSectionDTO.bubbles)
        return """
                \cvsect{${chartSectionDTO.title}}

                ${chartSectionDTO.text}
                \vspace{12pt}

                \begin{minipage}[t]{0.1\textwidth} 
                $barChart
                \vspace{0.5cm}
                $bubbles
                \end{minipage}

                \pagebreak
            """.trimIndent()
    }
}