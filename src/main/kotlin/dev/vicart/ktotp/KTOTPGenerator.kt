package dev.vicart.ktotp

import java.time.Duration
import java.time.Instant
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

/**
 * Class for generating Time-based OTP
 * @param algorithm the HMAC algorithm to use
 */
class KTOTPGenerator(private val algorithm: HmacAlgorithms = HmacAlgorithms.HmacSHA1) {

    /**
     * The duration of a valid OTP code
     */
    var period = Duration.ofSeconds(30)

    /**
     * The beginning Unix timestamp
     */
    var startCountTime = 0
    private val khotpGenerator = KHOTPGenerator(algorithm)

    /**
     * @param period The duration of a valid OTP code
     * @param algorithm the HMAC algorithm to use
     */
    constructor(period: Long, algorithm: HmacAlgorithms = HmacAlgorithms.HmacSHA1) : this(algorithm) {
        this.period = Duration.ofSeconds(period)
    }

    /**
     * Generate a TOTP code as string
     * @param secretKey the secret key for the HMAC function
     * @param time the current instant for generating code
     * @return an OTP code as string
     */
    fun generateCode(secretKey: SecretKey, time: Instant = Instant.now()) : String {
        return khotpGenerator.generateCode(secretKey, generateCounter(time))
    }

    /**
     * Generate a TOTP code as string
     * @param secretKey the secret key for the HMAC function
     * @param time the current instant for generating code
     * @return an OTP code as string
     */
    fun generateCode(secretKey: ByteArray, time: Instant = Instant.now()): String {
        val key = SecretKeySpec(secretKey, algorithm.algorithm)
        return generateCode(key, time)
    }

    /**
     * Generate a TOTP code as string
     * @param secretKey the secret key for the HMAC function
     * @param time the current instant for generating code
     * @return an OTP code as string
     */
    fun generateCode(secretKey: String, time: Instant = Instant.now()): String {
        return generateCode(secretKey.toByteArray(), time)
    }

    private fun generateCounter(time: Instant) : Byte {
        val currentTime = time.epochSecond
        val counter = (currentTime - startCountTime) / period.seconds
        return counter.toByte()
    }
}