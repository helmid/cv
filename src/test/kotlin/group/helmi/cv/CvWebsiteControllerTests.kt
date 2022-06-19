package group.helmi.cv

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
class CvWebsiteControllerTests(@Autowired private val wac: WebApplicationContext) {

    private lateinit var mockMvc: MockMvc

    @LocalServerPort
    private val port = 0

    @BeforeEach
    fun setup() {
        val builder = MockMvcBuilders.webAppContextSetup(wac)
        mockMvc = builder.build()
        MockitoAnnotations.openMocks(this)
        val request = MockHttpServletRequest(null, "", "/${Path.Document.DOCUMENT_BASE}")
        request.scheme = "https"
        request.serverPort = port
        RequestContextHolder.setRequestAttributes(ServletRequestAttributes(request))
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun `test website load`() {
        val result = mockMvc.get("https://localhost:$port").andReturn()
        Assertions.assertEquals(HttpStatus.OK.value(), result.response.status, "Expected HTTP Ok (200)")
        Assertions.assertEquals(
            "text/html;charset=UTF-8",
            result.response.getHeaders(HttpHeaders.CONTENT_TYPE).firstOrNull(),
            "Expected text/html;charset=UTF-8 as content type"
        )
    }
}