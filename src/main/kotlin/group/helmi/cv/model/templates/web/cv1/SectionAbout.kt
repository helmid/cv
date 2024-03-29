package group.helmi.cv.model.templates.web.cv1

import group.helmi.cv.config.localization.Translator
import group.helmi.cv.dto.AboutEntryDTO
import group.helmi.cv.dto.ChartSectionDTO
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
        val chartSection = item.chartSection?.let { makeSkillsSection(it) } ?: ""
        return "$about\n$chartSection"
    }

    private fun makeSkillsSection(item: ChartSectionDTO): String {
        val barChart = LineChart.make(item.barTitle, item.barchart)
        val bubbleChart = item.bubbles?.map { it.bubbleToLineChart(item.bubbleMaxSkill) }
            ?.let { LineChart.make(item.bubblesTitle, it) } ?: ""
        return """
            $barChart
            $bubbleChart
        """.trimIndent()
    }

    private fun makeAboutSection(title: String, item: AboutEntryDTO): String {
        return """
            <div class="section" id="${title.toKebapCase()}">
        <div class="container">
          <div class="card" data-aos="fade-up" data-aos-offset="10">
            <div class="row">
              <div class="col-lg-6 col-md-12">
                <div class="card-body">
                  <h4 class="h4 mt-0 title">$title</h4>
                  <p>${item.text}</p>
                </div>
              </div>
              <div class="col-lg-6 col-md-12">
                <div class="card-body">
                  <h4 class="h4 mt-0 title">${Translator.toLocale("about_basic_information")}</h4>
                  <div class="row mt-3">
                    <div class="col-sm-4"><strong class="text-uppercase">${Translator.toLocale("about_country")}:</strong></div>
                    <div class="col-sm-8">${item.country}</div>
                  </div>
                  <div class="row mt-3">
                    <div class="col-sm-4"><strong class="text-uppercase">${Translator.toLocale("about_operating_radius")}:</strong></div>
                    <div class="col-sm-8">${item.operatingRadius}</div>
                  </div>
                  <div class="row mt-3">
                    <div class="col-sm-4"><strong class="text-uppercase">${Translator.toLocale("about_working_modes")}:</strong></div>
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