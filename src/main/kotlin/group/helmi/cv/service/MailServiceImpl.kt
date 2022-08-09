package group.helmi.cv.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import group.helmi.cv.dto.ContactRequestDTO
import group.helmi.cv.dto.RequestMailDTO
import group.helmi.cv.util.CvPathUtil.getContactFormMailJson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class MailServiceImpl(@Autowired private val javaMailSender: JavaMailSender) : MailService {
    override fun sendContactRequestMail(contactRequestDTO: ContactRequestDTO): Boolean {
        val msg = SimpleMailMessage()
        val mailBaseData = jacksonObjectMapper().readValue<RequestMailDTO>(getContactFormMailJson())
        msg.setFrom(mailBaseData.from)
        msg.setTo(mailBaseData.to)
        msg.setSubject("${mailBaseData.subject}${contactRequestDTO.email}")
        msg.setText("${mailBaseData.text}${contactRequestDTO.toMailDetails()}")
        return try {
            javaMailSender.send(msg)
            true
        } catch (e: MailException) {
            false
        }
    }
}