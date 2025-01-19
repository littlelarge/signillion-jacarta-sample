package ru.aladdin.mobileSDK

import org.pkcs11.jacknji11.CKA
import org.pkcs11.jacknji11.CKC
import org.pkcs11.jacknji11.CKO

object Default {

    /**
     * Возможные значения атрибута CKA_GOST28147_PARAMS
     */
    val STR_GOST_28147_TEST_PARAMSET = byteArrayOf(
        0x06.toByte(), 0x07.toByte(), 0x2A.toByte(), 0x85.toByte(), 0x03.toByte(),
        0x02.toByte(), 0x02.toByte(), 0x1F.toByte(), 0x00.toByte()
    )
    val STR_CRYPTO_PRO_GOST_28147_A = byteArrayOf(
        0x06.toByte(), 0x07.toByte(), 0x2A.toByte(), 0x85.toByte(), 0x03.toByte(),
        0x02.toByte(), 0x02.toByte(), 0x1F.toByte(), 0x01.toByte()
    )
    val STR_CRYPTO_PRO_GOST_28147_B = byteArrayOf(
        0x06.toByte(), 0x07.toByte(), 0x2A.toByte(), 0x85.toByte(), 0x03.toByte(),
        0x02.toByte(), 0x02.toByte(), 0x1F.toByte(), 0x02.toByte()
    )
    val STR_CRYPTO_PRO_GOST_28147_C = byteArrayOf(
        0x06.toByte(), 0x07.toByte(), 0x2A.toByte(), 0x85.toByte(), 0x03.toByte(),
        0x02.toByte(), 0x02.toByte(), 0x1F.toByte(), 0x03.toByte()
    )
    val STR_CRYPTO_PRO_GOST_28147_D = byteArrayOf(
        0x06.toByte(), 0x07.toByte(), 0x2A.toByte(), 0x85.toByte(), 0x03.toByte(),
        0x02.toByte(), 0x02.toByte(), 0x1F.toByte(), 0x04.toByte()
    )
    val STR_CRYPTO_PRO_GOST3411 = byteArrayOf(
        0x06.toByte(), 0x07.toByte(), 0x2A.toByte(), 0x85.toByte(), 0x03.toByte(),
        0x02.toByte(), 0x02.toByte(), 0x1E.toByte(), 0x01.toByte()
    )

    /**
     * Возможные значения атрибута CKA_GOSTR3410_PARAMS
     */
    val STR_CRYPTO_PRO_A = byteArrayOf(
        0x06.toByte(), 0x07.toByte(), 0x2A.toByte(), 0x85.toByte(), 0x03.toByte(),
        0x02.toByte(), 0x02.toByte(), 0x23.toByte(), 0x01.toByte()
    )
    val STR_CRYPTO_PRO_B = byteArrayOf(
        0x06.toByte(), 0x07.toByte(), 0x2A.toByte(), 0x85.toByte(), 0x03.toByte(),
        0x02.toByte(), 0x02.toByte(), 0x23.toByte(), 0x02.toByte()
    )
    val STR_CRYPTO_PRO_C = byteArrayOf(
        0x06.toByte(), 0x07.toByte(), 0x2A.toByte(), 0x85.toByte(), 0x03.toByte(),
        0x02.toByte(), 0x02.toByte(), 0x23.toByte(), 0x03.toByte()
    )
    val STR_CRYPTO_PRO_XCHA = byteArrayOf(
        0x06.toByte(), 0x07.toByte(), 0x2A.toByte(), 0x85.toByte(), 0x03.toByte(),
        0x02.toByte(), 0x02.toByte(), 0x24.toByte(), 0x00.toByte()
    )
    val STR_CRYPTO_PRO_XCHB = byteArrayOf(
        0x06.toByte(), 0x07.toByte(), 0x2A.toByte(), 0x85.toByte(), 0x03.toByte(),
        0x02.toByte(), 0x02.toByte(), 0x24.toByte(), 0x01.toByte()
    )

    val GOSTR3411_256_OID = byteArrayOf(
        0x06.toByte(), 0x08.toByte(), 0x2A.toByte(), 0x85.toByte(), 0x03.toByte(),
        0x07.toByte(), 0x01.toByte(), 0x01.toByte(), 0x02.toByte(), 0x02.toByte()
    )

