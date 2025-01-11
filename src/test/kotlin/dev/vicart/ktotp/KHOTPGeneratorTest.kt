package dev.vicart.ktotp

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class KHOTPGeneratorTest {

    @Test
    fun generateCode() {
        val expected = "999981"

        val generator = KHOTPGenerator()
        val code = generator.generateCode("testSecretKey", 0)
        assertEquals(expected, code)
    }
}