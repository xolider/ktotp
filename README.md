# ktotp

This library allows generating HOTP and TOTP codes for 2FA softwares.
This is written in Kotlin targeting the JVM, but can easily be add to Android projects as well.

## Summary

* [What is a HOTP code ?](#what-is-a-hotp-code-)
* [What is a TOTP code ?](#what-is-a-totp-code-)
* [How to use this library](#how-to-use-this-library)
  * [Generate a HOTP code](#generate-a-hotp-code)
  * [Generate a TOTP code](#generate-a-totp-code)

## What is a HOTP code ?

A HOTP (HMAC-Based One Time Password) code is a 6-8 digits code used for multi-factor authentication. It is described by the [RFC 4226](https://www.ietf.org/rfc/rfc4226.txt)
and uses a **HMAC** function and a **counter** to generate codes.

The client and the server must synchronize themselves to share the counter value.

## What is a TOTP code ?

A TOTP (Time-Based One Time Password) code is a 6-8 digits code used for multi-factor authentication. It is described by the
[RFC 6238](https://www.ietf.org/rfc/rfc6238.txt) and is based on the HOTP algorithm.

Instead of using a counter, it uses the current time to generate a code. These codes are valid for a certain
time (30 seconds by default).

## How to use this library

### Generate a HOTP code

In Kotlin:

```kotlin
val secretKey = (your secret key)
val counter = 0x45.toByte()
val generator = KHOTPGenerator()
val code: String = generator.generateCode(secretKey, counter)
```

In Java:

```java
import javax.crypto.SecretKey;

SecretKey secretKey = (your secret key);
byte counter = 0x00;
KHOTPGenerator generator = new KHOTPGenerator();
String code = generator.generateCode(secretKey, counter);
```

### Generate a TOTP code

In Kotlin:

```kotlin
import java.time.Instant

val secretKey = (your secret key)
val time = Instant.now()
val generator = KHOTPGenerator()
val code: String = generator.generateCode(secretKey, time)
```

In Java:

```java
import javax.crypto.SecretKey;
import java.time.Instant;

SecretKey secretKey = (your secret key);
Instant time = Instant.now();
KHOTPGenerator generator = new KHOTPGenerator();
String code = generator.generateCode(secretKey, time);
```
