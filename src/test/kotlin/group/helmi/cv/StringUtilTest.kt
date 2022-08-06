package group.helmi.cv

import group.helmi.cv.util.extension.concatAndCamelCase
import group.helmi.cv.util.extension.toKebapCase
import group.helmi.cv.util.extension.toScreamingSnakeCase
import group.helmi.cv.util.extension.toSnakeCase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StringUtilTest {
    companion object {
        private val originalValues = listOf(
            "helloWorld",
            "helloWOrld",
            "hello World",
            "hello world",
            "hello-world",
            "hello_world",
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
            "hello-world",
            "helloworld",
            "h-w",
            "h",
            "h",
            ""
        )
        assert(originalValues.size == expected.size)
        expected.forEachIndexed { index, _ ->
            val result = originalValues[index].toKebapCase()
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
            "HELLO_WORLD",
            "HELLOWORLD",
            "H_W",
            "H",
            "H",
            ""
        )
        assert(originalValues.size == expected.size)
        expected.forEachIndexed { index, _ ->
            val result = originalValues[index].toScreamingSnakeCase()
            Assertions.assertEquals(expected[index], result)
        }
    }

    @Test
    fun `test snake case`() {
        val expected = listOf(
            "hello_world",
            "hello_w_orld",
            "hello_world",
            "hello_world",
            "hello_world",
            "hello_world",
            "helloworld",
            "h_w",
            "h",
            "h",
            ""
        )
        assert(originalValues.size == expected.size)
        expected.forEachIndexed { index, _ ->
            val result = originalValues[index].toSnakeCase()
            Assertions.assertEquals(expected[index], result)
        }
    }

    @Test
    fun `test concat and camel case`() {
        val input = listOf(
            "Hello_world",
            "hello_w_orld",
            "hello_world",
            "hello world",
            "hello w orld",
            "hello-world",
            "helloworld",
            "h_w",
            "h ",
            " h",
            " h ",
            "h",
            ""
        )
        val expected = listOf(
            "helloWorld",
            "helloWOrld",
            "helloWorld",
            "helloWorld",
            "helloWOrld",
            "helloWorld",
            "helloworld",
            "hW",
            "h",
            "h",
            "h",
            "h",
            ""
        )

        assert(input.size == expected.size)
        expected.forEachIndexed { index, _ ->
            val result = input[index].concatAndCamelCase()
            Assertions.assertEquals(expected[index], result)
        }
    }
}