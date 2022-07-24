package group.helmi.cv.controller.website

import group.helmi.cv.service.ImprintService
import group.helmi.cv.util.LanguageUtil
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.util.*

@Controller
class ImprintController(private val imprintService: ImprintService) : WebMvcConfigurerController() {
    @GetMapping("/imprint.html", "impressum.html")
    fun getImprint(model: Model, locale: Locale): String {
        val imprintKey = imprintService.generateKey()
        val imprintDTO = imprintService.loadData(imprintKey)
        model.addAttribute(imprintDTO)
        model.addAttribute("imprintKey", Base64.getEncoder().encodeToString(imprintKey))
        model.addAllAttributes(LanguageUtil.getLanguageAttributes(locale))
        return "imprint"
    }
}