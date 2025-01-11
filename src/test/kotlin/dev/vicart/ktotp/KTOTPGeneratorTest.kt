package dev.vicart.ktotp

import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.test.assertEquals

class KTOTPGeneratorTest {

    @Test
    fun testGenerateCode() {
        val secretKey = "secretKeyTest"
        val instant = LocalDateTime.of(2024, 1, 1, 10, 0, 0)
            .toInstant(ZoneOffset.UTC)

        val generator = KTOTPGenerator()
        val code = generator.generateCode(secretKey, instant)

        assertEquals("999954", code)

    }
}