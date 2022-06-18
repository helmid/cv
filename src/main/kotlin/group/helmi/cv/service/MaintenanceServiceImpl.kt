package group.helmi.cv.service

import group.helmi.cv.util.CvPathUtil
import group.helmi.cv.util.FileUtil
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
@EnableScheduling
class MaintenanceServiceImpl : MaintenanceService {
    companion object {
        private const val CRON_5_AM_SUN = "0 5 * * 0"
        private const val SPRING_CRON_5_AM_SUN = "0 $CRON_5_AM_SUN"
        private const val UTC = "UTC"
    }

    private val logger = LoggerFactory.getLogger(MaintenanceServiceImpl::class.java)

    @Scheduled(cron = SPRING_CRON_5_AM_SUN, zone = UTC)
    override fun cleanOutput() {
        val result = FileUtil.deleteDirectory(CvPathUtil.baseOutputPath.toFile())
        logger.info(result)
    }
}