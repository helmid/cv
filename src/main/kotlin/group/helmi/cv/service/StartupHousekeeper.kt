package group.helmi.cv.service

import group.helmi.cv.config.localization.CustomLocaleResolver
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class StartupHousekeeper(private val cvService: CvService, private val maintenanceService: MaintenanceService) {
    @EventListener(ContextRefreshedEvent::class)
    fun contextRefreshedEvent() {
        maintenanceService.cleanOutput()
        CustomLocaleResolver.locales.forEach {
            cvService.getPublicProfile(it.locale)
        }
    }
}