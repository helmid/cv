package group.helmi.cv.model.templates.tex.cv1

import group.helmi.cv.model.CvDTO

object Cv {
    fun make(cv: CvDTO): String {
        val header = Header.make(cv.firstName, cv.lastName, cv.jobTitle, cv.contact)
        val sections = Section.make(cv.sections)
        return """
                %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                % Developer CV
                % LaTeX Template
                % Version 1.0 (28/1/19)
                %
                % This template originates from:
                % http://www.LaTeXTemplates.com
                %
                % Authors:
                % Jan Vorisek (jan@vorisek.me)
                % Based on a template by Jan Küster (info@jankuester.com)
                % Modified for LaTeX Templates by Vel (vel@LaTeXTemplates.com)
                %
                % License:
                % The MIT License (see included LICENSE file)
                %
                %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

                %----------------------------------------------------------------------------------------
                %	PACKAGES AND OTHER DOCUMENT CONFIGURATIONS
                %----------------------------------------------------------------------------------------

                \documentclass[9pt]{developercv} % Default font size, values from 8-12pt are recommended

                %----------------------------------------------------------------------------------------

                \begin{document}

                $header
                
                $sections

                \end{document}
            """.trimIndent()
    }
}