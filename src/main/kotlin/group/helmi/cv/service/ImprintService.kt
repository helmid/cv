package group.helmi.cv.service

import group.helmi.cv.dto.ImprintDTO

interface ImprintService {
    fun loadData(key: ByteArray?): ImprintDTO
    fun generateKey(): ByteArray
}