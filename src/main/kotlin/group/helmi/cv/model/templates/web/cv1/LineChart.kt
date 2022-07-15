package group.helmi.cv.model.templates.web.cv1

import group.helmi.cv.dto.ChartItemDTO
import group.helmi.cv.util.extension.makeLines

object LineChart {
    fun make(title: String, items: List<ChartItemDTO>): String {
        val rows = makeTransformRows(items)
        return Section.makeSectionWrapper(title, rows)
    }

    private fun makeTransformRows(items: List<ChartItemDTO>): String {
        val columnsCount = 2
        val lines = items.makeLines(columnsCount)
        return makeRows(lines)
    }

    private fun makeRows(items: List<List<ChartItemDTO>>): String {
        return items.map {
            """
                <div class="row">
                    ${makeCols(it)}
                </div>
            """.trimIndent()
        }.joinToString("\n")
    }

    private fun makeCols(items: List<ChartItemDTO>): String {
        return items.map { makeColItem(it) }.joinToString("\n")
    }

    private fun makeColItem(item: ChartItemDTO): String {
        return """
            <div class="col-md-6">
                <div class="progress-container progress-primary"><span class="progress-badge">${item.title}</span>
                    <div class="progress">
                        <div class="progress-bar progress-bar-primary" data-aos="progress-full" data-aos-offset="10" data-aos-duration="2000" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: ${item.value.toInt()}%;"></div>
                        <span class="progress-value">${item.value}%</span>
                    </div>                                                   
                </div>
            </div>
        """.trimIndent()
    }
}