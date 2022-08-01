package group.helmi.cv.util

import group.helmi.cv.config.localization.CustomLocaleResolver
import group.helmi.cv.config.localization.Translator
import group.helmi.cv.dto.LocaleDTO
import java.util.*

object LanguageUtil {
    fun getLanguageAttributes(locale: Locale): Map<String, Any> {
        return mapOf(
            "language" to locale.language,
            "toggleLanguage" to getToggleLanguageName(locale)
        )
    }

    private fun getToggleLanguageName(locale: Locale): LocaleDTO {
        val loc = CustomLocaleResolver.locales.firstOrNull { it.locale.language != locale.language }
            ?: CustomLocaleResolver.defaultLocalizableLocale
        return LocaleDTO(
            localizedName = Translator.toForcedLocale(loc.locale, loc.localizableKey),
            toggleValue = loc.locale.language
        )
    }
}