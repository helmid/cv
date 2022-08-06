package group.helmi.cv.model.templates.web.cv1

import group.helmi.cv.dto.EducationEntryDTO
import group.helmi.cv.util.extension.concatAndCamelCase
import group.helmi.cv.util.extension.toKebapCase

object SectionEducation {

    fun make(title: String, items: List<EducationEntryDTO>): String {
        val cards = items
            .mapIndexed { index, item -> makeCard(item, "${title.concatAndCamelCase()}$index", index) }
            .joinToString("\n")
        val button = CollapsableWrapper.makeButton(title, items)
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

    private fun makeCard(item: EducationEntryDTO, id: String, index: Int): String {
        val innerContent =
            if (item.content.url != null) "<p><a href=\"${item.content.url}\">${item.content.text}</a></p>" else "<p>${item.content.text}</p>"
        val date =
            if (item.start == item.end) """<span class="hg-line">${item.end}</span>""" else """<span class="hg-line">${item.start}&nbsp;-&nbsp;</span><span class="hg-line">${item.end}</span>"""
        val content = """
                <div class="card">
                    <div class="row">
                        <div class="col-md-3 bg-primary" data-aos="fade-right" data-aos-offset="50" data-aos-duration="500">
                            <div class="card-body cc-experience-header">
                                <p>$date</p>
                                <div class="h5">${item.facility}</div>
                            </div>
                        </div>
                        <div class="col-md-9" data-aos="fade-left" data-aos-offset="50" data-aos-duration="500">
                            <div class="card-body">
                                <div class="h5">${item.title}</div>
                                $innerContent
                            </div>
                        </div>
                    </div>
                </div>
        """.trimIndent()
        return CollapsableWrapper.wrap(content, index, id)
    }
}