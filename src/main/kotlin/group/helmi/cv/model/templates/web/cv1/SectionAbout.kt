package group.helmi.cv.model.templates.web.cv1

import group.helmi.cv.model.AboutEntryDTO
import group.helmi.cv.util.extension.toKebapCase
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
        val barChart = LineChart.make(item.barTitle, item.barchart)
        val bubbleChart = item.bubbles?.map { it.bubbleToLineChart(item.bubbleMaxSkill) }
            ?.let { LineChart.make(item.bubblesTitle, it) } ?: ""
        return "$about\n$barChart\n$bubbleChart"
    }

    private fun makeAboutSection(title: String, item: AboutEntryDTO): String {
        return """
            <div class="section" id="${title.toKebapCase()}">
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
}