package group.helmi.cv

import group.helmi.cv.util.extension.toKebapCase
import group.helmi.cv.util.extension.toScreamingSnakeCase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StringUtilTest {
    companion object {
        val keys = listOf(
            "helloWorld",
            "helloWOrld",
            "hello World",
            "hello world",
            "hello-world",
            "helloworld",
            "HW",
            "h",
            "H ",
            ""
        )
    }

    @Test
    fun `test kebap case`() {
        val expected = listOf(
            "hello-world",
            "hello-w-orld",
            "hello-world",
            "hello-world",
            "hello-world",
            "helloworld",
            "h-w",
            "h",
            "h",
            ""
        )
        assert(keys.size == expected.size)
        expected.forEachIndexed { index, _ ->
            val result = keys[index].toKebapCase()
            Assertions.assertEquals(expected[index], result)
        }
    }

    @Test
    fun `test screaming snake case`() {
        val expected = listOf(
            "HELLO_WORLD",
            "HELLO_W_ORLD",
            "HELLO_WORLD",
            "HELLO_WORLD",
            "HELLO_WORLD",
            "HELLOWORLD",
            "H_W",
            "H",
            "H",
            ""
        )
        assert(keys.size == expected.size)
        expected.forEachIndexed { index, _ ->
            val result = keys[index].toScreamingSnakeCase()
            Assertions.assertEquals(expected[index], result)
        }
    }
}