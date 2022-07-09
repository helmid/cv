package group.helmi.cv.model.templates.web.cv1

import group.helmi.cv.model.HistoryEntryDTO
import group.helmi.cv.util.extension.concatAndSnakeCase
import group.helmi.cv.util.extension.toKebapCase

object SectionHistory {
    private const val collapsedThreshold = 4

    fun make(title: String, items: List<HistoryEntryDTO>): String {
        val snakeTitle = title.concatAndSnakeCase()
        val ids =
            List(items.size) { index -> if (index > collapsedThreshold) "$snakeTitle$index" else "" }.filter { it.isNotEmpty() }
                .joinToString(" ")
        val cards = items.mapIndexed { index, item -> makeCard(item, "$snakeTitle$index", index) }.joinToString("\n")
        return """
        <div class="section" id="${title.toKebapCase()}">
            <div class="container cc-experience">
                <div class="h4 text-center mb-4 title">$title</div>
                    $cards
                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target=".multi-collapse" aria-expanded="false" aria-controls="$ids">Toggle $title</button>
            </div>
        </div>
    """.trimIndent()
    }

    private fun makeCard(item: HistoryEntryDTO, id: String, index: Int): String {
        val skills = item.skills.joinToString(" / ")
        val multiCollapseOpen =
            if (index > collapsedThreshold) "<div class=\"collapse multi-collapse\" id=\"$id\">" else ""
        val multiCollapseClose = if (index > collapsedThreshold) "</div>" else ""
        return """
            $multiCollapseOpen
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
            $multiCollapseClose
        """.trimIndent()
    }
}