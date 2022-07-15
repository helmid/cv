// https://medium.com/ibisdev/how-to-create-an-api-in-spring-with-support-for-multiple-languages-34dee2a71e84
package group.helmi.cv.config.language

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.stereotype.Component

@Component
class Translator @Autowired constructor(messageSource: ResourceBundleMessageSource) {
    init {
        Companion.messageSource = messageSource
    }
    companion object {
        private var messageSource: ResourceBundleMessageSource? = null
        fun toLocale(key: String, args: Array<String>? = null): String {
            val locale = LocaleContextHolder.getLocale()
            return messageSource?.getMessage(key, args, locale) ?: key
        }
    }
}