package group.helmi.cv.model.templates.web.cv1

import group.helmi.cv.util.extension.concatAndSnakeCase

object CollapsableWrapper {
    private const val collapsedThreshold = 4
    fun wrap(content: String, index: Int, id: String): String {
        val multiCollapseOpen =
            if (index > collapsedThreshold) "<div class=\"collapse multi-collapse\" id=\"$id\">" else ""
        val multiCollapseClose = if (index > collapsedThreshold) "</div>" else ""
        return """
        $multiCollapseOpen
            $content
        $multiCollapseClose
    """.trimIndent()
    }

    fun makeButton(title: String, items: List<Any>): String {
        if (items.size <= collapsedThreshold) return ""
        val snakeTitle = title.concatAndSnakeCase()
        val ids =
            List(items.size) { index -> if (index > collapsedThreshold) "$snakeTitle$index" else "" }.filter { it.isNotEmpty() }
                .joinToString(" ")
        return """
            <button class="btn btn-primary" type="button" data-toggle="collapse" data-target=".multi-collapse" aria-expanded="false" aria-controls="$ids">Toggle $title</button>
        """.trimIndent()
    }
}