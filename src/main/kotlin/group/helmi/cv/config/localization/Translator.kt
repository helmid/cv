// https://medium.com/ibisdev/how-to-create-an-api-in-spring-with-support-for-multiple-languages-34dee2a71e84
package group.helmi.cv.config.localization

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.stereotype.Component
import java.util.*

@Component
class Translator @Autowired constructor(messageSource: ResourceBundleMessageSource) {
    companion object {
        private var messageSource: ResourceBundleMessageSource? = null
        fun toLocale(key: String, args: Array<String>? = null): String {
            val locale = LocaleContextHolder.getLocale()
            return messageSource?.getMessage(key, args, locale) ?: key
        }

        fun toForcedLocale(locale: Locale, key: String, args: Array<String>? = null): String {
            return messageSource?.getMessage(key, args, locale) ?: key
        }
    }

    init {
        Companion.messageSource = messageSource
    }
}