    val testCertValue = byteArrayOf(
        0x30.toByte(),
        0x82.toByte(),
        0x01.toByte(),
        0x77.toByte(),
        0x30.toByte(),
        0x82.toByte(),
        0x01.toByte(),
        0x22.toByte(),
        0xA0.toByte(),
        0x03.toByte(),
        0x02.toByte(),
        0x01.toByte(),
        0x02.toByte(),
        0x02.toByte(),
        0x04.toByte(),
        0x00.toByte(),
        0xA9.toByte(),
        0x8A.toByte(),
        0xC7.toByte(),
        0x30.toByte(),
        0x0C.toByte(),
        0x06.toByte(),
        0x08.toByte(),
        0x2A.toByte(),
        0x85.toByte(),
        0x03.toByte(),
        0x07.toByte(),
        0x01.toByte(),
        0x01.toByte(),
        0x03.toByte(),
        0x02.toByte(),
        0x05.toByte(),
        0x00.toByte(),
        0x30.toByte(),
        0x36.toByte(),
        0x31.toByte(),
        0x14.toByte(),
        0x30.toByte(),
        0x12.toByte(),
        0x06.toByte(),
        0x03.toByte(),
        0x55.toByte(),
        0x04.toByte(),
        0x03.toByte(),
        0x0C.toByte(),
        0x0B.toByte(),
        0x49.toByte(),
        0x76.toByte(),
        0x61.toByte(),
        0x6E.toByte(),
        0x20.toByte(),
        0x49.toByte(),
        0x76.toByte(),
        0x61.toByte(),
        0x6E.toByte(),
        0x6F.toByte(),
        0x76.toByte(),
        0x31.toByte(),
        0x0B.toByte(),
        0x30.toByte(),
        0x09.toByte(),
        0x06.toByte(),
        0x03.toByte(),
        0x55.toByte(),
        0x04.toByte(),
        0x06.toByte(),
        0x13.toByte(),
        0x02.toByte(),
        0x52.toByte(),
        0x55.toByte(),
        0x31.toByte(),
        0x11.toByte(),
        0x30.toByte(),
        0x0F.toByte(),
        0x06.toByte(),
        0x05.toByte(),
        0x2A.toByte(),
        0x85.toByte(),
        0x03.toByte(),
        0x64.toByte(),
        0x03.toByte(),
        0x12.toByte(),
        0x06.toByte(),
        0x31.toByte(),
        0x32.toByte(),
        0x33.toByte(),
        0x34.toByte(),
        0x35.toByte(),
        0x36.toByte(),
        0x30.toByte(),
        0x1E.toByte(),
        0x17.toByte(),
        0x0D.toByte(),
        0x31.toByte(),
        0x39.toByte(),
        0x30.toByte(),
        0x33.toByte(),
        0x30.toByte(),
        0x35.toByte(),
        0x31.toByte(),
        0x30.toByte(),
        0x33.toByte(),
        0x32.toByte(),
        0x35.toByte(),
        0x38.toByte(),
        0x5A.toByte(),
        0x17.toByte(),
        0x0D.toByte(),
        0x32.toByte(),
        0x30.toByte(),
        0x30.toByte(),
        0x33.toByte(),
        0x30.toByte(),
        0x34.toByte(),
        0x31.toByte(),
        0x30.toByte(),
        0x33.toByte(),
        0x32.toByte(),
        0x35.toByte(),
        0x38.toByte(),
        0x5A.toByte(),
        0x30.toByte(),
        0x36.toByte(),
        0x31.toByte(),
        0x14.toByte(),
        0x30.toByte(),
        0x12.toByte(),
        0x06.toByte(),
        0x03.toByte(),
        0x55.toByte(),
        0x04.toByte(),
        0x03.toByte(),
        0x0C.toByte(),
        0x0B.toByte(),
        0x49.toByte(),
        0x76.toByte(),
        0x61.toByte(),
        0x6E.toByte(),
        0x20.toByte(),
        0x49.toByte(),
        0x76.toByte(),
        0x61.toByte(),
        0x6E.toByte(),
        0x6F.toByte(),
        0x76.toByte(),
        0x31.toByte(),
        0x0B.toByte(),
        0x30.toByte(),
        0x09.toByte(),
        0x06.toByte(),
        0x03.toByte(),
        0x55.toByte(),
        0x04.toByte(),
        0x06.toByte(),
        0x13.toByte(),
        0x02.toByte(),
        0x52.toByte(),
        0x55.toByte(),
        0x31.toByte(),
        0x11.toByte(),
        0x30.toByte(),
        0x0F.toByte(),
        0x06.toByte(),
        0x05.toByte(),
        0x2A.toByte(),
        0x85.toByte(),
        0x03.toByte(),
        0x64.toByte(),
        0x03.toByte(),
        0x12.toByte(),
        0x06.toByte(),
        0x31.toByte(),
        0x32.toByte(),
        0x33.toByte(),
        0x34.toByte(),
        0x35.toByte(),
        0x36.toByte(),
        0x30.toByte(),
        0x66.toByte(),
        0x30.toByte(),
        0x1F.toByte(),
        0x06.toByte(),
        0x08.toByte(),
        0x2A.toByte(),
        0x85.toByte(),
        0x03.toByte(),
        0x07.toByte(),
        0x01.toByte(),
        0x01.toByte(),
        0x01.toByte(),
        0x01.toByte(),
        0x30.toByte(),
        0x13.toByte(),
        0x06.toByte(),
        0x07.toByte(),
        0x2A.toByte(),
        0x85.toByte(),
        0x03.toByte(),
        0x02.toByte(),
        0x02.toByte(),
        0x23.toByte(),
        0x01.toByte(),
        0x06.toByte(),
        0x08.toByte(),
        0x2A.toByte(),
        0x85.toByte(),
        0x03.toByte(),
        0x07.toByte(),
        0x01.toByte(),
        0x01.toByte(),
        0x02.toByte(),
        0x02.toByte(),
        0x03.toByte(),
        0x43.toByte(),
        0x00.toByte(),
        0x04.toByte(),
        0x40.toByte(),
        0x71.toByte(),
        0x89.toByte(),
        0x97.toByte(),
        0x2D.toByte(),
        0xFE.toByte(),
        0x36.toByte(),
        0xC8.toByte(),
        0x0D.toByte(),
        0xA5.toByte(),
        0x87.toByte(),
        0x4A.toByte(),
        0x0C.toByte(),
        0x4D.toByte(),
        0x68.toByte(),
        0x00.toByte(),
        0x5E.toByte(),
        0xCF.toByte(),
        0xAA.toByte(),
        0x4C.toByte(),
        0x25.toByte(),
        0xD5.toByte(),
        0xDE.toByte(),
        0x72.toByte(),
        0x1A.toByte(),
        0x18.toByte(),
        0x09.toByte(),
        0x9A.toByte(),
        0xD4.toByte(),
        0x6A.toByte(),
        0xDE.toByte(),
        0x11.toByte(),
        0x5C.toByte(),
        0xF5.toByte(),
        0x65.toByte(),
        0xDA.toByte(),
        0x8D.toByte(),
        0x69.toByte(),
        0x1C.toByte(),
        0x32.toByte(),
        0x5B.toByte(),
        0x70.toByte(),
        0x9C.toByte(),
        0x8A.toByte(),
        0xE0.toByte(),
        0xA3.toByte(),
        0xB1.toByte(),
        0x75.toByte(),
        0x9B.toByte(),
        0x0C.toByte(),
        0x79.toByte(),
        0x10.toByte(),
        0xF5.toByte(),
        0xBB.toByte(),
        0xEE.toByte(),
        0x11.toByte(),
        0xB4.toByte(),
        0xB4.toByte(),
        0xFA.toByte(),
        0x7B.toByte(),
        0xEC.toByte(),
        0x00.toByte(),
        0xC4.toByte(),
        0x14.toByte(),
        0x3E.toByte(),
        0xA3.toByte(),
        0x0F.toByte(),
        0x30.toByte(),
        0x0D.toByte(),
        0x30.toByte(),
        0x0B.toByte(),
        0x06.toByte(),
        0x03.toByte(),
        0x55.toByte(),
        0x1D.toByte(),
        0x0F.toByte(),
        0x04.toByte(),
        0x04.toByte(),
        0x03.toByte(),
        0x02.toByte(),
        0x05.toByte(),
        0xA0.toByte(),
        0x30.toByte(),
        0x0C.toByte(),
        0x06.toByte(),
        0x08.toByte(),
        0x2A.toByte(),
        0x85.toByte(),
        0x03.toByte(),
        0x07.toByte(),
        0x01.toByte(),
        0x01.toByte(),
        0x03.toByte(),
        0x02.toByte(),
        0x05.toByte(),
        0x00.toByte(),
        0x03.toByte(),
        0x41.toByte(),
        0x00.toByte(),
        0x5B.toByte(),
        0x1B.toByte(),
        0x81.toByte(),
        0x1E.toByte(),
        0xC3.toByte(),
        0xE4.toByte(),
        0x61.toByte(),
        0x3A.toByte(),
        0xA5.toByte(),
        0x3C.toByte(),
        0xE8.toByte(),
        0x0D.toByte(),
        0xB2.toByte(),
        0xDB.toByte(),
        0x88.toByte(),
        0x32.toByte(),
        0xF9.toByte(),
        0x48.toByte(),
        0x43.toByte(),
        0xCF.toByte(),
        0x31.toByte(),
        0xEA.toByte(),
        0xDB.toByte(),
        0x9C.toByte(),
        0xA2.toByte(),
        0x17.toByte(),
        0xD4.toByte(),
        0x61.toByte(),
        0x5A.toByte(),
        0x85.toByte(),
        0xDC.toByte(),
        0xC3.toByte(),
        0x6B.toByte(),
        0x31.toByte(),
        0xA8.toByte(),
        0x2E.toByte(),
        0x21.toByte(),
        0xDE.toByte(),
        0xB7.toByte(),
        0xCF.toByte(),
        0x23.toByte(),
        0x5F.toByte(),
        0x42.toByte(),
        0x5F.toByte(),
        0x26.toByte(),
        0x22.toByte(),
        0x8D.toByte(),
        0x1E.toByte(),
        0xF8.toByte(),
        0x6D.toByte(),
        0x12.toByte(),
        0xC9.toByte(),
        0x0C.toByte(),
        0x8C.toByte(),
        0xC0.toByte(),
        0xD9.toByte(),
        0x3C.toByte(),
        0x16.toByte(),
        0xC5.toByte(),
        0xD9.toByte(),
        0xF0.toByte(),
        0xC7.toByte(),
        0xFD.toByte(),
        0xB2.toByte()
    )

