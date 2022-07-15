package group.helmi.cv.model.templates.tex.cv1

import group.helmi.cv.dto.ChartItemDTO

object BarChart {
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