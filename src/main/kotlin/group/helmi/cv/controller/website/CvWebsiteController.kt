package group.helmi.cv.controller.website

import group.helmi.cv.dto.ContactRequestDTO
import group.helmi.cv.mapper.PopupBuilder
import group.helmi.cv.service.CvWebsiteService
import group.helmi.cv.service.MailService
import group.helmi.cv.util.LanguageUtil
import group.helmi.cv.util.LoggingUtil
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.util.*

@Controller
class CvWebsiteController(
    private val websiteService: CvWebsiteService,
    private val mailService: MailService
) : WebMvcConfigurerController() {
    private val logger = LoggerFactory.getLogger(CvWebsiteController::class.java)

    @GetMapping
    fun getCvPage(model: Model, locale: Locale): String {
        model.addAllAttributes(LanguageUtil.getLanguageAttributes(locale))
        val cvWebsiteDTO = websiteService.loadData()
        model.addAttribute(cvWebsiteDTO)
        return "index"
    }

    @PostMapping
    @ResponseBody
    fun submitForm(@ModelAttribute contactRequestDTO: ContactRequestDTO): String {
        logger.info(LoggingUtil.stringify("submitForm", contactRequestDTO))
        mailService.sendContactRequestMail(contactRequestDTO)
        return PopupBuilder.makeAlert(true)
    }
}