    val testCertId = byteArrayOf(
        't'.toByte(),
        'e'.toByte(),
        's'.toByte(),
        't'.toByte(),
        'i'.toByte(),
        'd'.toByte()
    )

    /**
     * Атрибуты по умолчанию для закрытого ключа
     */
    val prKeyAttribs = arrayOf(
        CKA(CKA.CLASS, CKO.PRIVATE_KEY), // класс закрытый ключ
        CKA(CKA.TOKEN, true), // в токене
        CKA(CKA.LABEL, "Private key"), // метка
        CKA(CKA.GOSTR3410_PARAMS, STR_CRYPTO_PRO_A), // тип ключевой пары согласно rfc 4357
        CKA(CKA.PRIVATE, true), // закрытый ключ
        CKA(CKA.SIGN, true), // атрибут подписи
        CKA(CKA.ID, testCertId) // общий идентификатор
    )

    /**
     * Атрибуты по умолчанию для открытого ключа
     */
    val pubKeyAttribs = arrayOf(
        CKA(CKA.CLASS, CKO.PUBLIC_KEY), // класс открытый ключ
        CKA(CKA.TOKEN, true), // в токене
        CKA(CKA.LABEL, "Public key"), // метка
        CKA(CKA.PRIVATE, false),
        CKA(CKA.VERIFY, true),
        CKA(CKA.GOSTR3410_PARAMS, STR_CRYPTO_PRO_A), // тип ключевой пары согласно rfc 4357
        CKA(CKA.GOSTR3411_PARAMS, GOSTR3411_256_OID),
        CKA(CKA.ID, testCertId) // общий идентификатор
    )

