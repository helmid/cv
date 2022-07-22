package group.helmi.cv.controller.website

import group.helmi.cv.service.GdprService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class GdprController(private val gdprService: GdprService) : WebMvcConfigurerController() {
    @GetMapping("/privacy.html", "datenschutz.html")
    fun getGDPR(model: Model): String {
        val gdprDTO = gdprService.loadData()
        model.addAttribute(gdprDTO)
        return "gdpr"
    }
}