package group.helmi.cv.controller.website

import group.helmi.cv.util.LanguageUtil
import group.helmi.cv.util.Path
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest

@Controller
class ErrorControllerImpl : ErrorController {
    @RequestMapping(value = [Path.CvWebsite.errorPath])
    fun error(model: Model, locale: Locale, request: HttpServletRequest): String {
        model.addAttribute("statusCode", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE))
        model.addAllAttributes(LanguageUtil.getLanguageAttributes(locale))
        return "error"
    }
}