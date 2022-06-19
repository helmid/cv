package group.helmi.cv.controller

import group.helmi.cv.service.CvWebsiteService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class CvWebsiteController(private val websiteService: CvWebsiteService) {
    @GetMapping
    fun getCvPage(model: Model): String {
        val cvWebsiteDTO = websiteService.loadData()
        model.addAttribute(cvWebsiteDTO)
        return "index"
    }
}