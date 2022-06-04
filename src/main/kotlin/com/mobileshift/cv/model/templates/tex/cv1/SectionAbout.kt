package com.mobileshift.cv.model.templates.tex.cv1

import com.mobileshift.cv.model.AboutEntryDTO
import com.mobileshift.cv.model.ColsEntryDTO

class SectionAbout {
    companion object {
        private val barChartMaxWidth = 5.5
        fun make(title: String, items: List<AboutEntryDTO>): String {
            if (items.isEmpty()) return ""
            val minipages = items.map { makeMinipages(it) }.joinToString("\n")
            return """
                \cvsect{$title}
                
                $minipages
            """.trimIndent()
        }

        private fun makeMinipages(item: AboutEntryDTO): String {
            val rightMinipage = makeCharts(item)
            return """
                \begin{minipage}[t]{0.4\textwidth} 
                	\vspace{-\baselineskip} % Required for vertically aligning minipages
                	${item.text}
                \end{minipage}
                $rightMinipage
            """.trimIndent()
        }

        private fun makeCharts(about: AboutEntryDTO): String {
            val barChart = BarChart.make(about.barchart, barChartMaxWidth)
            val bubbles = Bubbles.make(about.bubbles)
            return """
                \hfill % Whitespace between
                \begin{minipage}[t]{0.5\textwidth} 
                	\vspace{-\baselineskip} % Required for vertically aligning minipages
                	$barChart
                    $bubbles
                \end{minipage}
            """.trimIndent()
        }
    }
}