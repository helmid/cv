package group.helmi.cv.model.templates.tex.cv1

import group.helmi.cv.dto.ContactItemDTO

object Header {
    fun make(firstName: String, lastName: String, jobTitle: String, contact: List<ContactItemDTO>): String {
        return """
            ${makeNameAndJobTitle(firstName, lastName, jobTitle, makeContact(contact))}
            ${makeImage()}
        """.trimIndent()
    }

    private fun makeNameAndJobTitle(firstName: String, lastName: String, jobTitle: String, contact: String): String {
        return """
                \begin{minipage}[t]{0.667\textwidth} 
                	\vspace{-\baselineskip} % Required for vertically aligning minipages
                	
                	\colorbox{black}{{\HUGE\textcolor{white}{\textbf{\MakeUppercase{${firstName}}}}}} % First name
                	
                	\colorbox{black}{{\HUGE\textcolor{white}{\textbf{\MakeUppercase{${lastName}}}}}} % Last name
                	
                	\vspace{6pt}
                	
                	{\huge ${jobTitle}} % Career or current job title
                    $contact
                \end{minipage}
            """.trimIndent()
    }

    private fun makeImage(): String {
        return """
        \begin{minipage}[t]{0.333\textwidth} 
            \vspace{-\baselineskip}
	        \roundpic[xshift=0.5cm,yshift=-0.5cm]{5.8cm}{9cm}{cvProfilePicture}
        \end{minipage}
        """.trimIndent()
    }

    private fun makeContact(contact: List<ContactItemDTO>): String {
        var result = """
            
            \vspace{16pt}
        """.trimIndent()
        contact.forEach {
            result = """
                    $result
                    ${makeIconEntry(it)}
                """.trimIndent()
        }
        return result
    }

    private fun makeIconEntry(item: ContactItemDTO): String {
        val href = if (item.href != null) {
            "{\\href{${item.href}}{${item.text}}}"
        } else "{${item.text}}"
        return "\\icon{${item.icon}}{12}${href}\\\\\n"
    }
}