package dev.vicart.ktotp

import javax.crypto.Mac
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import kotlin.experimental.and
import kotlin.math.pow

/**
 * Class for generating HOTP codes
 * @param algorithm the HMAC algorithm to use
 */
class KHOTPGenerator(private val algorithm: HmacAlgorithms = HmacAlgorithms.HmacSHA1) {

    /**
     * Number of digits for the generated code
     */
    var length = 6

    private val hmacFunc = Mac.getInstance(algorithm.algorithm)

    /**
     * Generate a HOTP code as string
     * @param secretKey the secret key for the HMAC function
     * @param counter the counter from which to generate the code
     * @return an OTP code as string
     */
    fun generateCode(secretKey: SecretKey, counter: Byte) : String {
        val hash = generateHashCode(secretKey, counter)
        return truncateHash(hash).toString().padStart(6, '0')
    }

    /**
     * Generate a HOTP code as string
     * @param secretKey the secret key for the HMAC function
     * @param counter the counter from which to generate the code
     * @return an OTP code as string
     */
    fun generateCode(secretKey: ByteArray, counter: Byte) : String {
        return generateCode(SecretKeySpec(secretKey, algorithm.algorithm), counter)
    }

    /**
     * Generate a HOTP code as string
     * @param secretKey the secret key for the HMAC function
     * @param counter the counter from which to generate the code
     * @return an OTP code as string
     */
    fun generateCode(secretKey: String, counter: Byte) : String {
        return generateCode(secretKey.toByteArray(), counter)
    }

    private fun generateHashCode(secretKey: SecretKey, counter: Byte): ByteArray {
        hmacFunc.reset()
        hmacFunc.init(secretKey)

        return hmacFunc.doFinal(byteArrayOf(counter))
    }

    private fun truncateHash(hash: ByteArray) : Int {
        val offset = (hash[19] and 0XF).toInt()
        val binary = (hash[offset] and 0X7F).toInt().shl(24).toByte() +
            (hash[offset + 1] and 0XFF.toByte()).toInt().shl(16).toByte() +
            (hash[offset + 2] and 0XFF.toByte()).toInt().shl(8).toByte() +
            (hash[offset + 3] and 0XFF.toByte())

        return binary.mod(10f.pow(length).toInt())
    }
}