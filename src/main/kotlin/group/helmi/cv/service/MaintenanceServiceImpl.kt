package group.helmi.cv.service

import group.helmi.cv.config.file.PublicFolder
import group.helmi.cv.util.CvPathUtil
import group.helmi.cv.util.FileUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
@EnableScheduling
class MaintenanceServiceImpl(@Autowired private val publicFolder: PublicFolder) : MaintenanceService {
    companion object {
        private const val CRON_4_AM_FIRST_OF_MONTH = "0 4 1 * *"
        private const val CRON_AT_ZERO_HRS = "0 0 * * *"
        private const val SPRING_CRON_4_AM_FIRST_OF_MONTH = "0 $CRON_4_AM_FIRST_OF_MONTH"
        private const val SPRING_CRON_AT_ZERO_HRS = "0 $CRON_AT_ZERO_HRS"
        private const val UTC = "UTC"
    }

    private val logger = LoggerFactory.getLogger(MaintenanceServiceImpl::class.java)

    @Scheduled(cron = SPRING_CRON_4_AM_FIRST_OF_MONTH, zone = UTC)
    override fun cleanOutput() {
        val result = FileUtil.deleteDirectory(CvPathUtil.baseOutputPath.toFile())
        logger.info(result)
    }

    @Scheduled(cron = SPRING_CRON_AT_ZERO_HRS, zone = UTC)
    override fun updatePublicFolder() {
        val result = publicFolder.deletePublicFolders()
        publicFolder.generatePublicFolders()
        logger.info(result)
    }
}