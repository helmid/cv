package group.helmi.cv.model.templates.web.cv1

import group.helmi.cv.model.AboutEntryDTO
import group.helmi.cv.model.ChartItemDTO
import group.helmi.cv.util.extension.makeLines
import org.slf4j.LoggerFactory

object SectionAbout {
    private val logger = LoggerFactory.getLogger(SectionAbout::class.java)
    fun make(title: String, items: List<AboutEntryDTO>): String {
        if (items.size != 1) {
            logger.info("Invalid About entry count")
            return ""
        }
        val item = items.first()
        val about = makeAboutSection(title, item)
        val barChart = makeSkillsSection(item.barTitle, item.barchart)
        val bubbleChart = item.bubbles?.map { it.bubbleToLineChart(item.bubbleMaxSkill) }
            ?.let { makeSkillsSection(item.bubblesTitle, it) } ?: ""
        return "$about\n$barChart\n$bubbleChart"
    }

    private fun makeAboutSection(title: String, item: AboutEntryDTO): String {
        return """
            <div class="section" id="${title.replace(" ", "")}">
        <div class="container">
          <div class="card" data-aos="fade-up" data-aos-offset="10">
            <div class="row">
              <div class="col-lg-6 col-md-12">
                <div class="card-body">
                  <div class="h4 mt-0 title">$title</div>
                  <p>${item.text}</p>
                </div>
              </div>
              <div class="col-lg-6 col-md-12">
                <div class="card-body">
                  <div class="h4 mt-0 title">Basic Information</div>
                  <div class="row mt-3">
                    <div class="col-sm-4"><strong class="text-uppercase">Country:</strong></div>
                    <div class="col-sm-8">${item.country}</div>
                  </div>
                  <div class="row mt-3">
                    <div class="col-sm-4"><strong class="text-uppercase">Operating radius:</strong></div>
                    <div class="col-sm-8">${item.operatingRadius}</div>
                  </div>
                  <div class="row mt-3">
                    <div class="col-sm-4"><strong class="text-uppercase">Working modes:</strong></div>
                    <div class="col-sm-8">${item.workingMode}</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
        """.trimIndent()
    }

    private fun makeSkillsSection(title: String, items: List<ChartItemDTO>): String {
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