package com.mobileshift.cv.model.templates.tex.cv1

import com.mobileshift.cv.model.ChartItemDTO

class BarChart {
    companion object {
        fun make(items: List<ChartItemDTO>, maxWidth: Double): String {
            var bars = ""
            items.forEach {
                bars = "${bars}\n${makeBar(it)}"
            }
            return """
                \begin{barchart}{$maxWidth}${bars}
                \end{barchart}
            """.trimIndent()
        }

        private fun makeBar(item: ChartItemDTO): String {
            return "\\baritem{${item.title}}{${item.value}}"
        }
    }
}