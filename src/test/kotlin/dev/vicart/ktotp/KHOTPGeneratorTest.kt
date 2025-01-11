package dev.vicart.ktotp

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class KHOTPGeneratorTest {

    @Test
    fun generateCode() {
        val expected = "999981"

        val generator = KHOTPGenerator()
        val couter = 0x00.toByte()
        val code = generator.generateCode("testSecretKey", couter)
        assertEquals(expected, code)
    }
}