    /**
     * Атрибуты сертификата
     */
    val certAttribs = arrayOf(
        CKA(CKA.CLASS, CKO.CERTIFICATE), // сертификат
        CKA(CKA.TOKEN, true), // в токене
        CKA(CKA.LABEL, "Certificate"),
        CKA(CKA.VALUE),
        CKA(CKA.SUBJECT, "Subject"),
        CKA(CKA.CERTIFICATE_TYPE, CKC.X_509), // тип сертификата
        CKA(CKA.ID, testCertId) // общий идентификатор
    )

    /**
     * Distinguished name для запроса на сертификат
     */
    val distinguishedName = arrayOf(
        "CN", "Ivan Ivanov", "C", "RU", "1.2.643.100.3", "NUMERICSTRING:123456"
    )

    /**
     * PIN-код по умолчанию для пользователя токена типа ГОСТ
     */
    const val DEFAULT_USER_PIN_GOST = "1234567890"

    /**
     * PIN-код по умолчанию для Администратора токена типа ГОСТ
     */
    const val DEFAULT_ADMIN_PIN_GOST = "1234567890"

    /**
     * PIN-код по умолчанию для Администратора токена типа ГОСТ
     */
    const val DEFAULT_CT2_ADMIN_PIN_GOST = "00000000000000000000000000000000"

    /**
     * PIN-код по умолчанию для пользователя токена типа ГОСТ-2
     */
    const val DEFAULT_USER_PIN_GOST2 = "1234567890"

    const val DEFAULT_CT2_PUK = "0987654321"

    /**
     * PIN-код по умолчанию для пользователя токена типа LASER
     */
    const val DEFAULT_USER_PIN_LASER = "11111111"

    /**
     * PIN-код по умолчанию для администратора токена типа LASER
     */
    const val DEFAULT_ADMIN_PIN_LASER = "00000000"

    /**
     * Метка токенов типа LASER
     */
    const val JACARTA_LASER = "JaCarta Laser"

    /**
     * Метка токенов типа LASER - старый вариант
     */
    const val JACARTA_LASER_OLD = "JaCarta"
}