package group.helmi.cv.controller.website

import group.helmi.cv.service.GdprService
import group.helmi.cv.util.LanguageUtil
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.util.*

@Controller
class GdprController(private val gdprService: GdprService) : WebMvcConfigurerController() {
    @GetMapping("/privacy.html", "datenschutz.html")
    fun getGDPR(model: Model, locale: Locale): String {
        val gdprDTO = gdprService.loadData()
        model.addAttribute(gdprDTO)
        model.addAllAttributes(LanguageUtil.getLanguageAttributes(locale))
        return "gdpr"
    }
}