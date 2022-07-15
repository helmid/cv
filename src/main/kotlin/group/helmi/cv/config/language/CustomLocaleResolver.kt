// https://medium.com/ibisdev/how-to-create-an-api-in-spring-with-support-for-multiple-languages-34dee2a71e84
package group.helmi.cv.config.language

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.*
import javax.servlet.http.HttpServletRequest

@Configuration
class CustomLocaleResolver : AcceptHeaderLocaleResolver(), WebMvcConfigurer {
    companion object {
        val LOCALES = listOf(
            Locale("de"),
            Locale("en"),
        )
    }

    override fun resolveLocale(request: HttpServletRequest): Locale {
        val headerLang = request.getHeader("Accept-Language")
        return if (headerLang.isNullOrEmpty()) {
            Locale.getDefault()
        } else {
            Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES)
        }
    }

    @Bean
    fun messageSource(): ResourceBundleMessageSource {
        val rs = ResourceBundleMessageSource()
        rs.setBasename("messages")
        rs.setDefaultEncoding("UTF-8")
        rs.setUseCodeAsDefaultMessage(true)
        return rs
    }
}