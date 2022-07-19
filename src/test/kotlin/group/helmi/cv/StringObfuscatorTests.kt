package group.helmi.cv

import group.helmi.cv.util.StringObfuscator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class StringObfuscatorTests {

    @Test
    fun `test crypt`() {
        val keys = listOf(
            "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.",
            "Lorem",
            "",
            "Lorem ipsum",
            ""
        )
        val plaintexts = listOf(
            UUID.randomUUID().toString(),
            "Lorem ipsum",
            "123",
            "",
            ""
        )
        assert(keys.size == plaintexts.size)
        plaintexts.forEachIndexed { index, _ ->
            val crypt = StringObfuscator.crypt(plaintexts[index].toByteArray(), keys[index].toByteArray())
            val plain = StringObfuscator.crypt(Base64.getDecoder().decode(crypt), keys[index].toByteArray())
            Assertions.assertEquals(plaintexts[index], String(Base64.getDecoder().decode(plain)))
        }

    }
}