package com.mobileshift.cv

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.mobileshift.cv.controller.DocumentController
import com.mobileshift.cv.data.TestCvData
import com.mobileshift.cv.model.CvDTO
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus

@SpringBootTest
class DocumentControllerTests(@Autowired private val documentController: DocumentController) {

    @Test
    fun `test cv creation`() {
        val cv = jacksonObjectMapper().readValue<CvDTO>(TestCvData.testDataValid)
        val response = documentController.buildDocument(cv)
        Assertions.assertEquals(HttpStatus.OK, response.statusCode, "Expected HTTP Ok")
        Assertions.assertEquals("cv.pdf", response.headers.contentDisposition.filename)
        Assertions.assertEquals(true, response.headers.contentDisposition.isAttachment)
    }
}