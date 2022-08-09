package group.helmi.cv.service

import group.helmi.cv.dto.ContactRequestDTO

interface MailService {
    fun sendContactRequestMail(contactRequestDTO: ContactRequestDTO): Boolean
}