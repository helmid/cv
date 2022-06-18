package group.helmi.cv

import group.helmi.cv.service.MaintenanceService
import group.helmi.cv.util.CvPathUtil
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.nio.file.Files

@SpringBootTest
class MaintenanceServiceTests(@Autowired private val maintenanceService: MaintenanceService) {

    @Test
    fun `test clean output`() {
        CvPathUtil.createBaseOutputPathIfNotExists()
        Assertions.assertTrue(Files.exists(CvPathUtil.baseOutputPath))
        maintenanceService.cleanOutput()
        Assertions.assertFalse(Files.exists(CvPathUtil.baseOutputPath))
    }
}