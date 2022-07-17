package group.helmi.cv.controller.website

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PrivacyController : WebMvcConfigurerController() {
    @GetMapping("/privacy.html", "datenschutz.html")
    fun getGDPR(): String {
        return "gdpr"
    }
}