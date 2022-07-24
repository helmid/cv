package group.helmi.cv.controller.website

import group.helmi.cv.dto.ContactRequestDTO
import group.helmi.cv.mapper.PopupBuilder
import group.helmi.cv.service.CvWebsiteService
import group.helmi.cv.util.LanguageUtil
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import java.util.*

@Controller
class CvWebsiteController(private val websiteService: CvWebsiteService) : WebMvcConfigurerController() {
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
        return PopupBuilder.makeAlert(true, "subba gmacht")
    }
}