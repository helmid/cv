// https://medium.com/ibisdev/how-to-create-an-api-in-spring-with-support-for-multiple-languages-34dee2a71e84
package group.helmi.cv.config.localization

import group.helmi.cv.model.LocalizableLocale
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.CookieLocaleResolver
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import java.util.*
import javax.servlet.http.HttpServletRequest


@Configuration
class CustomLocaleResolver : CookieLocaleResolver(), WebMvcConfigurer {
    companion object {
        val locales = listOf(
            LocalizableLocale(Locale("de"), "lang_toggle_german"),
            LocalizableLocale(Locale("en"), "lang_toggle_english")
        )
        val defaultLocalizableLocale = locales.first()

        private val langParam = "lang"
    }

    init {
        cookieName = langParam
        defaultLocale = defaultLocalizableLocale.locale
    }

    override fun resolveLocale(request: HttpServletRequest): Locale {
        val lang = request.cookies.firstOrNull { it.name == cookieName }?.value
        return if (lang.isNullOrEmpty()) {
            Locale.getDefault()
        } else {
            Locale.lookup(Locale.LanguageRange.parse(lang), locales.map { it.locale })
        }
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)
        registry.addInterceptor(localeChangeInterceptor())
    }

    @Bean("messageSource")
    fun messageSource(): ResourceBundleMessageSource {
        val rs = ResourceBundleMessageSource()
        rs.setBasename("messages")
        rs.setDefaultEncoding("UTF-8")
        rs.setUseCodeAsDefaultMessage(true)
        return rs
    }

    @Bean
    fun localeChangeInterceptor(): LocaleChangeInterceptor {
        val localeChangeInterceptor = LocaleChangeInterceptor()
        localeChangeInterceptor.paramName = langParam
        return localeChangeInterceptor
    }

    @Bean
    fun localeResolver(): LocaleResolver {
        return CustomLocaleResolver()
    }
}