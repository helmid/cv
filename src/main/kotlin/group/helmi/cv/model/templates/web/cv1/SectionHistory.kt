package group.helmi.cv.model.templates.web.cv1

import group.helmi.cv.dto.HistoryEntryDTO
import group.helmi.cv.util.extension.concatAndSnakeCase
import group.helmi.cv.util.extension.toKebapCase

object SectionHistory {
    fun make(title: String, items: List<HistoryEntryDTO>): String {
        val button = CollapsableWrapper.makeButton(title, items)
        val cards = items
            .mapIndexed { index, item -> makeCard(item, "${title.concatAndSnakeCase()}$index", index) }
            .joinToString("\n")
        return """
        <div class="section" id="${title.toKebapCase()}">
            <div class="container cc-experience">
                <div class="h4 text-center mb-4 title">$title</div>
                    $cards
                    $button
            </div>
        </div>
    """.trimIndent()
    }

    private fun makeCard(item: HistoryEntryDTO, id: String, index: Int): String {
        val skills = item.skills.joinToString(" / ")
        val content = """
            <div class="card">
                <div class="row">
                    <div class="col-md-3 bg-primary" data-aos="fade-right" data-aos-offset="50" data-aos-duration="500">
                        <div class="card-body cc-experience-header">
                            <p>${item.getDate("-")}</p>
                            <div class="h5">${item.customer}</div>
                        </div>
                    </div>
                    <div class="col-md-9" data-aos="fade-left" data-aos-offset="50" data-aos-duration="500">
                        <div class="card-body">
                            <div class="h5">${item.jobTitle}</div>
                            <p>${item.description}</p>
                            <p class="hg-skills-used">$skills</p>
                        </div>
                    </div>
                </div>
            </div>
        """.trimIndent()
        return CollapsableWrapper.wrap(content, index, id)
    }
}