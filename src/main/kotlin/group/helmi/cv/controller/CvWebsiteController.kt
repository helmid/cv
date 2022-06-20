package group.helmi.cv.controller

import group.helmi.cv.service.CvWebsiteService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Controller
class CvWebsiteController(private val websiteService: CvWebsiteService) : WebMvcConfigurer {
    @GetMapping
    fun getCvPage(model: Model): String {
        val cvWebsiteDTO = websiteService.loadData()
        model.addAttribute(cvWebsiteDTO)
        return "index"
    }

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/")
    }
}