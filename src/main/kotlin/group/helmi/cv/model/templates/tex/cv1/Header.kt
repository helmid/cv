package group.helmi.cv.model.templates.tex.cv1

import group.helmi.cv.dto.ContactItemDTO

object Header {
    private const val MAX_CONTACT_RIGHT_COL_LENGTH = 25
    fun make(firstName: String, lastName: String, jobTitle: String, contact: List<ContactItemDTO>): String {
        return "${makeNameAndJobTitle(firstName, lastName, jobTitle)}\n${makeContact(contact)}"
    }

    private fun makeNameAndJobTitle(firstName: String, lastName: String, jobTitle: String): String {
        return """
                \begin{minipage}[t]{0.46\textwidth} 
                	\vspace{-\baselineskip} % Required for vertically aligning minipages
                	
                	\colorbox{black}{{\HUGE\textcolor{white}{\textbf{\MakeUppercase{${firstName}}}}}} % First name
                	
                	\colorbox{black}{{\HUGE\textcolor{white}{\textbf{\MakeUppercase{${lastName}}}}}} % Last name
                	
                	\vspace{6pt}
                	
                	{\huge ${jobTitle}} % Career or current job title
                \end{minipage}
            """.trimIndent()
    }

    private fun makeContact(contact: List<ContactItemDTO>): String {
        var left = ""
        var right = ""
        if (contact.size > 1) {
            val oversized = contact.filter { it.text.length > MAX_CONTACT_RIGHT_COL_LENGTH }
            val fitting = contact.filter { it.text.length <= MAX_CONTACT_RIGHT_COL_LENGTH }
            //first half of the array plus the odd one if exists
            val leftEnd = fitting.size / 2 + fitting.size % 2
            fitting.subList(0, leftEnd).forEach {
                left = "${left}${makeIconEntry(it)}"
            }
            oversized.forEach {
                left = "${left}${makeIconEntry(it)}"
            }
            fitting.subList(leftEnd, fitting.size).forEach {
                right = "$right${makeIconEntry(it)}"
            }
        }
        val leftMinipage = if (left.isNotEmpty()) """
                \begin{minipage}[t]{0.3\textwidth} 
                	\vspace{-\baselineskip}
                	$left
                \end{minipage}
            """.trimIndent() else ""

        val rightMinipage = if (right.isNotEmpty()) """
                \begin{minipage}[t]{0.3\textwidth} 
                	\vspace{-\baselineskip}
                	$right
                \end{minipage}
            """.trimIndent() else ""
        return "${leftMinipage}${rightMinipage}"
    }

    private fun makeIconEntry(item: ContactItemDTO): String {
        val href = if (item.href != null) {
            "{\\href{${item.href}}{${item.text}}}"
        } else "{${item.text}}"
        return "\\icon{${item.icon}}{12}${href}\\\\\n"
    }
}