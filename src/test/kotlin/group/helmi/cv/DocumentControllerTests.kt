package group.helmi.cv

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import group.helmi.cv.controller.api.DocumentController
import group.helmi.cv.data.TestCvData
import group.helmi.cv.dto.CvDTO
import group.helmi.cv.util.FileUtil
import group.helmi.cv.util.Path
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DocumentControllerTests(
    @Autowired private val documentController: DocumentController,
    @Autowired private val wac: WebApplicationContext
) {

    private lateinit var mockMvc: MockMvc

    @LocalServerPort
    private val port = 0

    @BeforeEach
    fun setup() {
        val builder = MockMvcBuilders.webAppContextSetup(wac)
        mockMvc = builder.build()
        FileUtil.deleteDirectory(FileUtil.getPath("output").toFile())
        MockitoAnnotations.openMocks(this)
        val request = MockHttpServletRequest(null, "", "/${Path.Document.DOCUMENT_BASE}")
        request.scheme = "https"
        request.serverPort = port
        RequestContextHolder.setRequestAttributes(ServletRequestAttributes(request))
    }

    @AfterEach
    fun tearDown() {
        FileUtil.deleteDirectory(FileUtil.getPath("output").toFile())
    }

    @Test
    fun `test cv creation`() {
        val cv = jacksonObjectMapper().readValue<CvDTO>(TestCvData.testDataValid)
        val response = documentController.buildDocument(cv)
        Assertions.assertEquals(HttpStatus.CREATED, response.statusCode, "Expected HTTP Created (201)")
        val resultUri = response.headers.location
        Assertions.assertFalse(resultUri?.path.isNullOrEmpty(), "Result location cannot be null")
        val result = mockMvc.get(resultUri!!.path).andReturn()
        Assertions.assertEquals(HttpStatus.OK.value(), result.response.status, "Expected HTTP Ok (200)")
        Assertions.assertEquals(
            "application/pdf",
            result.response.getHeaders(HttpHeaders.CONTENT_TYPE).firstOrNull(),
            "Expected application/pdf as content type"
        )
        Assertions.assertTrue(result.response.contentLength > 50000, "Content is a pdf, it is expected to be larger")
    }
}